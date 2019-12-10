package gov.va.vba.vbms.perf.data.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.AbstractMap;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import gov.va.vba.vbms.framework.performance.impl.FieldSizes;
//import gov.va.vba.vbms.framework.performance.impl.Util;
import gov.va.vba.vbms.common.framework.performance.impl.FieldSizes;
import gov.va.vba.vbms.common.framework.performance.impl.Util;
import gov.va.vba.vbms.perf.data.DBProperties;
import gov.va.vba.vbms.perf.data.PerformanceDataQueryJdbcDb;
import gov.va.vba.vbms.perf.data.PropertyManager;
import gov.va.vba.vbms.perf.extractor.GenericDTOResultSetExtractor.DTOTypes;
import gov.va.vba.vbms.perf.mapper.GenericDTORowMapper;
import gov.va.vba.vbms.perf.model.OverallPropertyDTO;
import gov.va.vba.vbms.perf.model.OverallStatusDTO;
import gov.va.vba.vbms.perf.model.PropertyDTO;
import gov.va.vba.vbms.perf.sql.SqlQueries;
import gov.va.vba.vbms.perf.utils.Common;
import gov.va.vba.vbms.perf.utils.PerformanceDataQueryFactory;
//import gov.va.vba.vbms.perf.utils.PerformanceDataUtil;

public final class PropertyManagerImpl extends PropertyManager{
	//private static final String Common.FOURHUNDREDFIFTYTHOUSAND = "450000";
	//private static final String Common.FOURHUNDREDTHOUSAND = "400000";
//	private static final String Common.FIVEHUNDREDTHOUSAND = "500000";
//	private static final long Common.SIXTY_LONG = 60L;
//	private static final long Common.HOUR_IN_MILLIS = Common.SIXTY_LONG*Common.SIXTY_LONG*1000L;
//	private static final long Common.FIFTEEN_LONG = 15L;
//	private static final int FIFTY = 50;
	private static Logger logger = LoggerFactory.getLogger(PropertyManagerImpl.class);
	private static final PropertyManagerImpl SINGLETON = new PropertyManagerImpl();
	private static JdbcTemplate template = null;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static boolean checkForNewProperties = true;
	private static OverallStatusDTO noStatus = new OverallStatusDTO();
	private static OverallStatusDTO overallStatus = noStatus;
	private static AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(Common.FIFTY);  
	private static PerformanceDataQueryJdbcDb performanceDataQuery = PerformanceDataQueryFactory.getPeformanceDataQuery();
	private static final String ACCESSMETRICS = "AccessMetrics";
	private static final String ACTIVEUSER = "ActiveUser";
	private static final String INSERTMETRICS = "InsertMetrics";
	private static final String METHODMETRICS = "MethodMetrics";
	private static final String SYSAVAIL = "SystemAvailability";
	private static String host = null;
	private static boolean bHost = false;
//	private static boolean developer = false;
	
	static{
	    staticInit();
	}
	
	private PropertyManagerImpl() {
		scheduler.scheduleAtFixedRate(new PropertyChecker(), Common.FIFTEEN_LONG, Common.SIXTY_LONG, TimeUnit.SECONDS);
		DateTime nextHour = new DateTime().withFieldAdded(DurationFieldType.hours(), 1).withField(DateTimeFieldType.minuteOfHour(), 1).withField(DateTimeFieldType.secondOfMinute(), 0);
		scheduler.scheduleAtFixedRate(new MetricsSummarizer(), nextHour.getMillis() - System.currentTimeMillis() , Common.HOUR_IN_MILLIS, TimeUnit.MILLISECONDS);// summarize data every hour at 1 minute after the hour
	}

	public static PropertyManagerImpl getInstance() {
		return SINGLETON;
	}
	
	public static synchronized void setTemplate(JdbcTemplate jdbcTemplate) {
		template = jdbcTemplate;
		if (checkForNewProperties){
			setNewProperties();
		}
	}
	
	public static synchronized OverallStatusDTO getOverallStatus(){
		logger.debug("get OverallStatus = " + overallStatus.toString());
		if (getPropertyInt(DBProperties.MANUALPURGE) > 0){
			overallStatus.setAccessMetrics(template.queryForInt(SqlQueries.COUNTERSQL+"AccessMetrics"));
			overallStatus.setInsertMetrics(template.queryForInt(SqlQueries.COUNTERSQL+"InsertMetrics"));
			overallStatus.setMethodMetrics(template.queryForInt(SqlQueries.COUNTERSQL+"MethodMetrics"));
			overallStatus.setActiveUser(template.queryForInt(SqlQueries.COUNTERSQL+"ActiveUser"));
			overallStatus.setSystemAvailability(template.queryForInt(SqlQueries.COUNTERSQL+"SystemAvailability"));
		}
		return overallStatus;
	}

