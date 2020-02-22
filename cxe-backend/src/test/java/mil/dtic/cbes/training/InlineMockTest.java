package mil.dtic.cbes.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class InlineMockTest {
	
	@Test
	void testInlineMock() {
		
		Map mapMock = mock(Map.class);  //this is an inline mock.
		assertEquals(mapMock.size(), 0);
		
	}

}
