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

public class MethodDataInserter implements Runnable {
	private static Logger logger = LoggerFactory
			.getLogger(MethodDataInserter.class);// can't be final for JMockit
	private static Pattern classNameMethodNameRegexPattern = null;
	private static String classNameMethodNameRegex = null;
	private static Pattern userRegexPattern = null;
	private static String userRegex = null;
//	private static long totalTimeMin = -1;
	private static PerformanceDataStore pds = gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory.getPeformanceDataStore();
    private static final LinkedBlockingQueueAlmostSilentOffer<Object[]> QUEUE = pds.getQueueMethods();

	private final long startTime;
	private final long totalTime;
	private final String className;
	private final String methodName;
	private final String user;
	private final String exceptionType;
	private final String exceptionMessage;
    private final String methodSource;
    private final String serverName;
    private final long threadID;
	private final JdbcTemplate template;
    private final String threadSessionId;
    private final long dataSize;
    private final String userId;
    private final String parameters;

    public MethodDataInserter(final JdbcTemplate template,
                              final long startTime, final long totalTime, final String className,
                              final String methodName, final String user,
                              final String exceptionType, final String exceptionMessage,
                              final String methodSource, final String serverName, final long threadID,
                              final String threadSessionId, final long dataSize, final String userId, final String parameters) {
		this.template = template;
		this.startTime = startTime;
		this.totalTime = totalTime;
		this.className = className;
		this.methodName = methodName;
		this.user = user;
		this.exceptionType = exceptionType;
		this.exceptionMessage = exceptionMessage;
        this.methodSource = methodSource;
        this.serverName = serverName;
        this.threadID = threadID;
        this.threadSessionId = threadSessionId;
        this.dataSize = dataSize;
        this.userId = userId;
        this.parameters = parameters;
	}

	@Override
	public void run() {
		long insert = 0;
        boolean batch = pds.isBatchEnabled();
		long start = System.nanoTime();

		try {
			if(checkFilters()){//had to refactor this to a method due to cyclomatic complexity
				insert = System.nanoTime();
                Object[] params = new Object[] {
                        new Timestamp(startTime),
                        totalTime,
                        Util.trimField(className, FieldSizes.CLASSNAME_FIELD_SIZE),
                        Util.trimField(methodName, FieldSizes.METHODNAME_FIELD_SIZE),
                        Util.trimField(user, FieldSizes.USERNAME_FIELD_SIZE),
                        Util.trimField(exceptionType, FieldSizes.EXCEPTIONTYPE_FIELD_SIZE),
                        Util.trimField(exceptionMessage, FieldSizes.EXCEPTIONMESSAGE_FIELD_SIZE),
                        PerformanceDataStore.getHost(),
                        Util.trimField(methodSource, FieldSizes.METHODSOURCE_FIELD_SIZE),
                        Util.trimField(serverName, FieldSizes.SERVERNAME_FIELD_SIZE),
                        threadID,
                        threadSessionId,
                        dataSize,
                        userId,
                        parameters};

                if(batch){
                    QUEUE.offerAlmostSilently(params);
                }else{
                    template.update(Queries.INSERT_METHOD_QUERY, params);
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
                        batch?"MethodMetricsBatchSingle":"MethodMetrics");
			}
		}
	}

	// returns false if a regular expression matches, to prevent this data from being inserted into the database
	private boolean checkFilters() {
		if(exceptionType == null){// don't allow regex exclusions if there was an exception
// this was moved to the PerformanceMonitorAspect to since this check is very quick and would reduce the number of items placed in the queue
//			if(totalTime < totalTimeMin){
//				return false;// discard
//			}
			if(classNameMethodNameRegexPattern != null && classNameMethodNameRegexPattern.matcher(className + "." + methodName).matches()){
				return false;// discard
			}
			if(userRegexPattern != null && userRegexPattern.matcher(user).matches()){
				return false;// discard
			}
		}
		return true;
	}

	public static void setClassNameMethodNameRegex(String regex) {
		if(!StringUtils.equals(regex, classNameMethodNameRegex)){
			classNameMethodNameRegex = regex;
			logger.info("setting classNameMethodNameRegex to " + regex);
			if(StringUtils.isBlank(regex)){
				classNameMethodNameRegexPattern = null;
			}else{
				try {
					classNameMethodNameRegexPattern = Pattern.compile(regex);
				} catch (PatternSyntaxException e) {
					logger.warn("Caught",e);
				}
			}
		}
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
}
