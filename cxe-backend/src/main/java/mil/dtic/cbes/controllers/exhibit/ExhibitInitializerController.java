package mil.dtic.cbes.controllers.exhibit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.service.config.AppDefaultsService;
import mil.dtic.cbes.service.serviceagency.ServiceAgencyService;
import mil.dtic.cbes.utils.budgetcycle.BudgetCycleUtils;

@RestController
public class ExhibitInitializerController extends BaseRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AppDefaultsService appDefaultsService;
	private ServiceAgencyService serviceAgencyService;
	private BudgetCycleUtils budgetCycleUtils;
	
	public ExhibitInitializerController(AppDefaultsService appDefaultsService, ServiceAgencyService serviceAgencyService, BudgetCycleUtils budgetCycleUtils) {
		this.appDefaultsService = appDefaultsService;
		this.serviceAgencyService = serviceAgencyService;
		this.budgetCycleUtils = budgetCycleUtils;
	}
	
	@GetMapping("/exhibit/init")
	public ResponseEntity<ExhibitInitDto> getExhibitInitDto() {
		String ldapId = getCredential().getLdapId();
		log.trace("getExhibitInitDto- ldapId: " + ldapId);
		
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		exhibitInitDto.setBudgetCycles(appDefaultsService.getBudgetCycleDtos());
		exhibitInitDto.setServiceAgencies(serviceAgencyService.getServiceAgencies(ldapId));
		exhibitInitDto.setCurrentBudgetCycle(budgetCycleUtils.getCurrentBudgetCycle(null));
		return ResponseEntity.status(HttpStatus.OK).body(exhibitInitDto);
	}
	
}
