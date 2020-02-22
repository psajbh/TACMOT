package mil.dtic.cbes.testbed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonTest {

	@Test
	void groupedAssertions() {
		// given
		Person person = new Person(1, "Joe", "Buck");

		// then
		assertAll("Test Props Set", () -> assertEquals(person.getFirstName(), "Joe"),
				() -> assertEquals(person.getLastName(), "Buck"));
	}

	@Test
	void groupedAssertionMsgs() {
		// given
		Person person = new Person(1, "Joe", "Buck");

		// then
		assertAll("Test Props Set", () -> assertEquals(person.getFirstName(), "Joe", "First Name Failed"),
				() -> assertEquals(person.getLastName(), "Buck", "Last Name Failed"));
	}
}
