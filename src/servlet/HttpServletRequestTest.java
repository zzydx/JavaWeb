package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletRequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println(req.getRequestURI());//获取请求的资源相对路径
        System.out.println(req.getRequestURL());//获取请求的绝对路径
        System.out.println(req.getRemoteHost());//获取客户端的ip地址
        System.out.println(req.getHeader("User-Agent"));//获取请求头
        System.out.println(req.getMethod());//获取请求的方式



    }
}
