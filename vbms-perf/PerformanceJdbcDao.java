package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.common.framework.performance.impl.BatchUpdater;
import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;


public class PerformanceJdbcDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceJdbcDao.class);
	private JdbcTemplate jdbcTemplate;

    private SimpleJdbcTemplate simpleJdbcTemplate;
	private DataSource perfDataSource;

    public void setPerfDataSource(DataSource perfDataSource){
    	LOGGER.warn("perfDataSource: " + perfDataSource);
    	this.perfDataSource = perfDataSource;
    	setJdbcTemplate(perfDataSource);
    }
    public DataSource getPerfDataSource(){
    	return perfDataSource;
    }
    public void setJdbcTemplate(DataSource perfDataSource){
    	jdbcTemplate = new JdbcTemplate(perfDataSource);
        simpleJdbcTemplate = new SimpleJdbcTemplate(perfDataSource);
    	LOGGER.warn("jdbcTemplate: " + jdbcTemplate);
    	PerformanceDataStoreJdbcDbImpl pdsj = PerformanceDataStoreJdbcDbImpl.getInstance();
    	pdsj.setTemplate(jdbcTemplate);
        BatchUpdater.setSimpleJdbcTemplate(simpleJdbcTemplate);
    }

    public JdbcTemplate getJdbcTemplate(){
    	LOGGER.warn("inside getJdbcTemplate: " + jdbcTemplate);
    	return jdbcTemplate;
    }

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }


}
