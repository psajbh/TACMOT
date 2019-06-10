package guru.springframework.sfgpetclinic.services.springdatajpa;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
		Owner owner = ownerRepository.findByLastName(lastName);
		return owner;
		
	}
	
	@Override
	public List<Owner> findAllByLastNameLike(String likeName){
		List<Owner> ownerList = new ArrayList<>();
		Iterable<Owner> repoOwners = ownerRepository.findAll();
		Spliterator<Owner> spliterator = repoOwners.spliterator();
		Stream<Owner> ownerStream = StreamSupport.stream(spliterator, false);
		
		ownerList = ownerStream.filter((owner) -> {
			return owner.getLastName() != null && owner.getLastName().startsWith(likeName);
		}).collect(Collectors.toList());

		return ownerList;
	}

	@SuppressWarnings("unused")
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
