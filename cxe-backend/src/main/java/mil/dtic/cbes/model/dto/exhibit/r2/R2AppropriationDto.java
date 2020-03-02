package mil.dtic.cbes.model.dto.exhibit.r2;

import java.util.ArrayList;
import java.util.List;

import mil.dtic.cbes.model.dto.core.AppropriationDto;

public class R2AppropriationDto extends AppropriationDto {

	private List<R2BudgetActivityDto> r2BudgetActivities;
	
	public List<R2BudgetActivityDto> getBudgetActivities() {
		if (null == r2BudgetActivities) {
			r2BudgetActivities = new ArrayList<R2BudgetActivityDto>();
		}
		return r2BudgetActivities;
	}
	public void setBudgetActivities(List<R2BudgetActivityDto> budgetActivities) {
		this.r2BudgetActivities = budgetActivities;
	}
	
	
	
}
