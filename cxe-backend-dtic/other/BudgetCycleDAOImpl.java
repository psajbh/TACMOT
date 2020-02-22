/*
 * $Id$
 */
package mil.dtic.cbes.submissions.dao.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.constants.AppDefaults;
import mil.dtic.cbes.submissions.ValueObjects.BudgetCycle;
import mil.dtic.cbes.submissions.dao.BudgetCycleDAO;

@Component
public class BudgetCycleDAOImpl implements BudgetCycleDAO{
	
	@Autowired
	private AppDefaults appDefaults;

	@Override
	public BudgetCycle findByValue(String value)  {
		BudgetCycle cycle = null;
		for (BudgetCycle budgetCycle : getBudgetCycles()) {
			if (budgetCycle.getValue().equals(value)){
				cycle = budgetCycle;
				break;
			}
		}
      return cycle;
	}

	@Override
	public BudgetCycle findByCycleAndBudgetYear(String cycle, Integer budgetYear)  {
		return (findByValue(cycle + " " + budgetYear));
	}


	@Override
	public List<BudgetCycle> getBudgetCycles() {
		return appDefaults.getBudgetCycles();
	}
}


