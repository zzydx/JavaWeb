package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //获取请求参数
        System.out.println( "查看数据" + req.getParameter("username"));
        //查看servlet1处理后的数据
        System.out.println( "查看servlet1处理后的数据" + req.getAttribute("key"));
        System.out.println("servlet2业务");

    }
}