package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.impl.AlmostSilentDiscardPolicy;

import java.util.concurrent.ThreadPoolExecutor;

import mockit.Cascading;
import mockit.Deencapsulation;
import mockit.Expectations;

import org.slf4j.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class AlmostSilentDiscardPolicyTest {
	@Cascading LoggerFactory loggerFactory;
	@Cascading Logger logger;
	
	@Test
	public void testRejectedExecution(@Cascading final Runnable r, @Cascading final ThreadPoolExecutor e){
		AlmostSilentDiscardPolicy asdp = new AlmostSilentDiscardPolicy();
		Deencapsulation.setField(AlmostSilentDiscardPolicy.class,"logger", logger);
		new Expectations(){
			{
				e.getQueue().size(); result = 2;
				logger.error(anyString);
			}
		};
		asdp.rejectedExecution(r, e);
		asdp.rejectedExecution(r, e);
	}

}
