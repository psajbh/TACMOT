package mil.dtic.cbes.controllers.exhibit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;
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
	private ServiceAgencyService serviceAgencyService;
	private BudgetCycleUtils budgetCycleUtils;
	private ExhibitProjectionService exhibitProjectionService;
	
	
	public ExhibitInitializerController(AppDefaultsService appDefaultsService, ServiceAgencyService serviceAgencyService, 
			BudgetCycleUtils budgetCycleUtils, ExhibitProjectionService exhibitProjectionService) {
		this.appDefaultsService = appDefaultsService;
		//this.serviceAgencyService = serviceAgencyService;
		this.budgetCycleUtils = budgetCycleUtils;
		this.exhibitProjectionService = exhibitProjectionService;
	}
	
	@GetMapping("/exhibit/init/r2")
	public ResponseEntity<ExhibitInitDto> getR2ExhibitInitDto() {
		String ldapId = getCredential().getLdapId();
		log.trace("getExhibitInitDto- ldapId: " + ldapId);
		
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setBudgetCycles(appDefaultsService.getBudgetCycleDtos());
		exhibitInitDto.setR2ServiceAgencies(serviceAgencyService.getR2ServiceAgencies(ldapId));
		exhibitInitDto.setCurrentBudgetCycle(budgetCycleUtils.getCurrentBudgetCycle(null));
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
	//id=18 PB2020
	@GetMapping("/exhibit/finish/r2")
	public ResponseEntity<ExhibitInitDto> getAppropriationBudgetActivity(@RequestParam Integer serviceAgencyId, @RequestParam String budgetCycle){
		log.debug("getAppropriationBudgetActivity- serviceAgencyId: " + serviceAgencyId + " budgetCycle: " + budgetCycle);
		exhibitProjectionService.getR2AppnBudgetActivities();
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
}
