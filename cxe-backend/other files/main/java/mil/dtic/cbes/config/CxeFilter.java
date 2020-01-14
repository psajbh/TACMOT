package mil.dtic.cbes.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
public class CxeFilter implements Filter {
    
    public final static String KEY_ELEMENT = "ldapid";
    public final static String KEY_VALUE = "test-12345"; 
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
            FilterChain filterChain) throws IOException, ServletException {
        MutableHttpServletRequest mutableRequest = 
                new MutableHttpServletRequest((HttpServletRequest) servletRequest);
        
        if (null == mutableRequest.getHeader(CxeFilter.KEY_ELEMENT)) {
            mutableRequest.putHeader(CxeFilter.KEY_ELEMENT, KEY_VALUE);
        }
        filterChain.doFilter(mutableRequest, servletResponse);
    }
    
    @Override
    public void destroy() {}
    

}
