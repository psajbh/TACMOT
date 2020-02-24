package mil.dtic.cbes.repositories.exhibit.projection.r2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.entities.views.R2AppnBudgetActivityEntity;

public interface R2AppnBudgetActivityProjectionRepository extends JpaRepository<R2AppnBudgetActivityEntity, Integer> {
	
	List<R2AppnBudgetActivityEntity> findByServiceAgencyId(Integer id);
	List<R2AppnBudgetActivityEntity> findByServiceAgencyIdAndAppropriationIdAndBudgetActivityIdAndBudgetSubActivityId(Integer serviceAgencyId, 
			Integer appropriationId, Integer budgetActivityId, Integer budgetSubActivityId);

}