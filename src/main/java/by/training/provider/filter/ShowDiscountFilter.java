package by.training.provider.filter;

import by.training.provider.command.ParameterName;
import by.training.provider.entity.Discount;
import by.training.provider.exception.LogicException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/jsp/main.jsp"}, dispatcherTypes = DispatcherType.FORWARD)
public class ShowDiscountFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(ShowDiscountFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {
            List<Discount> discounts = new CommonService().findAllDiscounts();
            if (discounts.size() > 3) {
                httpServletRequest.getSession().setAttribute(ParameterName.PRINTED_DISCOUNTS, discounts.subList(discounts.size() - 4, discounts.size() - 1));
            }else {
                httpServletRequest.getSession().setAttribute(ParameterName.PRINTED_DISCOUNTS, discounts);
            }
        } catch (LogicException e) {
            LOG.error(e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
