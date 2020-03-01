package mil.dtic.cbes.model.dto.core.budgetcycle;

import java.util.ArrayList;
import java.util.List;

import mil.dtic.cbes.model.xml.SubmissionDate;

public class BudgetCycleDto {
	String budgetCycleId; //BES2010
	String label; // 'PB 2020'
	String cycle; // 'PB', 'BES', 'POM' etc.
	String budgetYear; // '2020';
	String amended;  //'0' or whatever
	List<SubmissionDateDto> submissionDates;
	public String getBudgetCycleId() {
		return budgetCycleId;
	}
	public void setBudgetCycleId(String budgetCycleId) {
		this.budgetCycleId = budgetCycleId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public String getAmended() {
		return amended;
	}
	public void setAmended(String amended) {
		this.amended = amended;
	}
	public List<SubmissionDateDto> getSubmissionDates() {
		if (null == submissionDates) {
			return new ArrayList<SubmissionDateDto>();
		}
		
		return submissionDates;
	}
	public void setSubmissionDates(List<SubmissionDateDto> submissionDates) {
		this.submissionDates = submissionDates;
	}
	
	


}
