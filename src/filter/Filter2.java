package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("/filterServlet.do")
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("f2前置代码");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("f2后置代码");
    }

    @Override
    public void destroy() {

    }
}
