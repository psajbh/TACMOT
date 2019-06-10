package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerMapServiceTest {
	
	OwnerMapService ownerMapService;
	
	private final Long id = 1L;

	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
		ownerMapService.save(Owner.builder().id(id).build()); //generate an Owner object.
	}

	@Test
	void testFindByLastName() {
		String lastName = "Jones";
		Owner owner = Owner.builder().id(id).lastName(lastName).build();
		ownerMapService.save(owner);
		owner = ownerMapService.findByLastName(lastName);
		assertEquals(lastName,  owner.getLastName());
	}

	@Test
	void testFindAllByLastNameLike() {
		ownerMapService.save(Owner.builder().id(2L).lastName("Jones").build());
		ownerMapService.save(Owner.builder().id(3L).lastName("Jonson").build());
		ownerMapService.save(Owner.builder().id(4L).lastName("Jonjacoby").build());
		ownerMapService.save(Owner.builder().id(5L).lastName("Joncanoy").build());
		ownerMapService.save(Owner.builder().id(6L).lastName("Jacobs").build());
		List<Owner> owners = ownerMapService.findAllByLastNameLike("Jon");
		assertEquals(4,owners.size());
		
	}

	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(id);
		assertNotNull(owner);
		assertEquals(id, owner.getId());
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = ownerMapService.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	void testSaveOwner() {
		Long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(id, savedOwner.getId());
	}
	
	@Test
	void testSaveOwnerNoId() throws Exception{
		Owner owner3 = Owner.builder().build();
		Owner savedOwner = ownerMapService.save(owner3);
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void testDeleteOwner() {
		Owner owner = ownerMapService.findById(id);
		ownerMapService.delete(owner);
		Set<Owner> owners = ownerMapService.findAll();
		assertTrue(owners.size() == 0);
	}

	@Test
	void testDeleteByIdLong() {
		Owner owner = ownerMapService.findById(id);
		ownerMapService.deleteById(owner.getId());
		Set<Owner> owners = ownerMapService.findAll();
		assertTrue(owners.size() == 0);
	}

}
