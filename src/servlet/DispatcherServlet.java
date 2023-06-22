package servlet;

import ioc.BeanFactory;
import thymeleaf.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;

    public void init() throws ServletException {
        super.init();
        //把原本DispatcherServlet初始化时主动创建的工厂BeanFactory 移到ContextLoaderListener监听器里
        // 随着web工程的创建来进行初始化工厂
//        beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactory1 = application.getAttribute("beanFactory");
        if(beanFactory1 != null){
            beanFactory = (BeanFactory) beanFactory1;
        }else {
            throw new RuntimeException("beanFactory为null");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        req.setCharacterEncoding("UTF-8");
        //通过getServletPath方法获取请求的servlet，
        // 比如地址栏是工程名/index.do，获取的就是/index.do
        String servletPath = req.getServletPath();
        //截取字符串获得index，去掉/和.do
        String substring = servletPath.substring(1);
        int lastIndexOf = substring.lastIndexOf(".do");
        String str = substring.substring(0, lastIndexOf);
//        System.out.println(s);
        Object beanServlet = beanFactory.getBean(str);
        String action = req.getParameter("action");
        if (action == null) {
            action = "index";
        }
        //通过反射获取action对应的业务方法
        try {
            //获取当前类中所有的方法
            Method[] methods = beanServlet.getClass().getDeclaredMethods();
            for (Method method : methods) {
                //获取方法名称
                if (action.equals(method.getName())) {
                    //统一获取请求参数
                    //获取当前方法的参数数组
                    Parameter[] parameters = method.getParameters();
                    //数组用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //如果参数名是以下三种则不是通过请求中获取参数的方式
                        if ("req".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if ("resp".equals(parameterName)) {
                            parameterValues[i] = resp;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            //从请求中获取参数
                            String parameterValue = req.getParameter(parameter.getName());
                            String typeName = parameter.getType().getName();
                            Object paramenterObj = parameterValue;
                            if (paramenterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    paramenterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = paramenterObj;
                        }
                    }
                    //反射 调用目标方法
                    method.setAccessible(true);
                    Object returnObj = method.invoke(beanServlet, parameterValues);
                    //视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")) {
                        //截取字符串，
                        String redirectStr = methodReturnStr.substring("redirect".length());
                        //重定向（有redirect：）
                        resp.sendRedirect(redirectStr);
                    } else {
                        //请求转发(没有redirect：)
                        //处理模板，第一个参数是逻辑视图名称，
                        //thymeleaf会把逻辑视图名称补全前缀和后缀（xml配置的前缀和后缀）组成完整的物理视图/index.html
                        super.processTemplate(methodReturnStr, req, resp);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
