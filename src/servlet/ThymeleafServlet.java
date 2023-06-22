package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ThymeleafServlet {
    protected String thymeleaf(String username, HttpServletRequest req)  {
        req.setAttribute(username,"asd");
        System.out.println("aaaaaaaa");
//        super.processTemplate("index", req, resp);
        return "index";
    }
}
