package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.LinkedBlockingQueueAlmostSilentOffer;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AccessDataInserter implements Runnable {
	private static Logger logger = LoggerFactory
			.getLogger(AccessDataInserter.class);// can't be final for JMockit
	private static Pattern pathRegexPattern = null;
	private static String pathRegex = null;
	private static Pattern userRegexPattern = null;
	private static String userRegex = null;
	private static long totalTimeMin = -1;
    private static PerformanceDataStore pds = gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory.getPeformanceDataStore();
    private static LinkedBlockingQueueAlmostSilentOffer<Object[]> queue = pds.getQueueAccess();// can't be final for JMockit
	
	private final long totalTime;
	private final long responseLength;
	private final String userName;
	private final String userId;
	private final String remoteAddr;
	private final long startTime;
	private final long downloadTime;
	private final long requestLength;
	private final String sessionId;
	private final String path;
	private final long uploadTime;
	private final JdbcTemplate template;

	public AccessDataInserter(final JdbcTemplate template, long totalTime,
			long responseLength, String userName, String remoteAddr,
			long startTime, long downloadTime, long requestLength,
			String sessionId, String path, long uploadTime, String userId) {
		this.template = template;
		this.totalTime = totalTime;
		this.responseLength = responseLength;
		this.userName = userName;
		this.userId = userId;
		this.remoteAddr = remoteAddr;
		this.startTime = startTime;
		this.downloadTime = downloadTime;
		this.requestLength = requestLength;
		this.sessionId = sessionId;
		this.path = path;
		this.uploadTime = uploadTime;
	}

	@Override
	public void run() {
		long insert = 0;
        boolean batch = pds.isBatchEnabled();
		long start = System.nanoTime();
		try {
            if(checkFilters()){//had to refactor this to a method due to cyclomatic complexity
                insert = System.nanoTime();
                Object[] params = new Object[] { new Timestamp(startTime),
                        Util.trimField(path, FieldSizes.PATH_FIELD_SIZE), totalTime,
                        uploadTime, downloadTime, responseLength,
                        remoteAddr, requestLength, userName, sessionId,
                        PerformanceDataStore.getHost(), userId };
                if(batch){
                    queue.offerAlmostSilently(params);
                }else{
                    template.update(Queries.INSERT_ACCESS_QUERY, params);
                }
            }
		} catch (Exception e) {
			logger.debug("Caught", e);
		}
		finally{
			long end = System.nanoTime();
			if(pds.isInsertEnabled()){
				long total = end - start;
				if(insert != 0){
					insert = end - insert;
				}
				pds.insertInsertData(System.currentTimeMillis(), total, insert, total-insert,
                        batch?"AccessMetricsBatchSingle":"AccessMetrics");
			}
		}
	}

    // returns false if a regular expression matches, to prevent this data from being inserted into the database
    private boolean checkFilters() {
        if(totalTimeMin != -1 && totalTime < totalTimeMin){
            return false;// discard
        }
        if(userRegexPattern != null && userRegexPattern.matcher(userName).matches()){
            return false;// discard
        }
        if(pathRegexPattern != null && pathRegexPattern.matcher(path).matches()){
            return false;// discard
        }
        return true;
    }


	public static void setUserRegex(String regex) {
		if(!StringUtils.equals(regex, userRegex)){
			userRegex = regex;
			logger.info("setting userRegex to " + regex);
			if(StringUtils.isBlank(regex)){
				userRegexPattern = null;
			}else{
				try {
					userRegexPattern = Pattern.compile(regex);
				} catch (PatternSyntaxException e) {
					logger.warn("Caught",e);
				}
			}
		}
	}
	
	public static void setPathRegex(String regex) {
		if(!StringUtils.equals(regex, pathRegex)){
			pathRegex = regex;
			logger.info("setting pathRegex to " + regex);
			if(StringUtils.isBlank(regex)){
				pathRegexPattern = null;
			}else{
				try {
					pathRegexPattern = Pattern.compile(regex);
				} catch (PatternSyntaxException e) {
					logger.warn("Caught",e);
				}
			}
		}
	}
	
	public static void setTotalTimeMin(String min) {
		long temp;
		try {
			temp = Long.parseLong(min);
		} catch (NumberFormatException e) {
			temp = -1;
		}
		if(temp != totalTimeMin){
			totalTimeMin = temp;
			logger.info("setting totalTimeMin to" + temp);
		}
	}
}
