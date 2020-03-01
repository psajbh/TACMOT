package mil.dtic.cbes.model.entities.views.p40;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;

@Entity
@Table(name="v_p40_appn_ba_bsa")
public class P40AppnBudgetActivityEntity implements IEntity, Serializable{
	private static final long serialVersionUID = 7514107165678051010L;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appropriationId == null) ? 0 : appropriationId.hashCode());
		result = prime * result + ((budgetActivityId == null) ? 0 : budgetActivityId.hashCode());
		result = prime * result + ((budgetSubActivityId == null) ? 0 : budgetSubActivityId.hashCode());
		result = prime * result + ((serviceAgencyId == null) ? 0 : serviceAgencyId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		P40AppnBudgetActivityEntity other = (P40AppnBudgetActivityEntity) obj;
		if (appropriationId == null) {
			if (other.appropriationId != null)
				return false;
		} else if (!appropriationId.equals(other.appropriationId))
			return false;
		if (budgetActivityId == null) {
			if (other.budgetActivityId != null)
				return false;
		} else if (!budgetActivityId.equals(other.budgetActivityId))
			return false;
		if (budgetSubActivityId == null) {
			if (other.budgetSubActivityId != null)
				return false;
		} else if (!budgetSubActivityId.equals(other.budgetSubActivityId))
			return false;
		if (serviceAgencyId == null) {
			if (other.serviceAgencyId != null)
				return false;
		} else if (!serviceAgencyId.equals(other.serviceAgencyId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "P40AppnBudgetActivityEntity [serviceAgencyId=" + serviceAgencyId + ", appropriationId="
				+ appropriationId + ", budgetActivityId=" + budgetActivityId + ", budgetSubActivityId="
				+ budgetSubActivityId + "]";
	}
	
	
	

}
	