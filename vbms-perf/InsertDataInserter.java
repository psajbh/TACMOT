package gov.va.vba.vbms.common.framework.performance.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

public class InsertDataInserter implements Runnable {
	private static Logger logger = LoggerFactory
			.getLogger(InsertDataInserter.class);// can't be final for JMockit

	private static final String INSERT_METHOD_QUERY = "INSERT INTO vbmsuiperf.InsertMetrics (startTime, totalTime, insertTime, filterTime, tableName) "
	+"VALUES ( ?, ?, ?, ?, ?)";
	
	private final long startTime;
	private final long totalTime;
	private final long insertTime;
	private final long filterTime;
	private final String tableName;
	private final JdbcTemplate template;

	public InsertDataInserter(final JdbcTemplate template,
			final long startTime, final long totalTime, final long insertTime, final long filterTime,
			final String tableName) {
		this.template = template;
		this.startTime = startTime;
		this.totalTime = totalTime;
		this.insertTime = insertTime;
		this.filterTime = filterTime;
		this.tableName = tableName;
	}

	@Override
	public void run() {
		try{
			template.update(INSERT_METHOD_QUERY, new Object[] {	new Timestamp(startTime),totalTime,insertTime,filterTime,tableName});
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Caught", e);
			}
		}
	}

}
