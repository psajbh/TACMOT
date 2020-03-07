package mil.dtic.cbes.model.entities.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.r2.ProgramElementEntity;
import mil.dtic.cbes.model.enums.exhibit.r2.project.ProjectBooleanFlag;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity implements IEntity, Serializable {
	private static final long serialVersionUID = -6384733107897400546L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUDGES_PROJ_ID", columnDefinition = "int(10)")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUDGES_PGM_ELEMENT_ID", columnDefinition = "int(10)")
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
	
	@Column(name="ARTICLES_IN_APP", columnDefinition ="enum('Y','N')")
	private ProjectBooleanFlag articlesInApp;
	
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
	
	@Column(name="PROJ_INCLUDE_IN_PDF", columnDefinition ="enum('Y','N')")
	private ProjectBooleanFlag projIncludeInPdf;
	
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
	
	@Column(name="CREATED_BY_USER_ID_R2A", columnDefinition ="int(10)")
	private Integer createdByuserIdR2a;
	
	@Column(name="DATE_CREATED_R2A", columnDefinition ="datetime")
	private Date dateCreatedR2a;
	
	@Column(name="MODIFIED_BY_USER_ID_R2A", columnDefinition ="int(10)")
	private Integer modifiedByUserIdR2a;
	
	@Column(name="DATE_MODIFIED_R2A", columnDefinition = "datetime")
	private Date dateModifiedR2a;
	
	@Column(name="DATE_CREATED_R3", columnDefinition ="datetime")
	private Date dateCreatedR3;
	
	@Column(name="DATE_MODIFIED_R3", columnDefinition ="datetime")
	private Date dateModifiedR3;
	
	@Column(name="CREATED_BY_USER_ID_R3", columnDefinition ="int(10)")
	private Integer createdByUserIdR3;
	
	@Column(name="MODIFIED_BY_USER_ID_R3", columnDefinition ="int(10)")
	private Integer modifiedByUserIdR3;
	
	@Column(name="DATE_CREATED_R4", columnDefinition ="datetime")
	private Date dateCreatedR4;
	
	@Column(name="DATE_MODIFIED_R4", columnDefinition ="datetime")
	private Date dateModifiedR4;
	
	@Column(name="CREATED_BY_USER_ID_R4", columnDefinition ="int(10)") 
	private Integer createdByuserIdR4; 
	
	@Column(name="MODIFIED_BY_USER_ID_R4", columnDefinition ="int(10)")
	private Integer createdModifiedByUserIdR4;
		
	@Column(name="DATE_CREATED_R4A", columnDefinition ="datetime")
	private Date dateCreatedR4a;
	
	@Column(name="DATE_MODIFIED_R4A", columnDefinition ="datetime")
	private Date dateModifiedR4a;
	
	@Column(name="CREATED_BY_USER_ID_R4A", columnDefinition ="int(10)")
	private Integer createdByUserIdR4a;
	
	@Column(name="MODIFIED_BY_USER_ID_R4A", columnDefinition ="int(10)")
	private Integer modifiedByUserIdR4a;
	
	@Column(name="DATE_CREATED_R5", columnDefinition =" datetime")
	private Date dateCreatedR5;
	
	@Column(name="DATE_MODIFIED_R5", columnDefinition =" datetime")
	private Date dateModifiedR5;
	
	@Column(name="CREATED_BY_USER_ID_R5", columnDefinition ="int(10)")
	private Integer createdByuserIdR5;
	
	@Column(name="MODIFIED_BY_USER_ID_R5", columnDefinition ="int(10)")
	private Integer modifiedByUserIdR5;
	
	@Column(name="DATE_MODIFIED_PJ_OVERALL", columnDefinition ="datetime")
	private Date dateModifiedPjOverall;
	
	@Column(name="MODIFIED_BY_USER_ID_PJ_OVERALL", columnDefinition ="int(10)")
	private Integer modifiedByUserIdPjOverall;
	
	@Column(name="DATE_CREATED", columnDefinition ="timestamp") 
	private Date dateCreated;
	
	@Column(name="DATE_MODIFIED", columnDefinition ="datetime")
	private Date dateModified;
	
	@Column(name="CREATED_BY_USER", columnDefinition ="varchar(256)")
	private String createdByUser;
	
	@Column(name="MODIFIED_BY_USER", columnDefinition ="varchar(256)")
	private String modifiedByUser;
}
