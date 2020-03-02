package mil.dtic.cbes.service.impl.core;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.repositories.core.BudgetActivityRepository;
import mil.dtic.cbes.service.core.BudgetActivityService;

@Service
public class BudgetActivityServiceImpl implements BudgetActivityService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BudgetActivityRepository budgetActivityRepository;
	
	public BudgetActivityServiceImpl(BudgetActivityRepository budgetActivityRepository) {
		this.budgetActivityRepository= budgetActivityRepository;
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public BudgetActivityDto getBudgetActivity(Integer id){
		log.trace("getBudgetActivity- id: " + id);
		BudgetActivityEntity budgetActivityEntity;
		Optional<BudgetActivityEntity> budgetActivityEntityOptional = budgetActivityRepository.findById(id);
		if (!budgetActivityEntityOptional.isPresent()) {
			 return null;
		}
		
		budgetActivityEntity = budgetActivityEntityOptional.get();
		System.out.println();
		
		return null;
	}

}