	private static synchronized void setOverallStatus(OverallStatusDTO updateData, int deletes){
		updateData.setRecentDeletes(deletes);
		overallStatus = updateData;
		if (deletes == 0){
			checkToAutoPurge();
			checkActiveUserOrphans();
		}
		else {
			// look at effect of manual purge
			logger.debug("post manualPurge propertydata = " + overallStatus.toString());
		}
	}

	//method called via scheduler.
	private static void setOverallStatus(OverallStatusDTO updateData){
		setOverallStatus(updateData,0);
	}
	
	private static void checkActiveUserOrphans(){
		template.update(SqlQueries.ADOPT_ACTIVEUSER_ORPHANS_PART1);
		template.update(SqlQueries.ADOPT_ACTIVEUSER_ORPHANS_PART2);
	}
	
	private static void checkToAutoPurge(){
		if (!bHost){
			return;
		}
		if(getPropertyInt(DBProperties.AUTO_PURGE)==1){
			checkIndividualAutoPurge(DBProperties.MAX_ACTIVE_USER, overallStatus.getActiveUser(), ACTIVEUSER);
			checkIndividualAutoPurge(DBProperties.MAX_METHOD_METRICS, overallStatus.getMethodMetrics(), METHODMETRICS);
			checkIndividualAutoPurge(DBProperties.MAX_ACCESS_METRICS, overallStatus.getAccessMetrics(), ACCESSMETRICS);
			checkIndividualAutoPurge(DBProperties.MAX_SYS_AVAILABILITY, overallStatus.getSystemAvailability(), SYSAVAIL);
			checkIndividualAutoPurge(DBProperties.MAX_INSERT_METRICS, overallStatus.getInsertMetrics(), INSERTMETRICS);
		}
	}

