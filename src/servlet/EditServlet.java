package servlet;

import thymeleaf.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("deit.do");
        System.out.println(req.getParameter("id"));
        System.out.println(req.getParameter("name"));

        req.getSession().setAttribute("pageNo", 1);
        req.getSession().setAttribute("pageCount", 4);

        super.processTemplate("index", req, resp);
    }
}
