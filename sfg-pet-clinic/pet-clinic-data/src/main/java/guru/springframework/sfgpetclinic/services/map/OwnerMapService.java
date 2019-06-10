package guru.springframework.sfgpetclinic.services.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService{
    
    private final PetService petService;
    private final PetTypeService petTypeService;
    
    @Autowired
    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }
    
	@Override
	public Owner findByLastName(String lastName) {
		/** old way **/
//		for (Owner owner : super.findAll()) {
//			if (owner.getLastName().contentEquals(lastName)) {
//				return owner;
//			}
//		}
//		return null;
		/** new way **/
		return this.findAll().stream().filter(owner -> owner.getLastName().equals(lastName)).findFirst().orElse(null);
	}
	
	//[[Java 8 - Stream filter() method example|https://www.boraji.com/java-8-stream-filter-method-example]]
	@Override
	public List<Owner> findAllByLastNameLike(String likeName){
		List<Owner> ownerList = new ArrayList<>();
		Set<Owner> ownerSet = super.findAll();
		Stream<Owner> stream = ownerSet.stream();
	
		
		/** old way **/
//		for (Owner owner : ownerSet) {
//			System.out.println("owner " + owner);
//			if (null == owner.getLastName()) {
//				continue;
//			} 
//			else if (!owner.getLastName().startsWith(likeName)) {
//				continue;
//			}
//			ownerList.add(owner);
//		}
		
		/** new way **/
		ownerList = stream.filter((owner) -> {
			return owner.getLastName() != null && owner.getLastName().startsWith(likeName);
		}).collect(Collectors.toList());
		
		return ownerList;
		
	}
    
    
	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Set<Owner> findAll(){
		return super.findAll();
	}
	
	
	@Override
	public Owner save(Owner owner) {
		if(null != owner) {
		    if (null != owner.getPets()) {
		        owner.getPets().forEach(pet -> {
		            if (pet.getPetType() != null) {
		                if(pet.getPetType().getId() == null) {
		                    pet.setPetType(petTypeService.save(pet.getPetType()));
		                }
		            }
		            else {
		                throw new RuntimeException("Pets must have a PetType");
		            }
		            if (null == pet.getId()) {
		                Pet savedPet = petService.save(pet);
		                pet.setId(savedPet.getId());
		            }
		        });
		    }
		    return super.save(owner);
		}
		else {
		    return null;
		}
	    
	    
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}


}
