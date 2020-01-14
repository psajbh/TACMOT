package mil.dtic.cbes.utils.aspect;

import java.util.Enumeration;
import java.util.Map;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.UserCredential;
import mil.dtic.cbes.service.user.api.UserCredentialEntityService;

@Aspect
@Component
@Configuration
public class CredentialsAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private static final boolean REST_APP_SECURE = true;  //at some point this will be removed. 
    private static final String  KEY_ELEMENT = "ldapid";  //this can be changed as desired, i.e. id.
    private static final String NO_AUTH_STATUS_MSG = "{\"status\" :\"NOT AUTHENTICATED\"}";
    private static final String NO_AUTHORIZATION_STATUS_MSG = "{\"status\":\"NOT AUTHORIZED\"}";
    private static final String USER_CREDENTIAL = "mil.dtic.cbes.model.dto.UserCredential";
    
    public static final String CREDENTIAL_KEY = "credentialKey";
    
    @Autowired
    private UserCredentialEntityService userCredentialEntityService;
    
    

    @Around("execution(* mil.dtic.cbes.controllers..*.*(..))")
    public Object credentialRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        String feature = proceedingJoinPoint.toShortString();
        log.info("credentialRequest- processing feature: " + feature);
        
        if (CredentialsAspect.REST_APP_SECURE) {
            boolean foundKeyElement = false;
            
            while (headerNames.hasMoreElements()) {
                String element = headerNames.nextElement();
                if (element.equals(CredentialsAspect.KEY_ELEMENT)) {
                    //log.trace("header name ldapid found");
                    foundKeyElement = true;
                    String value = null;
                    Enumeration<String> headerValues = request.getHeaders(element);
                    
                    while(headerValues.hasMoreElements()) {
                        value = headerValues.nextElement();
                    
                        if (null != value){
                            UserCredential userCredential = userCredentialEntityService.getCredentials(value);
                            
                            if (null != userCredential && userCredential.isValid()) {
                                Object retValue = null;
                                
                                if (!authorizeCredentialWithFeature(userCredential, feature)) {
                                    return new ResponseEntity<Object>(CredentialsAspect.NO_AUTHORIZATION_STATUS_MSG, HttpStatus.UNAUTHORIZED);
                                }
                                
                                request.setAttribute(CredentialsAspect.CREDENTIAL_KEY, userCredential);
                                
                                Object[] args = proceedingJoinPoint.getArgs();
                                
                                if (processArgs(args, userCredential)) {
                                    retValue = proceedingJoinPoint.proceed(args);
                                }
                                else {
                                    retValue = proceedingJoinPoint.proceed();
                                }
                                log.trace(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
                                
                                return retValue;
                            }
                        }
                    }
                }
            }
            
            if (!foundKeyElement) {
                processFakeSiteMinder();
                //return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);
            }
        }
        
        Object retValue = proceedingJoinPoint.proceed();
        log.debug(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
        return retValue;
        
    }
    
    private void processFakeSiteMinder() {
        Map<String, String> env = System.getenv();
        System.out.println();
        
    }
    
    private boolean authorizeCredentialWithFeature(UserCredential userCredential, String feature) {
        return FeatureQualifications.authorizeCredentialWithFeature(userCredential, feature);
    }

    private boolean processArgs(Object[] args, UserCredential userCredential) {
        boolean processArgs = false;
        
        if (null == args) {
            return processArgs;
        }
        
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].getClass().getName().equals(CredentialsAspect.USER_CREDENTIAL)) {
                    processArgs = true;
                    args[i] = userCredential;
                    break;
                }
            }
        }
        return processArgs;
    }

}
