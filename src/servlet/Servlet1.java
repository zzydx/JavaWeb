package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //查看请求参数
        System.out.println( "servlet1中查看请求参数" + req.getParameter("username"));
        //
        req.setAttribute("key","servlet处理后的数据");
        //转发到servlet2
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Servlet2");
        requestDispatcher.forward(req,resp);
    }
}
