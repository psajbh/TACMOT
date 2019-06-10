package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension; //does not auto import, had to write it in.

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	private final static String LAST_NAME = "Smith";
	private final static String LAST_NAME_2 = "Smithfield";
	private final static String LAST_NAME_3 = "Smithsonian";
	private final static String LAST_NAME_4 = "Smythe";
	private final static String NAME_LIKE = "Smith";
	
	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetRepository petRepository;
	@Mock
	PetTypeRepository petTypeRepository;
	@InjectMocks
	OwnerSDJpaService ownerJpaService;

	private Owner owner1;
	private Owner owner2;
	private Owner owner3;
	private Owner owner4;
	private Set<Owner> owners = new HashSet<>();
	private List<Owner> listOwners = new ArrayList<>();
	

	@BeforeEach
	void setUp() throws Exception {
		owner1 = Owner.builder().id(1L).lastName(LAST_NAME).build();
		owner2 = Owner.builder().id(2L).lastName(LAST_NAME_2).build();
		owner3 = Owner.builder().id(3L).lastName(LAST_NAME_3).build();
		owner4 = Owner.builder().id(4L).lastName(LAST_NAME_4).build();
		listOwners.add(owner1);
		listOwners.add(owner2);
		listOwners.add(owner3);
		listOwners.add(owner4);
		owners.add(owner1);
		owners.add(owner2);
		owners.add(owner3);
		owners.add(owner4);
	}
	
	private Owner returnOwner;

	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(owner1);
		returnOwner = ownerJpaService.findByLastName(LAST_NAME);
		assertNotNull(returnOwner);
		assertEquals(returnOwner.getLastName(),LAST_NAME);
		verify(ownerRepository).findByLastName(any());
	}

	@Test
	void testFindAllByLastNameLike() {
		when(ownerRepository.findAll()).thenReturn(listOwners);
		List<Owner> ownerList = ownerJpaService.findAllByLastNameLike(NAME_LIKE);
		for (Owner owner : ownerList) {
			assertTrue(owner.getLastName().startsWith(NAME_LIKE));
		}
	}

	@Test
	void testFindAll() {
		when(ownerRepository.findAll()).thenReturn(owners);
		Set<Owner> set = ownerJpaService.findAll();
		assertNotNull(set);
		assertEquals(4,set.size());
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner1));
		Owner owner = ownerJpaService.findById(1L);
		assertNotNull(owner);
	}
	
	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Owner owner = ownerJpaService.findById(1L);
		assertNull(owner);
	}

	@Test
	void testSave() {
		when(ownerRepository.save(owner1)).thenReturn(owner1);
		Owner savedOwner = ownerJpaService.save(owner1);
		assertNotNull(savedOwner);
		assertEquals(savedOwner.getLastName(), LAST_NAME);
		verify(ownerRepository, times(1)).save(any());
	}

	@Test
	void testDelete() {
		ownerJpaService.delete(owner1);
		verify(ownerRepository).delete(any());
	}

	@Test
	void testDeleteById() {
		ownerJpaService.deleteById(owner1.getId());
		verify(ownerRepository).deleteById(any());
	}

}
