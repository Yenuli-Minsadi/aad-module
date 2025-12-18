package edu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get("cid").getAsString();
        String name = jsonObject.get("cname").getAsString();
        String address = jsonObject.get("caddress").getAsString();
//        System.out.println(id+name+address);
        try {
            Connection conn = ds.getConnection();
            String query = "INSERT INTO customer (id,name,address) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0) {
                resp.getWriter().println("Customer Saved successfully");
            } else {
                resp.getWriter().println("Customer Saving failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            Connection conn = ds.getConnection();
            String query = "SELECT * FROM customer";
            PreparedStatement preparedStatement=conn.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                String cId=resultSet.getString("id");
                String cName=resultSet.getString("name");
                String cAddress=resultSet.getString("address");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get("cid").getAsString();
        String name = jsonObject.get("cname").getAsString();
        String address = jsonObject.get("caddress").getAsString();
//        System.out.println(id+name+address);
        try {
            Connection conn = ds.getConnection();
            String query = "UPDATE INTO customer (id,name,address) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0) {
                resp.getWriter().println("Customer Saved successfully");
            } else {
                resp.getWriter().println("Customer Saving failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        System.out.println("do delete invoked");
    }

//    @Override
//    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//        System.out.println("do Options invoked");
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//    }
}
