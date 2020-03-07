package mil.dtic.cbes.service.impl.exhibit.r2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mil.dtic.cbes.model.dto.core.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.r2.ProgramElementEntity;
import mil.dtic.cbes.model.enums.exhibit.r2.programelement.PeEditableSwFlag;
import mil.dtic.cbes.model.enums.exhibit.r2.programelement.PeFormatFlag;
import mil.dtic.cbes.model.enums.exhibit.r2.programelement.PeInitSourceFlag;
import mil.dtic.cbes.model.enums.exhibit.r2.programelement.PeStateFlag;
import mil.dtic.cbes.model.enums.exhibit.r2.programelement.PeSubmissionStatusFlag;
import mil.dtic.cbes.model.enums.exhibit.r2.programelement.PeTestFlag;
import mil.dtic.cbes.repositories.exhibit.r2.ProgramElementEntityRepository;
import mil.dtic.cbes.service.core.BudgetActivityService;
import mil.dtic.cbes.service.core.BudgetCycleDefaultsService;
import mil.dtic.cbes.service.core.ServiceAgencyService;
import mil.dtic.cbes.service.exhibit.r2.ProgramElementService;
import mil.dtic.cbes.service.security.user.UserEntityService;
import mil.dtic.cbes.utils.exceptions.service.exhibit.r2.ProgramElementServiceException;
import mil.dtic.cbes.utils.transform.impl.exhibit.r2.ProgramElementTransformer;

