package mil.dtic.cbes.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProgramElement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String r1Number;
	private String peNumber;
	private String peTitle;
//	@ManyToOne
//	@JoinColumn(name = "budgetActivity_id")
	private Integer budgetActivity;
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
	private BigDecimal pys;
	private BigDecimal pby1;
	private BigDecimal pby2;
	private BigDecimal by1base;
	private BigDecimal by1oco;
	private BigDecimal by1;
	private BigDecimal by2;
	private BigDecimal by3;
	private BigDecimal by4;
	private BigDecimal by5;
	private Boolean lockedFlag;
	@ManyToOne
	@JoinColumn(name="budgetCycle")
	private BudgetCycle budgetCycle;
	private Date dateModified;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getR1Number() {
		return r1Number;
	}
	public void setR1Number(String r1Number) {
		this.r1Number = r1Number;
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
	public Integer getBudgetActivity() {
		return budgetActivity;
	}
	public void setBudgetActivity(Integer budgetActivity) {
		this.budgetActivity = budgetActivity;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	public BigDecimal getPys() {
		return pys;
	}
	public void setPys(BigDecimal pys) {
		this.pys = pys;
	}
	public BigDecimal getPby1() {
		return pby1;
	}
	public void setPby1(BigDecimal pby1) {
		this.pby1 = pby1;
	}
	public BigDecimal getPby2() {
		return pby2;
	}
	public void setPby2(BigDecimal pby2) {
		this.pby2 = pby2;
	}
	public BigDecimal getBy1base() {
		return by1base;
	}
	public void setBy1base(BigDecimal by1base) {
		this.by1base = by1base;
	}
	public BigDecimal getBy1oco() {
		return by1oco;
	}
	public void setBy1oco(BigDecimal by1oco) {
		this.by1oco = by1oco;
	}
	public BigDecimal getBy1() {
		return by1;
	}
	public void setBy1(BigDecimal by1) {
		this.by1 = by1;
	}
	public BigDecimal getBy2() {
		return by2;
	}
	public void setBy2(BigDecimal by2) {
		this.by2 = by2;
	}
	public BigDecimal getBy3() {
		return by3;
	}
	public void setBy3(BigDecimal by3) {
		this.by3 = by3;
	}
	public BigDecimal getBy4() {
		return by4;
	}
	public void setBy4(BigDecimal by4) {
		this.by4 = by4;
	}
	public BigDecimal getBy5() {
		return by5;
	}
	public void setBy5(BigDecimal by5) {
		this.by5 = by5;
	}
	public Boolean getLockedFlag() {
		return lockedFlag;
	}
	public void setLockedFlag(Boolean lockedFlag) {
		this.lockedFlag = lockedFlag;
	}
	public BudgetCycle getBudgetCycle() {
		return budgetCycle;
	}
	public void setBudgetCycle(BudgetCycle budgetCycle) {
		this.budgetCycle = budgetCycle;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
		
}
