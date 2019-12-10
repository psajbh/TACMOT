package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.LinkedBlockingQueueAlmostSilentOffer;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.PerformanceMonitorAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CGunn
 *
 */
public final class PerformanceDataStoreJdbcDbImpl extends PerformanceDataStore{
	private static final String VALUE = "VALUE";
	private static final String NAME = "NAME";
	private static final String CAUGHT = "Caught";
	private static final String SYSTEM_PROPERY_PREFIX = "SystemAvailability|";
	private static final int OK_RESPONSE = 200;
    private static final int MAX_QUEUE_SIZE = 100000;
	private static final PerformanceDataStoreJdbcDbImpl SINGLETON = new PerformanceDataStoreJdbcDbImpl();
	private static Logger logger = LoggerFactory.getLogger(PerformanceDataStoreJdbcDbImpl.class);// can't be final for JMockit
    private static String saPinger = null;

    private final LinkedBlockingQueueAlmostSilentOffer<Object[]> queueMethods = new LinkedBlockingQueueAlmostSilentOffer<Object[]>(MAX_QUEUE_SIZE, "MethodMetrics");
    private final LinkedBlockingQueueAlmostSilentOffer<Object[]> queueAccess = new LinkedBlockingQueueAlmostSilentOffer<Object[]>(MAX_QUEUE_SIZE, "AccessMetrics");
    private final LinkedBlockingQueueAlmostSilentOffer<Object[]> queueActiveUsersInserts = new LinkedBlockingQueueAlmostSilentOffer<Object[]>(MAX_QUEUE_SIZE, "ActiveUserInserts");
    private final LinkedBlockingQueueAlmostSilentOffer<Object[]> queueActiveUsersUpdates = new LinkedBlockingQueueAlmostSilentOffer<Object[]>(MAX_QUEUE_SIZE, "ActiveUserUpdates");
    // this needs to be set to the number of queues above plus one  to be shared by the AvailabilityChecker and the PropertyChecker
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private ScheduledFuture<?> scheduleCheckAvailability = null;

