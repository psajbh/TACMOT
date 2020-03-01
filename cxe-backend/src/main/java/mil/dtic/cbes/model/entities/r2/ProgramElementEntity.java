package mil.dtic.cbes.model.entities.r2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;

@Entity
@Table(name="PGM_ELEMENT")
public class ProgramElementEntity implements IEntity, Serializable {
	private static final long serialVersionUID = -6487740749610234426L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BUDGES_PGM_ELEMENT_ID", columnDefinition="int(10)")
	private Integer id;
	
	@Column(name="BUDGES_PGM_ELEM_NUM", columnDefinition="varchar(10)")
	private String peNumber;
	
	@Column(name="PE_BUDGET_CYCLE", columnDefinition="varchar(10)")
	private String budgetCycle;

	@Column(name="PE_BUDGET_YEAR", columnDefinition="smallint(4)")
	private Integer budgetYear;
	
	//@Column(name="PE_FORMAT", columnDefinition="enum'R2Long')")
	//private String peFormat;  //enum('R2Long') private FormatFlagType  
	
	@Column(name="PE_R1_NUM", columnDefinition="varchar(10)")
	private String r1Number;
	
	@Column(name="PE_SUBM_DATE", columnDefinition="datetime")
	private Date submissionDate;
	 
	@Column(name="PE_TITLE", columnDefinition="varchar(255)")
	private String peTitle;
 
	@Column(name="BUDGES_BUDGET_ACTIVITY_ID", columnDefinition="int(10)")
	private BudgetActivityEntity budgetActivityEntity;
	
	@Column(name="BUDGES_SERV_AGY_ID", columnDefinition="int(10)")
	private ServiceAgencyEntity serviceAgencyEntity;
	
	@Column(name="pe_MDAP", columnDefinition="decimal(12,3)")
	private String peMdap;
	
	@Column(name="pe_apy", columnDefinition="decimal(12,3)")
	private BigDecimal peApy;
	
	@Column(name="PE_PY", columnDefinition="decimal(12,3) default 0.000") // : 0.000
	private BigDecimal pePy;
	
	@Column(name="PE_CY", columnDefinition="decimal(12,3) default 0.000") // : 0.000
	private BigDecimal peCy;

