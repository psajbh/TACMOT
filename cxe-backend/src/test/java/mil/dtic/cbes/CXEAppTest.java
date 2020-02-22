package mil.dtic.cbes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mil.dtic.cbes.controllers.security.CxeSecurityController;

@SpringBootTest
public class CXEAppTest {
	
	@Autowired
	CxeSecurityController controller;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
