//package mil.dtic.cbes.model.entities.r2;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Table;
//
//import mil.dtic.cbes.model.entities.IEntity;
//import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
//
//@Entity
//@Table(name="PGM_ELEMENT")
//public class ProgramElementEntity implements IEntity, Serializable {
//	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="BUDGES_PGM_ELEMENT_ID")
//	private Integer id;
//	
//	@Column(name="BUDGES_PGM_ELEM_NUM")
//	private String peNumber;
//	
//	@Column(name="PE_TITLE")
//	private String peTitle;
//	
//	@Column(name="PE_BUDGET_CYCLE")
//	private String budgetCycle;
//	
//	@Column(name="PE_BUDGET_YEAR")
//	private Integer budgetYear;
//	
//	//private FormatFlagType  
//	
//	@Column(name="PE_R1_NUM")
//	private String r1Number;
//	
//	@Column(name="PE_SUBM_DATE")
//	private Date submissionDate;
//	
////	@Column(name="BUDGES_BUDGET_ACTIVITY_ID")
////	private BudgesActivityEntity sudgetActivityEntity;
//	
//	@Column(name="BUDGES_SERV_AGY_ID")
//	private ServiceAgencyEntity serviceAgencyEntity;
//	 
//	
//	
//	
//	
//	
//	
//	
////	 : 261  -- budgetActivityId proc_budget_activity
////	BUDGES_SERV_AGY_ID : 18   service agency id.
////	PE_SUBM_DATE  '2020-02-01 00:00:00'  note 0's for time.  
////
////	PE_PY : 0.000
////	PE_CY : 0.000
////	PE_BY1 : 0.000
////	PE_BY1_BASE : 0.000
////	PE_BY2 : 0.000
////	PE_BY3 : 0.000
////	PE_BY4 : 0.000
////	PE_BY5 : 0.000
////	PE_COMP_COST : Continuing
////	PE_TOTAL_COST : Continuing
////	PE_PREV_PB_PY : 0.000
////	PE_PREV_PB_CY : 0.000
////	PE_PREV_PB_BY1 : 0.000
////	PE_PREV_PB_BY1_BASE : 0.000
////	PE_CURR_PB_PY : 0.000
////	PE_CURR_PB_CY : 0.000
////	PE_CURR_PB_BY1 : 0.000
////	PE_CURR_PB_BY_1BASE : 0.000
////	PE_TOTAL_ADJ_PY : 0.000
////	PE_TOTAL_ADJ_CY : 0.000
////	PE_TOTAL_ADJ_BY1 : 0.000
////	
////	PE_STATUS_SUBM  : A
////	PE_STATE : A
////	PE_INIT_SRC : W
////	PE_TEST : N  (Y if test check box selected) (from UI)
////	PE_EDITABLE_SW : T
////	PE_TAG : TAG  (from UI)
////
////	EDIT_LOCK_ID_PE_ONLY : 74  (userId)
////	DATE_LOCK_PE : '2020-02-26 08:08:02'  Local
////	CREATED_BY_USER_ID_R2 : 74 (userId)
////	DATE_CREATED_R2 : 2020-02-26 13:06:20 GMT
////	DATE_MODIFIED_R2 :  2020-02-26 13:08:02  GMT
////	DATE_MODIFIED_OVERALL : '2020-02-26 08:08:02'  Local
////
////	MODIFIED_BY_USER_ID_OVERALL : userId
////	DATE_CREATED  : 2020-02-26 13:06:19  GMT
////	DATE_MODIFIED :  '2020-02-26 08:08:02'  Local
////	CREATED_BY_USER : jhart@localhost
////	MODIFIED_BY_USER: jhart@localhost
//
//	
//	
//
//}