	@Column(name="PE_BY1", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peBy1;

	@Column(name="PE_BY1_BASE", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peBy1Base;

	@Column(name="PE_BY2", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peBy2;

	@Column(name="PE_BY3", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peBy3;

	@Column(name="PE_BY4", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peBy4;

	@Column(name="PE_BY5", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peBy5;

	@Column(name="PE_COMP_COST", columnDefinition="varchar(11) default 'Continuing'") //: Continuing
	private String peCompCost;

	@Column(name="PE_TOTAL_COST", columnDefinition="varchar(11) default 'Continuing'") //: Continuing
	private String peTotalCost;

	@Column(name="PE_PREV_PB_PY", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal pePrevPbPy;

	@Column(name="PE_PREV_PB_CY", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal pePrevPbCy;

	@Column(name="PE_PREV_PB_BY1", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal pePbBy1;

	@Column(name="PE_PREV_PB_BY1_BASE", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal pePrevPbBy1Base;

	@Column(name="PE_CURR_PB_PY", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peCurrPbPy;

	@Column(name="PE_CURR_PB_CY", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peCurrPbCy;

	@Column(name="PE_CURR_PB_BY1", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peCurrPbBy1;

	@Column(name="PE_CURR_PB_BY_1BASE", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peCurrPbBy1Base;

	@Column(name="PE_TOTAL_ADJ_PY", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peTotalAdjPy;

	@Column(name="PE_TOTAL_ADJ_CY", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peTotalAdjCy;

	@Column(name="PE_TOTAL_ADJ_BY1", columnDefinition="decimal(12,3) default 0.000") //: 0.000
	private BigDecimal peTotalAdjBy1;

	@Column(name="PE_STATUS_SUBM", columnDefinition="enum('A','E','V','W' default 'A'")  //: A
	private String peStatusSubmission;

	@Column(name="PE_STATE", columnDefinition="enum('A','I' default 'A'") //: A
	private String peState;

	@Column(name="PE_INIT_SRC", columnDefinition="enum('X','W' default 'W'") //: W
	private String peInitSrc;

	@Column(name="PE_TEST", columnDefinition="enum('Y','N' default 'N'") //: N  (Y if test check box selected) (from UI)
	private String peTest;

	@Column(name="PE_EDITABLE_SW", columnDefinition="enum('T','F' default 'T'") //: T
	private String peEditableSw;

	@Column(name="PE_TAG", columnDefinition="text") //: TAG  (from UI)
	private String peTag;

	@Column(name="EDIT_LOCK_ID_PE_ONLY", columnDefinition="int 10") //: 74  (userId)
	private Integer editLockIdPeOnly;

	@Column(name="DATE_LOCK_PE", columnDefinition="datetime") //: '2020-02-26 08:08:02'  Local
	private Date dateLockPe;

	@Column(name="CREATED_BY_USER_ID_R2", columnDefinition="int 10") //: 74 (userId)
	private UserEntity user;

	@Column(name="DATE_CREATED_R2", columnDefinition="timestamp") //: 2020-02-26 13:06:20 GMT
	private Date dateCreatedR2;

	@Column(name="DATE_MODIFIED_R2", columnDefinition="timestamp") //  2020-02-26 13:08:02  GMT
	private Date dateModifiedR2;

	@Column(name="DATE_MODIFIED_OVERALL", columnDefinition="datetime") //: '2020-02-26 08:08:02'  Local
	private Date dateModifiedOverall;

	@Column(name="MODIFIED_BY_USER_ID_OVERALL", columnDefinition="int 10") //: userId
	private UserEntity modifiedByUserIdOverall;

	@Column(name="DATE_CREATED", columnDefinition="timestamp")  //: 2020-02-26 13:06:19  GMT
	private Date dateCreated;

	@Column(name="DATE_MODIFIED", columnDefinition="datetime") ///'2020-02-26 08:08:02'  Local
	private Date dateModified;

	@Column(name="CREATED_BY_USER", columnDefinition="varchar(256)") //: jhart@localhost
	private UserEntity createdByUser;

	@Column(name="MODIFIED_BY_USER", columnDefinition="varchar(256)") //: jhart@localhost
	private UserEntity modifiedByUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPeNumber() {
		return peNumber;
	}

	public void setPeNumber(String peNumber) {
		this.peNumber = peNumber;
	}

	public String getBudgetCycle() {
		return budgetCycle;
	}

	public void setBudgetCycle(String budgetCycle) {
		this.budgetCycle = budgetCycle;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getR1Number() {
		return r1Number;
	}

	public void setR1Number(String r1Number) {
		this.r1Number = r1Number;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getPeTitle() {
		return peTitle;
	}

	public void setPeTitle(String peTitle) {
		this.peTitle = peTitle;
	}

	public BudgetActivityEntity getBudgetActivityEntity() {
		return budgetActivityEntity;
	}

	public void setBudgetActivityEntity(BudgetActivityEntity budgetActivityEntity) {
		this.budgetActivityEntity = budgetActivityEntity;
	}

	public ServiceAgencyEntity getServiceAgencyEntity() {
		return serviceAgencyEntity;
	}

	public void setServiceAgencyEntity(ServiceAgencyEntity serviceAgencyEntity) {
		this.serviceAgencyEntity = serviceAgencyEntity;
	}

	public String getPeMdap() {
		return peMdap;
	}

	public void setPeMdap(String peMdap) {
		this.peMdap = peMdap;
	}

	public BigDecimal getPeApy() {
		return peApy;
	}

	public void setPeApy(BigDecimal peApy) {
		this.peApy = peApy;
	}

	public BigDecimal getPePy() {
		return pePy;
	}

	public void setPePy(BigDecimal pePy) {
		this.pePy = pePy;
	}

	public BigDecimal getPeCy() {
		return peCy;
	}

	public void setPeCy(BigDecimal peCy) {
		this.peCy = peCy;
	}

	public BigDecimal getPeBy1() {
		return peBy1;
	}

	public void setPeBy1(BigDecimal peBy1) {
		this.peBy1 = peBy1;
	}

	public BigDecimal getPeBy1Base() {
		return peBy1Base;
	}

	public void setPeBy1Base(BigDecimal peBy1Base) {
		this.peBy1Base = peBy1Base;
	}

	public BigDecimal getPeBy2() {
		return peBy2;
	}

	public void setPeBy2(BigDecimal peBy2) {
		this.peBy2 = peBy2;
	}

	public BigDecimal getPeBy3() {
		return peBy3;
	}

	public void setPeBy3(BigDecimal peBy3) {
		this.peBy3 = peBy3;
	}

	public BigDecimal getPeBy4() {
		return peBy4;
	}

	public void setPeBy4(BigDecimal peBy4) {
		this.peBy4 = peBy4;
	}

	public BigDecimal getPeBy5() {
		return peBy5;
	}

	public void setPeBy5(BigDecimal peBy5) {
		this.peBy5 = peBy5;
	}

	public String getPeCompCost() {
		return peCompCost;
	}

	public void setPeCompCost(String peCompCost) {
		this.peCompCost = peCompCost;
	}

	public String getPeTotalCost() {
		return peTotalCost;
	}

	public void setPeTotalCost(String peTotalCost) {
		this.peTotalCost = peTotalCost;
	}

	public BigDecimal getPePrevPbPy() {
		return pePrevPbPy;
	}

	public void setPePrevPbPy(BigDecimal pePrevPbPy) {
		this.pePrevPbPy = pePrevPbPy;
	}

	public BigDecimal getPePrevPbCy() {
		return pePrevPbCy;
	}

	public void setPePrevPbCy(BigDecimal pePrevPbCy) {
		this.pePrevPbCy = pePrevPbCy;
	}

	public BigDecimal getPePbBy1() {
		return pePbBy1;
	}

	public void setPePbBy1(BigDecimal pePbBy1) {
		this.pePbBy1 = pePbBy1;
	}

	public BigDecimal getPePrevPbBy1Base() {
		return pePrevPbBy1Base;
	}

	public void setPePrevPbBy1Base(BigDecimal pePrevPbBy1Base) {
		this.pePrevPbBy1Base = pePrevPbBy1Base;
	}

	public BigDecimal getPeCurrPbPy() {
		return peCurrPbPy;
	}

	public void setPeCurrPbPy(BigDecimal peCurrPbPy) {
		this.peCurrPbPy = peCurrPbPy;
	}

	public BigDecimal getPeCurrPbCy() {
		return peCurrPbCy;
	}

	public void setPeCurrPbCy(BigDecimal peCurrPbCy) {
		this.peCurrPbCy = peCurrPbCy;
	}

	public BigDecimal getPeCurrPbBy1() {
		return peCurrPbBy1;
	}

	public void setPeCurrPbBy1(BigDecimal peCurrPbBy1) {
		this.peCurrPbBy1 = peCurrPbBy1;
	}

	public BigDecimal getPeCurrPbBy1Base() {
		return peCurrPbBy1Base;
	}

	public void setPeCurrPbBy1Base(BigDecimal peCurrPbBy1Base) {
		this.peCurrPbBy1Base = peCurrPbBy1Base;
	}

	public BigDecimal getPeTotalAdjPy() {
		return peTotalAdjPy;
	}

	public void setPeTotalAdjPy(BigDecimal peTotalAdjPy) {
		this.peTotalAdjPy = peTotalAdjPy;
	}

	public BigDecimal getPeTotalAdjCy() {
		return peTotalAdjCy;
	}

	public void setPeTotalAdjCy(BigDecimal peTotalAdjCy) {
		this.peTotalAdjCy = peTotalAdjCy;
	}

	public BigDecimal getPeTotalAdjBy1() {
		return peTotalAdjBy1;
	}

	public void setPeTotalAdjBy1(BigDecimal peTotalAdjBy1) {
		this.peTotalAdjBy1 = peTotalAdjBy1;
	}

	public String getPeStatusSubmission() {
		return peStatusSubmission;
	}

	public void setPeStatusSubmission(String peStatusSubmission) {
		this.peStatusSubmission = peStatusSubmission;
	}

	public String getPeState() {
		return peState;
	}

	public void setPeState(String peState) {
		this.peState = peState;
	}

	public String getPeInitSrc() {
		return peInitSrc;
	}

	public void setPeInitSrc(String peInitSrc) {
		this.peInitSrc = peInitSrc;
	}

	public String getPeTest() {
		return peTest;
	}

	public void setPeTest(String peTest) {
		this.peTest = peTest;
	}

	public String getPeEditableSw() {
		return peEditableSw;
	}

	public void setPeEditableSw(String peEditableSw) {
		this.peEditableSw = peEditableSw;
	}

	public String getPeTag() {
		return peTag;
	}

	public void setPeTag(String peTag) {
		this.peTag = peTag;
	}

	public Integer getEditLockIdPeOnly() {
		return editLockIdPeOnly;
	}

	public void setEditLockIdPeOnly(Integer editLockIdPeOnly) {
		this.editLockIdPeOnly = editLockIdPeOnly;
	}

	public Date getDateLockPe() {
		return dateLockPe;
	}

	public void setDateLockPe(Date dateLockPe) {
		this.dateLockPe = dateLockPe;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getDateCreatedR2() {
		return dateCreatedR2;
	}

	public void setDateCreatedR2(Date dateCreatedR2) {
		this.dateCreatedR2 = dateCreatedR2;
	}

	public Date getDateModifiedR2() {
		return dateModifiedR2;
	}

	public void setDateModifiedR2(Date dateModifiedR2) {
		this.dateModifiedR2 = dateModifiedR2;
	}

	public Date getDateModifiedOverall() {
		return dateModifiedOverall;
	}

	public void setDateModifiedOverall(Date dateModifiedOverall) {
		this.dateModifiedOverall = dateModifiedOverall;
	}

	public UserEntity getModifiedByUserIdOverall() {
		return modifiedByUserIdOverall;
	}

	public void setModifiedByUserIdOverall(UserEntity modifiedByUserIdOverall) {
		this.modifiedByUserIdOverall = modifiedByUserIdOverall;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public UserEntity getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(UserEntity createdByUser) {
		this.createdByUser = createdByUser;
	}

	public UserEntity getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(UserEntity modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((peNumber == null) ? 0 : peNumber.hashCode());
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
		ProgramElementEntity other = (ProgramElementEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (peNumber == null) {
			if (other.peNumber != null)
				return false;
		} else if (!peNumber.equals(other.peNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProgramElementEntity [id=" + id + ", peNumber=" + peNumber + ", budgetCycle=" + budgetCycle
				+ ", budgetYear=" + budgetYear + ", r1Number=" + r1Number + ", submissionDate=" + submissionDate
				+ ", peTitle=" + peTitle + "]";
	}

	

	
	
}
