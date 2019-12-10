package gov.va.vba.vbms.common.framework.performance;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Verifications;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: CGunn
 * Date: 3/21/12
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBlockingQueueAlmostSilentOfferTest {
    @Test
    public void testOfferAlmostSilently(@Cascading final LoggerFactory loggerFactory, @Cascading final Logger logger){
        Deencapsulation.setField(LinkedBlockingQueueAlmostSilentOffer.class,"logger",logger);
        LinkedBlockingQueueAlmostSilentOffer queue = new LinkedBlockingQueueAlmostSilentOffer(1,"Test");

        queue.offerAlmostSilently(new Object());// should not log since queue isn't full
        new Verifications(){{
            logger.error(anyString);times = 0;
        }};

        queue.offerAlmostSilently(new Object());// should log since queue is full
        new Verifications(){{
            logger.error(anyString);times = 1;
        }};

        queue.offerAlmostSilently(new Object());// should not log again since we logged recently so times in this test should still be 1 not 2
        new Verifications(){{
            logger.error(anyString);times = 1;
        }};
    }
}
