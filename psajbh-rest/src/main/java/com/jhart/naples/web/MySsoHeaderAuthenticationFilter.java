package com.jhart.naples.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySsoHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {
	
    private static final String REMOTE_USER_HEADER = "REMOTE_USER";
    private static final String SM_USER_HEADER = "SM_USER";
    
    private static final String MDC_KEY_USER_NAME = "user";
    private static final String MDC_KEY_IP_ADDRESS = "IP";


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    	log.debug("getPreAuthenticatedPrincipal- start");
    	
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            ThreadContext.put(MDC_KEY_USER_NAME, principal.getName());
            return principal.getName(); // already logged in, just return user name
        }

        // get the user name from request headers (where Siteminder put it)
        String loginId = findLoginId(request);

        if (loginId != null) {
            // extract the simple LDAP user ID from a DN string, if needed
            //loginId = uidFromDistinguishedName(loginId);
            //buildUserAuthority(roleRepository.findAll());

            // provide username & client host name to Log4J appenders
            ThreadContext.put(MDC_KEY_USER_NAME, loginId);
        } else {
        	ThreadContext.remove(MDC_KEY_USER_NAME);
        }
        ThreadContext.put(MDC_KEY_IP_ADDRESS, request.getRemoteHost());
        ThreadContext.put("URI", request.getRequestURI());

        return loginId;
    	
    }
    
    private String findLoginId(HttpServletRequest request) {
    	log.trace("findLogin-");
    	String sourceMessage;
    	
    	String resultSm = request.getHeader(MySsoHeaderAuthenticationFilter.SM_USER_HEADER);
    	String result = request.getHeader(MySsoHeaderAuthenticationFilter.REMOTE_USER_HEADER);
    	
    
    	if (result != null && resultSm != null && result.equals(resultSm) == false) {
    		throw new IllegalStateException("Security violation -- user ID spoofing. Both "
    				+result+" and "+resultSm+" headers are present, but differ.");
    	}

    	if (!isBlank(result)) {
    		sourceMessage = REMOTE_USER_HEADER + " request header";
    	} 
    	else {
    		// check for SM_USER in HTTP headers
    		result = request.getHeader(SM_USER_HEADER);
    		
    		if (!isBlank(result)) {
    			sourceMessage = SM_USER_HEADER + " request header";
    		} 
    		else {
            // check for REMOTE_USER environment variable (developer desktop use)
    			result = System.getenv(REMOTE_USER_HEADER);
    			sourceMessage = REMOTE_USER_HEADER + " environment variable";
    		}
    	}
    
    	//Ldap call.
    	//String safePrincipal = Security.safeLog(result);
    	//configure a real LdapServer.
    	
    	
//    	if (!result.equals(safePrincipal)) {
//    		log.warn("Denying access: Unsafe characters found in login ID: " + safePrincipal);
//    		return null;
//    	}
//    	
//    	LOGGER.debug("Found login ID " + safePrincipal + " in " + sourceMessage);
    //return safePrincipal;
    	return null;
    }
    
    
    private boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

    

}
