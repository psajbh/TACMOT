package gov.va.vba.vbms.common.framework.performance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gov.va.vba.vbms.common.framework.performance.PerformanceDataStore;
import gov.va.vba.vbms.common.framework.performance.PerformanceDataStoreFactory;
import gov.va.vba.vbms.common.framework.performance.PerformanceLoggingFilter;
import gov.va.vba.vbms.common.framework.performance.PerformanceLoggingFilter.PerformanceRequestWrapper;
import gov.va.vba.vbms.common.framework.performance.PerformanceLoggingFilter.PerformanceResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

import org.junit.Test;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

public class PerformanceLoggingFilterTest {
	
	@Cascading PerformanceDataStoreFactory pdsf;
	@Cascading PerformanceDataStore pds;
	
	@Test
	public void testInitDestroy(final FilterConfig config, final ServletContext servletContext) throws ServletException{
		PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		plf.init(config);
		plf.destroy();
	}	
	
	@Test
	public void testDoFilterBothDisabled(final ServletRequest servletRequest,	
			final HttpServletResponse servletResponse, 
			final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		new Expectations(){
			{
				pds.isActiveUserEnabled();result=false;
				pds.isAccessEnabled();result = false;
				filterChain.doFilter(servletRequest, servletResponse);			
			}
		};		
		plf.doFilter(servletRequest, servletResponse, filterChain);
	}
	