	private static void checkIndividualAutoPurge(String propertyName, int currentTableSize, String tableName) {
		int max = getPropertyInt(propertyName);
		if (max != -1 && currentTableSize >= max){
			logger.debug("need to purge "+ tableName);
			purge(tableName);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static synchronized void manualPurge(String table, int records){
		int i = -1;
		if (ACCESSMETRICS.equals(table)){
			i = performanceDataQuery.purgeAccessMetrics(records);
		}
		else if (ACTIVEUSER.equals(table)){
			i = performanceDataQuery.purgeActiveUserMetrics(records);
		}
		else if (INSERTMETRICS.equals(table)){
			i = performanceDataQuery.purgeInsertMetrics(records);
		}
		else if (METHODMETRICS.equals(table)){
			i = performanceDataQuery.purgeMethodMetrics(records);
		}
		else if (SYSAVAIL.equals(table)){
			i = performanceDataQuery.purgeSystemAvailability(records);
		}
		if (i > -1){
		    List<OverallStatusDTO> data = template.query(SqlQueries.GET_OVERALL_STATUS,
					new GenericDTORowMapper(DTOTypes.OverallStatusDTO));
		    setOverallStatus(data.get(0), i);
		}
		logger.debug("manually purged " + i + " records from table: " + table);
	}
	
	private static void purge(String table){
		int i = -1;
		if (ACCESSMETRICS.equals(table)){
			i = performanceDataQuery.purgeAccessMetrics(getPropertyInt(DBProperties.MAX_ACCESS_YELLOW_TARGET));
		}
		else if (ACTIVEUSER.equals(table)){
			i = performanceDataQuery.purgeActiveUserMetrics(getPropertyInt(DBProperties.MAX_ACTIVE_USER_YELLOW_TARGET));
		}
		else if (INSERTMETRICS.equals(table)){
			i = performanceDataQuery.purgeInsertMetrics(getPropertyInt(DBProperties.MAX_INSERT_YELLOW_TARGET));
		}
		else if (METHODMETRICS.equals(table)){
			i = performanceDataQuery.purgeMethodMetrics(getPropertyInt(DBProperties.MAX_METHOD_YELLOW_TARGET));
		}
		else if (SYSAVAIL.equals(table)){
			i = performanceDataQuery.purgeSystemAvailability(getPropertyInt(DBProperties.MAX_SYS_AVAIL_YELLOW_TARGET));
		}
		logger.debug("auto purge deleted " + i + " records from " + table);
	}
	
	private static void setNewProperties(){
		checkForNewProperties = false;
		for(String[] s : NEWPROPS){
			String property = s[0];
			String value = s[1];
			
			if (0 == template.queryForInt(SqlQueries.PROPERTY_EXISTS, new Object[]{property})){
				template.update(SqlQueries.INSERT_NEW_PROPERTY, new Object[]{property,value});
			}
		}
	}
	
	public static OverallPropertyDTO getOverallProperties(){
		OverallPropertyDTO opDTO = new OverallPropertyDTO();
		opDTO.setTargetDays(getPropertyInt(DBProperties.TARGETDAYS));
		opDTO.setDataDays(getPropertyInt(DBProperties.DATADAYS));
		opDTO.setCurrentExceptionPeriod(getPropertyInt(DBProperties.CURRENT_EXCEPTION_PERIOD));
		opDTO.setMaxAccessMetrics(getPropertyInt(DBProperties.MAX_ACCESS_METRICS));
		opDTO.setMaxAccessYellowTarget(getPropertyInt(DBProperties.MAX_ACCESS_YELLOW_TARGET));
		opDTO.setMaxAccessRedTarget(getPropertyInt(DBProperties.MAX_ACCESS_RED_TARGET));
		opDTO.setMaxSysAvailability(getPropertyInt(DBProperties.MAX_SYS_AVAILABILITY));
		opDTO.setMaxSysAvailYellowTarget(getPropertyInt(DBProperties.MAX_SYS_AVAIL_YELLOW_TARGET));
		opDTO.setMaxSysAvailRedTarget(getPropertyInt(DBProperties.MAX_SYS_AVAIL_RED_TARGET));
		opDTO.setMaxMethodMetrics(getPropertyInt(DBProperties.MAX_METHOD_METRICS));
		opDTO.setMaxMethodYellowTarget(getPropertyInt(DBProperties.MAX_METHOD_YELLOW_TARGET));
		opDTO.setMaxMethodRedTarget(getPropertyInt(DBProperties.MAX_METHOD_RED_TARGET));
		opDTO.setMaxActiveUser(getPropertyInt(DBProperties.MAX_ACTIVE_USER));
		opDTO.setMaxActiveUserYellowTarget(getPropertyInt(DBProperties.MAX_ACTIVE_USER_YELLOW_TARGET));
		opDTO.setMaxActiveUserRedTarget(getPropertyInt(DBProperties.MAX_ACTIVE_USER_RED_TARGET));
		opDTO.setAutoPurge(getPropertyInt(DBProperties.AUTO_PURGE));
		opDTO.setMaxInsertMetrics(getPropertyInt(DBProperties.MAX_INSERT_METRICS));
		opDTO.setMaxInsertYellowTarget(getPropertyInt(DBProperties.MAX_INSERT_YELLOW_TARGET));
		opDTO.setMaxInsertRedTarget(getPropertyInt(DBProperties.MAX_INSERT_RED_TARGET));
		opDTO.setManualPurge(getPropertyInt(DBProperties.MANUALPURGE));
		
		return opDTO;
	}
	
	public static int getPropertyInt(String name){
		String value = overallPropertyMap.get(name);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			logger.warn("Caught NumberFormatException inside getPropertyInt() with name: " + name + " value: " + value + ", returning -1;");
			return -1;
		}
	}
	public static String getProperty(String name){
		return overallPropertyMap.get(name);
	}
	
	/*
	 *  this call will generally be done as part of properties editing
	 */
	public static void setProperty(String name, String value){
		overallPropertyMap.put(name, value);
	}
	
	/**
	 *  set initial property values to be maintained by class.
	 *  also can be reset by changes to ui Property Editor
	 */
	@SuppressWarnings("unchecked")
	public static void setOverallProperties(){
		List<PropertyDTO> props = template.query(SqlQueries.OVERALLPROPERTY, 
				new GenericDTORowMapper(DTOTypes.PropertyDTO));
		Iterator<PropertyDTO> it = props.iterator();
		while (it.hasNext()){
			PropertyDTO propertyDTO = it.next();
			overallPropertyMap.put(propertyDTO.getName(),propertyDTO.getValue());	 
		}
	}
	
	private static final String[][] NEWPROPS = {
		{DBProperties.TARGETDAYS,"7"},
		{DBProperties.DATADAYS,"3"},
		{DBProperties.CURRENT_EXCEPTION_PERIOD,"24"},
		{DBProperties.MAX_ACCESS_METRICS,Common.FIVEHUNDREDTHOUSAND},
		{DBProperties.MAX_ACCESS_YELLOW_TARGET,Common.FOURHUNDREDTHOUSAND},
		{DBProperties.MAX_ACCESS_RED_TARGET,Common.FOURHUNDREDFIFTYTHOUSAND},
		{DBProperties.MAX_SYS_AVAILABILITY,Common.FIVEHUNDREDTHOUSAND},
		{DBProperties.MAX_SYS_AVAIL_YELLOW_TARGET,Common.FOURHUNDREDTHOUSAND},
		{DBProperties.MAX_SYS_AVAIL_RED_TARGET,Common.FOURHUNDREDFIFTYTHOUSAND},
		{DBProperties.MAX_METHOD_METRICS,Common.FIVEHUNDREDTHOUSAND},
		{DBProperties.MAX_METHOD_YELLOW_TARGET,Common.FOURHUNDREDTHOUSAND},
		{DBProperties.MAX_METHOD_RED_TARGET,Common.FOURHUNDREDFIFTYTHOUSAND},
		{DBProperties.MAX_ACTIVE_USER,Common.FIVEHUNDREDTHOUSAND},
		{DBProperties.MAX_ACTIVE_USER_YELLOW_TARGET,Common.FOURHUNDREDTHOUSAND},
		{DBProperties.MAX_ACTIVE_USER_RED_TARGET,Common.FOURHUNDREDFIFTYTHOUSAND},
		{DBProperties.PROPERTY_CHECK_INTERVAL,"1"},
		{DBProperties.AUTO_PURGE,"0"},// default to not auto purging
		{DBProperties.SUMMARIZE_AND_PURGE_HOST,"This property should hold the host name of the server that will summarize and possibly purge data, if you want summarizing or purging enabled. The value to put here can be found on the Current Status tab in the Ops Support Hosting server field"},
		{DBProperties.MAX_INSERT_METRICS,Common.FIVEHUNDREDTHOUSAND},
		{DBProperties.MAX_INSERT_YELLOW_TARGET,Common.FOURHUNDREDTHOUSAND},
		{DBProperties.MAX_INSERT_RED_TARGET,Common.FOURHUNDREDFIFTYTHOUSAND},
		{DBProperties.STATUSMSGADDRESSLIST," "},
		{DBProperties.STATUSTESTEMAIL,"0"},
		{DBProperties.DATAISSUEMSGADDRESSLIST," "},
		{DBProperties.ENABLEEMAIL,"0"},
		{DBProperties.EMAILSENDER,"OpsSupport@vbms.com"},
		{DBProperties.MANUALPURGE,"0"},
        {DBProperties.BATCHINSERTS,"1"}
	};

	private static void staticInit() {
		try {
	    	InetAddress local = InetAddress.getLocalHost();
			host = Util.trimField(local.getCanonicalHostName() + " " + local.getHostAddress(),FieldSizes.HOST_FIELD_SIZE);
		} catch (UnknownHostException e) {
			host = "unknown";
		}
	}

	public static synchronized String getHost() {
		return host;
	}

	static class PropertyChecker implements Runnable{

		@SuppressWarnings("unchecked")
		public void run(){
			try {
				if(1 == template.queryForInt(SqlQueries.HOST_CHECK, new Object[]{host})){
					bHost = true;
				    List<OverallStatusDTO> data = template.query(SqlQueries.GET_OVERALL_STATUS,
							new GenericDTORowMapper(DTOTypes.OverallStatusDTO));
				    if (data.size()>0){
				       setOverallStatus(data.get(0));
				    }
				    setOverallProperties();
				}else{
					bHost = false;
					overallStatus = noStatus;
					overallPropertyMap.clear();
				}
			}
			catch(Exception e){//NOSONAR
				logger.warn("PropertyChecker.run() method exception: " + e.getMessage());
			}
		}
	}
	
	static class MetricsSummarizer implements Runnable{
		public void run(){
			try {
				if (!bHost){
					return;
				}
				Timestamp hour = new Timestamp(System.currentTimeMillis());// this will be floor'd to zero minutes, seconds and millis by the summarize calls
				logger.info("About to summarize all tables hour="+hour);
				performanceDataQuery.summarizeAccessMetrics(hour);
				performanceDataQuery.summarizeActiveUserMetrics(hour);
				performanceDataQuery.summarizeInsertMetrics(hour);
				performanceDataQuery.summarizeMethodMetrics(hour);
				performanceDataQuery.summarizeSystemAvailability(hour);
				logger.info("Done with summaries");
			}
			catch(Exception e){//NOSONAR
				logger.warn("MetricsSummarizer.run() method exception: " + e.getMessage());
			}
		}
	}
	
}
