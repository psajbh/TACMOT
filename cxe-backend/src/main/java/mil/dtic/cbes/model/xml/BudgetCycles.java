package mil.dtic.cbes.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "budgetcycles")
public class BudgetCycles {
	
	List<BudgetCycle> budgetCycles;
	
	public List<BudgetCycle> getBudgetCycles(){
		return budgetCycles;
	}
	
	@XmlElement(name = "budgetcycle")
	public void setBudgetCycles(List<BudgetCycle> budgetCycles) {
		this.budgetCycles = budgetCycles;
	}
	
	public void add(BudgetCycle budgetCycle) {
		if (this.budgetCycles == null) {
			this.budgetCycles = new ArrayList<BudgetCycle>();
		}
		this.budgetCycles.add(budgetCycle);
	}
	

}
