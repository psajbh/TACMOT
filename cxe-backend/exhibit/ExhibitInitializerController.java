package mil.dtic.cbes.controllers.exhibit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.service.config.AppDefaultsService;
import mil.dtic.cbes.service.serviceagency.ServiceAgencyService;

@RestController
public class ExhibitInitializerController extends BaseRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AppDefaultsService appDefaultsService;
	private ServiceAgencyService serviceAgencyService;
	
	public ExhibitInitializerController(AppDefaultsService appDefaultsService, ServiceAgencyService serviceAgencyService) {
		this.appDefaultsService = appDefaultsService;
		this.serviceAgencyService = serviceAgencyService;
	}
	
	@GetMapping("/exhibit/init")
	public ResponseEntity<ExhibitInitDto> getExhibitInitDto() {
		log.trace("getExhibitInitDto-");
		ExhibitInitDto exhibitInitDto = new ExhibitInitDto();
		
		exhibitInitDto.setBudgetCycles(appDefaultsService.getBudgetCycleDtos());
		
		List<ServiceAgencyDto> serviceAgenyEnties = serviceAgencyService.getServiceAgencies();
		
		return null;
	}
	
	
	
	


	
}
