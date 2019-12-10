package gov.va.vba.vbms.common.framework.performance;

import org.junit.Assert;

import org.junit.Test;

import gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory;
import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;
import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

public class PerformanceDataStoreFactoryTest {
	@Cascading PerformanceDataStoreJdbcDbImpl pds;
	
	@Test
	public void testConstructor(){
		Deencapsulation.newInstance(PerformanceDataStoreFactory.class);
	}
	@Test
	public void testGetPeformanceDataStore(){
		new Expectations(){
			{
				PerformanceDataStoreJdbcDbImpl.getInstance(); result = pds;
			}
		};
		Assert.assertEquals(pds, PerformanceDataStoreFactory.getPeformanceDataStore());
	}

}
