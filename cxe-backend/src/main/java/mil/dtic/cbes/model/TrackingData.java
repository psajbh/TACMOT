package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tracking_Data")
public class TrackingData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String trackingNumber;
	
	private String budgetCycle;
	
	private String appropriation;
	
	private String title;
	
	private String source;
	
	private String status;
	
	private String owner;
	
	private String serviceAgency;
	
	private String submitDate;
	
	private String workflowStatus;
	
	private Integer activeFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getBudgetCycle() {
		return budgetCycle;
	}

	public void setBudgetCycle(String budgetCycle) {
		this.budgetCycle = budgetCycle;
	}

	public String getAppropriation() {
		return appropriation;
	}

	public void setAppropriation(String appropriation) {
		this.appropriation = appropriation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getServiceAgency() {
		return serviceAgency;
	}

	public void setServiceAgency(String serviceAgency) {
		this.serviceAgency = serviceAgency;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	
	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

}
