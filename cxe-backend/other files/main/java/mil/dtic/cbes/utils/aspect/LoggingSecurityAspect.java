//package mil.dtic.cbes.utils.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@Configuration
//public class LoggingSecurityAspect {
//
//    
//    @Around("execution(* mil.dtic.cbes..*.*(..))")
//    public Object sanitizeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        String joinPoint = proceedingJoinPoint.toShortString();
//        System.out.println("joinPoint: " + joinPoint);
//        Object[] args = proceedingJoinPoint.getArgs();
//        return proceedingJoinPoint.proceed();
//        
//        //return null;
//    }
//}
