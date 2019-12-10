package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.LinkedBlockingQueueAlmostSilentOffer;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory;
import gov.va.vba.vbms.common.framework.performance.impl.AccessDataInserter;

import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccessDataInserterTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;
	
	@Before
	public void setup(){
		Deencapsulation.setField(AccessDataInserter.class, "logger", logger);
	}

	@Test
	public void testRunNonBatch(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds) {
		AccessDataInserter ins = new AccessDataInserter(jdbcTemplate, 123456, 1, "", "", 1, 1, 1, "", "", 1, "");
		Deencapsulation.setField(AccessDataInserter.class, "pds",pds);
		new Expectations(){
			{
                pds.isBatchEnabled(); result = false;
				pds.getHost();
				jdbcTemplate.update(anyString, (Object[])any);
				pds.isInsertEnabled(); result = true;
				pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "AccessMetrics");
			}
		};
		ins.run();		
	}

    @Test
    public void testRunBatch(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds,
                             final LinkedBlockingQueueAlmostSilentOffer<Object[]> queue) {
        AccessDataInserter ins = new AccessDataInserter(jdbcTemplate, 123456, 1, "", "", 1, 1, 1, "", "", 1, "");
        Deencapsulation.setField(AccessDataInserter.class, "pds",pds);
        Deencapsulation.setField(ins, "queue",queue);
        new Expectations(){
            {
                pds.isBatchEnabled(); result = true;
                pds.getHost();
                queue.offerAlmostSilently((Object[])any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "AccessMetricsBatchSingle");
            }
        };
        ins.run();
    }

    @Test
    public void testAccessDataInserterException(@Mocked final JdbcTemplate jdbcTemplate,
                                                @Mocked final PerformanceDataStore performanceDataStore) {
        final AccessDataInserter ins = new AccessDataInserter(jdbcTemplate, 123456, 1, "", "", 1, 1, 1, "", "", 1, "");
        new Expectations(){
            {
                setField(ins, "pds", performanceDataStore);

                performanceDataStore.isBatchEnabled(); result = Boolean.FALSE;
                PerformanceDataStore.getHost();
                jdbcTemplate.update(anyString, (Object[])any);result = new Exception();
                logger.debug(anyString,(Throwable)any);
                performanceDataStore.isInsertEnabled();
            }
        };
        ins.run();
    }
	
	@Test
	public void testAccessDataInserterTime(final JdbcTemplate jdbcTemplate) {
		AccessDataInserter ins = new AccessDataInserter(jdbcTemplate, 49, 1, "", "", 1, 1, 1, "", "", 1, "");
		AccessDataInserter ins2 = new AccessDataInserter(jdbcTemplate, 123456, 1, "", "", 1, 1, 1, "", "", 1, "");
		AccessDataInserter.setTotalTimeMin("50");
		new Expectations(){
			{
				//jdbcTemplate.update(anyString, (Object[])any); //this should not be called on first run
				jdbcTemplate.update(anyString, (Object[])any);// it should be called on the second run
			}
		};
		ins.run();
		ins2.run();
		AccessDataInserter.setTotalTimeMin("-1");
	}
	
	@Test
	public void testAccessDataInserterUser(final JdbcTemplate jdbcTemplate) {
		AccessDataInserter ins = new AccessDataInserter(jdbcTemplate, 123456, 1, "UNKNOWN", "", 1, 1, 1, "", "", 1, "");
		AccessDataInserter ins2 = new AccessDataInserter(jdbcTemplate, 123456, 1, "known", "", 1, 1, 1, "", "", 1, "");
		AccessDataInserter.setUserRegex("UNKNOWN");
		new Expectations(){
			{
				//jdbcTemplate.update(anyString, (Object[])any); //this should not be called due to regex
				jdbcTemplate.update(anyString, (Object[])any);// it should be called on the second run
			}
		};
		ins.run();
		ins2.run();
		AccessDataInserter.setUserRegex("");
	}
	
	@Test
	public void testAccessDataInserterPath(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds) {
		AccessDataInserter ins = new AccessDataInserter(jdbcTemplate, 123456, 1, "", "", 1, 1, 1, "", "/vbmsp2/", 1, "");
		AccessDataInserter ins2 = new AccessDataInserter(jdbcTemplate, 123456, 1, "", "", 1, 1, 1, "", "/vbmsp2-support", 1, "");
        Deencapsulation.setField(AccessDataInserter.class, "pds",pds);
		AccessDataInserter.setPathRegex("/vbmsp2/");
		new Expectations(){
			{
                pds.isBatchEnabled();result = false;
				//jdbcTemplate.update(anyString, (Object[])any); //this should not be called due to regex
				pds.isInsertEnabled(); result = true;
				pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "AccessMetrics");

                pds.isBatchEnabled();result = false;
				pds.getHost();
				jdbcTemplate.update(anyString, (Object[])any); // it should be called on the second run
				pds.isInsertEnabled(); result = true;
				pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "AccessMetrics");
			}
		};
		ins.run();
		ins2.run();
		AccessDataInserter.setPathRegex("");
	}
	
	@Test
	public void testRegexPatterSyntaxException(final Pattern pattern) {
		new Expectations(){
			{
				logger.info(anyString);
				Pattern.compile(anyString);result = new PatternSyntaxException("test", "test", 1);
				logger.warn(anyString,(Throwable)any);
				logger.info(anyString);
				Pattern.compile(anyString);result = new PatternSyntaxException("test", "test", 1);
				logger.warn(anyString,(Throwable)any);
			}
		};
		AccessDataInserter.setPathRegex("abc");
		AccessDataInserter.setPathRegex("abc");// hit VBMSStringUtils.equals branch
		AccessDataInserter.setUserRegex("abc");
		AccessDataInserter.setUserRegex("abc");// hit VBMSStringUtils.equals branch
	}
	
	@Test
	public void testTotalTimeMin(final Pattern pattern) {
		new Expectations(){
			{
				logger.info(anyString);
			}
		};
		AccessDataInserter.setTotalTimeMin("1");
		AccessDataInserter.setTotalTimeMin("1");//temp != totalTimeMin
	}
}
