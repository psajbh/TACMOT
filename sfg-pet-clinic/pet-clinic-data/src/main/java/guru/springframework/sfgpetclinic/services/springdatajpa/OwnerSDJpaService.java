package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService{
	
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	
    @Autowired
	public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		super();
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return null;
	}
	
	@Override
	public List<Owner> findAllByLastNameLike(String lastName){
		return null;
	}

	@Override
	public Set<Owner> findAll(){
		Set<Owner> owners= new HashSet<>();
		Iterable<Owner> iterableOwners = ownerRepository.findAll();
		
//		for(Owner owner : iterableOwners) {
//			owners.add(owner);
//		}
		ownerRepository.findAll().forEach(owners :: add);
		return owners;
	}
	
	public Owner findById(Long id) {
		//[[Difference between `Optional.orElse()` and `Optional.orElseGet()`|https://stackoverflow.com/questions/33170109/difference-between-optional-orelse-and-optional-orelseget]]
		/*
		 * Optional<Owner> optOwner = ownerRepository.findById(id); if
		 * (optOwner.isPresent()) { return optOwner.get(); }
		 */		
		return ownerRepository.findById(id).orElse(null);
	}
	
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}
	
	public void delete(Owner owner) {
		ownerRepository.delete(owner);
	}
	
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);
	}

}
