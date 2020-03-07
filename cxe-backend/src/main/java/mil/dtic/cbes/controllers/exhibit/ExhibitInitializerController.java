package mil.dtic.cbes.controllers.exhibit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.core.PeSuffixDto;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.R2AppropriationDto;
import mil.dtic.cbes.service.core.BudgetCycleDefaultsService;
import mil.dtic.cbes.service.core.ServiceAgencyService;
import mil.dtic.cbes.service.exhibit.ExhibitProjectionService;
import mil.dtic.cbes.utils.budgetcycle.BudgetCycleUtils;

@RestController
public class ExhibitInitializerController extends BaseRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BudgetCycleDefaultsService appDefaultsService;
	private BudgetCycleUtils budgetCycleUtils;
	private ExhibitProjectionService exhibitProjectionService;
	
	
	public ExhibitInitializerController(BudgetCycleDefaultsService appDefaultsService, ServiceAgencyService serviceAgencyService, 
			BudgetCycleUtils budgetCycleUtils, ExhibitProjectionService exhibitProjectionService) {
		this.appDefaultsService = appDefaultsService;
		this.budgetCycleUtils = budgetCycleUtils;
		this.exhibitProjectionService = exhibitProjectionService;
	}
	
	@GetMapping("/exhibit/init/r2")
	public ResponseEntity<ExhibitInitDto> getR2ExhibitInitDto() {
		if (null == getLdapId()) {
			log.error("getR2ExhibitInitDto- ldapId is null");
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

		log.trace("getExhibitInitDto- ldapId: " + getLdapId());
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setBudgetCycles(appDefaultsService.getBudgetCycleDtos());
		exhibitInitDto.setR2ServiceAgencies(exhibitProjectionService.getR2ServiceAgencies());
		exhibitInitDto.setCurrentBudgetCycle(budgetCycleUtils.getCurrentBudgetCycle(null));
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
	@GetMapping("/exhibit/finish/r2")
	public ResponseEntity<ExhibitInitDto> getAppropriationBudgetActivity(@RequestParam Integer serviceAgencyId, @RequestParam String budgetCycleId){
		if (null == getLdapId()) {
			log.error("getAppropriationBudgetActivity- ldapId is null");
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
		log.trace("getAppropriationBudgetActivity- ldapId: "+getLdapId()+" serviceAgencyId: "+serviceAgencyId+" budgetCycle: "+budgetCycleId);
		List<R2AppropriationDto> appropriationData = exhibitProjectionService.getR2AppnBudgetActivities(serviceAgencyId);
		List<PeSuffixDto> peSuffixs = exhibitProjectionService.getPeSuffixFromServiceAgencyId(serviceAgencyId);
		
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setSelectedBudgetCycleId(budgetCycleId);
		exhibitInitDto.setSelectedServiceAgencyId(serviceAgencyId);
		exhibitInitDto.setAppropriations(appropriationData);
		exhibitInitDto.setSerivceAgencyPESuffixs(peSuffixs);
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
	@GetMapping("/exhibit/r2/create/format")
	public ResponseEntity<ExhibitInitDto> r2CreatePeFormat(){
		if (null == getLdapId()) {
			log.error("r2CreatePeFormat- ldapId is null");
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setSelectedBudgetCycleId("PB2021");
		exhibitInitDto.setSelectedServiceAgencyId(18);
		exhibitInitDto.setSelectedBudgetActivityId(27);
		exhibitInitDto.setProgramElementNumber("1111111D8Z");
		exhibitInitDto.setProgramElementName("Help");
		exhibitInitDto.setR1LineNumber(12345);
		exhibitInitDto.setTag("Tag123");
		exhibitInitDto.setR2Long(false);
		exhibitInitDto.setTestPe(false);
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
}
