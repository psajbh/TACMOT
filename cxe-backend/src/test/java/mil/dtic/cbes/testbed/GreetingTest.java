package mil.dtic.cbes.testbed;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreetingTest {
	
	private Greeting greeting;
	
	@BeforeAll
	public static void beforeClass(){
		System.out.println("start testing Greeting class");
	}

	@AfterAll
	public static void afterClass() {
		System.out.println("finished testing Greeting class");
	}
	
	@BeforeEach
	void setup() {
		greeting = new Greeting();
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("tearDown-");
	}
	
	@Test
    void helloWorl1d() {
        System.out.println(greeting.helloWorld());

    }

    @Test
    void helloWorld1() {
        System.out.println(greeting.helloWorld("John"));
    }
    
    @Test
    void helloWorld2() {
        System.out.println(greeting.helloWorld("Sam"));
    }	
    

}
