import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns="/customer")
public class CustomerServlet extends HttpServlet {
    private static List<Customer> cust=new ArrayList<Customer>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Customer c: cust) {
            System.out.println("" +
                    "CID:"+c.getId()+
                    "CName:"+c.getName()+
                    "CaAddress"+c.getAddress());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();//like a scanner
        out.println("doPost");
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String address=req.getParameter("address");
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
                cust.remove(c);
                cust.add(new Customer(id,name,address));
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