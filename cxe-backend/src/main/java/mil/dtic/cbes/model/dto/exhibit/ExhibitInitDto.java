package mil.dtic.cbes.model.dto.exhibit;

import java.util.List;

import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.serviceagency.R2ServiceAgencyDto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;


public class ExhibitInitDto {
	
	List<BudgetCycleDto> budgetCycles;
	List<R2ServiceAgencyDto> r2ServiceAgencies;  //TODO: add support for P40 service agencies
	BudgetCycleDto currentBudgetCycle;
	ServiceAgencyDto selctedServiceAgency;  //
	
	public ExhibitInitDto() {};
	
	public List<BudgetCycleDto> getBudgetCycles() {
		return budgetCycles;
	}
	public void setBudgetCycles(List<BudgetCycleDto> budgetCycles) {
		this.budgetCycles = budgetCycles;
	}
	public List<R2ServiceAgencyDto> getR2ServiceAgencies() {
		return r2ServiceAgencies;
	}
	public void setR2ServiceAgencies(List<R2ServiceAgencyDto> r2ServiceAgencies) {
		this.r2ServiceAgencies = r2ServiceAgencies;
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

	@Override
	public String toString() {
		return "ExhibitInitDto [budgetCycles=" + budgetCycles + ", r2ServiceAgencies=" + r2ServiceAgencies
				+ ", currentBudgetCycle=" + currentBudgetCycle + "]";
	}
	
	

}