	@Test
	public void testDoFilterServletRequest(final ServletRequest servletRequest,	final HttpServletResponse servletResponse, 
			final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		new Expectations(){
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				filterChain.doFilter(servletRequest, servletResponse);			
			}
		};		
		plf.doFilter(servletRequest, servletResponse, filterChain);
	}
	
	@Test
	public void testDoFilterServletResponse(final HttpServletRequest servletRequest, final ServletResponse servletResponse, 
			final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		new Expectations(){
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				filterChain.doFilter(servletRequest, servletResponse);			
			}
		};		
		plf.doFilter(servletRequest, servletResponse, filterChain);
	}
	
	
	@Test
	public void testDoFilterServletResponse1(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse, 
			final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		new Expectations(){
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				servletRequest.getRequestURI();result = "/saml/metadata";
				servletRequest.getContextPath();result="";
				filterChain.doFilter(servletRequest, servletResponse);			
			}
		};		
		plf.doFilter(servletRequest, servletResponse, filterChain);
	}

	@Test
	public void testDoFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations(plf){
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				httpServletRequest.getRequestURI();result = alias;
				httpServletRequest.getContextPath();result="";
				httpServletRequest.getRequestURI();result = alias;
				invoke(plf, "getSessionId", withAny(HttpServletRequest.class)); result = "sessionId";
				httpServletRequest.getInputStream();
				httpServletResponse.getOutputStream();
				filterChain.doFilter((HttpServletRequest)any, (HttpServletResponse)any);				
				invoke(plf, "getUserName", withAny(HttpServletRequest.class)); result = "userName";
                invoke(plf, "getUserId", withAny(HttpServletRequest.class)); result = "1234";
                invoke(plf, "checkForLogin", withAny(HttpServletRequest.class), anyString, anyString, anyString, anyLong);
				httpServletRequest.getRemoteAddr();
				httpServletRequest.getContentLength();
				pds.insertAccessData(anyLong, anyString, anyLong, anyLong, anyLong, anyLong, anyString, anyLong, anyString, anyString, anyString);
			}
		};		
		plf.doFilter(httpServletRequest, httpServletResponse, filterChain);
	}
	
	
			
	@Test
	public void testDoFilterExceptions(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse, 
			final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations(plf){
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				servletRequest.getRequestURI();result = alias;
				servletRequest.getContextPath();result="";
				servletRequest.getRequestURI(); result = new ServletException("test");				
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				servletRequest.getRequestURI();result = alias;
				servletRequest.getContextPath();result="";
				servletRequest.getRequestURI(); result = new IOException("test");
			}
		};		
		plf.doFilter(servletRequest, servletResponse, filterChain);
		plf.doFilter(servletRequest, servletResponse, filterChain);
	}
	
	@Test
	public void testDoFilterRequestLength(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, 
			final FilterChain filterChain) throws Exception{		
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations(plf) {
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = true;
				httpServletRequest.getRequestURI();result = alias;
				httpServletRequest.getContextPath();result="";
				httpServletRequest.getRequestURI();result = alias;
				invoke(plf, "getSessionId", withAny(HttpServletRequest.class)); result = "sessionId";
				httpServletRequest.getInputStream();
				httpServletResponse.getOutputStream();
				filterChain.doFilter((HttpServletRequest)any, (HttpServletResponse)any);				
				invoke(plf, "getUserName", withAny(HttpServletRequest.class)); result = "userName";
                invoke(plf, "getUserId", withAny(HttpServletRequest.class)); result = "1234";
                invoke(plf, "checkForLogin", withAny(HttpServletRequest.class), anyString, anyString, anyString, anyLong);
				httpServletRequest.getRemoteAddr();
				httpServletRequest.getContentLength(); result = -1;
				pds.insertAccessData(anyLong, anyString, anyLong, anyLong, anyLong, anyLong, anyString, anyLong, anyString, anyString, anyString);
				
			}
		};
		plf.doFilter(httpServletRequest, httpServletResponse, filterChain);
	}
	
	@Test
	public void testGetSessionId(@Cascading final HttpServletRequest httpServletRequest) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		new Expectations() {
			{
				httpServletRequest.getSession().getId(); result = "sessionId";
				httpServletRequest.getSession().getId(); result = new Exception();
			}
		};
		Assert.assertEquals("sessionId", Deencapsulation.invoke(plf, "getSessionId", httpServletRequest));
		Assert.assertEquals("null", Deencapsulation.invoke(plf, "getSessionId", httpServletRequest));
	}
	
	@Test
	public void testGetUserName(@Cascading final HttpServletRequest httpServletRequest) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		new Expectations() {
			{
				httpServletRequest.getSession().getAttribute(anyString); result = "userName";
				httpServletRequest.getSession().getAttribute(anyString);
				httpServletRequest.getSession().getAttribute(anyString); result = new Exception();
			}
		};
		Assert.assertEquals("userName", Deencapsulation.invoke(plf, "getUserName", httpServletRequest));
		Assert.assertEquals("UNKNOWN", Deencapsulation.invoke(plf, "getUserName", httpServletRequest));
		Assert.assertEquals("UNKNOWN", Deencapsulation.invoke(plf, "getUserName", httpServletRequest));
	}	
	
	@Test
	public void testCheckForLogin1(@Cascading final HttpServletRequest request ) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		final Map<String,String> profile = new HashMap<String,String>();
		new Expectations() {
			{
				request.getContextPath(); result = "";
				request.getSession().getAttribute("profile"); result = profile;
				pds.insertActiveUser(anyLong, anyString, anyString, profile);
			}
		};
		Deencapsulation.invoke(plf, "checkForLogin", request, alias, "userName", "sessionId",1234L);
	}
	
	@Test
	public void testCheckForLogin2(@Cascading final HttpServletRequest request ) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations() {
			{
				request.getContextPath(); result = "";
			}
		};
		Deencapsulation.invoke(plf, "checkForLogin", request, alias+"wrong", "userName", "sessionId",1234L);
	}
	
	@Test
	public void testCheckForLogin3(@Cascading final HttpServletRequest request ) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		final String unknown = Deencapsulation.getField(plf, "UNKNOWN");
		new Expectations() {
			{
				request.getContextPath();result="";
			}
		};
		Deencapsulation.invoke(plf, "checkForLogin", request, alias, unknown, "sessionId",1234L);
	}
	
	@Test
	public void testCheckForLogin4(@Cascading final HttpServletRequest request ) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations() {
			{
				request.getContextPath();result="";
			}
		};
		Deencapsulation.invoke(plf, "checkForLogin", request, alias, "userName", "null",1234L);
	}
	
	@Test
	public void testCheckForLogin5(@Cascading final HttpServletRequest request ) throws Exception {
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		final String attribute = "profile";
		final Map<String,String> profile = new HashMap<String,String>();
		new Expectations() {
			{
				request.getContextPath();result="";
				request.getSession().getAttribute(attribute);result=profile;
				pds.insertActiveUser(1234L, "userName", "sessionId", profile);
			}
		};
		Deencapsulation.invoke(plf, "checkForLogin", request, alias, "userName", "sessionId",1234L);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPerformanceRequestWrapper1(@Cascading final HttpServletRequest request) throws Exception{
		PerformanceRequestWrapper prw = new PerformanceRequestWrapper(request);
		prw.getReader().read();
		prw.getReader().read();
		prw.getInputStream();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPerformanceRequestWrapper2(@Cascading final HttpServletRequest request) throws Exception{
		PerformanceRequestWrapper prw = new PerformanceRequestWrapper(request);
		prw.getInputStream().read();
		prw.getReader();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPerformanceResponseWrapper1(@Cascading final HttpServletResponse response) throws Exception{
		PerformanceResponseWrapper prw = new PerformanceResponseWrapper(response);
		prw.getWriter().write(100);
		prw.getWriter().write(100);
		prw.getOutputStream();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPerformanceResponseWrapper2(@Cascading final HttpServletResponse response) throws Exception{
		PerformanceResponseWrapper prw = new PerformanceResponseWrapper(response);
		prw.getOutputStream().write(100);
		prw.getOutputStream().write(100);
		prw.getWriter();
	}
	
	
	@Test
	public void testDoFilterAccessDisabled(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations(plf){
			{
				pds.isActiveUserEnabled();result=true;
				pds.isAccessEnabled();result = false;
				httpServletRequest.getRequestURI();result = alias;
				httpServletRequest.getContextPath();result="";
				httpServletRequest.getRequestURI();result = alias;
				invoke(plf, "getSessionId", withAny(HttpServletRequest.class)); result = "sessionId";
				filterChain.doFilter((HttpServletRequest)any, (HttpServletResponse)any);				
				invoke(plf, "getUserName", withAny(HttpServletRequest.class)); result = "userName";
                invoke(plf, "getUserId", withAny(HttpServletRequest.class)); result = "1234";
                invoke(plf, "checkForLogin", withAny(HttpServletRequest.class), anyString, anyString, anyString, anyLong);
			}
		};		
		plf.doFilter(httpServletRequest, httpServletResponse, filterChain);
	}

	@Test
	public void testDoFilterActiveUserDisabled(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws Exception{
		final PerformanceLoggingFilter plf = new PerformanceLoggingFilter();
		final String alias = Deencapsulation.getField(plf, "NEWSESSIONPATH");
		new Expectations(plf){
			{
				pds.isActiveUserEnabled();result=false;
				pds.isAccessEnabled();result = true;
				httpServletRequest.getRequestURI();result = alias;
				httpServletRequest.getContextPath();result="";
				httpServletRequest.getRequestURI();result = alias;
				invoke(plf, "getSessionId", withAny(HttpServletRequest.class)); result = "sessionId";
				httpServletRequest.getInputStream();
				httpServletResponse.getOutputStream();
				filterChain.doFilter((HttpServletRequest)any, (HttpServletResponse)any);				
				invoke(plf, "getUserName", withAny(HttpServletRequest.class)); result = "userName";
				invoke(plf, "getUserId", withAny(HttpServletRequest.class)); result = "1234";

				httpServletRequest.getRemoteAddr();
				httpServletRequest.getContentLength();
				pds.insertAccessData(anyLong, anyString, anyLong, anyLong, anyLong, anyLong, anyString, anyLong, anyString, anyString, anyString);
			}
		};		
		plf.doFilter(httpServletRequest, httpServletResponse, filterChain);
	}

}
