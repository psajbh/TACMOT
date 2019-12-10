package gov.va.vba.vbms.common.framework.performance;

//import gov.va.vba.vbms.performance.service.impl.PerformanceDataStoreJdbcImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

public class PerformanceLoggingFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PerformanceLoggingFilter.class);
	private static final PerformanceDataStore PDS = PerformanceDataStoreFactory
			.getPeformanceDataStore();
	private static final String NEWSESSIONPATH = "/saml/SSO/alias/vbms-claims";// path for BEP idp
	private static final String UNKNOWN = "UNKNOWN";
	private boolean activeUserEnabled = false;
	private boolean accessEnabled = false;

    private static ThreadLocal<String> threadSessionId = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return new String();
        }
    };

	@Override
	public void init(FilterConfig config) throws ServletException {
		// servletContext = config.getServletContext();
	}

	@Override
	public void destroy() {
		// servletContext = null;
		// pds.shutDown();//do we want to do this?
	}

	public void processRequest(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		// ALL the parentheses are important to ensure the third test does not
		// throw a class cast exception.
		// Modify at your own risk!
		//
		if ((!(servletRequest instanceof HttpServletRequest))
				|| (!(servletResponse instanceof HttpServletResponse))
				|| ((HttpServletRequest) servletRequest).getRequestURI()
						.equals(((HttpServletRequest) servletRequest)
						.getContextPath()+ "/saml/metadata")) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			long startTime = System.currentTimeMillis();
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String path = request.getRequestURI();
			String sessionId = getSessionId(request);
            threadSessionId.set(sessionId);
			PerformanceRequestWrapper requestWrapper = null;
			PerformanceResponseWrapper responseWrapper = null;
			if(accessEnabled){
				requestWrapper = new PerformanceRequestWrapper(request);
				responseWrapper = new PerformanceResponseWrapper(response);
				filterChain.doFilter(requestWrapper, responseWrapper);
			}else{
				filterChain.doFilter(request, response);
			}
			String userName = getUserName(request);
            String userId = getUserId(request);
			if (activeUserEnabled) {
				checkForLogin(request, path, userName, sessionId, startTime);
			}
			if (accessEnabled) {
				long timeEnd = System.currentTimeMillis();
				String remoteAddr = servletRequest.getRemoteAddr();
				long requestLength = servletRequest.getContentLength();
				long timeOfLastByteUploaded = requestWrapper.getTimeOfLastByte();
				long responseLength = responseWrapper.getResponseLength();
				long timeOfFirstByteDownloaded = responseWrapper.getTimeOfFirstByte();
				long totalTime = timeEnd - startTime;
				long uploadTime = timeOfLastByteUploaded - startTime;
				long downloadTime = timeEnd - timeOfFirstByteDownloaded;
				if (requestLength == -1) {
					requestLength = requestWrapper.getRequestLength();
				}
				PDS.insertAccessData(startTime, path, totalTime, uploadTime, downloadTime, responseLength, remoteAddr,requestLength, userName, sessionId, userId);
			}
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain) {
		activeUserEnabled = PDS.isActiveUserEnabled();
		accessEnabled = PDS.isAccessEnabled();
		try {
			
			if (accessEnabled || activeUserEnabled) {
				processRequest(servletRequest, servletResponse, filterChain);
			} else {
				filterChain.doFilter(servletRequest, servletResponse);
			}
		} catch (ServletException se) {
			LOGGER.error("Caught ServletException in PerformancLoggingFilter", se);
		} catch (IOException ie) {
			LOGGER.error("Caught IOException in PerformancLoggingFilter", ie);
		}
	}

	private String getUserName(HttpServletRequest request) {
		Object oUser = null;
		String user = null;
		try {
			oUser = request.getSession().getAttribute("userName");
			if (oUser == null) {
				user = UNKNOWN;
			} else {
				user = oUser.toString();
			}
		} catch (Exception e) {//NOSONAR
			user = UNKNOWN;
		}
		return user;
	}

    private String getUserId(HttpServletRequest request) {
        Object oUser = null;
        String userId = null;
        try {
            oUser = request.getSession().getAttribute("subjectId");
            if (oUser == null) {
                userId = null;
            } else {
                userId = oUser.toString();
            }
        } catch (Exception e) {//NOSONAR
            userId = null;
        }
        return userId;
    }

	private void checkForLogin(HttpServletRequest request, String path,
			String user, String sessionId, long startTime) {
		if ((request.getContextPath() + NEWSESSIONPATH).equals(path)
				&& !UNKNOWN.equals(user) && !"null".equals(sessionId)) {
			Map<String, String> profile = (Map<String, String>) request
					.getSession().getAttribute("profile");
			PDS.insertActiveUser(startTime, user, sessionId, profile);
		}
	}


	public static class PerformanceRequestWrapper extends
			HttpServletRequestWrapper {
		private PerformanceServletInputStream psis = null;
		private BufferedReader br = null;
		private boolean getReaderCalled = false;
		private boolean getISCalled = false;

		public PerformanceRequestWrapper(HttpServletRequest request)
				throws IOException {
			super(request);
			psis = new PerformanceServletInputStream(request.getInputStream());
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (getReaderCalled) {
				throw new IllegalStateException(
						"getReader has already been callded.");
			}
			getISCalled = true;
			return psis;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			getReaderCalled = true;
			if (getISCalled) {
				throw new IllegalStateException(
						"getInputStream has already been callded.");
			}
			if (br == null) {
				br = new BufferedReader(new InputStreamReader(psis));
			}
			return br;
		}

		public long getTimeOfLastByte() {
			return psis.getTimeOfLastByte();
		}

		public long getRequestLength() {
			return psis.getRequestLength();
		}

		public static class PerformanceServletInputStream extends
				ServletInputStream {
			private long timeOfLastByte = System.currentTimeMillis();
			private long requestLength = 0;
			private InputStream is = null;

			public PerformanceServletInputStream(InputStream is) {
				this.is = is;
			}

			@Override
			public int read() throws IOException {
				timeOfLastByte = System.currentTimeMillis();
				requestLength++;
				return is.read();
			}

			public long getTimeOfLastByte() {
				return timeOfLastByte;
			}

			public long getRequestLength() {
				return requestLength;
			}

		}
	}

	public static class PerformanceResponseWrapper extends
			HttpServletResponseWrapper {

		private PerformanceServletOutputStream psos = null;
		private PrintWriter pw = null;
		private boolean getOSCalled = false;
		private boolean getWriterCalled = false;

		public PerformanceResponseWrapper(HttpServletResponse response)
				throws IOException {
			super(response);
			psos = new PerformanceServletOutputStream(response
					.getOutputStream());
		}

		public PrintWriter getWriter() {
			if (getOSCalled) {
				throw new IllegalStateException(
						"getOutputStream has already been callded.");
			}
			getWriterCalled = true;
			if (pw == null) {
				pw = new PrintWriter(psos);
			}
			return pw;
		}

		public ServletOutputStream getOutputStream() {
			if (getWriterCalled) {
				throw new IllegalStateException(
						"getWriter has already been callded.");
			}
			getOSCalled = true;
			return psos;
		}

		public long getResponseLength() {
			return psos.getResponseLength();
		}

		public long getTimeOfFirstByte() {
			return psos.getTimeOfFirstByte();
		}

		public static class PerformanceServletOutputStream extends
				ServletOutputStream {
			private long timeOfFirstByte = System.currentTimeMillis();
			private long responseLength = 0;
			private OutputStream os;

			PerformanceServletOutputStream(OutputStream os) {
				this.os = os;
			}

			public void write(int param) throws IOException {
				if (responseLength++ == 0) {
					timeOfFirstByte = System.currentTimeMillis();
				}
				os.write(param);
			}

			public long getTimeOfFirstByte() {
				return timeOfFirstByte;
			}

			public long getResponseLength() {
				return responseLength;
			}

		}
	}

	private String getSessionId(HttpServletRequest request) {
		String tmp = null;
		try {
			tmp = request.getSession().getId();
		} catch (Exception e) {//NOSONAR
			tmp = "null";
		}
		return tmp;
	}

    public static String getThreadSessionId() {
        return threadSessionId.get();
    }
}
