package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.security.RoleEntity;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    
}
