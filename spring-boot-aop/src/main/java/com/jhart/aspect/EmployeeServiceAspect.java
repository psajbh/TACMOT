package com.jhart.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.jhart.model.Employee;

@Aspect
@Component
public class EmployeeServiceAspect {
	

	//best advice to get any method and determine time to execute.
	@Around(value = "execution(* com.jhart.service.EmployeeService.*(..))")
	public Object createEmployeeAroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		String methodName = pjp.getSignature().getName();
		Long start = System.currentTimeMillis();
		Object o = pjp.proceed();
		Long executionTime = System.currentTimeMillis() - start;
		System.out.println("EmployeeService."+methodName+"(..): execution:" + executionTime);
		return (Employee)o;
	}
	
//	@Around(value = "execution(* com.javainuse.service.EmployeeService.*(..)) and args(name,empId)")
//	public Object createEmployeeAroundAdvice(ProceedingJoinPoint pjp, String name, String empId) throws Throwable{
//		String methodName = pjp.getSignature().getName();
//		Long start = System.currentTimeMillis();
//		Object o = pjp.proceed();
//		Long executionTime = System.currentTimeMillis() - start;
//		System.out.println("EmployeeService."+methodName+"(..): execution:" + executionTime);
//		return (Employee)o;
//	}




}
