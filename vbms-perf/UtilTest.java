package gov.va.vba.vbms.common.framework.performance.impl;

import gov.va.vba.vbms.common.framework.performance.impl.Util;
import mockit.Deencapsulation;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

	@Test
	public void testTrimField(){
		Deencapsulation.newInstance(Util.class);
		Assert.assertEquals("abc", Util.trimField("abcde", 3));
		Assert.assertEquals("abcde", Util.trimField("abcde", 5));
		Assert.assertEquals("abcde", Util.trimField("abcde", 10));
		Assert.assertEquals(null, Util.trimField(null, 10));
	}
}
