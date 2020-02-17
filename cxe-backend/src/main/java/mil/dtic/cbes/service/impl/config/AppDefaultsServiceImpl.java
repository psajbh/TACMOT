package mil.dtic.cbes.service.impl.config;

import java.util.List;

import mil.dtic.cbes.model.xml.BudgetCycle;
import mil.dtic.cbes.model.xml.SubmissionDate;
import mil.dtic.cbes.service.config.AppDefaultsService;

public class AppDefaultsServiceImpl implements AppDefaultsService {
	
	@Override
	public List<SubmissionDate> getSubmissionDates(){
		return null;
	}
	
	@Override
	public List<BudgetCycle> getBudgetCycles(){
		return null;
	}

}
