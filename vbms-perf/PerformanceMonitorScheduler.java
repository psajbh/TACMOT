package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Starts up the performance monitor.  Normally it would be preferable for the PDS to do this itself,
 * but it is not currently a spring bean, and it is too risky to introduce that level of change
 * at the end of a release cycle.  Story# 210610 for technical debt.
 */
public class PerformanceMonitorScheduler implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		PerformanceDataStoreJdbcDbImpl.getInstance().init();
	}
}
