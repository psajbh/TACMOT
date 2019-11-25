package com.jhart.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class TaskAopBefore {

	//@Before("execution(* com.jhart.service.task.*.*(..))")
	@Before("execution(* com.jhart.service.*.*.*(..))")
	public void before(JoinPoint joinPoint){
		//Advice
		log.info("aop before: ");
		log.info(" Allowed execution for {}", joinPoint);
	}
	
	
}
