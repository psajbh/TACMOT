package mil.dtic.cbes.model.dto.exhibit;

import mil.dtic.cbes.model.dto.Dto;

public class AppnBudgetActivityProjectionDto extends Dto {
	
	private Integer serviceAgencyId;
	private Integer appropriationId;
	private Integer budgetActivityId;
	private Integer budgetSubActivityId;
	private String appnCode;
	private String appnName;
	private Integer budgetActivityNum;
	private String budgetActivityTitle;
	private Integer budgetSubActivityNum;
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
	public Integer getBudgetActivityNum() {
		return budgetActivityNum;
	}
	public void setBudgetActivityNum(Integer budgetActivityNum) {
		this.budgetActivityNum = budgetActivityNum;
	}
	public String getBudgetActivityTitle() {
		return budgetActivityTitle;
	}
	public void setBudgetActivityTitle(String budgetActivityTitle) {
		this.budgetActivityTitle = budgetActivityTitle;
	}
	public Integer getBudgetSubActivityNum() {
		return budgetSubActivityNum;
	}
	public void setBudgetSubActivityNum(Integer budgetSubActivityNum) {
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
		AppnBudgetActivityProjectionDto other = (AppnBudgetActivityProjectionDto) obj;
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
		return "AppnBudgetActivityProjectionDto [serviceAgencyId=" + serviceAgencyId + ", appropriationId="
				+ appropriationId + ", budgetActivityId=" + budgetActivityId + ", budgetSubActivityId="
				+ budgetSubActivityId + ", appnCode=" + appnCode + ", appnName=" + appnName + ", budgetActivityNum="
				+ budgetActivityNum + ", budgetActivityTitle=" + budgetActivityTitle + ", budgetSubActivityNum="
				+ budgetSubActivityNum + ", budgetSubActivityTitle=" + budgetSubActivityTitle + "]";
	}
	
	

}
