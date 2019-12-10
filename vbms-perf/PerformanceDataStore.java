package gov.va.vba.vbms.common.framework.performance;

import gov.va.vba.vbms.common.framework.performance.impl.FieldSizes;
import gov.va.vba.vbms.common.framework.performance.impl.Util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;


public abstract class PerformanceDataStore {

	public abstract void insertAccessData(final long startTime,
			final String path, final long totalTime, final long uploadTime,
			final long downloadTime, final long responseLength,
			final String remoteAddr, final long requestLength,
			final String userName, final String sessionId,
            final String userId);

	public abstract void insertMethodData(final long startTime,
			final long totalTime, final String className,
			final String methodName, final String user,
			final String exceptionType, final String exceptionMessage, final String methodSource,
            final String serverName, final long threadID, final String threadSessionId, final long dataSize,
            final String userId, final String parameters);
	
	public abstract void insertActiveUser(final long startTime, final String user, 
			final String sessionId, Map<String, String> profile);

	public abstract void logout(final String user, final String sessionId, final long logoutTime);
	
	public abstract void insertInsertData(final long startTime,	final long totalTime,
			final long insertTime,final long filterTime, final String tableName);
	
	public abstract boolean isAccessEnabled();
	public abstract boolean isMethodEnabled();
	public abstract boolean isActiveUserEnabled();
	public abstract boolean isInsertEnabled();
    public abstract boolean isBatchEnabled();

    public abstract LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueMethods();
    public abstract LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueAccess();
    public abstract LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueActiveUsersInserts();
    public abstract LinkedBlockingQueueAlmostSilentOffer<Object[]> getQueueActiveUsersUpdates();

	private static String host = null;
	static{
	    staticInit();
	}

	private static void staticInit() {
		try {
	    	InetAddress local = InetAddress.getLocalHost();
			host = Util.trimField(local.getCanonicalHostName() + " " + local.getHostAddress(),FieldSizes.HOST_FIELD_SIZE);
		} catch (UnknownHostException e) {
			host = "unknown";
		}
	}

	/**
	 * @return the host
	 */
	public static String getHost() {
		return host;
	}


}
