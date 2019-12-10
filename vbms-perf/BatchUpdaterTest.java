package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BatchUpdaterTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;

	@Before
	public void setup(){
        Deencapsulation.setField(BatchUpdater.class, "logger", logger);
	}

	@Ignore
    @Test
    public void testRun_FullBatch_CallsInsertBatch(final LinkedBlockingQueue<Object[]> queue
    ) throws InterruptedException {
        final String insertQuery = "testing query";
        final String tableName = "TestTable";
        final BatchUpdater ins = new BatchUpdater(null, queue, insertQuery,tableName);
        Deencapsulation.setField(BatchUpdater.class,"batchSize",2);
        Deencapsulation.setField(BatchUpdater.class,"maxWait",-1);// set maxWait to negative so time check will hit break;
        final Object[] objAr = {1,2,3,4,5,6,7,8};
        new Expectations(ins){
            {
                queue.poll(1000, TimeUnit.MILLISECONDS);result = null;
                queue.poll(1000, TimeUnit.MILLISECONDS);result = objAr;//!= null branch
                queue.poll(1000, TimeUnit.MILLISECONDS);result = objAr;//!= null branch
				Deencapsulation.invoke(ins,"insertBatch");
            }
        };
        ins.run();
    }

	@Ignore
	@Test
	public void testRun_PartialBatchTimeCheck_CallsInsertBatch(final LinkedBlockingQueue<Object[]> queue
	) throws InterruptedException {
		final String insertQuery = "testing query";
		final String tableName = "TestTable";
		final BatchUpdater ins = new BatchUpdater(null, queue, insertQuery,tableName);
		Deencapsulation.setField(BatchUpdater.class,"batchSize",2);
		Deencapsulation.setField(BatchUpdater.class,"maxWait",-1);// set maxWait to negative so time check will hit break;
		final Object[] objAr = {1,2,3,4,5,6,7,8};
		new Expectations(ins){
			{
				queue.poll(1000, TimeUnit.MILLISECONDS);result = null;
				queue.poll(1000, TimeUnit.MILLISECONDS);result = objAr;//!= null branch
				queue.poll(1000, TimeUnit.MILLISECONDS);result = null;//since size is not 0, this should hit the time check branch
				Deencapsulation.invoke(ins, "insertBatch");
			}
		};
		ins.run();
	}

	@Ignore
    @Test
    public void testRun_InterruptedException_CallsInsertBatch(final LinkedBlockingQueue<Object[]> queue
    ) throws InterruptedException {
        final String insertQuery = "testing query";
        final String tableName = "TestTable";
        final BatchUpdater ins = new BatchUpdater(null, queue, insertQuery,tableName);
        Deencapsulation.setField(BatchUpdater.class,"batchSize",2);
        Deencapsulation.setField(BatchUpdater.class,"maxWait",-1);// set maxWait to negative so time check will hit break;
        new Expectations(ins){
            {
                queue.poll(1000, TimeUnit.MILLISECONDS);result = new InterruptedException("testing");
				Deencapsulation.invoke(ins,"insertBatch");
            }
        };
        ins.run();
    }

	@Ignore
    @Test
    public void testRun_Exception_Exits(final LinkedBlockingQueue<Object[]> queue
    ) throws InterruptedException {
        final String insertQuery = "testing query";
        final String tableName = "TestTable";
        final BatchUpdater ins = new BatchUpdater(null, queue, insertQuery,tableName);
        new Expectations(){
            {
                queue.poll(1000, TimeUnit.MILLISECONDS);
				result = new RuntimeException("testing");
				logger.info(anyString,(Exception)any);
            }
        };
        ins.run();
    }

	@Ignore
	@Test
	public void testInsertBatch_EmptyBatch_ReturnsFalse() {
		final String insertQuery = "testing query";
		final String tableName = "TestTable";
		final BatchUpdater ins = new BatchUpdater(null, null, insertQuery,tableName);
		Boolean result = Deencapsulation.invoke(ins,"insertBatch");
		Assert.assertFalse(result);
	}

	@Ignore
	@Test
	public void testInsertBatch_InsertEnabledFullBatch_InsertsDataReturnsTrue(final PerformanceDataStore pds, final SimpleJdbcTemplate template) {
		final String insertQuery = "testing query";
		final String tableName = "TestTable";
		final BatchUpdater ins = new BatchUpdater(pds, null, insertQuery,tableName);
		ins.setSimpleJdbcTemplate(template);

		Deencapsulation.setField(BatchUpdater.class,"batchSize",2);
		List<Object[]> batch = new ArrayList<Object[]>();
		batch.add(new Object[0]);
		batch.add(new Object[0]);
		Deencapsulation.setField(ins,"batch",batch); // full batch, size 2


		new Expectations() {
			{
				template.batchUpdate(insertQuery, (List<Object[]>)any);
				pds.isInsertEnabled();result = true;
				pds.insertInsertData(anyLong, anyLong, anyLong, anyLong, tableName+"Batch");
			}
		};

		Boolean result = Deencapsulation.invoke(ins,"insertBatch");
		Assert.assertTrue(result);
	}

	@Ignore
	@Test
	public void testInsertBatch_InsertDisabledPartialBatch_NoInsertReturnsFalse(final PerformanceDataStore pds, final SimpleJdbcTemplate template) {
		final String insertQuery = "testing query";
		final String tableName = "TestTable";
		final BatchUpdater ins = new BatchUpdater(pds, null, insertQuery,tableName);
		ins.setSimpleJdbcTemplate(template);

		Deencapsulation.setField(BatchUpdater.class,"batchSize",2);
		List<Object[]> batch = new ArrayList<Object[]>();
		batch.add(new Object[0]);
		Deencapsulation.setField(ins,"batch",batch); // not full batch, size 1


		new Expectations() {
			{
				template.batchUpdate(insertQuery, (List<Object[]>)any);
				pds.isInsertEnabled();result = false;
			}
		};

		Boolean result = Deencapsulation.invoke(ins,"insertBatch");
		Assert.assertFalse(result);
	}
}
