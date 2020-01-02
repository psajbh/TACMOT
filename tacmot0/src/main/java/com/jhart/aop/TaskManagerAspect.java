package com.jhart.aop;

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

import com.jhart.dto.UserBackBean;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Configuration
public class TaskManagerAspect {
	
	private static final String  KEY_ELEMENT = "ldapid";
	private static final boolean REST_APP_SECURE = true;
	private static final String SHOW_STOPPER_USERS = "execution(UserRestController.getAllUsers())";
	private static final String SHOW_STOPPER_TASKS = "execution(TaskRestController.getAllTasks())";
	private static final String SHOW_STOPPER_REST_TASKS = "execution(GetTaskRestController.getRestAllTasks())";
	
	@Autowired
	private UserService userService;
	
	// read: https://howtodoinjava.com/spring-aop-tutorial/
	// need to use a @Around advice to change behavior.
	@Around("execution(* com.jhart.web.rest..*.*(..))")
	public Object aroundRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		log.trace("aop advice execution(* com.jhart.web..*.*(..)) Allowed execution for {}", proceedingJoinPoint);
		long start = System.currentTimeMillis();
		boolean foundKeyElement = false;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Enumeration<String> headerNames = request.getHeaderNames();
		
		while (headerNames.hasMoreElements()) {
			if (TaskManagerAspect.REST_APP_SECURE) {
				String element = headerNames.nextElement();
				
				log.debug("header key: " + element);
				if (element.equals(TaskManagerAspect.KEY_ELEMENT)) {
					log.debug("header name ldapid found");
					foundKeyElement = true;
					Enumeration<String> headerValues = request.getHeaders(element);
					while(headerValues.hasMoreElements()) {
						String value = headerValues.nextElement();
						log.debug("header value: : " + value);
					
						if (null != value){
							if (null != userService.findByLdapId(value)) {
								UserBackBean userDto = new UserBackBean();
								userDto.setFirstName("TESTER");
								request.setAttribute("credentialKey", userDto);
								Object retValue = proceedingJoinPoint.proceed();
								log.debug(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
								return retValue;
							}
							else {
								return new ResponseEntity<Object>("Unathenticated User",HttpStatus.FORBIDDEN);
							}
						}
					}
				}
				else {
					log.debug("header name ldapid not found");
				}
			}
			else {
				String element = headerNames.nextElement();
				Enumeration<String> headerValues = request.getHeaders(element);
				while (headerValues.hasMoreElements()) {
					String value = headerValues.nextElement();
					log.debug(String.format("Key: %s Value: %s", element, value));
				}
			}
		}
		
		if (TaskManagerAspect.REST_APP_SECURE && !foundKeyElement) {
			if (proceedingJoinPoint.toShortString().equals(TaskManagerAspect.SHOW_STOPPER_USERS) 
					|| proceedingJoinPoint.toShortString().equals(TaskManagerAspect.SHOW_STOPPER_TASKS)) {
				log.debug(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
				return new ResponseEntity<Object>("Unathenticated User",HttpStatus.FORBIDDEN);	
			}
		}
		
		Object retValue = proceedingJoinPoint.proceed();
		log.debug(proceedingJoinPoint.toShortString() + " elapsed time: " +  (System.currentTimeMillis() - start) + " ms");
		return retValue;
	}
	
}

