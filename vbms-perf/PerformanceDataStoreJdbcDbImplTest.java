package gov.va.vba.vbms.common.framework.performance.impl;

import mockit.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Ignore
public class PerformanceDataStoreJdbcDbImplTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;
	
	@Test
	public void testScheduleAvailabilityCheck() {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();

		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Deencapsulation.setField(dataStore, "scheduler", scheduler);
		final int iSystemAvailabilityCheckFrequency = 1;
		Deencapsulation.setField(dataStore, "iSystemAvailabilityCheckFrequency", iSystemAvailabilityCheckFrequency);
		
		Deencapsulation.invoke(dataStore, "scheduleAvailabilityCheck");
		Deencapsulation.invoke(dataStore, "scheduleAvailabilityCheck");
	}
	@Test
	public void testSetTemplate(final JdbcTemplate jdbcTemplate) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		dataStore.setTemplate(jdbcTemplate);
		JdbcTemplate result = Deencapsulation.getField(dataStore,"template");
		Assert.assertSame(jdbcTemplate, result);
	}
	
	@Test
	public void testSetTemplateNull() {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		dataStore.setTemplate(null);
		JdbcTemplate result = Deencapsulation.getField(dataStore,"template");
		Assert.assertNull(result);
	}
	
	//this can't be the first test in the class
	@Test
	public void testStaticInit(final InetAddress local) throws Throwable {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		new Expectations(){
			{
				InetAddress.getLocalHost(); result = new UnknownHostException("test");
			}
		};
		Deencapsulation.invoke(dataStore,"staticInit");
	}
	
	@Test
	public void testSetupSystemAvailabilityCheck(final JdbcTemplate jdbcTemplate){
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		String SYSTEM_PROPERY_PREFIX = Deencapsulation.getField(dataStore, "SYSTEM_PROPERY_PREFIX");
		
		final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		final Map<String,Object> row = new HashMap<String,Object>();
		final String serviceName = "serviceName";
		final String serviceValue = "serviceValue";
		row.put("NAME",SYSTEM_PROPERY_PREFIX+serviceName);
		row.put("VALUE",serviceValue);
		list.add(row);
		new Expectations(){
			{
				jdbcTemplate.queryForList(anyString);result = list;
			}
		};
		dataStore.setTemplate(jdbcTemplate);
		Deencapsulation.invoke(dataStore,"setupSystemAvailabilityCheck");
		List<Map<String,Object>> result = Deencapsulation.getField(dataStore, "services");
		Assert.assertEquals(serviceName,result.get(0).get("NAME"));
	}
	
	@Test
	public void testSetupSystemAvailabilityCheckNull(final JdbcTemplate jdbcTemplate){
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		String SYSTEM_PROPERY_PREFIX = Deencapsulation.getField(dataStore, "SYSTEM_PROPERY_PREFIX");
		
		final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		final Map<String,Object> row = new HashMap<String,Object>();
		final String serviceName = "serviceName";
		final String serviceValue = "serviceValue";
		row.put("NAME",SYSTEM_PROPERY_PREFIX+serviceName);
		row.put("VALUE",serviceValue);
		list.add(row);
		new Expectations(){
			{
				jdbcTemplate.queryForList(anyString);result = null;
			}
		};
		dataStore.setTemplate(jdbcTemplate);
		Deencapsulation.invoke(dataStore,"setupSystemAvailabilityCheck");
		List<Map<String,Object>> result = Deencapsulation.getField(dataStore, "services");
		Assert.assertNull(result);
	}
	
	@Test
	public void testCheckSystemAvailability(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		dataStore.setTemplate(jdbcTemplate);
		
		final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		final Map<String,Object> row = new HashMap<String,Object>();
		final String serviceName = "serviceName";
		final String serviceValue = "serviceValue";
		row.put("NAME",serviceName);
		row.put("VALUE",serviceValue);
		list.add(row);
		
		Deencapsulation.setField(dataStore, "services", list);
		
		new Expectations(dataStore){
			{
				invoke(dataStore,"getStatus",serviceValue);result = 1;
				jdbcTemplate.update(anyString, (Object[])any);
			}
		};
		Deencapsulation.invoke(dataStore, "checkSystemAvailability");
	}
	
	@Test
	public void testCheckSystemAvailabilityException(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		dataStore.setTemplate(jdbcTemplate);
		Deencapsulation.setField(dataStore, "logger", logger);
		
		final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		final Map<String,Object> row = new HashMap<String,Object>();
		final String serviceName = "serviceName";
		final String serviceValue = "serviceValue";
		row.put("NAME",serviceName);
		row.put("VALUE",serviceValue);
		list.add(row);
		
		Deencapsulation.setField(dataStore, "services", list);
		
		new Expectations(dataStore){
			{
				invoke(dataStore,"getStatus",serviceValue);result = 1;
				jdbcTemplate.update(anyString, (Object[])any); result = new Exception("testing");
			}
		};
		Deencapsulation.invoke(dataStore, "checkSystemAvailability");
	}
	
	@Test
	public void testCheckSystemAvailabilityNull(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "services", null);
		Deencapsulation.invoke(dataStore, "checkSystemAvailability");
	}
	
	@Test
	public void testGetStatus(final @Cascading HttpURLConnection connection, @Cascading URL rl) throws Throwable{
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final String url = "test";
		new NonStrictExpectations(dataStore){
			{
				new URL(url).openConnection();result = connection;
				connection.setRequestMethod("GET");
				connection.getResponseCode(); result = 200;
			}
		};
		Assert.assertEquals(1, Deencapsulation.invoke(dataStore, "getStatus", "test"));
	}
	
	@Test
	public void testGetStatus404(final @Cascading HttpURLConnection connection, @Cascading URL rl) throws Throwable{
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final String url = "test";
		new NonStrictExpectations(dataStore){
			{
				new URL(url).openConnection();result = connection;
				connection.setRequestMethod("GET");
				connection.getResponseCode(); result = 404;
			}
		};
		Assert.assertEquals(0, Deencapsulation.invoke(dataStore, "getStatus", "test"));
	}

    @Test
    public void testGetStatus_IOExceptionFromGetResponseCodeAndReadAndCloseInputStream(final @Cascading HttpURLConnection connection, @Cascading URL rl) throws Throwable{
        final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
        final String url = "test";
        final InputStream errorStream = new ByteArrayInputStream(new byte[0]);

        new Expectations(dataStore){
            {
                new URL(url).openConnection();result = connection;
                connection.setRequestMethod("GET");
                connection.getResponseCode(); result = new IOException();

                logger.debug(anyString, (Exception)any);

                connection.getErrorStream();
                result = errorStream;

                dataStore.readAndCloseInputStream(errorStream);
                result = new IOException();

                logger.debug(anyString, (Exception)any);
            }
        };
        Assert.assertEquals(0, Deencapsulation.invoke(dataStore, "getStatus", "test"));
    }

    @Test
    public void testGetStatus_NullErrorStream(final @Cascading HttpURLConnection connection, @Cascading URL rl) throws Throwable{
        final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
        final String url = "test";
        final InputStream errorStream = new ByteArrayInputStream(new byte[0]);

        new Expectations(dataStore){
            {
                new URL(url).openConnection();result = connection;
                connection.setRequestMethod("GET");
                connection.getResponseCode(); result = new IOException();

                logger.debug(anyString, (Exception)any);

                connection.getErrorStream();
                result = null;

                dataStore.readAndCloseInputStream(errorStream);
                times = 0;

                logger.debug(anyString, (Exception)any);
                times = 0;
            }
        };
        Assert.assertEquals(0, Deencapsulation.invoke(dataStore, "getStatus", "test"));
    }

    @Test
    public void testReadAndCloseInputStream(final @Cascading InputStream is) throws Throwable{
        final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
        final byte[] buf = new byte[8092];

        new Expectations(){
            {
                is.read(buf);
                result = 1;

                is.read(buf);
                result = 0;

                is.close();
            }
        };

        dataStore.readAndCloseInputStream(is);
    }
	
	@Test
	public void testGetStatusException(final @Cascading HttpURLConnection connection, @Cascading URL rl) throws Throwable{
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "logger", logger);
		final String url = "test";
		new Expectations(dataStore){
			{
				new URL(url).openConnection();result = new Exception("test");
			}
		};
		Assert.assertEquals(0, Deencapsulation.invoke(dataStore, "getStatus", "test"));
	}
	
	@Test
	public void testCheckSystemAvailabilityDataException(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		String SYSTEM_PROPERY_PREFIX = Deencapsulation.getField(dataStore, "SYSTEM_PROPERY_PREFIX");
		dataStore.setTemplate(jdbcTemplate);
		Deencapsulation.setField(dataStore, "logger", logger);
		
		final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		final Map<String,Object> row = new HashMap<String,Object>();
		final String serviceName = "serviceName";
		final String serviceValue = "serviceValue";
		row.put("NAME",serviceName);
		row.put("VALUE",serviceValue);
		list.add(row);
		
		Deencapsulation.setField(dataStore, "services", list);
		
		new Expectations(dataStore){
			{
				invoke(dataStore,"getStatus",serviceValue);result = 1;
				jdbcTemplate.update(anyString, (Object[])any); result = new DataRetrievalFailureException("testing");
			}
		};
		Deencapsulation.invoke(dataStore, "checkSystemAvailability");
	}
	
	@Test
	public void testInsertAccessData(final ExecutorService service,final JdbcTemplate jdbcTemplate) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10000);
		final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);
		Deencapsulation.setField(dataStore, "executorService",executorService);
		dataStore.setTemplate(jdbcTemplate);
		dataStore.insertAccessData(1, "/", 1, 1, 1, 1, "10.10.10.10", 1, "user", "fjdafasdflafd", "12345");
	}
	
	
	
	@Test
	public void testInsertMethodData(final ExecutorService service,final JdbcTemplate jdbcTemplate) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10000);
		final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);
		Deencapsulation.setField(dataStore, "executorService",executorService);
		dataStore.setTemplate(jdbcTemplate);
