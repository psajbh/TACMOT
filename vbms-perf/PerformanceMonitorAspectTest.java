package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.security.common.VbmsUser;
import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

import mockit.Mocked;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceMonitorAspectTest {
	
	@Cascading PerformanceDataStoreFactory pdsf;
	
	@Test
    public void testLogAround(final ProceedingJoinPoint proceedingJoinPoint, @Cascading SecurityContextHolder securityContextHolder,
    		final Signature signature, @Cascading final PerformanceDataStore pds, final VbmsUser user) throws Throwable {
		final PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();

        new Expectations(VbmsUser.class, performanceMonitorAspect) {
            {
            	pds.isMethodEnabled();result = true;
            	proceedingJoinPoint.proceed();
            	proceedingJoinPoint.getTarget(); result = new Object();
            	proceedingJoinPoint.getSignature(); result = signature;
            	signature.getName();
            	SecurityContextHolder.getContext().getAuthentication().getName();
                invoke(performanceMonitorAspect, "findParameters", proceedingJoinPoint);
                VbmsUser.getCurrentUser(); result = user;
                user.getSubjectId(); result = anyString;
            	pds.insertMethodData(anyLong, anyLong, anyString, anyString, anyString, anyString, anyString, anyString, anyString, anyLong, anyString, anyLong, anyString, anyString);
            }
        };
    	Deencapsulation.setField(PerformanceMonitorAspect.class,"PDS",pds);
        Assert.assertNull(performanceMonitorAspect.monitorPerformance(proceedingJoinPoint));
	}

    @Test
    public void testMonitorPerformance_NullAuthentication(final ProceedingJoinPoint proceedingJoinPoint, @Cascading SecurityContextHolder securityContextHolder,
                              final Signature signature, @Cascading final PerformanceDataStore pds, final VbmsUser user) throws Throwable {
        final PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();

        new Expectations(VbmsUser.class, performanceMonitorAspect) {
            {
                pds.isMethodEnabled();result = true;
                proceedingJoinPoint.proceed();
                proceedingJoinPoint.getTarget(); result = new Object();
                proceedingJoinPoint.getSignature(); result = signature;
                signature.getName();
                SecurityContextHolder.getContext().getAuthentication();
                result = null;
                invoke(performanceMonitorAspect, "findParameters", proceedingJoinPoint);
                VbmsUser.getCurrentUser(); result = user;
                user.getSubjectId(); result = anyString;
                pds.insertMethodData(anyLong, anyLong, anyString, anyString, "unknown", anyString, anyString, anyString, anyString, anyLong, anyString, anyLong, anyString, anyString);
            }
        };
        Deencapsulation.setField(PerformanceMonitorAspect.class,"PDS",pds);
        Assert.assertNull(performanceMonitorAspect.monitorPerformance(proceedingJoinPoint));
    }
		
	@Test
    public void testLogAroundBelowMinTotalTime(final ProceedingJoinPoint proceedingJoinPoint, @Cascading SecurityContextHolder securityContextHolder,
    		final Signature signature, @Cascading final PerformanceDataStore pds) throws Throwable {
		
		new Expectations() {
            {
            	pds.isMethodEnabled();result = true;
            	proceedingJoinPoint.proceed();
            	proceedingJoinPoint.getTarget(); result = new Object();times=0;
            	proceedingJoinPoint.getSignature(); result = signature;times=0;
            	signature.getName();times=0;
            	SecurityContextHolder.getContext().getAuthentication().getName();times=0;
            	pds.insertMethodData(anyLong, anyLong, anyString, anyString, anyString, anyString, anyString, anyString, anyString, anyLong, anyString, anyLong, anyString, anyString);times=0;
            }
        };
    	PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();
    	Deencapsulation.setField(PerformanceMonitorAspect.class,"PDS",pds);
    	performanceMonitorAspect.setTotalTimeMin("50");
        Assert.assertNull(performanceMonitorAspect.monitorPerformance(proceedingJoinPoint));
    	performanceMonitorAspect.setTotalTimeMin("abc");//return value to default of -1 and get coverage of NumberFormatException
	}
		
	@Test
    public void testLogAroundBelowMinTotalTimeWithException(final ProceedingJoinPoint proceedingJoinPoint, @Cascading SecurityContextHolder securityContextHolder,
    		final Signature signature, @Cascading final PerformanceDataStore pds, final VbmsUser user) throws Throwable {
        final PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();

        new Expectations(VbmsUser.class, performanceMonitorAspect) {
            {
            	pds.isMethodEnabled();result = true;
            	proceedingJoinPoint.proceed();result = new Exception("testing");
            	proceedingJoinPoint.getTarget(); result = new Object();
            	proceedingJoinPoint.getSignature(); result = signature;
            	signature.getName();
            	SecurityContextHolder.getContext().getAuthentication().getName();
                invoke(performanceMonitorAspect, "findParameters", proceedingJoinPoint);
                VbmsUser.getCurrentUser(); result = user;
                user.getSubjectId(); result = anyString;
            	pds.insertMethodData(anyLong, anyLong, anyString, anyString, anyString, anyString, anyString,anyString, anyString, anyLong, anyString, anyLong, anyString, anyString);times=1;
            }
        };
        
    	Deencapsulation.setField(PerformanceMonitorAspect.class,"PDS",pds);
    	performanceMonitorAspect.setTotalTimeMin("50");
        try{Assert.assertNull(performanceMonitorAspect.monitorPerformance(proceedingJoinPoint));}catch(Exception e){}
    	performanceMonitorAspect.setTotalTimeMin("abc");//return value to default of -1 and get coverage of NumberFormatException
	}
	
	@Test
    public void testLogAroundPDSDisabled(final ProceedingJoinPoint proceedingJoinPoint,@Cascading final PerformanceDataStore pds) throws Throwable {
		
		new Expectations() {
            {
            	pds.isMethodEnabled();result = false;
            	proceedingJoinPoint.proceed();
            }
        };
    	PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();
    	Deencapsulation.setField(PerformanceMonitorAspect.class,"PDS",pds);
        Assert.assertNull(performanceMonitorAspect.monitorPerformance(proceedingJoinPoint));
	}
	
	@Test(expected=Exception.class)
    public void testLogAroundProceedException(final ProceedingJoinPoint proceedingJoinPoint, @Cascading SecurityContextHolder securityContextHolder,
    		final Signature signature,@Cascading final PerformanceDataStore pds, final VbmsUser user) throws Throwable {
        final PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();

        new Expectations(VbmsUser.class, performanceMonitorAspect) {
            {
            	pds.isMethodEnabled();result = true;
            	proceedingJoinPoint.proceed(); result = new Exception();
            	proceedingJoinPoint.getTarget(); result = new Object();
            	proceedingJoinPoint.getSignature(); result = signature;
            	signature.getName();
            	SecurityContextHolder.getContext().getAuthentication().getName();
                invoke(performanceMonitorAspect, "findParameters", proceedingJoinPoint);
                VbmsUser.getCurrentUser(); result = user;
                user.getSubjectId(); result = anyString;
            	pds.insertMethodData(anyLong, anyLong, anyString, anyString, anyString, anyString, anyString, anyString, anyString, anyLong, anyString, anyLong, anyString, anyString);
            	
            }
        };
    	Deencapsulation.setField(PerformanceMonitorAspect.class,"PDS",pds);
        Assert.assertNull(performanceMonitorAspect.monitorPerformance(proceedingJoinPoint));
	}

    @Test
    public void testMethodSource(){
        PerformanceMonitorAspect performanceMonitorAspect = new PerformanceMonitorAspect();
        performanceMonitorAspect.setMethodSource("methodSource");
        Assert.assertEquals("methodSource", performanceMonitorAspect.getMethodSource());
    }

    @Test
    public void testFindParameters(@Mocked final ProceedingJoinPoint pjp) {

        final PerformanceMonitorAspect performanceMonitoringAspect = new PerformanceMonitorAspect();
        final List<String> stringList = new ArrayList<>();
        stringList.add("test");
        final Map<Integer, String> mapTest = new HashMap<>();
        mapTest.put(new Integer("1"), "test");
        final Object[] args = new Object[3];
        args[0] = "";
        args[1] = mapTest;
        args[2] = stringList;

        new Expectations() {
            {
                pjp.getArgs(); result = args;
            }
        };

        Deencapsulation.invoke(performanceMonitoringAspect, "findParameters", pjp);

    }
	
//	@Test  // null checking looks to be about 10% slower 4.184s vs. 4.608s for 1 billion runs 
//	public void testPerformanceOfTryCatch(){
//		
//		MockJoinPoint joinPoint = new MockJoinPoint();
//		String targetClass;
//		String methodName;
//		String userName;
//		for(int i = 0; i < 1000000000; i++){
//			try {
//				targetClass = joinPoint.getTarget().getClass().getName();
//				methodName = joinPoint.getSignature().getName();
//				//userName = SecurityContextHolder.getContext().getAuthentication().getName();
//				userName = joinPoint.getContext().getAuthentication().getName();
//			} 
//			catch (Exception npe) {
//				userName = "unknown";
//			}
//		}
//	}
//	@Test  // null checking looks to be about 10% slower 4.184s vs. 4.608s for 1 billion runs 
//	public void testPerformanceOfNullChecking(){
//		
//		MockJoinPoint joinPoint = new MockJoinPoint();
//		String targetClass;
//		String methodName;
//		String userName;
//		for(int i = 0; i < 1000000000; i++){
//			Object target = joinPoint.getTarget();
//			if(target != null){
//				Class clazz = target.getClass();
//				if(clazz != null){
//					targetClass =  clazz.getName();
//				}
//			}
//			MockJoinPoint signature = joinPoint.getSignature();
//			if(signature != null){
//				methodName = signature.getName();
//			}
//			//userName = SecurityContextHolder.getContext().getAuthentication().getName();
//			MockJoinPoint context = joinPoint.getContext();
//			if(context != null){
//				MockJoinPoint auth = context.getAuthentication();
//				if(auth != null){
//					userName = auth.getName();
//				}
//			}
//			userName = "unknown";
//
//		}
//	}
//	public class MockJoinPoint{
//		public MockJoinPoint(){
//			
//		}
//		Object target = new Object();
//		Object getTarget(){
//			return target;
//		}
//		public MockJoinPoint getSignature(){
//			return this;
//		}
//		public MockJoinPoint getContext(){
//			return this;
//		}
//		public MockJoinPoint getAuthentication(){
//			return this;
//		}
//		public String getName(){
//			return "Bob";
//		}
//	}
}
