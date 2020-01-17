package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Budget_Cycle")
public class BudgetCycle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String budgetCycleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBudgetCycleName() {
		return budgetCycleName;
	}

	public void setBudgetCycleName(String budgetCycleName) {
		this.budgetCycleName = budgetCycleName;
	}
	
	
}
