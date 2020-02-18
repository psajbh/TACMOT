package mil.dtic.cbes.service.config;

import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDates;

public interface AppDefaultsService {
	
	SubmissionDates getSubmissionDates();
	BudgetCycles getBudgetCycles();

}