    private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(MAX_QUEUE_SIZE);
	private final ThreadPoolExecutor executorService = //Executors.newFixedThreadPool(1);
			new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue, new AlmostSilentDiscardPolicy());
	

	private int iSystemAvailabilityCheckFrequency = 1;//minute
	private boolean activeUserEnabled = false;
	private boolean accessEnabled = false;
	private boolean methodEnabled = false;
	private boolean insertEnabled = false;
    private boolean batchEnabled = true;

	private JdbcTemplate template = null;
	
	private List<Map<String,Object>> services = null;
	private boolean initialized = false;
    private static final int ARRAY_SIZE = 8092;

	private PerformanceDataStoreJdbcDbImpl() {
	}

	public synchronized void init() {
		if (initialized) {
			return;
		}

		schedule(new PropertyChecker(), 1, 1, TimeUnit.MINUTES);
		schedule(new BatchUpdater(this,queueMethods,Queries.INSERT_METHOD_QUERY, "MethodMetrics"), 1, 1, TimeUnit.SECONDS);
		schedule(new BatchUpdater(this,queueAccess, Queries.INSERT_ACCESS_QUERY, "AccessMetrics"), 1, 1, TimeUnit.SECONDS);
		schedule(new BatchUpdater(this,queueActiveUsersInserts, Queries.INSERT_ACTIVE_USER_QUERY, "ActiveUserInserts"), 1, 1, TimeUnit.SECONDS);
		schedule(new BatchUpdater(this,queueActiveUsersUpdates, Queries.UPDATE_ACTIVE_USER_QUERY, "ActiveUserUpdates"), 1, 1, TimeUnit.SECONDS);
		scheduleAvailabilityCheck();

		enableMonitors();
		initialized = true;
	}

	private ScheduledFuture schedule(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
		return scheduler.scheduleAtFixedRate(runnable, initialDelay, period, unit);
	}

	private void enableMonitors() {
		activeUserEnabled = (null != template);
		accessEnabled = (null != template);
		methodEnabled = (null != template);
		// let's just wait and check the db properties for this one, assuming it will be off most of the time
		//		insertEnabled = (null != template);
		setupSystemAvailabilityCheck();
	}

	private void scheduleAvailabilityCheck() {
		if(null != scheduleCheckAvailability){
			scheduleCheckAvailability.cancel(false);
		}
		scheduleCheckAvailability = scheduler.scheduleAtFixedRate(new AvailabilityChecker(), iSystemAvailabilityCheckFrequency, iSystemAvailabilityCheckFrequency, TimeUnit.MINUTES);
	}

	public static PerformanceDataStoreJdbcDbImpl getInstance() {
		return SINGLETON;
	}

	public void setTemplate(JdbcTemplate jdbcTemplate){
		template = jdbcTemplate;
	}
	
	private void setupSystemAvailabilityCheck(){
		if(null != template){
			services = template.queryForList("SELECT NAME, VALUE FROM VBMSUIPERF.PROPERTIES WHERE NAME LIKE '" + SYSTEM_PROPERY_PREFIX + "%'");
			//remove prefix from names
			if(null != services){
				for(Map<String,Object> service : services){
					service.put(NAME, ((String)service.get(NAME)).substring(SYSTEM_PROPERY_PREFIX.length()));
				}
			}
		}
	}

    private synchronized void setPinger(){
        saPinger = getHost();
    }
	
	private void checkSystemAvailability(){

		try{ // must catch all exceptions to prevent schedule from being canceled
			String name;
			String value;
			int status;
			long startTime;
			long totalTime;

            if (saPinger == null){
                setPinger();
            }

			if(null != services && getHost().equals(saPinger)){
				for(Map<String,Object> service : services){
					try {
						name = (String)service.get(NAME);
						value = (String)service.get(VALUE);
						startTime = System.currentTimeMillis();
						status = getStatus(value);
						totalTime = System.currentTimeMillis() - startTime;
						template.update("INSERT INTO VBMSUIPERF.SYSTEMAVAILABILITY (STARTTIME, HOST, SYSTEM, AVAILABLE, TOTALTIME) VALUES (?,?,?,?,?)",
								new Date(startTime), getHost(), name, status, totalTime );
					} catch (DataAccessException e) {
						logger.warn(CAUGHT,e);
					}
				}
			}
		}catch(Exception e){
			logger.warn(CAUGHT,e);
		}
	}
	
	private int getStatus(String url) {
		int rtnVal = 0;
        try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();

			conn.setRequestMethod("GET");

            try {
                int responseCode = conn.getResponseCode();
                if (responseCode == OK_RESPONSE) {
                    rtnVal = 1;
                }
                InputStream is = conn.getInputStream();
                readAndCloseInputStream(is);

            } catch (IOException e) {
                logger.debug("Unexpected IOException Caught in availability checker",e);
                try {
                    InputStream es = ((HttpURLConnection)conn).getErrorStream();
                    // If there's an error, close the error input stream to ensure java knows we're done with the connection
                    if (es!=null){
                        readAndCloseInputStream(es);
                    }
                } catch(IOException ex) {
                    logger.debug("Unexpected IOException Caught in availability checker closing error stream",e);
                }
            }

		} catch (Exception e) {//NOSONAR
            logger.debug("Unexpected Exception Caught in availability checker",e);
		}
		return rtnVal;
	}

    void readAndCloseInputStream(InputStream is) throws IOException {
        byte[] buf = new byte[ARRAY_SIZE];
        // close the inputstream to ensure java knows we're done with the connection

	    int readStuff = is.read(buf);
        while (readStuff > 0) {
	        readStuff = is.read(buf);
        }
        is.close();
    }

    protected void checkProperties(){
		try{ // must catch all exceptions to prevent schedule from being canceled 
			if(null != template){
				checkEnabled();
				checkThreads();
				checkFrequency();
				checkRegex();
			}
		}catch(Exception e){
			logger.warn(CAUGHT,e);
		}
	}

  protected void checkFrequency() {
		// check to see if we should change the SystemAvailbility check frequency
		try{
			int interval = template.queryForInt("SELECT VALUE FROM VBMSUIPERF.PROPERTIES WHERE NAME='SystemAvailabilityCheckFrequencyInMinutes'");
			if(iSystemAvailabilityCheckFrequency != interval){
				logger.warn("Updating iSystemAvailabilityCheckFrequency to " + interval + " minute(s)");
				iSystemAvailabilityCheckFrequency = interval;
				scheduleAvailabilityCheck();
			}
		}catch(DataAccessException e){
			//logger.warn(CAUGHT,e);
		}
	}

	private void checkThreads() {
		// check to see if we should change the thread pool size
		try{
			int threads = template.queryForInt("SELECT VALUE FROM VBMSUIPERF.PROPERTIES WHERE NAME='ExcecutorServiceThreadPoolSize'");
			if(executorService.getCorePoolSize() != threads){
				logger.warn("Updating executorService threadpool to " + threads + " thread(s)");
				executorService.setCorePoolSize(threads);
				executorService.setMaximumPoolSize(threads);// keep in sync with core pool size, and change after core pool size in case this is a downward change.
			}
		}catch(DataAccessException e){
			//logger.warn(CAUGHT,e);
		}
	}

	private void checkEnabled() {
		// check to see if we should capture Method data
		try{
			methodEnabled = 1 == template.queryForInt("SELECT COUNT(1) FROM VBMSUIPERF.PROPERTIES WHERE NAME='MethodEnabled' AND VALUE='1'");
		}catch(DataAccessException e){
			logger.warn(CAUGHT,e);
		}
		// check to see if we should capture Access data
		try{
			accessEnabled = 1 == template.queryForInt("SELECT COUNT(1) FROM VBMSUIPERF.PROPERTIES WHERE NAME='AccessEnabled' AND VALUE='1'");
		}catch(DataAccessException e){
			logger.warn(CAUGHT,e);
		}
		// check to see if we should capture Active User data
		try{
			activeUserEnabled = 1 == template.queryForInt("SELECT COUNT(1) FROM VBMSUIPERF.PROPERTIES WHERE NAME='ActiveUserEnabled' AND VALUE='1'");
		}catch(DataAccessException e){
			logger.warn(CAUGHT,e);
		}
		// check to see if we should capture Insert data
		try{
			insertEnabled = 1 == template.queryForInt("SELECT COUNT(1) FROM VBMSUIPERF.PROPERTIES WHERE NAME='InsertEnabled' AND VALUE='1'");
		}catch(DataAccessException e){
			logger.warn(CAUGHT,e);
		}
        // check to see if we should batch inserts
        try{
            batchEnabled = 1 == template.queryForInt("SELECT COUNT(1) FROM VBMSUIPERF.PROPERTIES WHERE NAME='BatchInserts' AND VALUE='1'");
        }catch(DataAccessException e){
            logger.warn(CAUGHT,e);
        }
	}

	private void checkRegex() {
		// check to see if we should Discard data
		try{
			List<Map<String,Object>> list = template.queryForList("SELECT NAME, VALUE FROM VBMSUIPERF.PROPERTIES WHERE NAME LIKE 'Discard|%'");
			String name;
			String value;
			
			for(Map<String,Object> map : list){
				name = (String) map.get(NAME);
				value = (String) map.get(VALUE);
				if("Discard|AccessDataInserter|path|regex".equals(name)){
					AccessDataInserter.setPathRegex(value);
				}else if("Discard|AccessDataInserter|user|regex".equals(name)){
					AccessDataInserter.setUserRegex(value);
				}else if("Discard|AccessDataInserter|totalTime|below".equals(name)){
					AccessDataInserter.setTotalTimeMin(value);
					
				}else if("Discard|MethodDataInserter|className.methodName|regex".equals(name)){
					MethodDataInserter.setClassNameMethodNameRegex(value);
//                    MethodDataBatcher.setClassNameMethodNameRegex(value);
				}else if("Discard|MethodDataInserter|user|regex".equals(name)){
					MethodDataInserter.setUserRegex(value);
//                    MethodDataBatcher.setUserRegex(value);
				}else if("Discard|MethodDataInserter|totalTime|below".equals(name)){
					//MethodDataInserter.setTotalTimeMin(value);
					PerformanceMonitorAspect.setTotalTimeMin(value);
				}
			}
		}catch(DataAccessException e){
			//logger.warn(CAUGHT,e);
		}
	}
	
	@Override
	public boolean isActiveUserEnabled() {
		return activeUserEnabled;
	}

	@Override
	public boolean isAccessEnabled() {
		return accessEnabled;
	}

	@Override
	public boolean isMethodEnabled() {
		return methodEnabled;
	}

	@Override
	public boolean isInsertEnabled() {
		return insertEnabled;
	}

    @Override
    public boolean isBatchEnabled() {
        return batchEnabled;
    }

	@Override
	public void insertAccessData(final long startTime, final String path,
			final long totalTime, final long uploadTime,
			final long downloadTime, final long responseLength,
			final String remoteAddr, final long requestLength,
			final String userName, final String sessionId,
            final String userId) {
		executorService.submit(new AccessDataInserter(template, totalTime, responseLength, userName, remoteAddr, startTime,
				downloadTime, requestLength, sessionId, path, uploadTime, userId));

	}

	@Override
	public void insertMethodData(final long startTime, final long totalTime,
			final String className, final String methodName, final String user,
			final String exceptionType, final String exceptionMessage, final String methodSource, final String serverName,
            final long threadID, final String threadSessionId, final long dataSize, final String userId, final String parameters) {
        executorService.submit(new MethodDataInserter(template, startTime, totalTime, className, methodName, user,
                exceptionType, exceptionMessage, methodSource, serverName, threadID, threadSessionId, dataSize, userId, parameters));
	}

	@Override
	public void insertActiveUser(final long startTime, final String user, 
			final String sessionId, Map<String, String> profile){
		executorService.submit(new ActiveUserInserter(template, startTime, user, sessionId, profile));

	}

	@Override
	public void logout(final String user, final String sessionId, final long logoutTime){
		executorService.submit(new ActiveUserInserter(template, user, sessionId,logoutTime));
	}

	@Override
	public void insertInsertData(long startTime, long totalTime,
			long insertTime, long filterTime, String tableName) {
		executorService.submit(new InsertDataInserter(template, startTime, totalTime, insertTime, filterTime, tableName));
		
	}
    @Override
    public LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueMethods() {
        return queueMethods;
    }

    @Override
    public LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueAccess() {
        return queueAccess;
    }

    @Override
    public LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueActiveUsersInserts() {
        return queueActiveUsersInserts;
    }

    @Override
    public LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueActiveUsersUpdates() {
        return queueActiveUsersUpdates;
    }


    public class PropertyChecker implements Runnable {
		public void run() { 
			checkProperties(); 
		} 		
	}
	public class AvailabilityChecker implements Runnable {
		public void run() {
			checkSystemAvailability(); 
		}
	}
}
