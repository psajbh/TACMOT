package mil.dtic.cbes.repositories.exhibit.r2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.r2.ProgramElementEntity;

@Repository
public interface ProgramElementEntityRepository extends JpaRepository<ProgramElementEntity, Integer> {

}
