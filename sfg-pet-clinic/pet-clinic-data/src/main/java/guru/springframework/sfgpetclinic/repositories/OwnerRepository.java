
package guru.springframework.sfgpetclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	// this is using SpringData see: Spring Data JPA - Reference Documentation|https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	Owner findByLastName(String lastName);
	
	//@Query("select u from #{#entityName} u where u.lastname = %?1")
	// the below works but you have to manually add the % around the value.
	List<Owner> findAllByLastNameLike(String lastName);
}
