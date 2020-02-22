package mil.dtic.cbes.service.config;

import java.util.List;

import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDates;

public interface AppDefaultsService {
	
	SubmissionDates getSubmissionDates();
	BudgetCycles getBudgetCycles();
	List<SubmissionDateDto> getSubmissionDateDtos();
	List<BudgetCycleDto> getBudgetCycleDtos();

}
