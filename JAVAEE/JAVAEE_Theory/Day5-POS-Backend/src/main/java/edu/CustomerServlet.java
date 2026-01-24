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

@WebServlet(urlPatterns = "/api/v1/customer")
public class CustomerServlet extends HttpServlet {
    BasicDataSource ds;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ds = (BasicDataSource) servletContext.getAttribute("datasource");
    }

    // ADD THIS METHOD - CRITICAL FOR CORS
    private void setCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    // ADD THIS METHOD - HANDLES PREFLIGHT REQUESTS
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp); // ADD THIS LINE
        resp.setContentType("application/json"); // ADD THIS LINE

        try {
            Connection connection= ds.getConnection();
            String query="SELECT * FROM customer";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            JsonArray customerList=new JsonArray();
            while (resultSet.next()) {
                String cId=resultSet.getString("id");
                String cName=resultSet.getString("name");
                String cAddress=resultSet.getString("address");
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("cid",cId);
                jsonObject.addProperty("cname",cName);
                jsonObject.addProperty("caddress",cAddress);
                customerList.add(jsonObject);
            }
            resp.getWriter().println(customerList);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp); // ADD THIS LINE

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get("cid").getAsString();
        String name = jsonObject.get("cname").getAsString();
        String address = jsonObject.get("caddress").getAsString();

        try {
            if(customerExists(id)) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                resp.getWriter().println("Customer with ID "+id+" already exists");
                return;
            }
            Connection connection=ds.getConnection();
            String query="INSERT INTO customer (id,name,address) VALUES (?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,address);
            int rowInserted=preparedStatement.executeUpdate();
            if(rowInserted>0){
                resp.getWriter().println("Customer Saved Successfully");
            }else {
                resp.getWriter().println("Customer Saved Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp); // ADD THIS LINE

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get("cid").getAsString();
        String name = jsonObject.get("cname").getAsString();
        String address = jsonObject.get("caddress").getAsString();

        try {
            Connection connection=ds.getConnection();
            String query="UPDATE customer SET name=?,address=? WHERE id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,id);
            int rowInserted=preparedStatement.executeUpdate();
            if(rowInserted>0){
                resp.getWriter().println("Customer Updated Successfully");
            }else {
                resp.getWriter().println("Customer Updated Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp); // ADD THIS LINE

        String id = req.getParameter("cid");
        try {
            Connection connection=ds.getConnection();
            String query="delete from customer WHERE id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            int rowInserted=preparedStatement.executeUpdate();
            if(rowInserted>0){
                resp.getWriter().println("Customer Deleted Successfully");
            }else {
                resp.getWriter().println("Customer Deleted Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean customerExists(String id) throws SQLException {
        Connection connection = ds.getConnection();
        String query = "SELECT COUNT(*) FROM customer WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }
        return false;
    }
}