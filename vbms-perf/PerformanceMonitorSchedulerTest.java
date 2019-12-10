package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;
import mockit.Cascading;
import mockit.Expectations;
import org.junit.Test;

public class PerformanceMonitorSchedulerTest {

	PerformanceMonitorScheduler scheduler = new PerformanceMonitorScheduler();

	@Test
	public void testOnApplicationEvent_AnyEvent_InitsJdbcImpl(@Cascading PerformanceDataStoreJdbcDbImpl dbImpl) {
		new Expectations() {
			{
				PerformanceDataStoreJdbcDbImpl.getInstance().init();
			}
		};

		scheduler.onApplicationEvent(null);
	}
}
