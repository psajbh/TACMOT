package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BatchUpdater implements Runnable {
	private static Logger logger = LoggerFactory
			.getLogger(BatchUpdater.class);// can't be final for JMockit
    private static final int DEFAULT_BATCH_SIZE = 2000;
    private static int batchSize = DEFAULT_BATCH_SIZE;// can't be final for JMockit
    private static final int DEFAULT_WAIT = 50000;
    private static int maxWait = DEFAULT_WAIT;// can't be final for JMockit
    private static SimpleJdbcTemplate simpleJdbcTemplate;
    private static final int QUEUE_POLL_TIMEOUT = 1000;

	private List<Object[]> batch = new ArrayList<Object[]>(batchSize);
    private PerformanceDataStore pds = null;
    private LinkedBlockingQueue<Object[]> queue;
    private String insertQuery;
    private String tableName;
    private long lastInsert = -1;

	public BatchUpdater(PerformanceDataStore pds, LinkedBlockingQueue<Object[]> queue, String insertQuery, String tableName) {
        this.pds = pds;
        this.queue = queue;
        this.insertQuery = insertQuery;
        this.tableName = tableName;
	}

    @Override
    public void run() {
        Object[] temp = null;
        boolean full = false;
        lastInsert = System.currentTimeMillis();
        try{
            do{
                try {
                    while(batch.size() < batchSize){
                        temp = queue.poll(QUEUE_POLL_TIMEOUT, TimeUnit.MILLISECONDS);
                        if (temp != null) {
                            batch.add(temp);
                        } else if(batch.size() == 0){ // got nothing this time and batch is empty try again
                            continue;
                        } else if(System.currentTimeMillis() - lastInsert > maxWait){
                            // got nothing this time and it's been a while since the last insert
                            // go ahead and insert what he have
                            break;
                        }
                    }
                }catch (InterruptedException e) {
                    //ignore and insert early
                    insertBatch();
                    return;
                }
                full = insertBatch();
            }while(full);
        }catch(Exception e){
            logger.info("Caught",e);
        }
    }

    private boolean insertBatch() {
        if(batch.size() == 0){
            return false;
        }
        try {
            long total = System.nanoTime();
            simpleJdbcTemplate.batchUpdate(insertQuery, batch);
            total = System.nanoTime() - total;
            lastInsert = System.currentTimeMillis();
            //TableName+"Batch" records use the startTime and totalTime normally, but
            //use the insertTime to record average time per record in the batch
            //and use filterTime to record the number of records in the batch
            if(pds.isInsertEnabled()){
                pds.insertInsertData(System.currentTimeMillis(), total, total/batch.size(), batch.size(), tableName+"Batch");
            }
        } finally {
            boolean retVal = batch.size() >= batchSize;
            batch.clear();
            return retVal;
        }
    }


    public static void setSimpleJdbcTemplate(SimpleJdbcTemplate template) {
        simpleJdbcTemplate = template;
    }

}
