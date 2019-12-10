package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.security.common.VbmsUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PerformanceMonitorAspect {
	
	private static final PerformanceDataStore PDS = PerformanceDataStoreFactory.getPeformanceDataStore();
	private static long totalTimeMin = -1;
    private String methodSource;

    public void setMethodSource(String methodSource){
        this.methodSource = methodSource;
    }

    public String getMethodSource(){
        return methodSource;
    }

	public final Object monitorPerformance(ProceedingJoinPoint joinPoint)
	//CHECKSTYLE:OFF
	throws Throwable { //NOSONAR - No choice but to throw throwable
	//CHECKSTYLE:ON
		if(PDS.isMethodEnabled()){
			Object result = null;
			String throwMsg = null;
			String throwType = null;
			long startTime = System.currentTimeMillis();
			try{
				result = joinPoint.proceed();
			}catch(Exception t){
				throwMsg = t.getMessage();
				throwType = t.getClass().getName();
				throw t;
			}finally{
				long totalTime = System.currentTimeMillis() - startTime;

				if(totalTime > totalTimeMin || throwType != null){
					String userName;
					String methodName;
                    String serverName;
                    long dataSize = 0;

                    final String className = getClassName(joinPoint);
                    methodName = joinPoint.getSignature().getName();
                    long threadID = Thread.currentThread().getId();
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication!=null){
                        userName = authentication.getName();
                    } else {
                        userName = "unknown";
                    }
                    serverName = System.getProperty("weblogic.Name");

                    //Gather collection data size
                    //TODO: complex objects, collections in maps
                    if (result instanceof Collection) {
                        dataSize += ((Collection) result).size();
                    } else if (result instanceof Map) {
                        dataSize += ((Map) result).size();
                    }

                    List<String> parameterList = findParameters(joinPoint);


                    PDS.insertMethodData(startTime, totalTime, className, methodName,userName, throwType,throwMsg, methodSource,
                                            serverName, threadID, PerformanceLoggingFilter.getThreadSessionId(),dataSize, VbmsUser.getCurrentUser().getSubjectId(),
                                                parameterList.toString());
				}
			}
			return result;
		}else{
			return joinPoint.proceed();
		}
	}
	
	public static void setTotalTimeMin(String min) {
		long temp;
		try {
			temp = Long.parseLong(min);
		} catch (NumberFormatException e) {
			temp = -1;
		}
		if(temp != totalTimeMin){
			totalTimeMin = temp;
		}
	}

    /**
     * Returns the proper class name (rather than com.sun.proxy.$ProxyXXX) of the object that the
     * aspect is wrapped around.
     * @param joinPoint {@link org.aspectj.lang.JoinPoint} that is injected into the aspect class
     * @return {@link String} containing the proper class name even if it's a proxy
     */
    public static String getClassName(final ProceedingJoinPoint joinPoint) {
        String className = "";

        if (joinPoint != null) {

            Object target = joinPoint.getTarget();
            if (AopUtils.isAopProxy(target)) {

                Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint);
                if (targetClass != null) {
                    className = targetClass.getName();
                }

            } else {
                className = target.getClass().getName();
            }

        }

        return className;
    }

    private List<String> findParameters(ProceedingJoinPoint joinPoint) {
        List<String> params = new ArrayList<>();
        Object[] args = joinPoint.getArgs();
        for(Object obj : args) {
            if (obj != null) {
                String clazz = obj.getClass().getName();

                if(obj instanceof Collection) {
                    params.add(clazz + "[" + ((Collection)obj).size() + "]");
                } else if (obj instanceof Map) {
                    params.add(clazz + "[" + ((Map)obj).size() + "]");
                } else {
                    params.add(clazz);
                }
            }
        }
        return params;
    }
}
