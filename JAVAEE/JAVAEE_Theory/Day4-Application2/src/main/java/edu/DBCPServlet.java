package edu;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/dbcp")
public class DBCPServlet extends HttpServlet {
    private static List<Customer> cust=new ArrayList<Customer>();
    BasicDataSource ds;

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        ds = (BasicDataSource)sc.getAttribute("dataSource");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            Connection conn = ds.getConnection();
            if (id==null) {
                String query="SELECT * FROM customer";
                PreparedStatement preparedStatement=conn.prepareStatement(query);
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String cId=resultSet.getString("id");
                    String cName=resultSet.getString("name");
                    String cAddress=resultSet.getString("address");
                    resp.getWriter().println(cId+","+cName+","+cAddress);
                }
            }else  {
                String query="SELECT * FROM customer WHERE id=?";
                PreparedStatement preparedStatement=conn.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String cId=resultSet.getString("id");
                    String cName=resultSet.getString("name");
                    String cAddress=resultSet.getString("address");
                    resp.getWriter().println(cId+","+cName+","+cAddress);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //dont let save an existing id
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();//like a scanner
        out.print("doPost");
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String address=req.getParameter("address");

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeeapp",
//                    "root","1234");

            Connection conn = ds.getConnection();
            String query = "INSERT INTO customer(id,name,address) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,address);
            int rowInserted = ps.executeUpdate();

            if (rowInserted>0) {
                resp.getWriter().println("Customer successfully added");
            } else {
                resp.getWriter().println("Customer saved failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("id: "+id);
        System.out.println("name: "+name);
        System.out.println("address: "+address);

        cust.add(new Customer(id,name,address));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String address=req.getParameter("address");

        try {
            Connection conn = ds.getConnection();
            String query = "UPDATE customer SET name=?,address=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,address);
            ps.setString(3,id);
            int rowInserted = ps.executeUpdate();

            if (rowInserted>0) {
                resp.getWriter().println("Customer successfully updated");
            } else {
                resp.getWriter().println("Customer update failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Customer c: cust) {
            if (c.getId().equals(id)) {
//                c.setName(name);
//                c.setAddress(address);
                if (name != null) c.setName(name);
                if (address != null) c.setAddress(address);
                resp.getWriter().println("customer updated");
                return;
            }
        }
        resp.getWriter().println("customer not found");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        try {
            Connection conn = ds.getConnection();
            String query = "DELETE from customer  WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,id);
            int rowsDeleted = ps.executeUpdate();  // MUST EXECUTE!

            if (rowsDeleted > 0) {
                // Also remove from ArrayList
                cust.removeIf(c -> c.getId().equals(id));
                resp.getWriter().println("Customer deleted successfully");
            } else {
                resp.getWriter().println("Customer not found in DB");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Customer c: cust) {
            if (c.getId().equals(id)) {
                cust.remove(c);
                resp.getWriter().println("customer deleted");
                return;
            }
        }
        resp.getWriter().println("customer not found");
    }

}