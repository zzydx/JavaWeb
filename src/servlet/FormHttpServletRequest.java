package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println(req.getParameter("username"));//获取表单中的username项内容
        System.out.println(req.getParameter("password"));
        req.getParameterValues("username");//获取多值，用于表单的多选按钮
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");//设置字符集，避免乱码，在获取参数前设置才有效，第一行
        System.out.println(req.getParameter("username"));//获取表单中的username项内容
        System.out.println(req.getParameter("password"));
        req.getParameterValues("username");//获取多值，用于表单的多选按钮
    }
}
