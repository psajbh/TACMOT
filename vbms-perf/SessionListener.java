package gov.va.vba.vbms.util;

import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//import gov.va.vba.vbms.performance.service.impl.PerformanceDataStoreJdbcImpl;
//import gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory;
//import org.springframework.web.context.request.RequestContextHolder;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener{
	//private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);
	private static final PerformanceDataStore PDS = PerformanceDataStoreFactory.getPeformanceDataStore();
	//private static final PerformanceDataStore PDS = PerformanceDataStoreJdbcImpl.getInstance();
	
	private static AtomicInteger activeSessions = new AtomicInteger(0);
	private static AtomicInteger createdSessions = new AtomicInteger(0);;
	private Map<String,String> sessionMap = new HashMap<String,String>();
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String id = session.getId();
		createdSessions.incrementAndGet();
		activeSessions.incrementAndGet();
		sessionMap.put(id, String.valueOf(createdSessions));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String id = session.getId();
		String user = (String) session.getAttribute("userName");
		if(PDS.isActiveUserEnabled() && user != null && id != null){
			PDS.logout(user, id, System.currentTimeMillis());
		}
		if (id != null){
			activeSessions.decrementAndGet();
			sessionMap.remove(id);
		}
	}
	
	public static int getActiveSessions(){
		return activeSessions.get();
	}
	
	public static int getCreatedSessions(){
		return createdSessions.get();
	}

}
