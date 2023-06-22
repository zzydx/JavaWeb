package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("response1");
//        //设置相应状态码，表示重定向
//        resp.setStatus(302);
//        //设置响应头重定向地址
//        resp.setHeader("Location","http://localhost:8080/JavaWeb/response2");

        //推荐使用，一行代码就可以
        resp.sendRedirect("http://localhost:8080/JavaWeb/response2");
    }
}
