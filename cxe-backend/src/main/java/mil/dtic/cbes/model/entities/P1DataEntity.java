package mil.dtic.cbes.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="P1Data")
public class P1DataEntity implements IEntity, Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p1data_id")
	private Integer id;
	
	protected Integer budgetYear;
	
	protected String account;
	
	protected String accountTitle;
	
	protected String organization;
	
	protected String budgetActivity;
	
	protected String budgetActivityTitle;
	
	protected String lineNumber;
	
	protected String budgetSubActivity;
	
	protected String budgetSubActivityTitle;
	
	protected String liNumber;
	
	protected String liTitle;
	
	protected String costType;
	
	protected String costTypeTitle;
	
	protected String addNonAdd;
	
	protected BigDecimal pyQuantity;
	
	protected BigDecimal pyAmount;
	
	protected BigDecimal cyQuantity;
	
	protected BigDecimal cyAmount;
	
	protected BigDecimal by1BaseAmount;
	
	protected BigDecimal by1OCOAmount;
	
	protected BigDecimal by1Quantity;
	
	protected BigDecimal by1Amount;
	
	protected BigDecimal by2Quantity;
	
	protected BigDecimal by2Amount;
	
	protected BigDecimal by3Quantity;
	
	protected BigDecimal by3Amount;
	
	protected BigDecimal by4Quantity;
	
	protected BigDecimal by4Amount;
	
	protected BigDecimal by5Quantity;
	
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

	public String getBudgetSubActivity() {
		return budgetSubActivity;
	}

	public void setBudgetSubActivity(String budgetSubActivity) {
		this.budgetSubActivity = budgetSubActivity;
	}

	public String getBudgetSubActivityTitle() {
		return budgetSubActivityTitle;
	}

	public void setBudgetSubActivityTitle(String budgetSubActivityTitle) {
		this.budgetSubActivityTitle = budgetSubActivityTitle;
	}

	public String getLiNumber() {
		return liNumber;
	}

	public void setLiNumber(String liNumber) {
		this.liNumber = liNumber;
	}

	public String getLiTitle() {
		return liTitle;
	}

	public void setLiTitle(String liTitle) {
		this.liTitle = liTitle;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getCostTypeTitle() {
		return costTypeTitle;
	}

	public void setCostTypeTitle(String costTypeTitle) {
		this.costTypeTitle = costTypeTitle;
	}

	public String getAddNonAdd() {
		return addNonAdd;
	}

	public void setAddNonAdd(String addNonAdd) {
		this.addNonAdd = addNonAdd;
	}

	public BigDecimal getPyQuantity() {
		return pyQuantity;
	}

	public void setPyQuantity(BigDecimal pyQuantity) {
		this.pyQuantity = pyQuantity;
	}

	public BigDecimal getPyAmount() {
		return pyAmount;
	}

	public void setPyAmount(BigDecimal pyAmount) {
		this.pyAmount = pyAmount;
	}

	public BigDecimal getCyQuantity() {
		return cyQuantity;
	}

	public void setCyQuantity(BigDecimal cyQuantity) {
		this.cyQuantity = cyQuantity;
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

	public BigDecimal getBy1Quantity() {
		return by1Quantity;
	}

	public void setBy1Quantity(BigDecimal by1Quantity) {
		this.by1Quantity = by1Quantity;
	}

	public BigDecimal getBy1Amount() {
		return by1Amount;
	}

	public void setBy1Amount(BigDecimal by1Amount) {
		this.by1Amount = by1Amount;
	}

	public BigDecimal getBy2Quantity() {
		return by2Quantity;
	}

	public void setBy2Quantity(BigDecimal by2Quantity) {
		this.by2Quantity = by2Quantity;
	}

	public BigDecimal getBy2Amount() {
		return by2Amount;
	}

	public void setBy2Amount(BigDecimal by2Amount) {
		this.by2Amount = by2Amount;
	}

	public BigDecimal getBy3Quantity() {
		return by3Quantity;
	}

	public void setBy3Quantity(BigDecimal by3Quantity) {
		this.by3Quantity = by3Quantity;
	}

	public BigDecimal getBy3Amount() {
		return by3Amount;
	}

	public void setBy3Amount(BigDecimal by3Amount) {
		this.by3Amount = by3Amount;
	}

	public BigDecimal getBy4Quantity() {
		return by4Quantity;
	}

	public void setBy4Quantity(BigDecimal by4Quantity) {
		this.by4Quantity = by4Quantity;
	}

	public BigDecimal getBy4Amount() {
		return by4Amount;
	}

	public void setBy4Amount(BigDecimal by4Amount) {
		this.by4Amount = by4Amount;
	}

	public BigDecimal getBy5Quantity() {
		return by5Quantity;
	}

	public void setBy5Quantity(BigDecimal by5Quantity) {
		this.by5Quantity = by5Quantity;
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
	
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
