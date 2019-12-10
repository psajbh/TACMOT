package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;

public final class PerformanceDataStoreFactory {
	private PerformanceDataStoreFactory(){
	}
	public static PerformanceDataStore getPeformanceDataStore(){
		return PerformanceDataStoreJdbcDbImpl.getInstance();
	}
}

