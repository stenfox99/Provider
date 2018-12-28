package by.training.provider.filter;

import by.training.provider.command.ParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.jsp"}, dispatcherTypes = DispatcherType.FORWARD)
public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletRequest.getSession().setAttribute(ParameterName.PAGE, httpServletRequest.getServletPath());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
