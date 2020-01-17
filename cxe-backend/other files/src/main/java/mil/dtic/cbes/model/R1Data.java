package mil.dtic.cbes.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class R1Data {
	private static final long serialVersionUID = 1L;

	public static final String BUDGET_YEAR = "budgetYear";
	public static final String ACCOUNT = "account";
	public static final String ORGANIZATION = "organization";
	public static final String BUDGET_ACTIVITY = "budgetActivity";
	public static final String R1_LINE_NUMBER = "lineNumber";
	public static final String PROGRAM_ELEMENT_NUMBER = "peNumber";
	public static final String PROJECT_NUMBER = "projectNumber";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected Integer budgetYear;
	protected String account;
	protected String accountTitle;
	protected String organization;
	protected String budgetActivity;
	protected String budgetActivityTitle;
	protected String lineNumber;
	protected String peNumber;
	protected String peTitle;
	protected String projectNumber;
	protected String projectTitle;
	protected String fundCategory;
	protected BigDecimal pyAmount;
	protected BigDecimal cyAmount;
	protected BigDecimal by1BaseAmount;
	protected BigDecimal by1OCOAmount;
	protected BigDecimal by1Amount;
	protected BigDecimal by2Amount;
	protected BigDecimal by3Amount;
	protected BigDecimal by4Amount;
	protected BigDecimal by5Amount;
	protected String classification;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getBudgetActivity() {
		return budgetActivity;
	}

	public void setBudgetActivity(String budgetActivity) {
		this.budgetActivity = budgetActivity;
	}

	public String getBudgetActivityTitle() {
		return budgetActivityTitle;
	}

	public void setBudgetActivityTitle(String budgetActivityTitle) {
		this.budgetActivityTitle = budgetActivityTitle;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getPeNumber() {
		return peNumber;
	}

	public void setPeNumber(String peNumber) {
		this.peNumber = peNumber;
	}

	public String getPeTitle() {
		return peTitle;
	}

	public void setPeTitle(String peTitle) {
		this.peTitle = peTitle;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getFundCategory() {
		return fundCategory;
	}

	public void setFundCategory(String fundCategory) {
		this.fundCategory = fundCategory;
	}

	public BigDecimal getPyAmount() {
		return pyAmount;
	}

	public void setPyAmount(BigDecimal pyAmount) {
		this.pyAmount = pyAmount;
	}

	public BigDecimal getCyAmount() {
		return cyAmount;
	}

	public void setCyAmount(BigDecimal cyAmount) {
		this.cyAmount = cyAmount;
	}

	public BigDecimal getBy1BaseAmount() {
		return by1BaseAmount;
	}

	public void setBy1BaseAmount(BigDecimal by1BaseAmount) {
		this.by1BaseAmount = by1BaseAmount;
	}

	public BigDecimal getBy1OCOAmount() {
		return by1OCOAmount;
	}

	public void setBy1OCOAmount(BigDecimal by1ocoAmount) {
		by1OCOAmount = by1ocoAmount;
	}

	public BigDecimal getBy1Amount() {
		return by1Amount;
	}

	public void setBy1Amount(BigDecimal by1Amount) {
		this.by1Amount = by1Amount;
	}

	public BigDecimal getBy2Amount() {
		return by2Amount;
	}

	public void setBy2Amount(BigDecimal by2Amount) {
		this.by2Amount = by2Amount;
	}

	public BigDecimal getBy3Amount() {
		return by3Amount;
	}

	public void setBy3Amount(BigDecimal by3Amount) {
		this.by3Amount = by3Amount;
	}

	public BigDecimal getBy4Amount() {
		return by4Amount;
	}

	public void setBy4Amount(BigDecimal by4Amount) {
		this.by4Amount = by4Amount;
	}

	public BigDecimal getBy5Amount() {
		return by5Amount;
	}

	public void setBy5Amount(BigDecimal by5Amount) {
		this.by5Amount = by5Amount;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}
