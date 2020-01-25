package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mil.dtic.cbes.model.entities.RoleEntity;

public interface RoleEntityRepository extends CrudRepository<RoleEntity, Integer>{
	List<RoleEntity> findAll();
}
