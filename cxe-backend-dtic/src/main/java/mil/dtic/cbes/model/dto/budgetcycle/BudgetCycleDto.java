package mil.dtic.cbes.model.dto.budgetcycle;

import java.io.Serializable;
import java.util.List;

import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.IDto;


public class BudgetCycleDto extends Dto implements Serializable {
	private static final long serialVersionUID = -8396811226019010101L;
	
	  private String label;
	  private String cycle;
	  private Integer budgetYear;
	  private Integer amended;
	  private List<SubmissionDateDto> submissionDates;

	
	

}
