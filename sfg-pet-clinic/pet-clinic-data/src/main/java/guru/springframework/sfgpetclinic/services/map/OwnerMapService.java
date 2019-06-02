package guru.springframework.sfgpetclinic.services.map;

import java.util.List;
import java.util.Set;

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
		return null;
	}
	
	@Override
	public List<Owner> findAllByLastNameLike(String lastName){
		return null;
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
