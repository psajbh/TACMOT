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
	private Integer ba_ID;   //int(10) //UN AI PK
	
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
	
	@Column(name="created_by_user") // varchar(255)
	private UserEntity createdBy;
	
	@Column(name="date_created") // timestamp
	private Date dateCreated;
	
	@Column(name="modified_by_user") // varchar(255)
	private UserEntity modifiedBy;
	
	@Column(name="date_modified") //timestamp
	private Date dateModified;


}
