package mil.dtic.cbes.controllers.exhibit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.service.config.AppDefaultsService;
import mil.dtic.cbes.service.exhibit.ExhibitProjectionService;
import mil.dtic.cbes.service.serviceagency.ServiceAgencyService;
import mil.dtic.cbes.utils.budgetcycle.BudgetCycleUtils;

@RestController
public class ExhibitInitializerController extends BaseRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	LocalDate localDate;
	LocalTime localTime;
	
	private AppDefaultsService appDefaultsService;
	private BudgetCycleUtils budgetCycleUtils;
	private ExhibitProjectionService exhibitProjectionService;
	
	
	public ExhibitInitializerController(AppDefaultsService appDefaultsService, ServiceAgencyService serviceAgencyService, 
			BudgetCycleUtils budgetCycleUtils, ExhibitProjectionService exhibitProjectionService) {
		this.appDefaultsService = appDefaultsService;
		this.budgetCycleUtils = budgetCycleUtils;
		this.exhibitProjectionService = exhibitProjectionService;
	}
	
	@GetMapping("/exhibit/init/r2")
	public ResponseEntity<ExhibitInitDto> getR2ExhibitInitDto() {
		String ldapId = getCredential().getLdapId();
		log.trace("getExhibitInitDto- ldapId: " + ldapId);
		
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setBudgetCycles(appDefaultsService.getBudgetCycleDtos());
		exhibitInitDto.setR2ServiceAgencies(exhibitProjectionService.getR2ServiceAgencies(ldapId));
		exhibitInitDto.setCurrentBudgetCycle(budgetCycleUtils.getCurrentBudgetCycle(null));
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
	@GetMapping("/exhibit/finish/r2")
	public ResponseEntity<ExhibitInitDto> getAppropriationBudgetActivity(@RequestParam Integer serviceAgencyId, @RequestParam String budgetCycleId){
		String ldapId = getCredential().getLdapId();
		log.trace("getAppropriationBudgetActivity- ldapId: "+ldapId+" serviceAgencyId: "+serviceAgencyId+" budgetCycle: "+budgetCycleId);
		List<AppnBudgetActivityProjectionDto> data = exhibitProjectionService.getR2AppnBudgetActivities(ldapId, serviceAgencyId);
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setSelectedBudgetCycleId(budgetCycleId);
		exhibitInitDto.setSelectedServiceAgencyId(serviceAgencyId);
		exhibitInitDto.setAppnBudgetActivities(data);
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
	//display a return exhibitInitDto that would be an appropriate post parameter that could be passed to exhibit/create/pe
	@GetMapping("/exhibit/r2/create/format")
	public ResponseEntity<ExhibitInitDto> r2CreatePeFormat(){
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setSelectedBudgetCycleId("PB2021");
		exhibitInitDto.setSelectedServiceAgencyId(18);
		exhibitInitDto.setSelectedAppnId(29);
		exhibitInitDto.setSelectedBudgetActivityId(27);
		//exhibitInitDto.setSelectedBudgetSubActivityId(1113);  used for p40
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
	
	@PostMapping("/exhibit/create/pe")
	public ResponseEntity<ExhibitInitDto> createProgramElement(@RequestBody ExhibitInitDto exhibitInitDto) {
		String ldapId = getCredential().getLdapId();
		return null;
	}
	
}
