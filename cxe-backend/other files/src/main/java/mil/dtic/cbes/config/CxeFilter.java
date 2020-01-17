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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import mil.dtic.cbes.service.config.ConfigurationService;


@Component
@Order(1)
public class CxeFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ConfigurationService configurationService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}
    
    @Override
    public void destroy() {}

    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
            FilterChain filterChain) throws IOException, ServletException {
        
        boolean proceed = false;
        boolean foundKey = false;
        UserDetails userDetails;
        String userLdapId;
        
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest((HttpServletRequest) servletRequest);
        userLdapId = getAuthenticationKeyData(mutableRequest);
        if (null != userLdapId) {
            foundKey = true;
            userDetails = getUserDetails(userLdapId);
            if (null != userDetails) {
                mutableRequest.setAttribute("user_info", userDetails);
                proceed = true;
            }
        }
        
       if (proceed || !foundKey) {
           filterChain.doFilter(mutableRequest, servletResponse);
       }
        else {
            throw new ServletException("doFilter- Failed Authentication userid: " + userLdapId);
        }
    }
    
    private UserDetails getUserDetails(String ldapId) {
        try {
            return userDetailsService.loadUserByUsername(ldapId);
        }
        catch(UsernameNotFoundException usernameNotFoundException) {
            log.error("doFilter- failed to capture user details for: " + ldapId);
        }
        return null;
    }
    
    private String getAuthenticationKeyData(MutableHttpServletRequest mutableRequest) {
        String key = configurationService.getKeyHeader();
        Enumeration<String> headerNames = mutableRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            if (element.equals(key)) {
                Enumeration<String> headerValues = mutableRequest.getHeaders(element);
                return headerValues.nextElement();
            }
        }
        log.warn("getAuthenticationKeyData- not found for " + key);
        return null;
    }
}
