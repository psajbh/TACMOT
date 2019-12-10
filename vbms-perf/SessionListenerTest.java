package gov.va.vba.vbms.util;

import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.junit.Assert;

import mockit.Cascading;
import mockit.Expectations;

import mockit.NonStrictExpectations;
import org.junit.Ignore;
import org.junit.Test;

public class SessionListenerTest {
	@Cascading PerformanceDataStoreFactory pdsf;
	@Cascading PerformanceDataStore pds;

	@Test
	public void testSessionCreated(final HttpSession httpSession, final HttpSessionEvent httpSessionEvent) {
		new Expectations(){
			{
				httpSessionEvent.getSession(); result = httpSession;
				httpSession.getId(); result = "sessionId";
			}
		};	
		final SessionListener sessionListener = new SessionListener();
		sessionListener.sessionCreated(httpSessionEvent);
		Assert.assertEquals(1,SessionListener.getCreatedSessions());
	}
	
	@Test @Ignore
	public void testSessionDestroyed(@Cascading final HttpSession httpSession, final HttpSessionEvent httpSessionEvent) {
		new NonStrictExpectations(){
			{
				httpSessionEvent.getSession(); result = httpSession;
				httpSession.getId(); result = "sessionId";
				httpSession.getAttribute(anyString); result = "userName";
                httpSession.getServletContext().getRealPath(anyString); result = "/path";
				pds.isActiveUserEnabled(); result = true;
				pds.logout(anyString,anyString,anyLong);
				
				httpSessionEvent.getSession(); result = httpSession;
				httpSession.getId(); result = "sessionId";
				httpSession.getAttribute(anyString); result = null;
                httpSession.getServletContext().getRealPath(anyString); result = "/path";
				pds.isActiveUserEnabled(); result = true;
				
				httpSessionEvent.getSession(); result = httpSession;
				httpSession.getId(); result = null;
				httpSession.getAttribute(anyString); result = "userName";
                httpSession.getServletContext().getRealPath(anyString); result = "/path";
				pds.isActiveUserEnabled(); result = true;
				
				httpSessionEvent.getSession(); result = httpSession;
				httpSession.getId(); result = null;
				httpSession.getAttribute(anyString); result = "userName";
                httpSession.getServletContext().getRealPath(anyString); result = "/path";
				pds.isActiveUserEnabled(); result = false;

                httpSessionEvent.getSession(); result = httpSession;
                httpSession.getId(); result = null;
                httpSession.getAttribute(anyString); result = "userName";
                httpSession.getServletContext().getRealPath(anyString); result = "/path";
                pds.isActiveUserEnabled(); result = false;
			}
		};	
		final SessionListener sessionListener = new SessionListener();
		sessionListener.sessionDestroyed(httpSessionEvent);
		Assert.assertEquals(0, SessionListener.getActiveSessions());
		
		sessionListener.sessionDestroyed(httpSessionEvent);
		Assert.assertEquals(0, SessionListener.getActiveSessions());
		
		sessionListener.sessionDestroyed(httpSessionEvent);
		Assert.assertEquals(0, SessionListener.getActiveSessions());
		
		sessionListener.sessionDestroyed(httpSessionEvent);
		Assert.assertEquals(0, SessionListener.getActiveSessions());

        sessionListener.sessionDestroyed(httpSessionEvent);
        Assert.assertEquals(0, SessionListener.getActiveSessions());
	}
	
	//@Test
	public void testSessionDestroyedWithException(final HttpSession httpSession, final HttpSessionEvent httpSessionEvent) {
		new Expectations(){
			{
				httpSessionEvent.getSession(); result = httpSession;
				httpSession.getId(); result = "sessionId";
				httpSession.getAttribute(anyString); result = "userName";
				pds.isActiveUserEnabled(); result = true;
				pds.logout(anyString,anyString,anyLong);result = new Exception("testing");
			}
		};	
		final SessionListener sessionListener = new SessionListener();
		sessionListener.sessionDestroyed(httpSessionEvent);
	}
	
	@Test
	public void testActiveSessions() {
		Assert.assertNotNull(SessionListener.getActiveSessions());
	}

	@Test
	public void testCreatedSessions() {
		Assert.assertNotNull(SessionListener.getCreatedSessions());
	}
	
}
