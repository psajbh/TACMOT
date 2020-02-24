package mil.dtic.cbes.utils.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.user.UserCredentialDto;
import mil.dtic.cbes.service.security.FeatureAccessService;
import mil.dtic.cbes.service.user.UserCredentialEntityService;
import mil.dtic.cbes.utils.exceptions.security.FeatureNotFoundException;
import mil.dtic.cbes.utils.security.FakeSiteminderSupport;
import mil.dtic.cbes.utils.security.FeatureQualifications;
import mil.dtic.cbes.utils.security.MutableHttpServletRequest;
import mil.dtic.cbes.utils.security.UserSecurity;

@Aspect
@Component
@Configuration
public class CredentialsAspect extends FakeSiteminderSupport{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String NO_AUTH_STATUS_MSG = "{\"status\" :\"NOT AUTHENTICATED\"}";
    private static final String NO_AUTHORIZATION_STATUS_MSG = "{\"status\":\"NOT AUTHORIZED\"}";
    private static final String USER_CREDENTIAL_ARG = "mil.dtic.cbes.model.dto.UserCredential";
    public static final String CREDENTIAL_KEY_ATTRIBUTE = "credentialKey";
    
    @Autowired
    private UserCredentialEntityService userCredentialEntityService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private FeatureQualifications featureQualifications;
    
    @Around("execution(* mil.dtic.cbes.controllers..*.*(..))")
    public Object credentialRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        log.trace("credentialRequest- start");
        
        Object retVal = null;
        String username = null;
        long start = System.currentTimeMillis();
        
        String feature = proceedingJoinPoint.toShortString();
        String keyHeader = configurationService.getKeyHeader();
        log.trace("credentialRequest- authorizing access to feature: "+feature+" with keyHeader:"+keyHeader);
        boolean noAuthentication = false;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        UserSecurity userSecurity = (UserSecurity)request.getAttribute("user_info");
        
        if (null != userSecurity) {
            log.trace("credentialRequest- userSecurity successfully acquired");
            username = userSecurity.getUsername();
            if (!validateUserName(username)) {
                return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);
            }
            log.trace("credentialRequest- user has been authenticated, user name: "+username);
            noAuthentication = true;
        }
        else {
            username = getUserName(request, keyHeader);
            if (username == null) {
                log.debug("credentialRequest- processing unauthenticated developer on local computer");
                username = processFakeSiteminder();
                if (!validateUserName(username)) {
                    log.error("credentialRequest- failed to authenticate developer - check property settings on local computer");
                    return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);
                }
                request.setAttribute("user_info", userDetailsService.loadUserByUsername(username));
                log.debug("credentialRequest- unauthenticated developer on local computer, username: "+username);
            }
        }
        
        if (noAuthentication) {
            log.debug("credentialRequest- processing authorization to user: "+username+" for feature:"+feature);
            retVal = processCredential(username, feature, request, proceedingJoinPoint);
            log.debug("credentialRequest- authorized access to endpoint "+proceedingJoinPoint.toShortString()+
                    " eplapsed time: "+(System.currentTimeMillis() - start)+" ms"); 
            return retVal;
        }
        else {
            log.debug("credentialRequest- authenticating developer on local computer: " + username);
            MutableHttpServletRequest devRequest = (MutableHttpServletRequest) request;
            devRequest.putHeader(keyHeader,username);
            
            retVal = processCredential(username, feature, devRequest, proceedingJoinPoint);
            log.debug("credentialRequest- authorized access to endpoint "+proceedingJoinPoint.toShortString()+
                    " eplapsed time: "+(System.currentTimeMillis() - start)+" ms"); 
            return retVal;
        }
    }
    
    private Object processCredential(String value, String feature, HttpServletRequest request, 
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        log.trace("processCredential- start");
        
        log.trace("processCredential- start value: "+value+" feature: "+feature);
        Object retVal = null;
        UserCredentialDto userCredential = userCredentialEntityService.getCredentials(value);
        
        if (null != userCredential && userCredential.isValid()) {
            log.debug("processCredential- credentials are valid");
            if (!authorizeCredentialWithFeature(userCredential, feature)) {
                log.error("processCredential- credentials are valid but feature is not supported");
                return new ResponseEntity<Object>(CredentialsAspect.NO_AUTHORIZATION_STATUS_MSG, HttpStatus.UNAUTHORIZED);
            }
            
            request.setAttribute(CredentialsAspect.CREDENTIAL_KEY_ATTRIBUTE, userCredential);
            Object[] args = proceedingJoinPoint.getArgs();
            
            if (processArgs(args, userCredential)) {
                return proceedingJoinPoint.proceed(args);
            }
            else {
                return proceedingJoinPoint.proceed();
            }
        }
        
        return retVal;
    }
    
    private boolean authorizeCredentialWithFeature(UserCredentialDto userCredential, String feature) {
        try {
            return featureQualifications.authorizeCredentialWithFeature(userCredential, feature);
        }
        catch(FeatureNotFoundException fnfe) {
            log.warn("authorizeCredentialWithFeature- " + fnfe.getMessage());
            return false;
        }
    }

    private boolean processArgs(Object[] args, UserCredentialDto userCredential) {
        log.trace("processArgs- start");
        boolean processArgs = false;
        
        if (null == args || args.length == 0) {
            log.trace("processArgs- no process, returning "+processArgs);
            return processArgs;
        }
        
        log.trace("processArgs- processing arguments in case an argument may be a UserCredential");
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass().getName().equals(CredentialsAspect.USER_CREDENTIAL_ARG)) {
                    processArgs = true;
                    args[i] = userCredential;
                    log.info("processArgs- userCredential parameter updated for user:" + userCredential.getLdapId());
                    break;
                }
            }
        }
        log.trace("processArgs- returning "+processArgs);
        return processArgs;
    }
    
    private String getUserName(HttpServletRequest request, String keyHeader) {
        log.trace("getUserName- start  keyHeader: "+keyHeader);
        String rValue = null;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            log.trace("credentialRequest- processing element: "+element);
            if (element.equals(keyHeader)) {
                log.trace("credentialRequest- header name captured from siteminder");
                Enumeration<String> headerValues = request.getHeaders(element);
                while(headerValues.hasMoreElements()) {
                    rValue = headerValues.nextElement();
                }
            }
        }
        log.trace("getUserName- returning: "+rValue);
        return rValue;
    }
    
    private boolean validateUserName(String username) {
        return StringUtils.hasText(username);
    }

}
