package mil.dtic.cbes.service.budgetcycle;

import java.util.Calendar;

import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;

public interface BudgetCycleService {
	
	BudgetCycleDto getCurrentBudgetCycle(Calendar currentDate);

}
