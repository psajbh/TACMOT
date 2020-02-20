package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Deprecated
@Entity
public class CreateR2 {
	
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

	private String programElementName;
	
	private String programElementNumber;
	
	private String r1LineItemNumber;
	
	private String r2Long;
	
	private String tag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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

	public String getProgramElementName() {
		return programElementName;
	}

	public void setProgramElementName(String programElementName) {
		this.programElementName = programElementName;
	}

	public String getProgramElementNumber() {
		return programElementNumber;
	}

	public void setProgramElementNumber(String programElementNumber) {
		this.programElementNumber = programElementNumber;
	}

	public String getR1LineItemNumber() {
		return r1LineItemNumber;
	}

	public void setR1LineItemNumber(String r1LineItemNumber) {
		this.r1LineItemNumber = r1LineItemNumber;
	}

	public String getR2Long() {
		return r2Long;
	}

	public void setR2Long(String r2Long) {
		this.r2Long = r2Long;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
