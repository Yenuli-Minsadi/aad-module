package edu;

//MIME TYPES - Multipurpose Internet Mail Extension
/*
* A MIME type (Media Type) is the cotent negotiation protocol that tells the
* client(server) what type of data server(client) is sending
*
* they are used in HTTP headers (like content-Type) to describe the format of data being transmitted
* */

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/mime-types")
public class MIMETypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
     resp.setContentType("application/json;");
     PrintWriter out = resp.getWriter();
     out.println("hjh");

    }

}
