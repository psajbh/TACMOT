package mil.dtic.cbes.model.dto.exhibit;

import java.util.ArrayList;
import java.util.List;

import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.core.AppropriationDto;
import mil.dtic.cbes.model.dto.core.PeSuffixDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;


public class ExhibitInitDto extends Dto {
	
	List<BudgetCycleDto> budgetCycles;
	BudgetCycleDto currentBudgetCycle;
	List<ServiceAgencyProjectionDto> r2ServiceAgencies;
	List<AppropriationDto> appropriations;
	List<AppnBudgetActivityProjectionDto> appnBudgetActivities;
	List<PeSuffixDto> serivceAgencyPESuffixs;
	String selectedBudgetCycleId;
	Integer selectedServiceAgencyId;
	Integer selectedBudgetActivityId;
	Integer selectedBudgetSubActivityId;
	String programElementNumber;
	String programElementName;
	Integer r1LineNumber;
	String tag;
	boolean testPe;
	boolean r2Long;
	
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

	public String getProgramElementNumber() {
		return programElementNumber;
	}

	public void setProgramElementNumber(String programElementNumber) {
		this.programElementNumber = programElementNumber;
	}

	public String getProgramElementName() {
		return programElementName;
	}

	public void setProgramElementName(String programElementName) {
		this.programElementName = programElementName;
	}

	public Integer getR1LineNumber() {
		return r1LineNumber;
	}

	public void setR1LineNumber(Integer r1LineNumber) {
		this.r1LineNumber = r1LineNumber;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isTestPe() {
		return testPe;
	}

	public void setTestPe(boolean testPe) {
		this.testPe = testPe;
	}

	public boolean isR2Long() {
		return r2Long;
	}

	public void setR2Long(boolean r2Long) {
		this.r2Long = r2Long;
	}

	public List<AppropriationDto> getAppropriations() {
		if (null == appropriations) {
			appropriations = new ArrayList<>();
		}
		return appropriations;
	}

	public void setAppropriations(List<AppropriationDto> appropriations) {
		this.appropriations = appropriations;
	}

	public List<PeSuffixDto> getSerivceAgencyPESuffixs() {
		if (null == serivceAgencyPESuffixs) {
			serivceAgencyPESuffixs = new ArrayList<>();
		}
		return serivceAgencyPESuffixs;
	}

	public void setSerivceAgencyPESuffixs(List<PeSuffixDto> serivceAgencyPESuffixs) {
		this.serivceAgencyPESuffixs = serivceAgencyPESuffixs;
	}

	@Override
	public String toString() {
		return "ExhibitInitDto [selectedBudgetCycleId=" + selectedBudgetCycleId + ", selectedServiceAgencyId="
				+ selectedServiceAgencyId + ", selectedBudgetActivityId=" + selectedBudgetActivityId
				+ ", selectedBudgetSubActivityId=" + selectedBudgetSubActivityId + ", programElementNumber="
				+ programElementNumber + ", programElementName=" + programElementName + ", r1LineNumber=" + r1LineNumber
				+ ", tag=" + tag + ", testPe=" + testPe + ", r2Long=" + r2Long + "]";
	}

}
