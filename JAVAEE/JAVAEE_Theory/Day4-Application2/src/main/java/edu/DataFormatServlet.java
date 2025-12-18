package edu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet (urlPatterns = "/data-format")
@MultipartConfig
public class DataFormatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentType = req.getContentType();
        System.out.println("Content Type: "+contentType);
        //1.query params
//        System.out.println(req.getParameter("id"));
//        System.out.println(req.getParameter("name"));

        //2.path variable

        //3.x-www-form-url-encoded
//        System.out.println(req.getParameter("id"));
//        System.out.println(req.getParameter("name"));

        //4. form data
        System.out.println(req.getParameter("id"));
        System.out.println(req.getParameter("name"));

        //read the file
        Part filePart =req.getPart("image");
        System.out.println(filePart.getSubmittedFileName());

        //create a directory
        File upload = new File("F:\\IJSE-GDSE73\\Sem2\\AAD\\JAVAEE\\JAVAEE_Theory\\Day4-Application2\\src\\main\\resources\\images");
        if (!upload.exists()) {
            upload.mkdir();
        }

        //save file
        String fullPath = upload.getAbsolutePath()+File.separator+filePart.getSubmittedFileName();
        filePart.write(fullPath);


//        Path source = Paths.get(this.getClass().getResource("/").getPath());
//        Path newFolder = Paths.get(source.toAbsolutePath() + "/newFolder/");
//        Files.createDirectories(newFolder);

    }
}
