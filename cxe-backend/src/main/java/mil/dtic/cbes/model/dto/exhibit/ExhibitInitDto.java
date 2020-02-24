package mil.dtic.cbes.model.dto.exhibit;

import java.util.List;

import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;


public class ExhibitInitDto extends Dto {
	
	List<BudgetCycleDto> budgetCycles;
	List<ServiceAgencyProjectionDto> r2ServiceAgencies;  //TODO: add support for P40 service agencies
	List<AppnBudgetActivityProjectionDto> appnBudgetActivities;
	BudgetCycleDto currentBudgetCycle;
	Integer selectedServiceAgencyId; 
	String selectedBudgetCycleId;
	Integer selectedAppnId;
	Integer selectedBudgetActivityId;
	Integer selectedBudgetSubActivityId;
	
	public ExhibitInitDto() {};
	
	public List<BudgetCycleDto> getBudgetCycles() {
		return budgetCycles;
	}
	
	public void setBudgetCycles(List<BudgetCycleDto> budgetCycles) {
		this.budgetCycles = budgetCycles;
	}
	
	public List<ServiceAgencyProjectionDto> getR2ServiceAgencies() {
		return r2ServiceAgencies;
	}
	
	public void setR2ServiceAgencies(List<ServiceAgencyProjectionDto> r2ServiceAgencies) {
		this.r2ServiceAgencies = r2ServiceAgencies;
	}
	
	public BudgetCycleDto getCurrentBudgetCycle() {
		return currentBudgetCycle;
	}
	
	public void setCurrentBudgetCycle(BudgetCycleDto currentBudgetCycle) {
		this.currentBudgetCycle = currentBudgetCycle;
	}
	
	public List<AppnBudgetActivityProjectionDto> getAppnBudgetActivities() {
		return appnBudgetActivities;
	}

	public void setAppnBudgetActivities(List<AppnBudgetActivityProjectionDto> appnBudgetActivities) {
		this.appnBudgetActivities = appnBudgetActivities;
	}
	
	public Integer getSelectedServiceAgencyId() {
		return selectedServiceAgencyId;
	}

	public void setSelectedServiceAgencyId(Integer selectedServiceAgencyId) {
		this.selectedServiceAgencyId = selectedServiceAgencyId;
	}
	
	public String getSelectedBudgetCycleId() {
		return selectedBudgetCycleId;
	}

	public void setSelectedBudgetCycleId(String selectedBudgetCycleId) {
		this.selectedBudgetCycleId = selectedBudgetCycleId;
	}

	public Integer getSelectedAppnId() {
		return selectedAppnId;
	}

	public void setSelectedAppnId(Integer selectedAppnId) {
		this.selectedAppnId = selectedAppnId;
	}

	public Integer getSelectedBudgetActivityId() {
		return selectedBudgetActivityId;
	}

	public void setSelectedBudgetActivityId(Integer selectedBudgetActivityId) {
		this.selectedBudgetActivityId = selectedBudgetActivityId;
	}

	public Integer getSelectedBudgetSubActivityId() {
		return selectedBudgetSubActivityId;
	}

	public void setSelectedBudgetSubActivityId(Integer selectedBudgetSubActivityId) {
		this.selectedBudgetSubActivityId = selectedBudgetSubActivityId;
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