@Service
public class ProgramElementServiceImpl implements ProgramElementService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final static String DATE_FORMAT_TEMPLATE= "dd-MMM-yyyy";
	private final static String DATE_VALUE_TEMPLATE = "1-%s-%s"; 
	private final static BigDecimal DEFAULT_FUNDING = new BigDecimal(0.000);
	private final static String DEFAULT_COST = "Continuing";
	
	private BudgetCycleDefaultsService budgetCycleDefaultsService;
	private BudgetActivityService budgetActivityService;
	private ServiceAgencyService serviceAgencyService;
	private ProgramElementEntityRepository programElementRepository;
	private ProgramElementTransformer programElementTransformer;
	private UserEntityService userEntityService;
	
	public ProgramElementServiceImpl(BudgetCycleDefaultsService budgetCycleDefaultsService, 
			BudgetActivityService budgetActivityService, ServiceAgencyService serviceAgencyService,
			ProgramElementEntityRepository programElementRepository, ProgramElementTransformer programElementTransformer,
			UserEntityService userEntityService) {
		this.budgetCycleDefaultsService = budgetCycleDefaultsService;
		this.budgetActivityService = budgetActivityService;
		this.serviceAgencyService = serviceAgencyService;
		this.programElementRepository = programElementRepository;
		this.programElementTransformer = programElementTransformer;
		this.userEntityService = userEntityService;
	}

	@Transactional
	@Override
	public ProgramElementDto createPe(ExhibitInitDto exhibitInitDto) throws ProgramElementServiceException {
		log.trace("createPe- exhibitInitDto: " + exhibitInitDto);
		
		ProgramElementDto programElementDto = getProgramElementDto(exhibitInitDto);
		ProgramElementEntity programElementEntity = programElementTransformer.transform(programElementDto);
		ProgramElementEntity savedProgramElementEntity = null;
		
		try {
			savedProgramElementEntity = programElementRepository.save(programElementEntity);
			log.debug("createPe- savedProgramElementEntity: " + savedProgramElementEntity);
		} 
		catch(DataIntegrityViolationException | ConstraintViolationException e1) {
			log.error("createPe- e1 exception: "+e1.getClass().toString() + " msg: " + e1.getMessage());  
			if (e1.getMessage().contains(ProgramElementServiceException.PG_ELEM_UN_BUDGES_PE_NUM_BUDGET_CYCLE)) {
				throw new ProgramElementServiceException(ProgramElementServiceException.PGM_ELEM_EXCEPTION_DUP_KEY);
			}
			else {
				throw new ProgramElementServiceException(ProgramElementServiceException.PGM_ELEM_EXCEPTION_DATA_INEGRITY);
			}
		}
		catch(RuntimeException re2) {
			log.error("createPe- runtimeException msg: "+re2.getMessage(),re2);
			throw new ProgramElementServiceException(ProgramElementServiceException.PGM_ELEM_EXCEPTION_GENERIC);
		}
		
		if (null != savedProgramElementEntity) {
			return programElementTransformer.transform(savedProgramElementEntity);
		}
		
		throw new ProgramElementServiceException(ProgramElementServiceException.PGM_ELEM_PROCESS_FAILURE);
	}
	
	private ProgramElementDto getProgramElementDto(ExhibitInitDto exhibitInitDto) throws ProgramElementServiceException{
		ProgramElementDto programElementDto = new ProgramElementDto();
		
		BudgetCycleDto budgetCycleDto = budgetCycleDefaultsService.getBudgetCycleById(exhibitInitDto.getSelectedBudgetCycleId());
		programElementDto.setBudgetCycle(budgetCycleDto.getCycle());
		programElementDto.setBudgetYear(Integer.valueOf(budgetCycleDto.getBudgetYear()));
		programElementDto.setSubmissionDate(getCurrentDate(budgetCycleDto.getCurrentSubmissionDate()));
		programElementDto.setBudgetActivityDto(budgetActivityService.getR2BudgetActivity(exhibitInitDto.getSelectedBudgetActivityId()));
		
		UserDto userDto = userEntityService.findUserDto();
		
		if (userDto.getId().equals(exhibitInitDto.getUserId())) {
			programElementDto.setCreatedByUserR2(userDto);
			programElementDto.setModifiedByUserR2(userDto);
			programElementDto.setModifiedByUserIdOverall(userDto);
		}
		else {
			log.error("getProgramElementDto- User validation failure-  userDtoId: "+userDto.getId ()+" exhibitInitDto.userId: "+exhibitInitDto.getUserId());
			throw new ProgramElementServiceException(ProgramElementServiceException.PGM_ELEM_USER_VALIDATION_FAILURE);
		}
		
		Date now = new Date();
		programElementDto.setDateCreated(now);
		programElementDto.setDateCreatedR2(now);
		programElementDto.setDateModifiedR2(now);
		programElementDto.setDateModifiedOverall(now);
		programElementDto.setDateModified(now);
		
		ServiceAgencyDto serviceAgencyDto = serviceAgencyService.getServiceAgency(exhibitInitDto.getSelectedServiceAgencyId());
		serviceAgencyDto.setAppropriations(null);
		programElementDto.setServiceAgencyDto(serviceAgencyDto);
		
		programElementDto.setPeNumber(exhibitInitDto.getProgramElementNumber());
		programElementDto.setPeTitle(exhibitInitDto.getProgramElementName());
		programElementDto.setR1Number(String.valueOf(exhibitInitDto.getR1LineNumber()));
		programElementDto.setPeTag(exhibitInitDto.getTag());
		
		if (exhibitInitDto.isTestPe()) {
			programElementDto.setPeTest(PeTestFlag.Y);
		}
		else {
			programElementDto.setPeTest(PeTestFlag.N);
		}
		
		if (exhibitInitDto.isR2Long()) {
			programElementDto.setPeFormat(PeFormatFlag.R2Long);
		}
		
		programElementDto.setPeInitSrc(PeInitSourceFlag.W);
		programElementDto.setPeEditableSw(PeEditableSwFlag.T);
		programElementDto.setPeStatusSubmission(PeSubmissionStatusFlag.A);
		programElementDto.setPeState(PeStateFlag.A);
		programElementDto.setDateCreated(new Date());
		
		if (exhibitInitDto.isR2Long()) {
			programElementDto.setPeFormat(PeFormatFlag.R2Long);
		}
		
		programElementDto.setPeApy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeApy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPePy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeCy(ProgramElementServiceImpl.DEFAULT_FUNDING); 
		programElementDto.setPeBy1(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeBy1Base(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeBy2(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeBy3(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeBy4(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeBy5(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPePrevPbPy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPePrevPbCy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPePbBy1(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPePrevPbBy1Base(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeCurrPbPy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeCurrPbCy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeCurrPbBy1(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeCurrPbBy1Base(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeTotalAdjPy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeTotalAdjCy(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeTotalAdjBy1(ProgramElementServiceImpl.DEFAULT_FUNDING);
		programElementDto.setPeTotalAdjBy1Base(ProgramElementServiceImpl.DEFAULT_FUNDING);
		
		programElementDto.setPeCompCost(ProgramElementServiceImpl.DEFAULT_COST);
		programElementDto.setPeTotalCost(ProgramElementServiceImpl.DEFAULT_COST);
		
		return programElementDto;
	}
	
	private Date getCurrentDate(SubmissionDateDto submissionDateDto) throws ProgramElementServiceException{
		log.trace("getCurrentDate-");
		String submissionDateId = submissionDateDto.getSubmissionDateId();
		String dateTemplate = String.format(ProgramElementServiceImpl.DATE_VALUE_TEMPLATE, submissionDateId.substring(0, 3), submissionDateId.substring(3));
		SimpleDateFormat formatter2 = new SimpleDateFormat(ProgramElementServiceImpl.DATE_FORMAT_TEMPLATE);

		try {
			return formatter2.parse(dateTemplate);
		} 
		catch (Exception e) { //TODO: create a new exception class to support PE issues.
			log.error("getCurrentDate- failure to extract date from submissionDateDto - msg: "+e.getMessage(),e);
			throw new ProgramElementServiceException(ProgramElementServiceException.PGM_ELEM_EXCEPTION_GENERIC);
		}
	}
}
