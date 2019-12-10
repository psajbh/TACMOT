package gov.va.vba.vbms.common.framework.performance.impl;

import mockit.Deencapsulation;
import org.junit.Test;

public class QueriesTest {
	@Test
	public void testQueries(){
		Queries queries = Deencapsulation.newInstance(Queries.class);
		String temp = queries.INSERT_METHOD_QUERY;
	}
}
