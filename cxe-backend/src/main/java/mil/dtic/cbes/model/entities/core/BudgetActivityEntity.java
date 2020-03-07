package mil.dtic.cbes.model.entities.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.model.enums.core.BudgetActivityStatusFlag;

@Entity
@Table(name="proc_budget_activity")
public class BudgetActivityEntity implements IEntity, Serializable {
	private static final long serialVersionUID = 8517365746268719691L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ba_ID", columnDefinition = "int(10)")
	private Integer id;   //int(10) //UN AI PK

    @Column(name="aa_ID", columnDefinition = "int(10)") // int(10) UN
	private Integer appropriationId;  //
		
	@Column(name="ba_num", columnDefinition = "tinytint(3)") 
	private Integer budgetNumber;
	
	
	@Column(name="ba_title", columnDefinition = "varchar(255)") 
	private String budgetActivityTitle;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ba_status_flag")
	private BudgetActivityStatusFlag budgetActivityStatusFlag;
	
	@Column(name="ba_RDTE_flag", columnDefinition = "tinytint(1)") 
	private Integer rdteFlag;
	
	@Column(name="ba_PROC_flag", columnDefinition = "tinytint(1)") 
	private Integer procFlag;
	
	public BudgetActivityStatusFlag getBudgetActivityStatusFlag() {
		return budgetActivityStatusFlag;
	}

	public void setBudgetActivityStatusFlag(BudgetActivityStatusFlag budgetActivityStatusFlag) {
		this.budgetActivityStatusFlag = budgetActivityStatusFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAppropriationId() {
		return appropriationId;
	}

	public void setAppropriationId(Integer appropriationId) {
		this.appropriationId = appropriationId;
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
