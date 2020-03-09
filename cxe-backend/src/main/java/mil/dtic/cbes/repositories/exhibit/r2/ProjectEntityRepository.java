package mil.dtic.cbes.repositories.exhibit.r2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.exhibit.r2.ProjectEntity;

@Repository
public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, Integer>{

}