//        Deencapsulation.setField(dataStore,"batchEnabled",true);
//		dataStore.insertMethodData(1, 1, "sampleClassName", "sampleMethodName", "user", "type", "message");
//        Deencapsulation.setField(dataStore,"batchEnabled",false);
        dataStore.insertMethodData(1, 1, "sampleClassName", "sampleMethodName", "user", "type", "message", "methodSource", "serverName", 1, "sessionId", 1, "1234356", "params");
	}
	
	@Test
	public void testInsertActiveUser(final ExecutorService service, final JdbcTemplate jdbcTemplate, final Map<String, String> profile) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10000);
		final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);
		Deencapsulation.setField(dataStore, "executorService",executorService);
		dataStore.setTemplate(jdbcTemplate);
		dataStore.insertActiveUser(1, "user", "fjdafasdflafd", profile);
	}
	
	@Test
	public void testLogout(final ExecutorService service, final JdbcTemplate jdbcTemplate, final Map<String, String> profile) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10000);
		final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);
		Deencapsulation.setField(dataStore, "executorService",executorService);
		dataStore.setTemplate(jdbcTemplate);
		dataStore.logout("user", "fjdafasdflafd", 5682432);
	}
	
	@Test
	public void testInsertInsertData(final ExecutorService service,final JdbcTemplate jdbcTemplate) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10000);
		final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);
		Deencapsulation.setField(dataStore, "executorService",executorService);
		dataStore.setTemplate(jdbcTemplate);
		dataStore.insertInsertData(123456, 123, 120, 3, "testTable");
	}
	

	@Test
	public void testCheckPropertiesNew(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "logger", logger);
		dataStore.setTemplate(jdbcTemplate);
	
	    new NonStrictExpectations(dataStore){
			
			{	
				invoke(dataStore,"checkEnabled");
				invoke(dataStore,"checkThreads");
				invoke(dataStore,"checkFrequency");
				invoke(dataStore,"checkRegex");
			
			}
		};
		dataStore.checkProperties();
	}
	@Test
	public void testCheckPropertiesException(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "logger", logger);
		dataStore.setTemplate(jdbcTemplate);
		
		new Expectations(dataStore){
			{
				invoke(dataStore,"checkEnabled");result = new Exception("testing");
			}
		};
		//Deencapsulation.invoke(dataStore,"checkProperties");
		dataStore.checkProperties();
	}
	
	@Test
	public void testCheckPropertiesNullTemplate(){
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		dataStore.setTemplate(null);
	//	Deencapsulation.invoke(dataStore,"checkProperties");
		dataStore.checkProperties();
	}
	
	@Test
	public void testCheckFrequency(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "logger", logger);
		dataStore.setTemplate(jdbcTemplate);
	
		new Expectations(dataStore){
			{
				jdbcTemplate.queryForInt(anyString); result = 1;
				jdbcTemplate.queryForInt(anyString); result = 0;
//				logger.warn(anyString);
				invoke(dataStore,"scheduleAvailabilityCheck");
				jdbcTemplate.queryForInt(anyString); result = new EmptyResultDataAccessException(1);
			}
		};
        dataStore.checkFrequency();
        dataStore.checkFrequency();
        dataStore.checkFrequency();

	}
	
	@Test
	public void testCheckThreads(final JdbcTemplate jdbcTemplate, final ThreadPoolExecutor executorService){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "executorService",executorService);
		Deencapsulation.setField(dataStore, "logger", logger);
		dataStore.setTemplate(jdbcTemplate);
	
		new Expectations(dataStore){
			{
				jdbcTemplate.queryForInt(anyString); result = 1;
				executorService.getCorePoolSize(); result = 1;
				jdbcTemplate.queryForInt(anyString); result = 2;
				executorService.getCorePoolSize(); result = 1;
				logger.warn(anyString);
				executorService.setCorePoolSize(2);
				executorService.setMaximumPoolSize(2);
				jdbcTemplate.queryForInt(anyString); result = new DataRetrievalFailureException("test");
			}
		};
		Deencapsulation.invoke(dataStore,"checkThreads");
		Deencapsulation.invoke(dataStore,"checkThreads");
		Deencapsulation.invoke(dataStore,"checkThreads");
	}
	
	@Test
	public void testCheckEnabled(final JdbcTemplate jdbcTemplate){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "logger", logger);
		dataStore.setTemplate(jdbcTemplate);
		
		new Expectations(dataStore){
			{
				//first run
				jdbcTemplate.queryForInt(anyString); result = 1;
				jdbcTemplate.queryForInt(anyString); result = 1;
				jdbcTemplate.queryForInt(anyString); result = 1;
				jdbcTemplate.queryForInt(anyString); result = 1;
                jdbcTemplate.queryForInt(anyString); result = 1;
				//second run
				jdbcTemplate.queryForInt(anyString); result = 0;
				jdbcTemplate.queryForInt(anyString); result = 0;
				jdbcTemplate.queryForInt(anyString); result = 0;
				jdbcTemplate.queryForInt(anyString); result = 0;
                jdbcTemplate.queryForInt(anyString); result = 0;
				//third run
				jdbcTemplate.queryForInt(anyString); result = new EmptyResultDataAccessException(1);
				logger.warn(anyString,(Throwable)any);
				jdbcTemplate.queryForInt(anyString); result = new EmptyResultDataAccessException(1);
				logger.warn(anyString,(Throwable)any);
				jdbcTemplate.queryForInt(anyString); result = new EmptyResultDataAccessException(1);
				logger.warn(anyString,(Throwable)any);
				jdbcTemplate.queryForInt(anyString); result = new EmptyResultDataAccessException(1);
				logger.warn(anyString,(Throwable)any);
                jdbcTemplate.queryForInt(anyString); result = new EmptyResultDataAccessException(1);
                logger.warn(anyString,(Throwable)any);
			}
		};
		Deencapsulation.invoke(dataStore,"checkEnabled");
		Deencapsulation.invoke(dataStore,"checkEnabled");
		Deencapsulation.invoke(dataStore,"checkEnabled");
	}
	
	@Test
	public void testCheckRegex(final JdbcTemplate jdbcTemplate, @Mocked final Map<String,Object> map){
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore, "logger", logger);
		dataStore.setTemplate(jdbcTemplate);
		final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		new Expectations(){
			{
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "Discard|AccessDataInserter|path|regex";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "Discard|AccessDataInserter|user|regex";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "Discard|AccessDataInserter|totalTime|below";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "Discard|MethodDataInserter|className.methodName|regex";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "Discard|MethodDataInserter|user|regex";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = list;
				map.get("NAME");result = "Discard|MethodDataInserter|totalTime|below";
				map.get("VALUE");result = "";
				
				jdbcTemplate.queryForList(anyString); result = new EmptyResultDataAccessException(1);
			}
		};
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
		Deencapsulation.invoke(dataStore,"checkRegex");
	}
	
	@Test
	public void testEnabled(final JdbcTemplate jdbcTemplate){
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore,"batchEnabled",true);
		dataStore.setTemplate(jdbcTemplate);
		Deencapsulation.invoke(dataStore,"enableMonitors");
		Assert.assertTrue(dataStore.isActiveUserEnabled());
		Assert.assertTrue(dataStore.isAccessEnabled());
		Assert.assertTrue(dataStore.isMethodEnabled());
		Assert.assertFalse(dataStore.isInsertEnabled());
        Assert.assertTrue(dataStore.isBatchEnabled());
	}
	
	@Test
	public void testPropertyChecker(){
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		PerformanceDataStoreJdbcDbImpl.PropertyChecker pc = dataStore.new PropertyChecker();
		pc.run();
	}
	
	@Test
	public void testAvailabilityChecker(){
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		PerformanceDataStoreJdbcDbImpl.AvailabilityChecker ac = dataStore.new AvailabilityChecker();
		ac.run();
	}

	@Test
	public void testInit_Initialized_DoesNothing(final ScheduledExecutorService scheduler) {
		PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore,"initialized",true);
		dataStore.init();
	}

	@Test
	public void testInit_HappyPath() {
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Deencapsulation.setField(dataStore,"initialized",false);

		new Expectations(dataStore) {
			{
				invoke(dataStore,"schedule",withAny(Runnable.class), anyLong, anyLong, withAny(TimeUnit.class));
				invoke(dataStore,"schedule",withAny(Runnable.class), anyLong, anyLong, withAny(TimeUnit.class));
				invoke(dataStore,"schedule",withAny(Runnable.class), anyLong, anyLong, withAny(TimeUnit.class));
				invoke(dataStore,"schedule",withAny(Runnable.class), anyLong, anyLong, withAny(TimeUnit.class));
				invoke(dataStore,"schedule",withAny(Runnable.class), anyLong, anyLong, withAny(TimeUnit.class));
				invoke(dataStore,"scheduleAvailabilityCheck");
				invoke(dataStore,"enableMonitors");

			}
		};
		dataStore.init();
		Assert.assertTrue((Boolean)Deencapsulation.getField(dataStore,"initialized"));
	}

	@Test
	public void testSchedule_HappyPath() {
		final PerformanceDataStoreJdbcDbImpl dataStore = PerformanceDataStoreJdbcDbImpl.getInstance();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
			}
		};
		ScheduledFuture scheduledFuture = Deencapsulation.invoke(dataStore,"schedule",runnable,1L,1L,TimeUnit.MINUTES);
		scheduledFuture.cancel(true); // We don't want the scheduler to run while testing.
	}
}
