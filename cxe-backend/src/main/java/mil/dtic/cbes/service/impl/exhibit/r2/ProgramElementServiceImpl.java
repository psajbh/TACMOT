package mil.dtic.cbes.service.impl.exhibit.r2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;
import mil.dtic.cbes.service.core.BudgetActivityService;
import mil.dtic.cbes.service.core.BudgetCycleDefaultsService;
import mil.dtic.cbes.service.exhibit.r2.ProgramElementService;

@Service
public class ProgramElementServiceImpl implements ProgramElementService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BudgetCycleDefaultsService budgetCycleDefaultsService;
	private BudgetActivityService budgetActivityService;
	
	
	public ProgramElementServiceImpl(BudgetCycleDefaultsService budgetCycleDefaultsService, BudgetActivityService budgetActivityService) {
		this.budgetCycleDefaultsService = budgetCycleDefaultsService;
		this.budgetActivityService = budgetActivityService;
		
	}
	
	
	@Override
	public ProgramElementDto createPe(ExhibitInitDto exhibitInitDto) {
		log.trace("createPe- exhibitInitDto: " + exhibitInitDto);
		
		ProgramElementDto programElementDto = new ProgramElementDto();
		
		BudgetCycleDto budgetCycleDto = budgetCycleDefaultsService.getBudgetCycleById(exhibitInitDto.getSelectedBudgetCycleId());
		BudgetActivityDto budgetActivityDto = budgetActivityService.getBudgetActivity(exhibitInitDto.getSelectedBudgetActivityId());

		programElementDto.setBudgetCycle(budgetCycleDto);
		programElementDto.setSubmissionDate(budgetCycleDto.getCurrentDate());
		programElementDto.setPeNumber(exhibitInitDto.getProgramElementNumber());
		programElementDto.setPeTitle(exhibitInitDto.getProgramElementName());
		programElementDto.setR1Number(String.valueOf(exhibitInitDto.getR1LineNumber()));
		
		exhibitInitDto.getProgramElementNumber();
		
		log.trace("createPe- budgetCycle: " + budgetCycleDto.getLabel());
		
		
//				+ currentSubmisionDateDto);
//		programElementDto.setSubmissionDate(currentSubmisionDateDto.getSubmissionDate());
		
		
		
		return null;
		
		
//		String budgetCycleId = exhibitInitDto.getSelectedBudgetCycleId();
//		@SuppressWarnings("unused")
//		BudgetCycleDto budgetCycleDto = budgetCycleDefaultsService.getBudgetCycleById(budgetCycleId);
//		SubmissionDateDto currentSubmisionDateDto = budgetCycleDto.getCurrentSubmissionDate();
				
		//ProgramElementDto programElementDto = new ProgramElementDto();
		
		//programElementDto.
		// convert exhibitInitDto  into a programElementDto
		// transform programElementDto into a ProgamElementEntity
		// save ProgramElementEntity
		// transform back to programElementDto
		// return ProgramElementDto
		
		
		
		
	}

}
