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

import mil.dtic.cbes.model.entities.BudgetActivityEntity;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;

@Entity
@Table(name="PGM_ELEMENT")
public class ProgramElementEntity implements IEntity, Serializable {
	private static final long serialVersionUID = -6487740749610234426L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BUDGES_PGM_ELEMENT_ID")
	private Integer id;
	
	@Column(name="BUDGES_PGM_ELEM_NUM")
	private String peNumber;
	
	@Column(name="PE_BUDGET_CYCLE")
	private String budgetCycle;

	@Column(name="PE_BUDGET_YEAR")
	private Integer budgetYear;
	
	@Column(name="PE_FORMAT")
	private String peFormat;  //enum('R2Long') private FormatFlagType  
	
	@Column(name="PE_R1_NUM")
	private String r1Number;
	
	@Column(name="PE_SUBM_DATE")
	private Date submissionDate;
	 
	@Column(name="PE_TITLE")
	private String peTitle;

	@Column(name="BUDGES_BUDGET_ACTIVITY_ID")
	private BudgetActivityEntity budgetActivityEntity;
	
	@Column(name="BUDGES_SERV_AGY_ID")
	private ServiceAgencyEntity serviceAgencyEntity;
	
	@Column(name="pe_MDAP")
	private String peMdap;
	
	@Column(name="pe_apy")
	private BigDecimal peApy;
	
//	@Column(name="PE_PY") // : 0.000
//	private BigDecimal xx;
//	
//	@Column(name="PE_CY") // : 0.000
//	private BigDecimal xx;
//
//	@Column(name="PE_BY1") //: 0.000
//	private BigDecimal xx;
//
//	@Column(name="PE_BY1_BASE") //: 0.000
//	private BigDecimal xx;
//
//@Column(name="PE_BY2") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_BY3") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_BY4") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_BY5") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_COMP_COST") //: Continuing
//private BigDecimal xx;
//
//@Column(name="PE_TOTAL_COST") //: Continuing
//private BigDecimal xx;
//
//@Column(name="PE_PREV_PB_PY") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_PREV_PB_CY") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_PREV_PB_BY1") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_PREV_PB_BY1_BASE") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_CURR_PB_PY") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_CURR_PB_CY") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_CURR_PB_BY1") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_CURR_PB_BY_1BASE") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_TOTAL_ADJ_PY") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_TOTAL_ADJ_CY") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_TOTAL_ADJ_BY1") //: 0.000
//private BigDecimal xx;
//
//@Column(name="PE_STATUS_SUBM")  //: A
//private BigDecimal xx;
//
//@Column(name="PE_STATE") //: A
//private BigDecimal xx;
//
//@Column(name="PE_INIT_SRC") //: W
//private BigDecimal xx;
//
//@Column(name="PE_TEST") //: N  (Y if test check box selected) (from UI)
//private BigDecimal xx;
//
//@Column(name="PE_EDITABLE_SW") //: T
//private BigDecimal xx;
//
//@Column(name="PE_TAG") //: TAG  (from UI)
//private BigDecimal xx;
//
//@Column(name="EDIT_LOCK_ID_PE_ONLY") //: 74  (userId)
//private BigDecimal xx;
//
//@Column(name="DATE_LOCK_PE") //: '2020-02-26 08:08:02'  Local
//private BigDecimal xx;
//
//@Column(name="CREATED_BY_USER_ID_R2") //: 74 (userId)
//private Date xx;
//
//@Column(name="DATE_CREATED_R2") //: 2020-02-26 13:06:20 GMT
//private BigDate xx;
//
//@Column(name="DATE_MODIFIED_R2") //  2020-02-26 13:08:02  GMT
//private Date xx;
//
//@Column(name="DATE_MODIFIED_OVERALL") //: '2020-02-26 08:08:02'  Local
//private Date xx;
//
//
//@Column(name="MODIFIED_BY_USER_ID_OVERALL") //: userId
//private Date xx;
//
//@Column(name="DATE_CREATED")  //: 2020-02-26 13:06:19  GMT
//private Date xx;
//
//@Column(name="DATE_MODIFIED") ///'2020-02-26 08:08:02'  Local
//private Date xx;
//
//@Column(name="CREATED_BY_USER") //: jhart@localhost
//private User xx;
//
//@Column(name="MODIFIED_BY_USER") //: jhart@localhost
//private User xx;

	
	

}
