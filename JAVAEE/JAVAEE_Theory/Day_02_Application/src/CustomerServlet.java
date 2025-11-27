import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns="/customer")// eliye idn ena http req ekk servlet ekt map krnna
public class CustomerServlet extends HttpServlet {
    private static List<Customer> cust=new ArrayList<Customer>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id == null) {
            // Get All
            for (Customer c : cust) {
                resp.getWriter().println(c.getId() + " - " + c.getName() + " - " + c.getAddress());
            }
        } else {
            // Get by ID
            for (Customer c : cust) {
                if (c.getId().equals(id)) {
                    resp.getWriter().println(c.getId() + " - " + c.getName() + " - " + c.getAddress());
                    return;
                }
            }
            resp.getWriter().println("Customer not found");
        }

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