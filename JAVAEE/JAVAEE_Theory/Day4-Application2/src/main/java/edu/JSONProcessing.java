package edu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JSONProcessing extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        JsonObject customer = gson.fromJson(req.getReader(), JsonObject.class);
        System.out.println(customer);
        System.out.println(customer.get("age").getAsString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject customer = new JsonObject();
        customer.addProperty("name", "Yen");
        customer.addProperty("age", 20);

        JsonObject address1 = new JsonObject();
        address1.addProperty("no", 12);
        address1.addProperty("street", "Flower street");
        address1.addProperty("city", "New York");

        JsonObject address2 = new JsonObject();
        address1.addProperty("no", 12);
        address1.addProperty("street", "Flower street");
        address1.addProperty("city", "New York");

        JsonArray address = new JsonArray();
        address.add(address1);
        address.add(address2);

        customer.add("address", address);
        resp.setContentType("application/json");//content type app/json nattan ynne text widiyt and json obj ektt support wenne na
        resp.getWriter().print(customer);
    }
}
