package mil.dtic.cbes.repositories.core.p40;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.exhibit.p40.P40ServiceAgencyAppnActivityEntity;

@Repository
public interface P40ServiceAgencyRepository extends JpaRepository<P40ServiceAgencyAppnActivityEntity, Integer>{

	List<P40ServiceAgencyAppnActivityEntity> findByServiceAgencyId(Integer serviceAgencyId);
}
