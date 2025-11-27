import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns="/customer")// eliye idn ena http req ekk servlet ekt map krnna
public class CustomerServlet extends HttpServlet {
    private static List<Customer> cust=new ArrayList<Customer>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeeapp",
                    "root","1234");
            if (id==null) {
                String query="SELECT * FROM customer";
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String cId=resultSet.getString("id");
                    String cName=resultSet.getString("name");
                    String cAddress=resultSet.getString("address");
                    resp.getWriter().println(cId+","+cName+","+cAddress);
                }
            }else  {
                String query="SELECT * FROM customer WHERE id=?";
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String cId=resultSet.getString("id");
                    String cName=resultSet.getString("name");
                    String cAddress=resultSet.getString("address");
                    resp.getWriter().println(cId+","+cName+","+cAddress);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeeapp",
//                    "root","1234");
//
//            PreparedStatement ps = null;
//            ResultSet rs;
//
//            if (id == null) {
//                // Get All
//                ps = conn.prepareStatement("select * from customer");
//
//            } else {
//                // Get by ID
//                for (Customer c : cust) {
//                    if (c.getId().equals(id)) {
//                        ps = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
//                        ps.setString(1, id);
//                    }
//                }
//
//                resp.getWriter().println("Customer not found");
//            }
//            rs = ps.executeQuery();
//            resp.setContentType("text/plain");
//            PrintWriter out = resp.getWriter();
//
//            boolean found = false;
//            while (rs.next()) {
//                found = true;
//                out.println(
//                        rs.getString("id") + " - " +
//                                rs.getString("name") + " - " +
//                                rs.getString("address")
//                );
//            }
//
//            if (!found) {
//                out.println("Customer not found");
//            }
//
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        resp.setContentType("text/html;charset=UTF-8");
//        for(Customer c:cust){
//            resp.getWriter().println("<tr>" +
//                    "<td>"+c.getId()+"</td>" +
//                    "<td>"+c.getName()+"</td>" +
//                    "<td>"+c.getAddress()+"</td>" +
//                    "</tr>");
//        }
//        resp.getWriter().write(cust.toString());

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeeapp",
                    "root","1234");
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

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeeapp",
                    "root","1234");
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeeapp",
                    "root","1234");
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

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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