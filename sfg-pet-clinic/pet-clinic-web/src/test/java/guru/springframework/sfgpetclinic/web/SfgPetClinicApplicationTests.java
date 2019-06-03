package guru.springframework.sfgpetclinic.web;

// JUnit4 -> JUnit5 changes.  also, look at pom.xml changes.

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SfgPetClinicApplicationTests {

	@Test
	public void contextLoads() {
	}

}
