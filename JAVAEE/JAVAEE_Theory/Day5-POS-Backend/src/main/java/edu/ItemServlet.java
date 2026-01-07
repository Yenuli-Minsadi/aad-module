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

@WebServlet(urlPatterns = "/api/v1/item")
public class ItemServlet extends HttpServlet {
    BasicDataSource ds;
    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ds = (BasicDataSource) servletContext.getAttribute("datasource");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get("iid").getAsString();
        String name = jsonObject.get("iname").getAsString();
        double price = jsonObject.get("iprice").getAsDouble();
        int quantity = jsonObject.get("iquantity").getAsInt();

        try {
            if(itemExists(id)) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);//409
                resp.getWriter().println("Item with ID "+id+" already exists");
                return;
            }
            Connection connection=ds.getConnection();
            String query="INSERT INTO item (item_id,item_name,price,quantity) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setDouble(3,price);
            preparedStatement.setInt(4,quantity);
            int rowInserted=preparedStatement.executeUpdate();
            if(rowInserted>0){
                resp.getWriter().println("Item Saved Successfully");
            }else {
                resp.getWriter().println("Item Saved Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean itemExists(String id) throws SQLException {
        Connection connection = ds.getConnection();
        String query = "SELECT COUNT(*) FROM item WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);  //check for the specific ID
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;  //returns true if count > 0
        }
        return false;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get("iid").getAsString();
        String name = jsonObject.get("iname").getAsString();
        double price = jsonObject.get("iprice").getAsDouble();
        int quantity = jsonObject.get("iquantity").getAsInt();

        try {
            Connection connection=ds.getConnection();
            String query="UPDATE item SET item_name=?,price=?,quantity=? WHERE item_id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,quantity);
            preparedStatement.setString(4,id);
            int rowInserted=preparedStatement.executeUpdate();
            if(rowInserted>0){
                resp.getWriter().println("Item Updated Successfully");
            }else {
                resp.getWriter().println("Item Updated Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection= ds.getConnection();
            String query="SELECT * FROM item";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            JsonArray itemList=new JsonArray();
            while (resultSet.next()) {
                String iId=resultSet.getString("item_id");
                String iName=resultSet.getString("item_name");
                double iPrice=resultSet.getDouble("price");
                int iQuantity=resultSet.getInt("quantity");

                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("iid",iId);
                jsonObject.addProperty("iname",iName);
                jsonObject.addProperty("iprice",iPrice);
                jsonObject.addProperty("iquantity",iQuantity);
                itemList.add(jsonObject);
            }
            resp.getWriter().println(itemList);
            resp.setContentType("application/json");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("iid");
        try {
            Connection connection=ds.getConnection();
            String query="delete from item WHERE item_id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            int rowInserted=preparedStatement.executeUpdate();
            if(rowInserted>0){
                resp.getWriter().println("Item Deleted Successfully");
            }else {
                resp.getWriter().println("Item Deleted Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}