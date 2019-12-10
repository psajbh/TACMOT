package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.impl.InsertDataInserter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertDataInserterTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;
	
	@Before
	public void setup(){
		Deencapsulation.setField(InsertDataInserter.class, "logger", logger);
	}

	@Test
	public void testInsertDataInserter(final JdbcTemplate jdbcTemplate, final ExecutorService service) {
		new Expectations(){
			{
				jdbcTemplate.update(anyString, (Object[])any);
				
				jdbcTemplate.update(anyString, (Object[])any); result = new Exception("testing");
				logger.isDebugEnabled(); result = true;
				logger.debug(anyString,(Throwable)any);
				
				jdbcTemplate.update(anyString, (Object[])any); result = new Exception("testing");
				logger.isDebugEnabled(); result = false;
			}
		};
		InsertDataInserter ins = new InsertDataInserter(jdbcTemplate,123456,123,100,23,"TestTable");
		ins.run();		
		ins.run();		
		ins.run();		
	}
}
