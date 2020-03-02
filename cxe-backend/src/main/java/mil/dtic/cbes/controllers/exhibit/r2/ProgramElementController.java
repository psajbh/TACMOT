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
	BudgetCycleDefaultsService budgetCycleDefaultsService;
	
	public ProgramElementController(ProgramElementService programElementService, 
			BudgetCycleDefaultsService budgetCycleDefaultsService) {
		this.programElementService =programElementService;
		this.budgetCycleDefaultsService = budgetCycleDefaultsService;
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/exhibit/create/pe", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProgramElementDto> createProgramElement(@RequestBody ExhibitInitDto exhibitInitDto) throws Exception {
		if (null == getLdapId()) {
			log.error("createProgramElement- ldapId is null");
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
		String budgetCycleId = exhibitInitDto.getSelectedBudgetCycleId();
		@SuppressWarnings("unused")
		BudgetCycleDto budgetCycleDto = budgetCycleDefaultsService.getBudgetCycleById(budgetCycleId);
		SubmissionDateDto currentSubmisionDateDto = budgetCycleDto.getCurrentSubmissionDate();
				
		ProgramElementDto programElementDto = new ProgramElementDto();
		
		//programElementDto.
		// convert exhibitInitDto  into a programElementDto
		// transform programElementDto into a ProgamElementEntity
		// save ProgramElementEntity
		// transform back to programElementDto
		// return ProgramElementDto
		
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}


}
