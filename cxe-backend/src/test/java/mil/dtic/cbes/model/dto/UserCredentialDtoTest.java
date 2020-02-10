package mil.dtic.cbes.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCredentialDtoTest {
	private static final Logger log = LoggerFactory.getLogger(UserCredentialDtoTest.class);
	
	UserCredentialDto userCredentialDto;
	 
	@AfterAll
	static void done() {
	    log.info("done- AfterAll - executed UserCredentialDtoTest test methods.");
	}
	@BeforeEach
	void setup() {
		log.debug("setup-");
		userCredentialDto = new UserCredentialDto();
		userCredentialDto.setName("Joe Tester");
		userCredentialDto.setUserId(1);
		userCredentialDto.setUserRole(UserCredentialDto.GROUP_R2_APP_ADMIN);
		userCredentialDto.setStrAgencies("ABC");
		userCredentialDto.setRoleId("1");
		userCredentialDto.setRoleName("roleName");
		userCredentialDto.setStatus("A");
		userCredentialDto.setValid(true);
	}
	
	@AfterEach
	public void tearDown() {
		log.debug("teardown-");
		userCredentialDto = null;
	}
	
	@Test
	public void getName() {
		assertEquals(userCredentialDto.getName(),"Joe Tester");
		assertNotEquals(userCredentialDto.getName(),"Joe Test");
	}

}
