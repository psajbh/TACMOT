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
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import mil.dtic.cbes.service.config.ConfigurationService;


@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class CxeSecurityFilter implements Filter {
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
        log.trace("doFilter- start");
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
            log.error("doFilter- authentication failure for userLdapId: " + userLdapId);
            throw new ServletException("doFilter- Failed Authentication userid: " + userLdapId);
        }
       log.trace("doFilter- finished");
    }
    
    private UserDetails getUserDetails(String ldapId) {
        log.trace("getUserDetails- ldapId: " + ldapId);
        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername(ldapId);
        }
        catch(UsernameNotFoundException usernameNotFoundException) {
            log.error("getUserDetails- failed to capture user details for: " + ldapId);
        }
        log.trace("getUserDetails- returning: " + userDetails.toString());
        return userDetails;
    }
    
    private String getAuthenticationKeyData(MutableHttpServletRequest mutableRequest) {
        log.trace("getAuthenticationKeyData- start");
        String key = configurationService.getKeyHeader();
        String rValue = null;
        log.debug("getAuthenticationKeyData- key: " + key);
        Enumeration<String> headerNames = mutableRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {   
            String element = headerNames.nextElement().toUpperCase();
            log.trace("getAuthenticationKeyData- "+element);
            if (element.equals(key)) {
                Enumeration<String> headerValues = mutableRequest.getHeaders(element);
                rValue = headerValues.nextElement();
                break;
            }
        }
        if(null == rValue) {
            log.warn("getAuthenticationKeyData- not found for " + key);
        }
        log.debug("getAuthenticationKeyData- value: " + rValue);
        return rValue;
    }
}
