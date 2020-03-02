package mil.dtic.cbes.controllers.exhibit.r2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;
import mil.dtic.cbes.service.core.BudgetCycleDefaultsService;
import mil.dtic.cbes.service.exhibit.r2.ProgramElementService;

@RestController
public class ProgramElementController extends BaseRestController{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	ProgramElementService programElementService;
	
	
	public ProgramElementController(ProgramElementService programElementService) {
		this.programElementService = programElementService;
	}
	
	@PostMapping(value = "/exhibit/create/pe", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProgramElementDto> createProgramElement(@RequestBody ExhibitInitDto exhibitInitDto) throws Exception {
		log.trace("createProgramElement-");
		
		if (null == getLdapId()) {
			log.error("createProgramElement- ldapId is null");
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
		ProgramElementDto programElementDto = programElementService.createPe(exhibitInitDto);
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(programElementDto);
		
	}


}
