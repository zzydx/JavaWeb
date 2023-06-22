package servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzengy ：
 * @create 2023/4/17-18:02
 */
public class HelloServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("这是get方法");
        //获取xml中配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext();
        String user = context.getInitParameter("user");
        System.out.println(user);
        //获取当前的工程路径
        System.out.println("工程路径" + context.getContextPath());
        //获取工程在硬盘的绝对路径（/映射到web目录）
        System.out.println("绝对路径" + context.getRealPath("/"));
        System.out.println("绝对路径" + context.getRealPath("/imgs"));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //super.init(config);
        System.out.println("重写init初始化方法");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println("这是post方法");
          //获取xml中配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext();
        String user = context.getInitParameter("user");
        System.out.println(user);
        //获取当前的工程路径
        System.out.println("工程路径" + context.getContextPath());
        //获取工程在硬盘的绝对路径
        System.out.println("绝对路径" + context.getRealPath("/"));
    }
}