package mil.dtic.cbes.utils.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import mil.dtic.cbes.utils.exceptions.security.InvalidHeadersException;
import mil.dtic.cbes.utils.exceptions.security.LdapRetrievalFailureException;
import mil.dtic.cbes.utils.exceptions.security.SecurityExceptionMessageHolder;

public class CxeHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String REMOTE_USER_HEADER = "REMOTE_USER";
    private static final String SM_USER_HEADER = "SM_USER";
    private static final String MDC_KEY_USER_NAME = "user";
    private static final String MDC_KEY_IP_ADDRESS = "IP";
    
    @Autowired
    FakeSiteminderSupport fakeSiteminderSupport;
    
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        log.trace("getPreAuthenticatedPrincipal-");
        
        Principal principal = request.getUserPrincipal();
        if (null != principal) {
            log.trace("getPreAuthenticatedPrincipal- principal attached to request with name: " + principal.getName());
            ThreadContext.put(MDC_KEY_USER_NAME, principal.getName());
            return principal.getName();
        }

        String loginId = null; 
        try {
            loginId = findLoginId(request);
        }
        catch(RuntimeException re) {
            log.error("getPreAuthenticatedPrincipal- exception finding loginId: "+re.getMessage(),re);
            //TODO: fix this to user Exception API return ResponseEntity.status(HttpStatus.FORBIDDEN).body(re.getMessage());
        }

        if (loginId != null) {
            loginId = uidFromDistinguishedName(loginId);
            ThreadContext.put(MDC_KEY_USER_NAME, loginId);
        } 
        else {
            ThreadContext.remove(MDC_KEY_USER_NAME);
        }
        ThreadContext.put(MDC_KEY_IP_ADDRESS, request.getRemoteHost());
        ThreadContext.put("URI", request.getRequestURI());
        log.trace("getPreAuthenticatedPrincipal: ThreadContext: " + ThreadContext.getContext());

        return loginId;
    }

    private String findLoginId(HttpServletRequest request) throws InvalidHeadersException, LdapRetrievalFailureException{
        log.trace("findLoginId-");
        String sourceMessage;
        String result = request.getHeader(CxeHeaderAuthenticationFilter.REMOTE_USER_HEADER);
        String resultSm = request.getHeader(CxeHeaderAuthenticationFilter.SM_USER_HEADER);
        
        if (null == result && null == resultSm) {
            
            result = fakeSiteminderSupport.processFakeSiteminder();
            
            if (null == result) {
            	log.error("No supported headers in request");
                throw new InvalidHeadersException(SecurityExceptionMessageHolder.INVALID_HEADER_EXCEPTION_MSG);
            }
        }
        
        if (result != null && resultSm != null && result.equals(resultSm) == false) {
        	log.error("Security violation: user ID spoofing. Both "+result+" and "+resultSm+" headers are present, but differ.");
            throw new InvalidHeadersException(SecurityExceptionMessageHolder.INVALID_HEADER_EXCEPTION_MSG);
        }

        if (!isBlank(result)) {
            sourceMessage = REMOTE_USER_HEADER + " request header";
        } 
        else {
            sourceMessage = SM_USER_HEADER + " request header";
            result = request.getHeader(SM_USER_HEADER);
            
            String[] userGroups = null;
                
            try {
            	// userGroups = NetLDAP.userGroups(result); 
            	//TODO: Fix this in real application.  also try to get NetLDAP jar file.
                log.trace("findLoginId- is registered in Ldap with userGroups: " + userGroups);
            }
            //catch(LDAPException ldapException) {
            catch(Exception ldapException) {
                String  ldapExceptionMsg = ldapException.getMessage();
                log.trace("findLoginId- ldapExceptionMsg" + ldapExceptionMsg);
                if (ldapExceptionMsg.contains(SecurityExceptionMessageHolder.LDAP_EXCEPTION_FAILURE)) {
                    log.trace("findLoginId- checking for not siteminder developer user");
                    if (null != fakeSiteminderSupport.processFakeSiteminder()) {
                            log.trace("findLoginId- local developer authenticated access on local environment");
                    }
                    else {
                        log.error("findLoginId- authentication failure - NOTE: if local environment check property settings");
                        throw new LdapRetrievalFailureException(ldapExceptionMsg);
                    }
                }
                else {
                    String notAuthenticatedMsg = String.format("findLoginId- %s is not registered in LDAP",result);
                    log.warn(notAuthenticatedMsg,ldapException);
                    throw new LdapRetrievalFailureException(SecurityExceptionMessageHolder.LDAP_EXCEPTION_MSG);
                }
            }
        }
        
        //String safePrincipal = Security.safeLog(result);  
        String safePrincipal = result;  
        
        if (!result.equals(safePrincipal)) {
            log.warn("Denying access: Unsafe characters found in login ID: " + safePrincipal);
            return null;
        }
        log.debug("Found login ID "+safePrincipal+" in "+sourceMessage);
        return safePrincipal;
    }

    private String uidFromDistinguishedName(String principal) {
        log.trace("uidFromDistinguishedName- begin value: "+principal);
        final String prefix = "uid=";
        int prefixLength = prefix.length();
        int prefixLoc = principal.indexOf(prefix);
        if (prefixLoc >= 0) {
            int start = prefixLoc + prefixLength;
            int end = principal.indexOf(",", start);
            if (end > start) {
                return principal.substring(start, end);
            }
            return principal.substring(start);
        }
        log.trace("uidFromDistinguishedName- end value: "+principal);
        return principal;
    }

    private boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

}
