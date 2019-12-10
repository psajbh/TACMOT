package gov.va.vba.vbms.common.framework.performance.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A handler for rejected tasks that almost silently discards the rejected task.
 * A message will be logged at error level warning of the discard if a message has not been logged in the last 5 minutes
 */
public class AlmostSilentDiscardPolicy implements RejectedExecutionHandler {
	private static Logger logger = LoggerFactory.getLogger(AlmostSilentDiscardPolicy.class);
	private long lastWarning = 0;
	private static final long WARNING_INTERVAL = 300000;// 5 minutes in milliseconds
	/**
	 * Creates a <tt>DiscardPolicy</tt>.
	 */
	public AlmostSilentDiscardPolicy() {
	}

	/**
	 * Does nothing but log that a request is being discarded, which has the effect of discarding task r.
	 * 
	 * @param r
	 *            the runnable task requested to be executed
	 * @param e
	 *            the executor attempting to execute this task
	 */
	public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
		if(System.currentTimeMillis() > lastWarning + WARNING_INTERVAL){
			logger.error("PerformanceData Queue is full! Requests to store performance data are being discarded.  This message will not be repeated for 5 minutes. Current queue size is approximately " + e.getQueue().size());
			lastWarning = System.currentTimeMillis();
		}
	}

}
