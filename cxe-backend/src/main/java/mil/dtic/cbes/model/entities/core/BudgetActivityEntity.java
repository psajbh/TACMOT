package mil.dtic.cbes.model.entities.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;

@Entity
@Table(name="proc_budget_activity")
public class BudgetActivityEntity implements IEntity, Serializable {
	private static final long serialVersionUID = 8517365746268719691L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ba_ID")
	private Integer id;   //int(10) //UN AI PK

	@Column(name="aa_ID") // int(10) UN
	private Integer appriationId;
	
	@Column(name="ba_num") // tinyint(3) UN
	private Integer budgetNumber;
	
	@Column(name="ba_title") //varchar(255)
	private String budgetActivityTitle;
	
	//@Column(name="ba_status_flag")// enum('A','I')
	//private Integer xxappriationId;
	
	@Column(name="ba_RDTE_flag") // tinyint(1)
	private Integer rdteFlag;
	
	@Column(name="ba_PROC_flag") // tinyint(1)
	private Integer procFlag;
	
//	@Column(name="created_by_user") // varchar(255)
//	private UserEntity createdBy;
//	
//	@Column(name="date_created") // timestamp
//	private Date dateCreated;
//	
//	@Column(name="modified_by_user") // varchar(255)
//	private UserEntity modifiedBy;
//	
//	@Column(name="date_modified") //timestamp
//	private Date dateModified;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAppriationId() {
		return appriationId;
	}

	public void setAppriationId(Integer appriationId) {
		this.appriationId = appriationId;
	}

	public Integer getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(Integer budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	public String getBudgetActivityTitle() {
		return budgetActivityTitle;
	}

	public void setBudgetActivityTitle(String budgetActivityTitle) {
		this.budgetActivityTitle = budgetActivityTitle;
	}

	public Integer getRdteFlag() {
		return rdteFlag;
	}

	public void setRdteFlag(Integer rdteFlag) {
		this.rdteFlag = rdteFlag;
	}

	public Integer getProcFlag() {
		return procFlag;
	}

	public void setProcFlag(Integer procFlag) {
		this.procFlag = procFlag;
	}

//	public UserEntity getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(UserEntity createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Date getDateCreated() {
//		return dateCreated;
//	}
//
//	public void setDateCreated(Date dateCreated) {
//		this.dateCreated = dateCreated;
//	}

//	public UserEntity getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(UserEntity modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public Date getDateModified() {
//		return dateModified;
//	}
//
//	public void setDateModified(Date dateModified) {
//		this.dateModified = dateModified;
//	}

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
		BudgetActivityEntity other = (BudgetActivityEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	


}
