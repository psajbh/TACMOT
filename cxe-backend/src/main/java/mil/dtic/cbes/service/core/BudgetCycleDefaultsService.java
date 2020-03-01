package mil.dtic.cbes.service.core;

import java.util.List;

import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDates;

public interface BudgetCycleDefaultsService {
	
	SubmissionDates getSubmissionDates();
	BudgetCycles getBudgetCycles();
	List<SubmissionDateDto> getSubmissionDateDtos();
	List<BudgetCycleDto> getBudgetCycleDtos();

}
