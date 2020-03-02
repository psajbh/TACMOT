package mil.dtic.cbes.model.dto.core.budgetcycle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BudgetCycleDto {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String budgetCycleId; // BES2010
	private String label; // 'PB 2020'
	private String cycle; // 'PB', 'BES', 'POM' etc.
	private String budgetYear; // '2020';
	private String amended; // '0' or whatever
	private List<SubmissionDateDto> submissionDates;
	private Date currentDate;

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

	public Date getCurrentDate() {
		log.trace("getCurrentDate()-");
		SubmissionDateDto submissionDateDto = getCurrentSubmissionDate();
		String submissionDateId = submissionDateDto.getSubmissionDateId();
		String dateTemplate = String.format("1-%-%", submissionDateId.substring(0, 3), submissionDateId.substring(3));

		String dateFormat = "dd-MMM-yyyy";

		SimpleDateFormat formatter2 = new SimpleDateFormat(dateFormat);
		currentDate = null;

		try {
			currentDate = formatter2.parse(dateTemplate);
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return currentDate;
	}

}
