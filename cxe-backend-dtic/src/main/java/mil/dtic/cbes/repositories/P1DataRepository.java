package mil.dtic.cbes.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mil.dtic.cbes.model.entities.P1DataEntity;
import mil.dtic.cbes.model.entities.R1DataEntity;

public interface P1DataRepository extends JpaRepository<P1DataEntity, Integer>{
	
	@Query(value = "SELECT p FROM P1DataEntity p WHERE p.budgetYear = :budgetYear AND p.organization IN :organizationList")
	public List<P1DataEntity> findAllForYearAndOrganization(@Param("budgetYear") Integer budgetYear,
			@Param("organizationList") Collection<String> organizationList);
}
