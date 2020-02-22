package mil.dtic.cbes.testbed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerTest {
	
	@Test
    void dependentAssertions() {

        Owner owner = new Owner(1, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
                        () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City Did Not Match"),
                        () -> assertEquals("1231231234", owner.getTelephone())
                ));
    }
	
//	@Test
//	void dependentFail() {
//		
//        Owner owner = new Owner(1, "Joe", "Buck");
//        owner.setCity("Key West");
//        owner.setTelephone("1231231234");
//		assertAll("",() -> fail("Joe1", owner.getFirstName(), "First Name Did not Match"));
//	}

}
