package mil.dtic.cbes.training;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnnotationMocksTest {
	
	@Mock
	Map<String, Object> mapMock;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testMock() {
		mapMock.put("keyVaue", "foo");
	}

}
