package mil.dtic.cbes.service.core;

import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.dto.core.BudgetActivityDto;

@Repository
public interface BudgetActivityService {
	
	BudgetActivityDto getBudgetActivity(Integer id);

}
