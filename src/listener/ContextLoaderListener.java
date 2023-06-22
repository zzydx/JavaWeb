package listener;

import ioc.BeanFactory;
import ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//监听上下文启动，启动时创建ioc容器，将其保存到servletContext作用域
//中央控制器DispatcherServlet 再从servletContext作用域中获取ioc容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //servletContext上下文对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        //读取xml上下文参数
        String path = servletContext.getInitParameter("contextConfigLocation");
        //创建ioc容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        //将ioc容器保存到application作用域
        servletContext.setAttribute("beanFactory", beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
