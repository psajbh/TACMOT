package mil.dtic.cbes.utils.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.model.UserCredential;
import mil.dtic.cbes.service.user.api.UserCredentialEntityService;

@Slf4j
@Aspect
@Component
@Configuration
public class CredentialsAspect {
     
    private static final boolean REST_APP_SECURE = true;  //at some point this will be removed. 
    private static final String  KEY_ELEMENT = "ldapid";  //this can be changed as desired, i.e. id.
    private static final String NO_AUTH_STATUS_MSG = "{\"status\" :\"NOT AUTHENTICATED\"}";
    private static final String NO_AUTHORIZATION_STATUS_MSG = "{\"status\":\"NOT AUTHORIZED\"}";
    private static final String USER_CREDENTIAL = "mil.dtic.cbes.model.dto.UserCredential";
    
    public static final String CREDENTIAL_KEY = "credentialKey";
    
    //TODO: REMOVE -temporarily for test purposes: 
    private static final String SHOW_STOPPER_USERS_1 = "execution(ManageUsersController.getManagedUsers())";
    private static final String SHOW_STOPPER_USERS_2 = "execution(UserProfileController.getProfile())";
    
    @Autowired
    private UserCredentialEntityService userCredentialEntityService;

    @Around("execution(* mil.dtic.cbes.controllers..*.*(..))")
    public Object credentialRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	long start = System.currentTimeMillis();
    	Object retVal = null;
        String feature = proceedingJoinPoint.toShortString();
        log.debug("credentialRequest- processing for feature: " + feature);
        
        boolean foundKeyElement = false;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        
        while (headerNames.hasMoreElements()) {
            if (CredentialsAspect.REST_APP_SECURE) {
                String element = headerNames.nextElement();
                if (element.equals(CredentialsAspect.KEY_ELEMENT)) {
                	log.debug("credentialRequest- processing secured application with element: " + element);
                    foundKeyElement = true;
                    String value = null;
                    Enumeration<String> headerValues = request.getHeaders(element);
                    
                    while(headerValues.hasMoreElements()) {
                        value = headerValues.nextElement();
                        log.debug("credentialRequest- processing value: " + value);
                    
                        if (null != value){
                            UserCredential userCredential = userCredentialEntityService.getCredentials(value);
                            if (null != userCredential && userCredential.isValid()) {
                                Object retValue = null;log.debug("credentialRequest- user credential CAPTURED");
                                if (!authorizeCredentialWithFeature(userCredential, feature)) {
                                	log.debug("credentialRequest- feature: " + feature + " NOT SUPPORTED");
                                    return new ResponseEntity<Object>(CredentialsAspect.NO_AUTHORIZATION_STATUS_MSG, HttpStatus.UNAUTHORIZED);
                                }
                                
                                request.setAttribute(CredentialsAspect.CREDENTIAL_KEY, userCredential);
                                log.debug("credentialRequest- user credential set to request attribute");
                                
                                Object[] args = proceedingJoinPoint.getArgs();
                                
                                if (processArgs(args, userCredential)) {
                                	log.debug("credentialRequest- processing proceed with args");
                                	retVal = proceedingJoinPoint.proceed(args);
                                }
                                else {
                                	log.debug("credentialRequest- processing proceed");
                                	retVal =  proceedingJoinPoint.proceed();
                                }
                                log.trace(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
                                return retValue;
                            }
                            else {
                            	log.debug("credentialRequest- credential NULL or INVALID");
                                return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);
                            }
                        }
                    }
                }
            }
        }
        
        // this can happen if the  key is found but there are no values
        if (CredentialsAspect.REST_APP_SECURE && foundKeyElement) {
            return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);
        }
        
        // do not let show stoppers proceed if somehow they did not get processed. This will be handled more as the app grows.
        if (CredentialsAspect.REST_APP_SECURE && !foundKeyElement) {
            if (proceedingJoinPoint.toShortString().equals(CredentialsAspect.SHOW_STOPPER_USERS_1) 
                    || proceedingJoinPoint.toShortString().equals(CredentialsAspect.SHOW_STOPPER_USERS_2)) {
                log.debug(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
                return new ResponseEntity<Object>(CredentialsAspect.NO_AUTH_STATUS_MSG, HttpStatus.FORBIDDEN);  
            }
        }
        
        //REST_APP_SECURE false process.
        retVal = proceedingJoinPoint.proceed();
        log.debug(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
        return retVal;
        
    }
    
    private boolean authorizeCredentialWithFeature(UserCredential userCredential, String feature) {
        return FeatureQualifications.authorizeCredentialWithFeature(userCredential, feature);
    }

    //determine if an argument is a userCredential, if so -- need to process the args
    private boolean processArgs(Object[] args, UserCredential userCredential) {
        boolean processArgs = false;
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
