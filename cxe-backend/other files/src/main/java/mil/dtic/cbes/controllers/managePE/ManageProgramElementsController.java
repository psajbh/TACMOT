package mil.dtic.cbes.controllers.managePE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.Agency;
import mil.dtic.cbes.model.BudgetCycle;
import mil.dtic.cbes.model.ProgramElement;
import mil.dtic.cbes.repositories.AgencyRepository;
import mil.dtic.cbes.repositories.BudgetCycleRepository;
import mil.dtic.cbes.repositories.ProgramElementRepository;

@RestController
public class ManageProgramElementsController extends BaseRestController{
	@Autowired
	private AgencyRepository agencyRepo;
	
	@Autowired
	private BudgetCycleRepository budgetCycleRepo;
	
	@Autowired
	private ProgramElementRepository peRepo;

	@GetMapping("/programElement/getServiceAgencys")
	public List<Agency> getAllServiceAgencys() {
		return agencyRepo.findAll();
	}
	
	@GetMapping("/programElement/getBudgetCycles")
	public List<BudgetCycle> getBudgetCycles() {
		return budgetCycleRepo.findAll();
	}
	
	@GetMapping("/programElement/getProgramElements")
	public List<ProgramElement> getProgramElements() {
		return peRepo.findAll();
	}
}
