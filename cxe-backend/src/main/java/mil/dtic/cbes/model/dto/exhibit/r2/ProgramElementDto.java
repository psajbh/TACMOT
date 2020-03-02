package mil.dtic.cbes.model.dto.exhibit.r2;

import java.math.BigDecimal;
import java.util.Date;


import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.dto.core.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;

public class ProgramElementDto extends Dto {
	
	private Integer id;
	private String peNumber;
	
	private BudgetCycleDto budgetCycle;
	
	private String r1Number;
	private Date submissionDate;
	private String peTitle;
	private BudgetActivityDto budgetActivityEntity;
	private ServiceAgencyDto serviceAgencyEntity;
	private String peMdap;
	private BigDecimal peApy;
	private BigDecimal pePy;
	private BigDecimal peCy;
	private BigDecimal peBy1;
	private BigDecimal peBy1Base;
	private BigDecimal peBy2;
	private BigDecimal peBy3;
	private BigDecimal peBy4;
	private BigDecimal peBy5;
	private String peCompCost;
	private String peTotalCost;
	private BigDecimal pePrevPbPy;
	private BigDecimal pePrevPbCy;
	private BigDecimal pePbBy1;
	private BigDecimal pePrevPbBy1Base;
	private BigDecimal peCurrPbPy;
	private BigDecimal peCurrPbCy;
	private BigDecimal peCurrPbBy1;
	private BigDecimal peCurrPbBy1Base;
	private BigDecimal peTotalAdjPy;
	private BigDecimal peTotalAdjCy;
	private BigDecimal peTotalAdjBy1;
	private String peStatusSubmission;
	private String peState;
	private String peInitSrc;
	private String peTest;
	private String peEditableSw;
	private String peTag;
	private Integer editLockIdPeOnly;
	private Date dateLockPe;
	private UserDto user;
	private Date dateCreatedR2;
	private Date dateModifiedR2;
	private Date dateModifiedOverall;
	private UserEntity modifiedByUserIdOverall;
	private Date dateCreated;
	private Date dateModified;
	private UserEntity createdByUser;
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
	public BudgetCycleDto getBudgetCycle() {
		return budgetCycle;
	}
	public void setBudgetCycle(BudgetCycleDto budgetCycle) {
		this.budgetCycle = budgetCycle;
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
	public BudgetActivityDto getBudgetActivityEntity() {
		return budgetActivityEntity;
	}
	public void setBudgetActivityEntity(BudgetActivityDto budgetActivityEntity) {
		this.budgetActivityEntity = budgetActivityEntity;
	}
	public ServiceAgencyDto getServiceAgencyEntity() {
		return serviceAgencyEntity;
	}
	public void setServiceAgencyEntity(ServiceAgencyDto serviceAgencyEntity) {
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
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
	
	
	


}
