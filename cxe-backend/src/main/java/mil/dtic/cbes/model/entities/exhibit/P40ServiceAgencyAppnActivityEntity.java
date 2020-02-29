package mil.dtic.cbes.model.entities.exhibit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="P40_SERV_AGY_APPN_BA_BSA")
	public class P40ServiceAgencyAppnActivityEntity {
		
		@Id
		@Column(name="ID")
		private Integer id;
		
		@Column(name="VERSION")
		private Integer version;
		
		@Column(name="SERVICE_AGENCY_ID")
		private Integer serviceAgencyId;
		
		@Column(name="APPN_ID")
		private Integer appropriationId;
		
		@Column(name="BUDGE_ACTIVITY_ID")
		private Integer budgetActivityId;
		
		@Column(name="BUDGESTS_SUB_ACTIVITY_ID")
		private Integer budgetSubActivityId;
		
		@Column(name="APP_CODE")
		private String appnCode;
		
		@Column(name="APP_NAME")
		private String appnName;
		
		@Column(name="BA_NUM")
		private String budgetActivityNum;
		
		@Column(name="BA_TITLE")
		private String budgetActivityTitle;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			P40ServiceAgencyAppnActivityEntity other = (P40ServiceAgencyAppnActivityEntity) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "P40ServiceAgencyAppnActivity [id=" + id + ", serviceAgencyId=" + serviceAgencyId
					+ ", appropriationId=" + appropriationId + ", budgetActivityId=" + budgetActivityId
					+ ", budgetSubActivityId=" + budgetSubActivityId + ", appnCode=" + appnCode + ", appnName="
					+ appnName + ", budgetActivityNum=" + budgetActivityNum + ", budgetActivityTitle="
					+ budgetActivityTitle + "]";
		}

		
		

}