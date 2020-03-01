package mil.dtic.cbes.repositories.serviceagency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.views.r2.PeSuffixEntity;

@Repository
public interface PeSuffixRepository extends JpaRepository<PeSuffixEntity, String>{
	List<PeSuffixEntity> findByServiceAgencyId(Integer serviceAgencyId);

}
