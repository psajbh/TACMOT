package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.LinkedBlockingQueueAlmostSilentOffer;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.impl.ActiveUserInserter;

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

public class ActiveUserInserterTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;
	
	@Before
	public void setup(){
		Deencapsulation.setField(ActiveUserInserter.class, "logger", logger);
	}

	@Test
	public void testRunLogin(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds,
                             final LinkedBlockingQueueAlmostSilentOffer<Object[]> queueInserts) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("securityLevel", "1234");
        ActiveUserInserter ins = new ActiveUserInserter(jdbcTemplate, 123456, "", "", map);
        Deencapsulation.setField(ActiveUserInserter.class, "pds",pds);
        Deencapsulation.setField(ActiveUserInserter.class,"queueInserts",queueInserts);
		new Expectations(){
			{
                pds.isBatchEnabled();result = false;
				jdbcTemplate.update(anyString, (Object[])any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserInsert");

                pds.isBatchEnabled();result = false;
				jdbcTemplate.update(anyString, (Object[])any);result = new Exception();
				logger.isDebugEnabled();result = true;
				logger.debug(anyString,(Throwable)any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "ActiveUserInsert");

                pds.isBatchEnabled();result = false;
				jdbcTemplate.update(anyString, (Object[])any);result = new Exception();
				logger.isDebugEnabled();result = false;
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserInsert");

                pds.isBatchEnabled();result = true;
                queueInserts.offerAlmostSilently((Object[])any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserInsertBatchSingle");

			}
		};
		ins.run();
		ins.run();		
		ins.run();
        ins.run();
	}

	@Test
	public void testRunLoginNumberFormatException(final JdbcTemplate jdbcTemplate, final ExecutorService service) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("securityLevel", "abc");
        ActiveUserInserter ins = new ActiveUserInserter(jdbcTemplate, 123456, "", "", map);
//		new Expectations(){
//			{
//				jdbcTemplate.update(anyString, (Object[])any);
//			}
//		};
		ins.run();
	}

	@Test
	public void testRunLogout(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds,
                              final LinkedBlockingQueueAlmostSilentOffer<Object[]> queueUpdates) {
        ActiveUserInserter ins = new ActiveUserInserter(jdbcTemplate, "", "", 123456);
        Deencapsulation.setField(ActiveUserInserter.class, "pds",pds);
        Deencapsulation.setField(ActiveUserInserter.class,"queueUpdates",queueUpdates);
		new Expectations(){
			{
                pds.isBatchEnabled(); result = false;
				jdbcTemplate.update(anyString, (Object[])any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserUpdate");

                pds.isBatchEnabled(); result = false;
				jdbcTemplate.update(anyString, (Object[])any);result = new Exception();
				logger.isDebugEnabled();result = true;
				logger.debug(anyString,(Throwable)any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserUpdate");

                pds.isBatchEnabled(); result = false;
				jdbcTemplate.update(anyString, (Object[])any);result = new Exception();
				logger.isDebugEnabled();result = false;
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserUpdate");

                pds.isBatchEnabled(); result = true;
                queueUpdates.offerAlmostSilently((Object[])any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong,anyLong,anyLong,anyLong,"ActiveUserUpdateBatchSingle");
			}
		};
		ins.run();
		ins.run();		
		ins.run();
        ins.run();
    }
}
