package servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zhangzengy ：
 * @create 2023/4/17-21:58
 */
public class InitServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //获取Servlet程序的servlet-name
        System.out.print("别名是" + servletConfig.getServletName());
        //获取初始化参数init-param
        System.out.println("初始化参数是" + servletConfig.getInitParameter("username"));
        System.out.println("初始化url的值是" + servletConfig.getInitParameter("url"));
        //获取servletcontext对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
