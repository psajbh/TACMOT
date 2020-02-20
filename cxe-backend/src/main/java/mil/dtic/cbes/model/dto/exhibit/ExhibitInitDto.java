package mil.dtic.cbes.model.dto.exhibit;

import java.util.List;

import mil.dtic.cbes.model.dto.BudgetCycleDto;
import mil.dtic.cbes.model.dto.ServiceAgencyDto;

public class ExhibitInitDto {
	
	List<BudgetCycleDto> budgetCycles;
	List<ServiceAgencyDto> serviceAgencies;
	BudgetCycleDto currentBudgetCycle;
	
	public ExhibitInitDto() {};
	
	public List<BudgetCycleDto> getBudgetCycles() {
		return budgetCycles;
	}
	public void setBudgetCycles(List<BudgetCycleDto> budgetCycles) {
		this.budgetCycles = budgetCycles;
	}
	public List<ServiceAgencyDto> getServiceAgencies() {
		return serviceAgencies;
	}
	public void setServiceAgencies(List<ServiceAgencyDto> serviceAgencies) {
		this.serviceAgencies = serviceAgencies;
	}
	public BudgetCycleDto getCurrentBudgetCycle() {
		return currentBudgetCycle;
	}
	public void setCurrentBudgetCycle(BudgetCycleDto currentBudgetCycle) {
		this.currentBudgetCycle = currentBudgetCycle;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((budgetCycles == null) ? 0 : budgetCycles.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExhibitInitDto other = (ExhibitInitDto) obj;
		if (budgetCycles == null) {
			if (other.budgetCycles != null)
				return false;
		} else if (!budgetCycles.equals(other.budgetCycles))
			return false;
		return true;
	}
	
	

}
