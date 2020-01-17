package mil.dtic.cbes.service.create;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.Agency;
import mil.dtic.cbes.model.Appropriation;
import mil.dtic.cbes.model.BudgetActivity;
import mil.dtic.cbes.model.BudgetCycle;
import mil.dtic.cbes.model.BudgetSubActivity;
import mil.dtic.cbes.model.CreateP40;
import mil.dtic.cbes.model.CreateR2;
import mil.dtic.cbes.repositories.AgencyRepository;
import mil.dtic.cbes.repositories.AppropriationRepository;
import mil.dtic.cbes.repositories.BudgetActivityRepository;
import mil.dtic.cbes.repositories.BudgetCycleRepository;
import mil.dtic.cbes.repositories.BudgetSubActivityRepository;
import mil.dtic.cbes.repositories.CreateP40Repository;
import mil.dtic.cbes.repositories.CreateR2Repository;

@Service
public class CreateService {
	@Autowired
	private BudgetCycleRepository budgetCycleRepository;
	
	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private AppropriationRepository appropriationRepository;
	
	@Autowired
	private BudgetActivityRepository activityRepository;
	
	@Autowired
	private BudgetSubActivityRepository budgetSubActivity;
	
	@Autowired
	private CreateP40Repository createP40Repository;
	
	@Autowired
	private CreateR2Repository createR2Repository;
	
	public List<BudgetCycle> getBudgetCycle() {
		 List<BudgetCycle> budgetCycle = budgetCycleRepository.findAll();	
		 return budgetCycle;
	}
	
	public List<Agency> getAgency() {
		List<Agency> agencies = agencyRepository.findAll();
		return agencies;
	}

	public List<Appropriation> getAppropriation() {
		List<Appropriation> appropriations = appropriationRepository.findAll();
		return appropriations;
	}
	
	public List<BudgetActivity> getBudgetActivity() {
		List<BudgetActivity> budgetActivities = activityRepository.findAll();
		return budgetActivities;
	}
	
	public List<BudgetSubActivity> getBudgetSubActivity() {
		List<BudgetSubActivity> budgetSubActivities = budgetSubActivity.findAll();
		return budgetSubActivities;
	}
	
	public void createP40(CreateP40 createP40) {
		createP40Repository.save(createP40);
	}
	
	public void createR2(CreateR2 createR2) {
		createR2Repository.save(createR2);
	}
	
}
