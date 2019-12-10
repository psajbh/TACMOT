package gov.va.vba.vbms.common.framework.performance;
import gov.va.vba.vbms.common.framework.performance.PerformanceJdbcDao;
import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;
import org.junit.Assert;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.NonStrictExpectations;

import org.junit.Test;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class PerformanceJdbcDaoTest {
	@Test	
	public void testsetPerfDataSource(final DataSource ds, @Cascading final PerformanceDataStoreJdbcDbImpl pdsj) {
        final PerformanceJdbcDao  perfDao = new  PerformanceJdbcDao();
        perfDao.setPerfDataSource(ds);
        perfDao.getSimpleJdbcTemplate();

    }
	@Test 
	public void  testgetPerfDataSource(final DataSource ds, @Cascading final PerformanceDataStoreJdbcDbImpl pdsj){
        final PerformanceJdbcDao  perfDao = new  PerformanceJdbcDao();
        perfDao.setPerfDataSource(ds);
        Assert.assertEquals(ds, perfDao.getPerfDataSource());
	}	
	
	
	@Test 
	public void  testgetJdbcTemplate(final DataSource ds,final JdbcTemplate template){
        final PerformanceJdbcDao  perfDao = new  PerformanceJdbcDao();
        perfDao.setPerfDataSource(ds);
        Assert.assertNotNull(perfDao.getJdbcTemplate());
	}
}
