package edu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/api/v1/order")
public class PlaceOrderServlet extends HttpServlet {
    BasicDataSource ds;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ds = (BasicDataSource) servletContext.getAttribute("datasource");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp); // ADD THIS
        Connection connection = null;
        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false); // Start transaction

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);

            String orderId = jsonObject.get("orderId").getAsString();
            String customerId = jsonObject.get("customerId").getAsString();
            String orderDate = jsonObject.get("orderDate").getAsString();
            double subtotal = jsonObject.get("subtotal").getAsDouble();
            double tax = jsonObject.get("tax").getAsDouble();
            double discount = jsonObject.get("discount").getAsDouble();
            double total = jsonObject.get("total").getAsDouble();
            JsonArray items = jsonObject.getAsJsonArray("items");

            // Check if order already exists
            if(orderExists(orderId, connection)) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                resp.getWriter().println("Order with ID " + orderId + " already exists");
                connection.rollback();
                return;
            }

            // Insert into orders table
            String orderQuery = "INSERT INTO orders (order_id, customer_id, order_date, subtotal, tax, discount, total) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement orderStmt = connection.prepareStatement(orderQuery);
            orderStmt.setString(1, orderId);
            orderStmt.setString(2, customerId);
            orderStmt.setString(3, orderDate);
            orderStmt.setDouble(4, subtotal);
            orderStmt.setDouble(5, tax);
            orderStmt.setDouble(6, discount);
            orderStmt.setDouble(7, total);
            orderStmt.executeUpdate();

            // Insert order items and update item quantities
            String orderItemQuery = "INSERT INTO order_items (order_id, item_id, quantity, unit_price, total_price) VALUES (?,?,?,?,?)";
            String updateItemQuery = "UPDATE item SET quantity = quantity - ? WHERE item_id = ?";
            String checkQuantityQuery = "SELECT quantity, price FROM item WHERE item_id = ?";

            PreparedStatement orderItemStmt = connection.prepareStatement(orderItemQuery);
            PreparedStatement updateItemStmt = connection.prepareStatement(updateItemQuery);
            PreparedStatement checkQuantityStmt = connection.prepareStatement(checkQuantityQuery);

            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.get(i).getAsJsonObject();
                String itemId = item.get("itemId").getAsString();
                int quantity = item.get("quantity").getAsInt();

                // Check if item has enough quantity
                checkQuantityStmt.setString(1, itemId);
                ResultSet rs = checkQuantityStmt.executeQuery();

                if (rs.next()) {
                    int availableQuantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("price");

                    if (availableQuantity < quantity) {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        resp.getWriter().println("Insufficient quantity for item " + itemId +
                                ". Available: " + availableQuantity + ", Requested: " + quantity);
                        connection.rollback();
                        return;
                    }

                    // Insert order item
                    double totalPrice = unitPrice * quantity;
                    orderItemStmt.setString(1, orderId);
                    orderItemStmt.setString(2, itemId);
                    orderItemStmt.setInt(3, quantity);
                    orderItemStmt.setDouble(4, unitPrice);
                    orderItemStmt.setDouble(5, totalPrice);
                    orderItemStmt.executeUpdate();

                    // Update item quantity
                    updateItemStmt.setInt(1, quantity);
                    updateItemStmt.setString(2, itemId);
                    updateItemStmt.executeUpdate();
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().println("Item " + itemId + " not found");
                    connection.rollback();
                    return;
                }
            }

            connection.commit(); // Commit transaction
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().println("Order Placed Successfully");

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Order placement failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean orderExists(String orderId, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM orders WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp); // ADD THIS LINE AT THE VERY TOP
        resp.setContentType("application/json"); // ADD THIS LINE
        try {
            Connection connection = ds.getConnection();
            String query = "SELECT o.*, c.name as customer_name FROM orders o " +
                    "JOIN customer c ON o.customer_id = c.id ORDER BY o.order_date DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            JsonArray orderList = new JsonArray();
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("orderId", resultSet.getString("order_id"));
                jsonObject.addProperty("customerId", resultSet.getString("customer_id"));
                jsonObject.addProperty("customerName", resultSet.getString("customer_name"));
                jsonObject.addProperty("orderDate", resultSet.getString("order_date"));
                jsonObject.addProperty("subtotal", resultSet.getDouble("subtotal"));
                jsonObject.addProperty("tax", resultSet.getDouble("tax"));
                jsonObject.addProperty("discount", resultSet.getDouble("discount"));
                jsonObject.addProperty("total", resultSet.getDouble("total"));
                orderList.add(jsonObject);
            }

            resp.setContentType("application/json");
            resp.getWriter().println(orderList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}