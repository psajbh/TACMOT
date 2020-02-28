package mil.dtic.cbes.repositories.serviceagency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.exhibit.P40ServiceAgencyAppnActivityEntity;

@Repository
public interface P40ServiceAgencyRepository extends JpaRepository<P40ServiceAgencyAppnActivityEntity, Integer>{

	List<P40ServiceAgencyAppnActivityEntity> findByServiceAgencyId(Integer serviceAgencyId);
}
