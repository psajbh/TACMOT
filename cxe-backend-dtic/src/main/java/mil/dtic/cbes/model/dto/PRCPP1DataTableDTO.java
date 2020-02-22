package mil.dtic.cbes.model.dto;

import java.math.BigDecimal;

public class PRCPP1DataTableDTO implements IDto {
	private String organization;
	
	private String account;
	
	private String budgetActivity;
	
	private String budgetSubActivity;
	
	private String lineNumber;
	
	private String liNumber;
	
	private String costType;
	
	private BigDecimal pyAmount;
	
	private BigDecimal cyAmount;
	
	private BigDecimal by1BaseAmount;
	
	private BigDecimal by1OCOAmount;
	
	private BigDecimal by1Amount;
	
	private BigDecimal by2Amount;
	
	private BigDecimal by3Amount;
	
	private BigDecimal by4Amount;
	
	private BigDecimal by5Amount;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBudgetActivity() {
		return budgetActivity;
	}

	public void setBudgetActivity(String budgetActivity) {
		this.budgetActivity = budgetActivity;
	}

	public String getBudgetSubActivity() {
		return budgetSubActivity;
	}

	public void setBudgetSubActivity(String budgetSubActivity) {
		this.budgetSubActivity = budgetSubActivity;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getLiNumber() {
		return liNumber;
	}

	public void setLiNumber(String liNumber) {
		this.liNumber = liNumber;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
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

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionId() {
		// TODO Auto-generated method stub
		return null;
	}
}
