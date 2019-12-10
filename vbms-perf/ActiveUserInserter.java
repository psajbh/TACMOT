package gov.va.vba.vbms.common.framework.performance.impl;


import gov.va.vba.vbms.common.framework.performance.LinkedBlockingQueueAlmostSilentOffer;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.Map;

public class ActiveUserInserter implements Runnable {
	private static Logger logger = LoggerFactory
			.getLogger(ActiveUserInserter.class);// can't be final for JMockit

    private static PerformanceDataStore pds = gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory.getPeformanceDataStore();
    private static LinkedBlockingQueueAlmostSilentOffer<Object[]> queueUpdates = pds.getQueueActiveUsersUpdates();
    private static LinkedBlockingQueueAlmostSilentOffer<Object[]> queueInserts = pds.getQueueActiveUsersInserts();

	private long startTime = -1L;
	private long endTime = -1L;
	private final String user;
	private final String sessionId;
	private long securityLevel = -1;
	private String stationId = null;
	private String userRole = null;
	private String mode = null;
	private String userName = null;
	private String subject = null;
	private String clientIp = null;
	private String emailAddress = null;
	private String theProfile = null;
	private Map<String, String> profile;		
	private final JdbcTemplate template;

	//logout code
	public ActiveUserInserter(final JdbcTemplate template, final String user, final String sessionId, final long logoutTime) {
		mode = "UPDATE";
		this.template = template;
		this.user = user;
		this.sessionId = sessionId;
		this.endTime = logoutTime;
	}

	//login code
	public ActiveUserInserter(final JdbcTemplate template, final long startTime, 
			final String user, final String sessionId,
			final Map<String, String> profile) {
		mode = "INSERT";
		this.template = template;
		this.startTime = startTime;
		this.user = user;
		this.sessionId = sessionId;
//		processProfile(profile);//moved to run to speed up synchronous processing
		this.profile = profile;
	}

	private void processProfile(Map<String, String> profile) {
		
		stationId = profile.get("stationId");
		try {
			securityLevel = Long.parseLong(profile.get("securityLevel"));
		}
		catch(NumberFormatException nfe){
			logger.debug("securityLevel profile issue: " + nfe.getMessage());	
		}
		
		userRole = profile.get("roles");
		subject = profile.get("bepSubjectIdentifier");
		userName = profile.get("fName") + " " + profile.get("lName");
		emailAddress = profile.get("emailAddress");
		clientIp = profile.get("clientIP");
		theProfile = profile.toString();
		
	}

	@Override
	public void run() {
        StringBuffer tableName = new StringBuffer("ActiveUser");
        boolean batch = pds.isBatchEnabled();
        long insert = 0;
        long start = System.nanoTime();
		if (mode.equals("INSERT")) {
            tableName.append("Insert");
			processProfile(profile);
            insert = System.nanoTime();
			doInsert(batch);
		} else /*if (mode.equals("UPDATE"))*/ {
            tableName.append("Update");
            insert = System.nanoTime();
			doUpdate(batch);
		}
        long end = System.nanoTime();
        if(pds.isInsertEnabled()){
            long total = end - start;
            insert = end - insert;
            tableName.append(batch?"BatchSingle":"");
            pds.insertInsertData(System.currentTimeMillis(), total, insert, total-insert, tableName.toString());
        }
	}

	private void doUpdate(boolean batch) {
		try{
            Object[] params = new Object[]{new Timestamp(endTime), sessionId, user};
            if(batch){
                queueUpdates.offerAlmostSilently(params);
            }else{
			    template.update(Queries.UPDATE_ACTIVE_USER_QUERY, params);
            }
	}catch(Exception e){
		if(logger.isDebugEnabled()){logger.debug("Caught",e);}
	}
			}// for some odd reason this closing brace doesn't get unit test code coverage if it's on the next line

	private void doInsert(boolean batch) {
		try{
            Object [] params = new Object[] { new Timestamp(startTime), sessionId, user, securityLevel, stationId,
                    Util.trimField(userRole,FieldSizes.USERROLE_FIELD_SIZE),
                    Util.trimField(theProfile,FieldSizes.PROFILE_FIELD_SIZE), clientIp, userName,	subject,
                    emailAddress};
            if(batch){
                queueInserts.offerAlmostSilently(params);
            }else{
                template.update(Queries.INSERT_ACTIVE_USER_QUERY, params);
            }
		}catch(Exception e){
			if(logger.isDebugEnabled()){logger.debug("Caught",e);} }// for some odd reason this closing brace doesn't get unit test code coverage if it's on the next line
	}
}
