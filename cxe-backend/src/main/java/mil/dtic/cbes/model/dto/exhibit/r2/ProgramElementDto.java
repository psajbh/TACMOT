package mil.dtic.cbes.model.dto.exhibit.r2;

import java.math.BigDecimal;
import java.util.Date;


import mil.dtic.cbes.model.dto.Dto;
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
	private BudgetActivityEntity budgetActivityEntity;
	private ServiceAgencyEntity serviceAgencyEntity;
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


}
