package mil.dtic.cbes.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.entities.FeatureEntity;


public interface FeatureAccessRepository extends JpaRepository<FeatureEntity, Integer>{

}
