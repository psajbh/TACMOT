package guru.springframework.repositories;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.model.UnitOfMeasure;

/**
 * UnitOfMeasureRepositoryIT Integration Test
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findByUomIT() throws Exception{
		Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepository.findByUom("Teaspoon");
		assertEquals("Teaspoon", optionalUom.get().getUom());
	}
	
	@Test
	@DirtiesContext  //DirtiesContext will reestablish the spring context.
	public void findByUomCupIT() throws Exception{
		Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepository.findByUom("Cup");
		assertEquals("Cup", optionalUom.get().getUom());
	}
	

}
