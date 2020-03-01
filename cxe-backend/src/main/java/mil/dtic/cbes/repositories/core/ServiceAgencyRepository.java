 package mil.dtic.cbes.repositories.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;

@Repository
public interface ServiceAgencyRepository extends JpaRepository<ServiceAgencyEntity, Integer> {

}
