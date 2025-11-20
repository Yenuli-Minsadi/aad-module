import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//API Endpoint, mapping to servlet - request, responses
@WebServlet(urlPatterns="/saved")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();//like a scanner
        out.println("hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
//        out.println("saved");
        String action = req.getParameter("action");
        if ("save".equals(action)) {
            out.println("save");
        } else if ("update".equals(action)) {
            out.println("update");
        } else if ("delete".equals(action)) {
            out.println("delete");
        }
    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out = resp.getWriter();
//        out.println("updated");
//    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out = resp.getWriter();//like a scanner
//        out.println("deleted");
//    }

}
