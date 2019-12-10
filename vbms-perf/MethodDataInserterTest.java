package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.LinkedBlockingQueueAlmostSilentOffer;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.impl.MethodDataInserter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.Assert;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class MethodDataInserterTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;

	@Before
	public void setup(){
		Deencapsulation.setField(MethodDataInserter.class, "logger", logger);
	}

	@Test
	public void testAccessDataInserter(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds) {
		final MethodDataInserter ins =   new MethodDataInserter(jdbcTemplate, 123456, 1, "", "", "", null, null,"", "", 1, "", 1, "6543", "params");
		MethodDataInserter ins2 = new MethodDataInserter(jdbcTemplate, 123456, 51, "", "", "", null, null,"", "", 51, "", 1, "6543", "params");
		Deencapsulation.setField(MethodDataInserter.class, "pds",pds);
		new Expectations(ins){
			{
                pds.isBatchEnabled(); result = false;
				Deencapsulation.invoke(ins, "checkFilters");result = false;
				//jdbcTemplate.update(anyString, (Object[])any);//not called first time
				pds.isInsertEnabled();result = true;
				pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "MethodMetrics");

                pds.isBatchEnabled(); result = false;
				pds.getHost();
				jdbcTemplate.update(anyString, (Object[])any);
				pds.isInsertEnabled(); result = true;
				pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "MethodMetrics");
			}
		};
		ins.run();
		ins2.run();
	}

    @Test
    public void testAccessDataInserterBatch(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds,
                                       final LinkedBlockingQueueAlmostSilentOffer<Object[]> queue) {
        final MethodDataInserter ins = new MethodDataInserter(jdbcTemplate, 123456, 1, "", "", "", null, null,"", "", 1, "", 1, "6543", "params");
        MethodDataInserter ins2 = new MethodDataInserter(jdbcTemplate,123456, 51, "", "", "", null, null,"", "", 51, "", 1, "6543", "params");
        Deencapsulation.setField(MethodDataInserter.class, "pds",pds);
        Deencapsulation.setField(ins,"QUEUE",queue);
        new Expectations(ins){
            {
                pds.isBatchEnabled();result = true;
                Deencapsulation.invoke(ins, "checkFilters");result = false;
                queue.offerAlmostSilently((Object[])any);times = 0; // should not be called due to checkFilters
                pds.isInsertEnabled();result = true;
                pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "MethodMetricsBatchSingle");

                pds.isBatchEnabled();result = true;
                pds.getHost();
                queue.offerAlmostSilently((Object[])any);
                pds.isInsertEnabled(); result = true;
                pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "MethodMetricsBatchSingle");
            }
        };
        ins.run();
        ins2.run();
    }

	@Test
	public void testAccessDataInserterException(final JdbcTemplate jdbcTemplate, final PerformanceDataStore pds,
                                                final ExecutorService service) {
		MethodDataInserter ins = new MethodDataInserter(jdbcTemplate, 123456, 1, "", "", "", "notnull", "notnull","", "", 1, "", 1, "6543", "params");
        Deencapsulation.setField(MethodDataInserter.class, "pds",pds);
		new Expectations(){
			{
                pds.isBatchEnabled(); result = false;
                pds.getHost();
				jdbcTemplate.update(anyString, (Object[])any);result = new Exception();
				logger.debug(anyString,(Throwable)any);
                pds.isInsertEnabled(); result = false;
                //pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, "MethodMetrics");
			}
		};
		ins.run();
	}

	@Test
	public void testCheckFilters(final JdbcTemplate jdbcTemplate, final ExecutorService service) {
		MethodDataInserter ins = new MethodDataInserter(jdbcTemplate, 123456, 10, "className", "methodName", "user", null, null,"methodSource", "serverName", 15, "", 1, "6543", "params");
//		MethodDataInserter.setTotalTimeMin("-1");
		MethodDataInserter.setClassNameMethodNameRegex(null);
		MethodDataInserter.setUserRegex(null);
		//test time
//		Assert.assertTrue((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
//		MethodDataInserter.setTotalTimeMin("9");
//		Assert.assertTrue((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
//		MethodDataInserter.setTotalTimeMin("11");
//		Assert.assertFalse((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
//		MethodDataInserter.setTotalTimeMin("-1");

		//test class and method
		MethodDataInserter.setClassNameMethodNameRegex("class.*");
		Assert.assertFalse((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
		MethodDataInserter.setClassNameMethodNameRegex("abc");
		Assert.assertTrue((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
		MethodDataInserter.setClassNameMethodNameRegex(null);

		//test user
		MethodDataInserter.setUserRegex("user");
		Assert.assertFalse((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
		MethodDataInserter.setUserRegex("abc");
		Assert.assertTrue((Boolean) Deencapsulation.invoke(ins, "checkFilters"));
		MethodDataInserter.setUserRegex(null);

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
		MethodDataInserter.setClassNameMethodNameRegex("abc");
		MethodDataInserter.setClassNameMethodNameRegex("abc");// hit VBMSStringUtils.equals branch
		MethodDataInserter.setUserRegex("abc");
		MethodDataInserter.setUserRegex("abc");// hit VBMSStringUtils.equals branch
	}
}
