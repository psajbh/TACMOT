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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.config.MutableHttpServletRequest;
import mil.dtic.cbes.model.UserSecurity;
import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.service.user.api.UserCredentialEntityService;

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

    @Around("execution(* mil.dtic.cbes.controllers..*.*(..))")
    public Object credentialRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object retVal = null;
        String username = null;
        long start = System.currentTimeMillis();
        
        String feature = proceedingJoinPoint.toShortString();
        String keyHeader = configurationService.getKeyHeader();
        log.debug("credentialRequest- authorizing access to feature: "+feature+" with keyHeader:"+keyHeader);
        boolean noAuthentication = false;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        UserSecurity userSecurity = (UserSecurity)request.getAttribute("user_info");
        
        if (null != userSecurity) {
            log.debug("credentialRequest- user has been authenticated, user name: "+username);
            username = userSecurity.getUsername();
            if (!validateUserName(username)) {
                return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);
            }
            log.debug("credentialRequest- user has been authenticated, user name: "+username);
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
        
        log.debug("processCredential- start value: "+value+" feature: "+feature);
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
        return FeatureQualifications.authorizeCredentialWithFeature(userCredential, feature);
    }

    private boolean processArgs(Object[] args, UserCredentialDto userCredential) {
        boolean processArgs = false;
        
        if (null == args || args.length == 0) {
            return processArgs;
        }
        
        log.trace("processArgs: processing arguments in case an argument may be a UserCredential");
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass().getName().equals(CredentialsAspect.USER_CREDENTIAL_ARG)) {
                    processArgs = true;
                    args[i] = userCredential;
                    break;
                }
            }
        }
        return processArgs;
    }
    
    private String getUserName(HttpServletRequest request, String keyHeader) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            log.trace("credentialRequest- processing element: "+element);
            if (element.equals(keyHeader)) {
                log.trace("credentialRequest- header name captured from siteminder");
                Enumeration<String> headerValues = request.getHeaders(element);
                while(headerValues.hasMoreElements()) {
                    return headerValues.nextElement();
                }
            }
        }
        return null;
    }
    
    private boolean validateUserName(String username) {
        return StringUtils.hasText(username);
    }

}
