package mil.dtic.cbes.model.dto.core.budgetcycle;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BudgetCycleDto {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String budgetCycleId; 	//example: BES2010
	private String label; 			//example: 'PB 2020'
	private String cycle; 			//example: 'PB', 'BES', 'POM' etc.
	private String budgetYear; 		//example: '2020';
	private String amended; 		//example:  '0' or whatever
	private List<SubmissionDateDto> submissionDates;

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

	public SubmissionDateDto getCurrentSubmissionDate() {
		log.trace("getCurrentSubmissionDate-");
		SubmissionDateDto rankingDto = null;
		for (SubmissionDateDto submssionDateDto : getSubmissionDates()) {
			if (null == rankingDto) {
				rankingDto = submssionDateDto;
			} else {
				if (submssionDateDto.getRank() > rankingDto.getRank()) {
					rankingDto = submssionDateDto;
				}
			}
		}
		
		return rankingDto;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((budgetCycleId == null) ? 0 : budgetCycleId.hashCode());
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
		BudgetCycleDto other = (BudgetCycleDto) obj;
		if (budgetCycleId == null) {
			if (other.budgetCycleId != null)
				return false;
		} else if (!budgetCycleId.equals(other.budgetCycleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BudgetCycleDto [budgetCycleId=" + budgetCycleId + ", label=" + label + ", cycle=" + cycle
				+ ", budgetYear=" + budgetYear + ", amended=" + amended + "]";
	}

	
	

}
