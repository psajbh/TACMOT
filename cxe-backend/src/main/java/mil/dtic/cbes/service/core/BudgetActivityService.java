package mil.dtic.cbes.service.core;

import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.dto.core.BudgetActivityDto;

@Repository
public interface BudgetActivityService {
	
	BudgetActivityDto getR2BudgetActivity(Integer id);
	BudgetActivityDto getP40BudgetActivity(Integer id);

}
