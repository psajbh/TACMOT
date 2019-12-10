package gov.va.vba.vbms.common.framework.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: CGunn
 * Date: 3/21/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */

public class LinkedBlockingQueueAlmostSilentOffer<E> extends LinkedBlockingQueue<E> {
    private static final long serialVersionUID = 8419396608761537170L;
    private static Logger logger = LoggerFactory.getLogger(LinkedBlockingQueueAlmostSilentOffer.class);
    private static final long WARNING_INTERVAL = 300000;// 5 minutes in milliseconds
    private long lastWarning = 0;
    private String name = null;

    /**
     *
     * @param capacity the capacity of this queue
     * @param name A name for this queue to be included in log messages
     */
    public LinkedBlockingQueueAlmostSilentOffer(int capacity, String name){
        super(capacity);
        this.name = name;
    }

    /**
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * If this queue is full an error will be logged at most once every 5 minutes
     * When using a capacity-restricted queue, this method is generally
     * preferable to method {@link java.util.concurrent.BlockingQueue#add add}, which can fail to
     * insert an element only by throwing an exception.
     *
     * @throws NullPointerException if the specified element is null
     */
    public void offerAlmostSilently(E e) {
        boolean added = offer(e);
        if(!added && System.currentTimeMillis() > lastWarning + WARNING_INTERVAL){
            logger.error("PerformanceData Queue is full! Requests to store ("+name+") performance data are being discarded.  This message will not be repeated for 5 minutes. Current queue size is approximately " + this.size());
            lastWarning = System.currentTimeMillis();
        }
    }

}
