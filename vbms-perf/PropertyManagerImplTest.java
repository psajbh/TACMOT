package gov.va.vba.vbms.perf.data.impl;

import gov.va.vba.vbms.common.framework.performance.impl.PerformanceDataStoreJdbcDbImpl;
import gov.va.vba.vbms.perf.data.DBProperties;
import gov.va.vba.vbms.perf.data.PerformanceDataQueryJdbcDb;
import gov.va.vba.vbms.perf.data.impl.PropertyManagerImpl.PropertyChecker;
import gov.va.vba.vbms.perf.data.impl.PropertyManagerImpl.MetricsSummarizer;
import gov.va.vba.vbms.perf.mapper.GenericDTORowMapper;
import gov.va.vba.vbms.perf.model.OverallPropertyDTO;
import gov.va.vba.vbms.perf.model.OverallStatusDTO;
import gov.va.vba.vbms.perf.model.PropertyDTO;
import gov.va.vba.vbms.perf.model.VbmsStatusDTO;
import gov.va.vba.vbms.perf.sql.SqlQueries;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.NonStrictExpectations;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class PropertyManagerImplTest {

	@Test
	public void testPropertyManagerImpl(final ScheduledExecutorService scheduler, 
			final PropertyChecker pc) {
		final long a = 1L;
		final long b = 1L;
		new NonStrictExpectations(){
			{
				scheduler.scheduleAtFixedRate(pc, a, b, (TimeUnit)any);
			}
		};
		Deencapsulation.newInstance(PropertyManagerImpl.class);
	}

	//this can't be the first test in the class
	@Test
	public void testStaticInit() throws Throwable {
		PropertyManagerImpl pmi = PropertyManagerImpl.getInstance();
		Deencapsulation.invoke(pmi,"staticInit");
	}
	//this can't be the first test in the class
	@Test
	public void testStaticInitException(final InetAddress local) throws Throwable {
		PropertyManagerImpl pmi = PropertyManagerImpl.getInstance();
		new Expectations(){
			{
				InetAddress.getLocalHost(); result = new UnknownHostException("test");
			}
		};
		Deencapsulation.invoke(pmi,"staticInit");
	}
	
	@Test
	public void testGetInstance(final JdbcTemplate jdbcTemplate, final OverallStatusDTO osdto){
		final int deletes = 1;
		PropertyManagerImpl.getInstance();
//		PropertyManagerImpl.setTargetHost("test");
		PropertyManagerImpl.setTemplate(jdbcTemplate);
		//PropertyManagerImpl.getOverallStatus();
	}
	
	@Test
	public void testSetTemplate(final JdbcTemplate jdbcTemplate){
		Deencapsulation.setField(PropertyManagerImpl.class,"checkForNewProperties",false);
		PropertyManagerImpl.setTemplate(jdbcTemplate);
	}
	

	@Test
	public void testGetOverallStatus(final OverallStatusDTO osdto) throws Exception{
		Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallStatus", osdto,1);
		PropertyManagerImpl.getOverallStatus();
	}
	
	@Test
	public void testGetOverallStatusManualPurgeOn(final JdbcTemplate template) throws Exception{
		final PropertyDTO pdto = new PropertyDTO();
		pdto.setName(DBProperties.MANUALPURGE);
		pdto.setValue("1");
		final List<PropertyDTO> props = new ArrayList();
		props.add(pdto);
		PropertyManagerImpl.setTemplate(template);
		new NonStrictExpectations(PropertyManagerImpl.class){
			{
				template.query(anyString, (GenericDTORowMapper)any);result=props;
			}
		};
		
		PropertyManagerImpl.setOverallProperties();
		PropertyManagerImpl.getOverallStatus();
	}
	
	
	@Test
	public void testGetOverallStatusWithNoDeletes(final OverallStatusDTO osdto, final JdbcTemplate template){
		new NonStrictExpectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class, "checkToAutoPurge");
				Deencapsulation.invoke(PropertyManagerImpl.class, "checkActiveUserOrphans");
			}
		};
		
		Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallStatus", osdto, 0);
	}
	
	@Test
	public void testGetOverallStatusWithNoDeletes2(final OverallStatusDTO osdto, final JdbcTemplate template){
		new NonStrictExpectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class, "checkToAutoPurge");
				Deencapsulation.invoke(PropertyManagerImpl.class, "checkActiveUserOrphans");
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallStatus", osdto);
	}

	@Test
	public void testCheckActiveUserOrphans(final JdbcTemplate template){
		new Expectations(){
			{
				template.update(SqlQueries.ADOPT_ACTIVEUSER_ORPHANS_PART1);
				template.update(SqlQueries.ADOPT_ACTIVEUSER_ORPHANS_PART2);
			}
		};
		Deencapsulation.setField(PropertyManagerImpl.class,"template",template);
		Deencapsulation.invoke(PropertyManagerImpl.class,"checkActiveUserOrphans");
	}
	
	@Test
	public void testCheckToAutoPurge2() throws Exception{
		final OverallStatusDTO osdto = new OverallStatusDTO();
		osdto.setActiveUser(1001);
		osdto.setMethodMetrics(1001);
		osdto.setAccessMetrics(1001);
		osdto.setSystemAvailability(1001);
		osdto.setInsertMetrics(1001);
		final String host = "test";
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, host);
		overallPropertyMap.put(DBProperties.AUTO_PURGE, "1");
		overallPropertyMap.put(DBProperties.MAX_ACTIVE_USER, "1000");
		overallPropertyMap.put(DBProperties.MAX_METHOD_METRICS, "1000");
		overallPropertyMap.put(DBProperties.MAX_ACCESS_METRICS, "1000");
		overallPropertyMap.put(DBProperties.MAX_SYS_AVAILABILITY, "1000");
		overallPropertyMap.put(DBProperties.MAX_INSERT_METRICS, "1000");
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		
		Deencapsulation.setField(PropertyManagerImpl.class, "overallStatus",osdto);
		Deencapsulation.setField(PropertyManagerImpl.class,"host",host);
		Deencapsulation.setField(PropertyManagerImpl.class,"bHost",true);
		
		new Expectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","ActiveUser");
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","MethodMetrics");
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","AccessMetrics");
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","SystemAvailability");
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","InsertMetrics");
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class,"checkToAutoPurge");
	}
	
	@Test
	public void testCheckToAutoPurge3() throws Exception{
		final OverallStatusDTO osdto = new OverallStatusDTO();
		osdto.setActiveUser(999);
		osdto.setMethodMetrics(999);
		osdto.setAccessMetrics(999);
		osdto.setSystemAvailability(999);
		osdto.setInsertMetrics(999);
		final String host = "test";
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, host);
		overallPropertyMap.put(DBProperties.AUTO_PURGE, "1");
		overallPropertyMap.put(DBProperties.MAX_ACTIVE_USER, "1000");
		overallPropertyMap.put(DBProperties.MAX_METHOD_METRICS, "1000");
		overallPropertyMap.put(DBProperties.MAX_ACCESS_METRICS, "1000");
		overallPropertyMap.put(DBProperties.MAX_SYS_AVAILABILITY, "1000");
		overallPropertyMap.put(DBProperties.MAX_INSERT_METRICS, "1000");
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		
		Deencapsulation.setField(PropertyManagerImpl.class, "overallStatus",osdto);
		Deencapsulation.setField(PropertyManagerImpl.class,"host",host);
		Deencapsulation.setField(PropertyManagerImpl.class,"bHost",true);
		
		new Expectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","ActiveUser");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","MethodMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","AccessMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","SystemAvailability");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","InsertMetrics");times=0;
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class,"checkToAutoPurge");
	}
	
	@Test
	public void testCheckToAutoPurge4() throws Exception{
		final OverallStatusDTO osdto = new OverallStatusDTO();
		osdto.setActiveUser(999);
		osdto.setMethodMetrics(999);
		osdto.setAccessMetrics(999);
		osdto.setSystemAvailability(999);
		osdto.setInsertMetrics(999);
		final String host = "test";
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, host);
		overallPropertyMap.put(DBProperties.AUTO_PURGE, "1");
		//don't set properties so that getPropertyInt returns -1
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		
		Deencapsulation.setField(PropertyManagerImpl.class, "overallStatus",osdto);
		Deencapsulation.setField(PropertyManagerImpl.class,"host",host);
		Deencapsulation.setField(PropertyManagerImpl.class,"bHost",true);
		
		new Expectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","ActiveUser");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","MethodMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","AccessMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","SystemAvailability");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","InsertMetrics");times=0;
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class,"checkToAutoPurge");
	}
	
	@Test
	public void testCheckToAutoPurge5() throws Exception{
		final OverallStatusDTO osdto = new OverallStatusDTO();
		osdto.setActiveUser(999);
		osdto.setMethodMetrics(999);
		osdto.setAccessMetrics(999);
		osdto.setSystemAvailability(999);
		osdto.setInsertMetrics(999);
		final String host = "test";
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, host);
		overallPropertyMap.put(DBProperties.AUTO_PURGE, "-1");
		//don't set properties so that getPropertyInt returns -1
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		
		Deencapsulation.setField(PropertyManagerImpl.class, "overallStatus",osdto);
		Deencapsulation.setField(PropertyManagerImpl.class,"host",host);
		Deencapsulation.setField(PropertyManagerImpl.class,"bHost",true);
		
		new Expectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","ActiveUser");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","MethodMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","AccessMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","SystemAvailability");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","InsertMetrics");times=0;
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class,"checkToAutoPurge");
	}
	
	@Test
	public void testCheckToAutoPurge6() throws Exception{
		final OverallStatusDTO osdto = new OverallStatusDTO();
		osdto.setActiveUser(999);
		osdto.setMethodMetrics(999);
		osdto.setAccessMetrics(999);
		osdto.setSystemAvailability(999);
		osdto.setInsertMetrics(999);
		final String host = "test";
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, "someotherhost");
		overallPropertyMap.put(DBProperties.AUTO_PURGE, "-1");
		//don't set properties so that getPropertyInt returns -1
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		
		Deencapsulation.setField(PropertyManagerImpl.class, "overallStatus",osdto);
		Deencapsulation.setField(PropertyManagerImpl.class,"host",host);
		Deencapsulation.setField(PropertyManagerImpl.class,"bHost",false);
		
		new Expectations(PropertyManagerImpl.class){
			{
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","ActiveUser");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","MethodMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","AccessMetrics");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","SystemAvailability");times=0;
				Deencapsulation.invoke(PropertyManagerImpl.class,"purge","InsertMetrics");times=0;
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class,"checkToAutoPurge");
	}
	
	@Test
	public void testManualPurge(final PerformanceDataQueryJdbcDbImpl performanceDataQuery){

		final int records = 1;
		new Expectations(){
			{
				performanceDataQuery.purgeAccessMetrics(records);result=-1;
				performanceDataQuery.purgeActiveUserMetrics(records);result=-1;
				performanceDataQuery.purgeInsertMetrics(records);result=-1;
				performanceDataQuery.purgeMethodMetrics(records);result=-1;
				performanceDataQuery.purgeSystemAvailability(records);result=-1;
				
			}
		};
		PropertyManagerImpl.manualPurge("AccessMetrics", records);
		PropertyManagerImpl.manualPurge("ActiveUser", records);
		PropertyManagerImpl.manualPurge("InsertMetrics", records);
		PropertyManagerImpl.manualPurge("MethodMetrics", records);
		PropertyManagerImpl.manualPurge("SystemAvailability", records);
	}
	
	@Test
	public void testManualPurge2(final PerformanceDataQueryJdbcDbImpl performanceDataQuery, final JdbcTemplate template) {
		final OverallStatusDTO osdto = new OverallStatusDTO();
		final List<OverallStatusDTO> data = new ArrayList<OverallStatusDTO>();
		data.add(osdto);
		final int records = 1;
		new NonStrictExpectations(){
			{
				performanceDataQuery.purgeSystemAvailability(records);result=records;
				template.query(anyString,(GenericDTORowMapper)any);result=data;
				data.get(0);result=osdto;
				Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallStatus" , osdto, records);
			}
		};
		PropertyManagerImpl.manualPurge("SystemAvailability", records);
		PropertyManagerImpl.manualPurge("bs", records);
	}
	
	@Test
	public void testPurge(final PerformanceDataQueryJdbcDbImpl performanceDataQuery){
		
		new NonStrictExpectations(){
			{
				performanceDataQuery.purgeAccessMetrics(anyInt);result=1;
				performanceDataQuery.purgeActiveUserMetrics(anyInt);result=1;
				performanceDataQuery.purgeInsertMetrics(anyInt);result=1;
				performanceDataQuery.purgeMethodMetrics(anyInt);result=1;
				performanceDataQuery.purgeSystemAvailability(anyInt);result=1;
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class, "purge", "AccessMetrics");
		Deencapsulation.invoke(PropertyManagerImpl.class, "purge", "ActiveUser");
		Deencapsulation.invoke(PropertyManagerImpl.class, "purge", "InsertMetrics");
		Deencapsulation.invoke(PropertyManagerImpl.class, "purge", "MethodMetrics");
		Deencapsulation.invoke(PropertyManagerImpl.class, "purge", "SystemAvailability");
		Deencapsulation.invoke(PropertyManagerImpl.class, "purge", "bs");
	}
	
	@Test
	public void testSetNewProperties(final JdbcTemplate template){
		Deencapsulation.setField(PropertyManagerImpl.class, "template",template);
		final String[][] props = Deencapsulation.getField(PropertyManagerImpl.class,"NEWPROPS");
		new Expectations(){
			{
				template.queryForInt(anyString,(Object[])any);result = 1;times = props.length;
			}
		};
		Deencapsulation.invoke(PropertyManagerImpl.class, "setNewProperties");
	}
	@Test
	public void testGetOverallProperties(final OverallPropertyDTO opDto){
		new NonStrictExpectations(){
			{
				
				opDto.setTargetDays(anyInt);
				opDto.setDataDays(anyInt);
			}
		};
		PropertyManagerImpl.getOverallProperties();
	}
	
//	@Test
//	public void testGetProperty() throws Exception{
//		PropertyManagerImpl.getProperty("name");
//	}
	
	@Test
	public void testSetProperty(){
		PropertyManagerImpl.setProperty("name", "value");
		
	}
	
	@Test
	public void testGetHost(){
		PropertyManagerImpl.getHost();
	}
	
	// having issues getting this to work.
	@Test
	public void testSetOverallProperties(final JdbcTemplate template){
		
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.MAX_ACTIVE_USER, "1000");
		overallPropertyMap.put(DBProperties.MAX_METHOD_METRICS, "1000");
		overallPropertyMap.put(DBProperties.MAX_ACCESS_METRICS, "1000");
		overallPropertyMap.put(DBProperties.MAX_SYS_AVAILABILITY, "1000");
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		
		final List<PropertyDTO> props = new ArrayList<PropertyDTO>();
		PropertyDTO propDTO = new PropertyDTO();
		propDTO.setName("name");
		propDTO.setValue("value");
		props.add(propDTO);
		
		new NonStrictExpectations(){
			{
				template.query(anyString, (GenericDTORowMapper)any);result=props;
			}
		};
		PropertyManagerImpl.setOverallProperties();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPropertyChecker(@Cascading final JdbcTemplate template){
		final List<OverallStatusDTO> data = new ArrayList<OverallStatusDTO>();
		final OverallStatusDTO os = new OverallStatusDTO();
		data.add(os);
		final List<VbmsStatusDTO> vbmsStatus = new ArrayList<VbmsStatusDTO>();
		VbmsStatusDTO vs1 = new VbmsStatusDTO();
		vs1.setTotalseconds(200);
		VbmsStatusDTO vs2 = new VbmsStatusDTO();
		vs2.setTotalseconds(1);
		vbmsStatus.add(vs1);
		vbmsStatus.add(vs2);
		PropertyManagerImpl.setTemplate(template);
		new Expectations(PropertyManagerImpl.class){
			{
			   template.queryForInt(SqlQueries.HOST_CHECK, (Object[])any);result = 1;
			   template.query(SqlQueries.GET_OVERALL_STATUS, (GenericDTORowMapper)any);result = data;
			   Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallStatus",os);
			   Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallProperties");
			}
		};
		new PropertyChecker().run();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPropertyCheckerDataSize0(@Cascading final JdbcTemplate template){
		final List<OverallStatusDTO> data = new ArrayList<OverallStatusDTO>();
		PropertyManagerImpl.setTemplate(template);
		new Expectations(PropertyManagerImpl.class){
			{
			   template.queryForInt(SqlQueries.HOST_CHECK, (Object[])any);result = 1;
			   template.query(SqlQueries.GET_OVERALL_STATUS, (GenericDTORowMapper)any);result = data;
			   Deencapsulation.invoke(PropertyManagerImpl.class,"setOverallProperties");
			}
		};
		new PropertyChecker().run();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPropertyCheckerNotHost(@Cascading final JdbcTemplate template){
		final List<OverallStatusDTO> data = new ArrayList<OverallStatusDTO>();
		final OverallStatusDTO os = new OverallStatusDTO();
		data.add(os);
		final List<VbmsStatusDTO> vbmsStatus = new ArrayList<VbmsStatusDTO>();
		VbmsStatusDTO vs1 = new VbmsStatusDTO();
		vs1.setTotalseconds(200);
		VbmsStatusDTO vs2 = new VbmsStatusDTO();
		vs2.setTotalseconds(1);
		vbmsStatus.add(vs1);
		vbmsStatus.add(vs2);
		PropertyManagerImpl.setTemplate(template);
		new Expectations(PropertyManagerImpl.class){
			{
			   template.queryForInt(SqlQueries.HOST_CHECK, (Object[])any);result = 0;
			}
		};
		new PropertyChecker().run();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPropertyCheckerException(@Cascading final JdbcTemplate template){
		PropertyManagerImpl.setTemplate(template);
		new Expectations(PropertyManagerImpl.class){
			{
			   template.queryForInt(SqlQueries.HOST_CHECK, (Object[])any);result = 1;
			   template.query(SqlQueries.GET_OVERALL_STATUS, (GenericDTORowMapper)any);result = new Exception("testing");
			}
		};
		new PropertyChecker().run();
		
	}
	
	@Test
	public void testMetricsSummarizer(final PerformanceDataQueryJdbcDb performanceDataQuery){
		final String host = "the target host";
		Deencapsulation.setField(PropertyManagerImpl.class, "host", host);
		Deencapsulation.setField(PropertyManagerImpl.class, "bHost", true);
		Deencapsulation.setField(PropertyManagerImpl.class, "performanceDataQuery", performanceDataQuery);
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, host);
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		new Expectations(PropertyManagerImpl.class){
			{
				performanceDataQuery.summarizeAccessMetrics((Timestamp)any);
				performanceDataQuery.summarizeActiveUserMetrics((Timestamp)any);
				performanceDataQuery.summarizeInsertMetrics((Timestamp)any);
				performanceDataQuery.summarizeMethodMetrics((Timestamp)any);
				performanceDataQuery.summarizeSystemAvailability((Timestamp)any);
			}
		};
		new MetricsSummarizer().run();
	}
	
	@Test
	public void testMetricsSummarizer2(final PerformanceDataQueryJdbcDb performanceDataQuery){
		final String host = "the target host";
		Deencapsulation.setField(PropertyManagerImpl.class, "host", host);
		Deencapsulation.setField(PropertyManagerImpl.class, "bHost", false);
		Deencapsulation.setField(PropertyManagerImpl.class, "performanceDataQuery", performanceDataQuery);
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, "someotherhost");
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		new Expectations(PropertyManagerImpl.class){
			{
				performanceDataQuery.summarizeAccessMetrics((Timestamp)any);times=0;
				performanceDataQuery.summarizeActiveUserMetrics((Timestamp)any);times=0;
				performanceDataQuery.summarizeInsertMetrics((Timestamp)any);times=0;
				performanceDataQuery.summarizeMethodMetrics((Timestamp)any);times=0;
				performanceDataQuery.summarizeSystemAvailability((Timestamp)any);times=0;
			}
		};
		new MetricsSummarizer().run();
	}
	
	@Test
	public void testMetricsSummarizerException(final PerformanceDataQueryJdbcDb performanceDataQuery){
		final String host = "the target host";
		Deencapsulation.setField(PropertyManagerImpl.class, "host", host);
		Deencapsulation.setField(PropertyManagerImpl.class, "bHost", true);
		AbstractMap<String,String> overallPropertyMap = new ConcurrentHashMap<String,String>(50);
		overallPropertyMap.put(DBProperties.SUMMARIZE_AND_PURGE_HOST, host);
		Deencapsulation.setField(PropertyManagerImpl.class, "overallPropertyMap",overallPropertyMap);
		Deencapsulation.setField(PropertyManagerImpl.class, "performanceDataQuery", performanceDataQuery);
		new Expectations(PropertyManagerImpl.class){
			{
				performanceDataQuery.summarizeAccessMetrics((Timestamp)any);result = new Exception("testing");
			}
		};
		new MetricsSummarizer().run();
	}
}



