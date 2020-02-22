package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CreateP40 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "agency")
	private Agency agency;
	
	@ManyToOne
	@JoinColumn(name = "appropriation")
	private Appropriation appropriation;
	
	@ManyToOne
	@JoinColumn(name = "budgetActivity")
	private BudgetActivity budgetActivity;

	@ManyToOne
	@JoinColumn(name = "budgetCycle")
	private BudgetCycle budgetCycle;
	
	@ManyToOne
	@JoinColumn(name = "budgetSubActivity")
	private BudgetSubActivity budgetSubActivity;
	
	private String lineItemNumber;
	
	private String message;

	private String p1LineItemNumber;

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Appropriation getAppropriation() {
		return appropriation;
	}

	public void setAppropriation(Appropriation appropriation) {
		this.appropriation = appropriation;
	}

	public BudgetActivity getBudgetActivity() {
		return budgetActivity;
	}

	public void setBudgetActivity(BudgetActivity budgetActivity) {
		this.budgetActivity = budgetActivity;
	}

	public BudgetCycle getBudgetCycle() {
		return budgetCycle;
	}

	public void setBudgetCycle(BudgetCycle budgetCycle) {
		this.budgetCycle = budgetCycle;
	}

	public BudgetSubActivity getBudgetSubActivity() {
		return budgetSubActivity;
	}

	public void setBudgetSubActivity(BudgetSubActivity budgetSubActivity) {
		this.budgetSubActivity = budgetSubActivity;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLineItemNumber() {
		return lineItemNumber;
	}

	public void setLineItemNumber(String lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getP1LineItemNumber() {
		return p1LineItemNumber;
	}

	public void setP1LineItemNumber(String p1LineItemNumber) {
		this.p1LineItemNumber = p1LineItemNumber;
	}
	
}
