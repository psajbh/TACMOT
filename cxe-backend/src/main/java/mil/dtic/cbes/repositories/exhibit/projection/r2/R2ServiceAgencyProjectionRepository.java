package mil.dtic.cbes.repositories.exhibit.projection.r2;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.entities.views.r2.R2ServiceAgencyEntity;

public interface R2ServiceAgencyProjectionRepository extends JpaRepository<R2ServiceAgencyEntity, Integer>{

}
