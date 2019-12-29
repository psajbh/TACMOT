package com.jhart.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Configuration
public class TaskAopBefore {
	
	@Autowired
	private UserService userService;
	

//	@Before("execution(* com.jhart.service..*.*(..))")
//	public void beforeService(JoinPoint joinPoint){
//		log.trace("aop before service - Allowed execution for {}", joinPoint);
//	}
	
	// read: https://howtodoinjava.com/spring-aop-tutorial/
	// need to use a @Around advice to change behavior.
	@Around("execution(* com.jhart.web..*.*(..))")
	public Object beforeRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		log.trace("aop before web - Allowed execution for {}", proceedingJoinPoint);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		System.out.println();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String element = headerNames.nextElement();
			log.debug("header key: " + element);
			if (element.equals("ldapid")) {
				Enumeration<String> headerValues = request.getHeaders(element);
				while(headerValues.hasMoreElements()) {
					String value = headerValues.nextElement();
					log.debug("header value: : " + value);
					if (null != value){
						return proceedingJoinPoint.proceed();
					}
					else {
						return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);	
					}
				}
			}
		}
		
		return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);
		
	}
	
	
}

/*
@Around("repositoryClassMethods()")
public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    Object retval = pjp.proceed();
    long end = System.nanoTime();
    String methodName = pjp.getSignature().getName();
    logger.info("Execution of " + methodName + " took " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
    return retval;
}
*/
