package mil.dtic.cbes.model.entities.views.r2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;

@Entity
@Table(name="r2_serv_gyv_r2_appn_ba_bsa")
public class R2AppnBudgetActivityEntity implements IEntity, Serializable{
	private static final long serialVersionUID = -775620601454443414L;

	@Id
	@Column(name="sa_ID")
	private Integer serviceAgencyId;
	
	@Column(name="aa_ID")
	private Integer appropriationId;
	
	@Column(name="ba_ID")
	private Integer budgetActivityId;
	
	@Column(name="bsa_ID")
	private Integer budgetSubActivityId;
	
	@Column(name="appn_code")
	private String appnCode;
	
	@Column(name="appn_name")
	private String appnName;
	
	@Column(name="ba_num")
	private String budgetActivityNum;
	
	@Column(name="ba_title")
	private String budgetActivityTitle;
	
	@Column (name="bsa_num")
	private String budgetSubActivityNum;
	
	@Column(name="bsa_title")
	private String budgetSubActivityTitle;

	public Integer getServiceAgencyId() {
		return serviceAgencyId;
	}
	public void setServiceAgencyId(Integer serviceAgencyId) {
		this.serviceAgencyId = serviceAgencyId;
	}
	public Integer getAppropriationId() {
		return appropriationId;
	}
	public void setAppropriationId(Integer appropriationId) {
		this.appropriationId = appropriationId;
	}
	public Integer getBudgetActivityId() {
		return budgetActivityId;
	}
	public void setBudgetActivityId(Integer budgetActivityId) {
		this.budgetActivityId = budgetActivityId;
	}
	public Integer getBudgetSubActivityId() {
		return budgetSubActivityId;
	}
	public void setBudgetSubActivityId(Integer budgetSubActivityId) {
		this.budgetSubActivityId = budgetSubActivityId;
	}
	public String getAppnCode() {
		return appnCode;
	}
	public void setAppnCode(String appnCode) {
		this.appnCode = appnCode;
	}
	public String getAppnName() {
		return appnName;
	}
	public void setAppnName(String appnName) {
		this.appnName = appnName;
	}
	public String getBudgetActivityNum() {
		return budgetActivityNum;
	}
	public void setBudgetActivityNum(String budgetActivityNum) {
		this.budgetActivityNum = budgetActivityNum;
	}
	public String getBudgetActivityTitle() {
		return budgetActivityTitle;
	}
	public void setBudgetActivityTitle(String budgetActivityTitle) {
		this.budgetActivityTitle = budgetActivityTitle;
	}
	public String getBudgetSubActivityNum() {
		return budgetSubActivityNum;
	}
	public void setBudgetSubActivityNum(String budgetSubActivityNum) {
		this.budgetSubActivityNum = budgetSubActivityNum;
	}
	public String getBudgetSubActivityTitle() {
		return budgetSubActivityTitle;
	}
	public void setBudgetSubActivityTitle(String budgetSubActivityTitle) {
		this.budgetSubActivityTitle = budgetSubActivityTitle;
	}

}
