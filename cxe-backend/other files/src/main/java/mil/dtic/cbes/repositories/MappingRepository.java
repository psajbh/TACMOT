package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.Mapping;

public interface MappingRepository extends JpaRepository<Mapping, Integer>{
	List<Mapping> findByTypeId(int id);
}
