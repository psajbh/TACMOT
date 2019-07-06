package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;

@Component   //note CommandLineRunner is a specific SpringBoot interface.
public class DataLoader implements CommandLineRunner {
	// this class will run automatically on startup because it is a spring bean (component)
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;

	@Autowired  //autowired not required as of Spring 5, but I think it is nice to note in the codebase.
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		// this is used to not load if already is loaded, i.e. when we use jpa.
		if (petTypeService.findAll().size() == 0) {
			loadData();
		}
		
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType dogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("cat");
		PetType catPetType = petTypeService.save(cat);
		
		System.out.println("loaded PetTypes.....");
		
		Speciality specialty1 = new Speciality();
		specialty1.setDescription("radiology");
		specialty1 = specialtyService.save(specialty1);
		
		
		Speciality specialty2 = new Speciality();
		specialty2.setDescription("dentistry");
		specialty2 = specialtyService.save(specialty2);
		
		Speciality specialty3 = new Speciality();
		specialty3.setDescription("surgery");
		specialty3 = specialtyService.save(specialty3);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Ryan");
		owner1.setLastName("Zimmerman");
		owner1.setAddress("11 Main Street");
		owner1.setCity("Fairfax");
		owner1.setTelephone("701-122-5434");
		
		//Owner.builder().id(7L).firstName("Bryce").lastName("Harper").address("123 Main Street").city("Herndon").telephone("555");
		
        Pet pet1 = new Pet();
        pet1.setName("sparky");
        pet1.setBirthDate(LocalDate.of(2015, 5, 15));
        pet1.setOwner(owner1);
        pet1.setPetType(dogPetType);
        
        owner1.getPets().add(pet1);
		owner1 = ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Trea");
		owner2.setLastName("Turner");
		owner2.setAddress("11 First Street");
	    owner2.setCity("Annandale");
	    owner2.setTelephone("703-122-3345");
	    
        Pet pet2 = new Pet();
        pet2.setName("tiger");
        pet2.setBirthDate(LocalDate.of(2015, 8, 30));
        pet2.setOwner(owner2);
        pet2.setPetType(catPetType);
        
        owner2.getPets().add(pet2);
        owner2 = ownerService.save(owner2);
        
        Owner owner3 = new Owner();
		owner3.setFirstName("Jack");
		owner3.setLastName("Turnover");
		owner3.setAddress("12 Third Street");
	    owner3.setCity("Springfield");
	    owner3.setTelephone("703-231-2243");
	    
        Pet pet3 = new Pet();
        pet3.setName("kitty");
        pet3.setBirthDate(LocalDate.of(2016, 6, 30));
        pet3.setOwner(owner3);
        pet3.setPetType(catPetType);
        
        owner3.getPets().add(pet3);
        owner3 = ownerService.save(owner3);

		System.out.println("loaded owners.....");
		
		Visit catVisit = new Visit();
		catVisit.setPet(pet2);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        
        visitService.save(catVisit);
		
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Max");
		vet1.setLastName("Scherzer");
		vet1.getSpecialties().add(specialty1);
		vet1.getSpecialties().add(specialty2);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Stephen");
		vet2.setLastName("Strasborg");
		vet2.getSpecialties().add(specialty3);
		
		vetService.save(vet2);
		
		System.out.println("loaded vets.....");
	}
	

}
