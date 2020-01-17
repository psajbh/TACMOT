package mil.dtic.cbes.controllers.create;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.Agency;
import mil.dtic.cbes.model.Appropriation;
import mil.dtic.cbes.model.BudgetActivity;
import mil.dtic.cbes.model.BudgetCycle;
import mil.dtic.cbes.model.BudgetSubActivity;
import mil.dtic.cbes.model.CreateP40;
import mil.dtic.cbes.model.CreateR2;
import mil.dtic.cbes.service.create.CreateService;

@RestController
public class CreateController extends BaseRestController {
	
	@Autowired
	CreateService createService;
	
	@GetMapping("create/getbudgetcycle")
	public List<BudgetCycle> getBudgetCycle() {
		return createService.getBudgetCycle();
	}
	
	@GetMapping("create/getagency")
	public List<Agency> getAgency() {
		return createService.getAgency();
	}
	
	@GetMapping("create/getappropriation")
	public List<Appropriation> getAppropriation() {
		return createService.getAppropriation();
	}
	
	@GetMapping("create/getbudgetactivty")
	public List<BudgetActivity> getbudgetActivities() {
		return createService.getBudgetActivity();
	}
	
	@GetMapping("create/getbudgetsubactivity")
	public List<BudgetSubActivity> getBudgetSubActivities() {
		return createService.getBudgetSubActivity();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("create/createp40")
	public void createP40(@RequestBody CreateP40 createP40) {
		createService.createP40(createP40);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("create/creater2")
	public void createR2(@RequestBody CreateR2 createR2) {
		createService.createR2(createR2);
	}
	
}
