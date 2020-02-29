package mil.dtic.cbes.repositories.serviceagency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.exhibit.R2ServiceAgencyAppnActivityEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;

@Repository
public interface R2ServiceAgencyAppnActivityRepository extends JpaRepository<R2ServiceAgencyAppnActivityEntity, Integer>{
	List<R2ServiceAgencyAppnActivityEntity> findByServiceAgencyId(Integer serviceAgencyId);

}
