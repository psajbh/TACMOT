package mil.dtic.cbes.model.entities.exhibit.r2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.model.enums.exhibit.r2.project.ArticlesInAppFlag;
import mil.dtic.cbes.model.enums.exhibit.r2.project.ProjectIncludeInPdfFlag;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity implements IEntity, Serializable {
	private static final long serialVersionUID = -6384733107897400546L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUDGES_PROJ_ID", columnDefinition = "int(10)")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUDGES_PGM_ELEM_ID", columnDefinition = "int(10)")
	private ProgramElementEntity programElementEntity;
	
	@Column(name="BUDGES_PROJ_NUM", columnDefinition = "varchar(25)")
	private String projectNumber;
	
	@Column(name="PROJ_TITLE", columnDefinition = "varchar(255)")
	private String projectTitle;
	
	@Column(name="proj_special_project", columnDefinition ="tinyint(1)")
	private Integer projSpecialProject;
	
	@Column(name="DISPLAY_ORDER", columnDefinition = "mediumint(9)") 
	private Integer displayOrder;
	
	@Column(name="proj_apy", columnDefinition = "decimal(12,3)")
	private BigDecimal projApy;
	
	@Column(name="PROJ_PY", columnDefinition ="decimal(12,3)")
	private BigDecimal projPy;
	
	@Column(name="PROJ_CY", columnDefinition ="decimal(12,3)")
	private BigDecimal projCy;
	
	@Column(name="PROJ_BY1", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy1
	; 
	@Column(name="PROJ_BY1_BASE", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy1Base;
	
	@Column(name="PROJ_BY1_OCO", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy1Oco;
	
	@Column(name="PROJ_BY2", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy2;
	
	@Column(name="PROJ_BY3", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy3;  
	
	@Column(name="PROJ_BY4", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy4;
	
	@Column(name="PROJ_BY5", columnDefinition ="decimal(12,3)")
	private BigDecimal projBy5;
	
	@Column(name="PROJ_COMP_COST", columnDefinition ="varchar(11)")
	private String projCompCost;
	
	@Column(name="PROJ_TOTAL_COST", columnDefinition ="varchar(11)")
	private String projTotalCost;
	
	@Column(name="PROJ_pys_delta", columnDefinition ="decimal(12,3)")
	private BigDecimal projPysDelta;
	
	@Column(name="proj_MDAP", columnDefinition ="varchar(4)")
	private String projMDAP;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ARTICLES_IN_APP", columnDefinition ="enum('Y','N' default 'N")
	private ArticlesInAppFlag articlesInAppFlag;
	
	@Column(name="proj_art_apy", columnDefinition ="int(4)")
	private Integer projArtApy;
	
	@Column(name="PROJ_ART_PY", columnDefinition ="int(4)")
	private Integer projArtPy;
	
	@Column(name="PROJ_ART_CY", columnDefinition ="int(4)")
	private Integer projArtCy;
	
	@Column(name="PROJ_ART_BY1", columnDefinition ="int(4)")
	private Integer projArtBy1;
	
	@Column(name="PROJ_ART_BY1_BASE", columnDefinition ="int(4)")
	private Integer projArtBy1Base;
	
	@Column(name="PROJ_ART_BY1_OCO", columnDefinition ="int(4)")
	private Integer projArtBy1Oco;
	
	@Column(name="PROJ_ART_BY2", columnDefinition ="int(4)")
	private Integer projArtBy2;
	
	@Column(name="PROJ_ART_BY3", columnDefinition ="int(4)")
	private Integer projArtBy3;
	
	@Column(name="PROJ_ART_BY4", columnDefinition ="int(4)")
	private Integer projArtBy4;
	
	@Column(name="PROJ_ART_BY5", columnDefinition ="int(4)")
	private Integer projArtBy5;
	
	@Column(name="PROJ_NOTES", columnDefinition ="text")
	private String projNotes;
	
	@Column(name="PROJ_MISSION_DESC", columnDefinition ="text")
	private String projMissionDesc; 
	
	@Column(name="PROJ_ACQ_STRATEGY", columnDefinition ="text ")
	private String projAcqSrategy; 
	
	@Column(name="PROJ_PERF_METRICS", columnDefinition ="text")
	private String projPerfMetrics;
	
	@Column(name="PROJ_MAJOR_PERFORMERS", columnDefinition ="text")
	private String projMajorPerformers;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PROJ_INCLUDE_IN_PDF", columnDefinition ="enum('Y','N') default 'Y'")
	private ProjectIncludeInPdfFlag projIncludeInPdfFlag;
	
	@Column(name="EDIT_LOCK_ID_PJ", columnDefinition ="int(10)")
	private Integer editLockIdPj;
	
	@Column(name="DATE_LOCK_PJ", columnDefinition ="datetime")
	private Date dateLockPj;
	
	@Column(name="proj_other_pgm_remarks", columnDefinition ="varchar(5000)")
	private String projOtherPgmRemarks;
	
	@Column(name="R3_REMARKS", columnDefinition ="varchar(5000)")
	private String r3Remarks;
	
	@Column(name="psp_remarks", columnDefinition ="varchar(5000)")
	private String pspRemarks;
	
	@Column(name="psd_remarks", columnDefinition ="varchar(5000)")
	private String psdRemarks;
	
	@Column(name="suppress_pdf", columnDefinition ="tinyint(1)")
	private Integer suppressPdf;
	
	//user r2a
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY_USER_ID_R2A", columnDefinition ="int(10)")
	private UserEntity createdByuserIdR2a;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY_USER_ID_R2A", columnDefinition ="int(10)")
	private UserEntity modifiedByUserIdR2a;
	
	//user r3
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY_USER_ID_R3", columnDefinition ="int(10)")
	private UserEntity createdByUserIdR3;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY_USER_ID_R3", columnDefinition ="int(10)")
	private UserEntity modifiedByUserIdR3;
	
	//user r4
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY_USER_ID_R4", columnDefinition ="int(10)") 
	private UserEntity createdByuserIdR4; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY_USER_ID_R4", columnDefinition ="int(10)")
	private UserEntity createdModifiedByUserIdR4;
		
	// user r4a
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY_USER_ID_R4A", columnDefinition ="int(10)")
	private UserEntity createdByUserIdR4a;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY_USER_ID_R4A", columnDefinition ="int(10)")
	private UserEntity modifiedByUserIdR4a;
	
	//user r5
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREATED_BY_USER_ID_R5", columnDefinition ="int(10)")
	private UserEntity createdByuserIdR5;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY_USER_ID_R5", columnDefinition ="int(10)")
	private UserEntity modifiedByUserIdR5;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MODIFIED_BY_USER_ID_PJ_OVERALL", columnDefinition ="int(10)")
	private UserEntity modifiedByUserIdPjOverall;

	//basic meta  
	@Column(name="DATE_CREATED", columnDefinition ="timestamp")  //NOT NULL 
	private Date dateCreated;
	
	@Column(name="DATE_MODIFIED", columnDefinition ="datetime")
	private Date dateModified;
	
	//date modified pj
	@Column(name="DATE_MODIFIED_PJ_OVERALL", columnDefinition ="datetime")
	private Date dateModifiedPjOverall;
	
	// date r2a
	@Column(name="DATE_CREATED_R2A", columnDefinition ="datetime")
	private Date dateCreatedR2a;

	@Column(name="DATE_MODIFIED_R2A", columnDefinition = "datetime")
	private Date dateModifiedR2a;
		
	// date r3
	@Column(name="DATE_CREATED_R3", columnDefinition ="datetime")
	private Date dateCreatedR3;
	
	@Column(name="DATE_MODIFIED_R3", columnDefinition ="datetime")
	private Date dateModifiedR3;

	//date r4
	@Column(name="DATE_CREATED_R4", columnDefinition ="datetime")
	private Date dateCreatedR4;
	
	@Column(name="DATE_MODIFIED_R4", columnDefinition ="datetime")
	private Date dateModifiedR4;

	// date r4a
	@Column(name="DATE_CREATED_R4A", columnDefinition ="datetime")
	private Date dateCreatedR4a;
	
	@Column(name="DATE_MODIFIED_R4A", columnDefinition ="datetime")
	private Date dateModifiedR4a;

	// date r5
	@Column(name="DATE_CREATED_R5", columnDefinition =" datetime")
	private Date dateCreatedR5;
	
	@Column(name="DATE_MODIFIED_R5", columnDefinition =" datetime")
	private Date dateModifiedR5;
	
	@Column(name="CREATED_BY_USER", columnDefinition ="varchar(256)")
	private String createdByUser;
	
	@Column(name="MODIFIED_BY_USER", columnDefinition ="varchar(256)")
	private String modifiedByUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProgramElementEntity getProgramElementEntity() {
		return programElementEntity;
	}

	public void setProgramElementEntity(ProgramElementEntity programElementEntity) {
		this.programElementEntity = programElementEntity;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public Integer getProjSpecialProject() {
		return projSpecialProject;
	}

	public void setProjSpecialProject(Integer projSpecialProject) {
		this.projSpecialProject = projSpecialProject;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public BigDecimal getProjApy() {
		return projApy;
	}

	public void setProjApy(BigDecimal projApy) {
		this.projApy = projApy;
	}

	public BigDecimal getProjPy() {
		return projPy;
	}

	public void setProjPy(BigDecimal projPy) {
		this.projPy = projPy;
	}

	public BigDecimal getProjCy() {
		return projCy;
	}

	public void setProjCy(BigDecimal projCy) {
		this.projCy = projCy;
	}

	public BigDecimal getProjBy1() {
		return projBy1;
	}

	public void setProjBy1(BigDecimal projBy1) {
		this.projBy1 = projBy1;
	}

	public BigDecimal getProjBy1Base() {
		return projBy1Base;
	}

	public void setProjBy1Base(BigDecimal projBy1Base) {
		this.projBy1Base = projBy1Base;
	}

	public BigDecimal getProjBy1Oco() {
		return projBy1Oco;
	}

	public void setProjBy1Oco(BigDecimal projBy1Oco) {
		this.projBy1Oco = projBy1Oco;
	}

	public BigDecimal getProjBy2() {
		return projBy2;
	}

	public void setProjBy2(BigDecimal projBy2) {
		this.projBy2 = projBy2;
	}

	public BigDecimal getProjBy3() {
		return projBy3;
	}

	public void setProjBy3(BigDecimal projBy3) {
		this.projBy3 = projBy3;
	}

	public BigDecimal getProjBy4() {
		return projBy4;
	}

	public void setProjBy4(BigDecimal projBy4) {
		this.projBy4 = projBy4;
	}

	public BigDecimal getProjBy5() {
		return projBy5;
	}

	public void setProjBy5(BigDecimal projBy5) {
		this.projBy5 = projBy5;
	}

	public String getProjCompCost() {
		return projCompCost;
	}

	public void setProjCompCost(String projCompCost) {
		this.projCompCost = projCompCost;
	}

	public String getProjTotalCost() {
		return projTotalCost;
	}

	public void setProjTotalCost(String projTotalCost) {
		this.projTotalCost = projTotalCost;
	}

	public BigDecimal getProjPysDelta() {
		return projPysDelta;
	}

	public void setProjPysDelta(BigDecimal projPysDelta) {
		this.projPysDelta = projPysDelta;
	}

	public String getProjMDAP() {
		return projMDAP;
	}

	public void setProjMDAP(String projMDAP) {
		this.projMDAP = projMDAP;
	}

	public Integer getProjArtApy() {
		return projArtApy;
	}

	public void setProjArtApy(Integer projArtApy) {
		this.projArtApy = projArtApy;
	}

	public Integer getProjArtPy() {
		return projArtPy;
	}

	public void setProjArtPy(Integer projArtPy) {
		this.projArtPy = projArtPy;
	}

	public Integer getProjArtCy() {
		return projArtCy;
	}

	public void setProjArtCy(Integer projArtCy) {
		this.projArtCy = projArtCy;
	}

	public Integer getProjArtBy1() {
		return projArtBy1;
	}

	public void setProjArtBy1(Integer projArtBy1) {
		this.projArtBy1 = projArtBy1;
	}

	public Integer getProjArtBy1Base() {
		return projArtBy1Base;
	}

	public void setProjArtBy1Base(Integer projArtBy1Base) {
		this.projArtBy1Base = projArtBy1Base;
	}

	public Integer getProjArtBy1Oco() {
		return projArtBy1Oco;
	}

	public void setProjArtBy1Oco(Integer projArtBy1Oco) {
		this.projArtBy1Oco = projArtBy1Oco;
	}

	public Integer getProjArtBy2() {
		return projArtBy2;
	}

	public void setProjArtBy2(Integer projArtBy2) {
		this.projArtBy2 = projArtBy2;
	}

	public Integer getProjArtBy3() {
		return projArtBy3;
	}

	public void setProjArtBy3(Integer projArtBy3) {
		this.projArtBy3 = projArtBy3;
	}

	public Integer getProjArtBy4() {
		return projArtBy4;
	}

	public void setProjArtBy4(Integer projArtBy4) {
		this.projArtBy4 = projArtBy4;
	}

	public Integer getProjArtBy5() {
		return projArtBy5;
	}

	public void setProjArtBy5(Integer projArtBy5) {
		this.projArtBy5 = projArtBy5;
	}

	public String getProjNotes() {
		return projNotes;
	}

	public void setProjNotes(String projNotes) {
		this.projNotes = projNotes;
	}

	public String getProjMissionDesc() {
		return projMissionDesc;
	}

	public void setProjMissionDesc(String projMissionDesc) {
		this.projMissionDesc = projMissionDesc;
	}

	public String getProjAcqSrategy() {
		return projAcqSrategy;
	}

	public void setProjAcqSrategy(String projAcqSrategy) {
		this.projAcqSrategy = projAcqSrategy;
	}

	public String getProjPerfMetrics() {
		return projPerfMetrics;
	}

	public void setProjPerfMetrics(String projPerfMetrics) {
		this.projPerfMetrics = projPerfMetrics;
	}

	public String getProjMajorPerformers() {
		return projMajorPerformers;
	}

	public void setProjMajorPerformers(String projMajorPerformers) {
		this.projMajorPerformers = projMajorPerformers;
	}

	public Integer getEditLockIdPj() {
		return editLockIdPj;
	}

	public void setEditLockIdPj(Integer editLockIdPj) {
		this.editLockIdPj = editLockIdPj;
	}

	public ArticlesInAppFlag getArticlesInAppFlag() {
		return articlesInAppFlag;
	}

	public void setArticlesInAppFlag(ArticlesInAppFlag articlesInAppFlag) {
		this.articlesInAppFlag = articlesInAppFlag;
	}

	public ProjectIncludeInPdfFlag getProjIncludeInPdfFlag() {
		return projIncludeInPdfFlag;
	}

	public void setProjIncludeInPdfFlag(ProjectIncludeInPdfFlag projIncludeInPdfFlag) {
		this.projIncludeInPdfFlag = projIncludeInPdfFlag;
	}

	public Date getDateLockPj() {
		return dateLockPj;
	}

	public void setDateLockPj(Date dateLockPj) {
		this.dateLockPj = dateLockPj;
	}

	public String getProjOtherPgmRemarks() {
		return projOtherPgmRemarks;
	}

	public void setProjOtherPgmRemarks(String projOtherPgmRemarks) {
		this.projOtherPgmRemarks = projOtherPgmRemarks;
	}

	public String getR3Remarks() {
		return r3Remarks;
	}

	public void setR3Remarks(String r3Remarks) {
		this.r3Remarks = r3Remarks;
	}

	public String getPspRemarks() {
		return pspRemarks;
	}

	public void setPspRemarks(String pspRemarks) {
		this.pspRemarks = pspRemarks;
	}

	public String getPsdRemarks() {
		return psdRemarks;
	}

	public void setPsdRemarks(String psdRemarks) {
		this.psdRemarks = psdRemarks;
	}

	public Integer getSuppressPdf() {
		return suppressPdf;
	}

	public void setSuppressPdf(Integer suppressPdf) {
		this.suppressPdf = suppressPdf;
	}

	public UserEntity getCreatedByuserIdR2a() {
		return createdByuserIdR2a;
	}

	public void setCreatedByuserIdR2a(UserEntity createdByuserIdR2a) {
		this.createdByuserIdR2a = createdByuserIdR2a;
	}

	public UserEntity getModifiedByUserIdR2a() {
		return modifiedByUserIdR2a;
	}

	public void setModifiedByUserIdR2a(UserEntity modifiedByUserIdR2a) {
		this.modifiedByUserIdR2a = modifiedByUserIdR2a;
	}

	public UserEntity getCreatedByUserIdR3() {
		return createdByUserIdR3;
	}

	public void setCreatedByUserIdR3(UserEntity createdByUserIdR3) {
		this.createdByUserIdR3 = createdByUserIdR3;
	}

	public UserEntity getModifiedByUserIdR3() {
		return modifiedByUserIdR3;
	}

	public void setModifiedByUserIdR3(UserEntity modifiedByUserIdR3) {
		this.modifiedByUserIdR3 = modifiedByUserIdR3;
	}

	public UserEntity getCreatedByuserIdR4() {
		return createdByuserIdR4;
	}

	public void setCreatedByuserIdR4(UserEntity createdByuserIdR4) {
		this.createdByuserIdR4 = createdByuserIdR4;
	}

	public UserEntity getCreatedModifiedByUserIdR4() {
		return createdModifiedByUserIdR4;
	}

	public void setCreatedModifiedByUserIdR4(UserEntity createdModifiedByUserIdR4) {
		this.createdModifiedByUserIdR4 = createdModifiedByUserIdR4;
	}

	public UserEntity getCreatedByUserIdR4a() {
		return createdByUserIdR4a;
	}

	public void setCreatedByUserIdR4a(UserEntity createdByUserIdR4a) {
		this.createdByUserIdR4a = createdByUserIdR4a;
	}

	public UserEntity getModifiedByUserIdR4a() {
		return modifiedByUserIdR4a;
	}

	public void setModifiedByUserIdR4a(UserEntity modifiedByUserIdR4a) {
		this.modifiedByUserIdR4a = modifiedByUserIdR4a;
	}

	public UserEntity getCreatedByuserIdR5() {
		return createdByuserIdR5;
	}

	public void setCreatedByuserIdR5(UserEntity createdByuserIdR5) {
		this.createdByuserIdR5 = createdByuserIdR5;
	}

	public UserEntity getModifiedByUserIdR5() {
		return modifiedByUserIdR5;
	}

	public void setModifiedByUserIdR5(UserEntity modifiedByUserIdR5) {
		this.modifiedByUserIdR5 = modifiedByUserIdR5;
	}

	public UserEntity getModifiedByUserIdPjOverall() {
		return modifiedByUserIdPjOverall;
	}

	public void setModifiedByUserIdPjOverall(UserEntity modifiedByUserIdPjOverall) {
		this.modifiedByUserIdPjOverall = modifiedByUserIdPjOverall;
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

	public Date getDateModifiedPjOverall() {
		return dateModifiedPjOverall;
	}

	public void setDateModifiedPjOverall(Date dateModifiedPjOverall) {
		this.dateModifiedPjOverall = dateModifiedPjOverall;
	}

	public Date getDateCreatedR2a() {
		return dateCreatedR2a;
	}

	public void setDateCreatedR2a(Date dateCreatedR2a) {
		this.dateCreatedR2a = dateCreatedR2a;
	}

	public Date getDateModifiedR2a() {
		return dateModifiedR2a;
	}

	public void setDateModifiedR2a(Date dateModifiedR2a) {
		this.dateModifiedR2a = dateModifiedR2a;
	}

	public Date getDateCreatedR3() {
		return dateCreatedR3;
	}

	public void setDateCreatedR3(Date dateCreatedR3) {
		this.dateCreatedR3 = dateCreatedR3;
	}

	public Date getDateModifiedR3() {
		return dateModifiedR3;
	}

	public void setDateModifiedR3(Date dateModifiedR3) {
		this.dateModifiedR3 = dateModifiedR3;
	}

	public Date getDateCreatedR4() {
		return dateCreatedR4;
	}

	public void setDateCreatedR4(Date dateCreatedR4) {
		this.dateCreatedR4 = dateCreatedR4;
	}

	public Date getDateModifiedR4() {
		return dateModifiedR4;
	}

	public void setDateModifiedR4(Date dateModifiedR4) {
		this.dateModifiedR4 = dateModifiedR4;
	}

	public Date getDateCreatedR4a() {
		return dateCreatedR4a;
	}

	public void setDateCreatedR4a(Date dateCreatedR4a) {
		this.dateCreatedR4a = dateCreatedR4a;
	}

	public Date getDateModifiedR4a() {
		return dateModifiedR4a;
	}

	public void setDateModifiedR4a(Date dateModifiedR4a) {
		this.dateModifiedR4a = dateModifiedR4a;
	}

	public Date getDateCreatedR5() {
		return dateCreatedR5;
	}

	public void setDateCreatedR5(Date dateCreatedR5) {
		this.dateCreatedR5 = dateCreatedR5;
	}

	public Date getDateModifiedR5() {
		return dateModifiedR5;
	}

	public void setDateModifiedR5(Date dateModifiedR5) {
		this.dateModifiedR5 = dateModifiedR5;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
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
		ProjectEntity other = (ProjectEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
