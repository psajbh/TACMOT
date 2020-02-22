package mil.dtic.cbes.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mil.dtic.cbes.model.entities.R1DataEntity;

public interface R1DataRepository extends JpaRepository<R1DataEntity, Integer> {
	
	@Query(value = "SELECT r FROM R1DataEntity r WHERE r.budgetYear = :budgetYear AND r.organization IN :organizationList")
	public List<R1DataEntity> findAllForYearAndOrganization(@Param("budgetYear") Integer budgetYear,
			@Param("organizationList") Collection<String> organizationList);
}
