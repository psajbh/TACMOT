package mil.dtic.cbes.repositories.serviceagency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.views.r2.R2ServiceAgencyEntity;

@Repository
public interface R2ServiceAgencyRepository extends JpaRepository<R2ServiceAgencyEntity, Integer>{

}
