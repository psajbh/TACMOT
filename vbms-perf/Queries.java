package gov.va.vba.vbms.common.framework.performance.impl;

/**
 * Created by IntelliJ IDEA.
 * User: CGunn
 * Date: 3/20/12
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public final class Queries {
    private Queries(){
    }
    public static final String INSERT_METHOD_QUERY = "INSERT INTO vbmsuiperf.MethodMetrics (startTime, totalTime, className, methodName, userName, "
            + "exceptionType, exceptionMessage, host, methodSource, serverName, threadID, sessionId, dataSize, userId, parameters) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_ACCESS_QUERY = "INSERT INTO vbmsuiperf.AccessMetrics (startTime, path, totalTime, uploadTime, downloadTime, responseLength,"
            + " remoteAddress, requestLength, userName, sessionId, host, userId) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    public static final String INSERT_ACTIVE_USER_QUERY = "INSERT INTO vbmsuiperf.ActiveUser (loginDateTime, sessionId, userID, status, securityLevel, stationId, "
            + " userRole, profile, clientIp, username, subject, emailAddress) VALUES ( ?, ?, ?, 'LOGIN', ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ACTIVE_USER_QUERY =	"UPDATE vbmsuiperf.ActiveUser SET logoutDateTime = ?, status = 'LOGOUT' WHERE sessionId = ? AND userID = ? ";

}
