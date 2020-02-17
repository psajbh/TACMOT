package mil.dtic.cbes.service.config;

import java.util.List;

import mil.dtic.cbes.model.xml.BudgetCycle;
import mil.dtic.cbes.model.xml.SubmissionDate;

public interface AppDefaultsService {
	
	List<SubmissionDate> getSubmissionDates();
	List<BudgetCycle> getBudgetCycles();

}
