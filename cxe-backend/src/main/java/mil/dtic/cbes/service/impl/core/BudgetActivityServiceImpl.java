package mil.dtic.cbes.service.impl.core;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.repositories.core.BudgetActivityRepository;
import mil.dtic.cbes.service.core.BudgetActivityService;
import mil.dtic.cbes.utils.exceptions.transform.TransformerException;
import mil.dtic.cbes.utils.transform.impl.budgetactivity.R2BudgetActivityTransformer;

@Service
public class BudgetActivityServiceImpl implements BudgetActivityService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BudgetActivityRepository budgetActivityRepository;
	private R2BudgetActivityTransformer r2BudgetActivityTransformer;
	
	public BudgetActivityServiceImpl(BudgetActivityRepository budgetActivityRepository, R2BudgetActivityTransformer r2BudgetActivityTransformer) {
		this.budgetActivityRepository= budgetActivityRepository;
		this.r2BudgetActivityTransformer = r2BudgetActivityTransformer;
	}
	
	@Override
	public BudgetActivityDto getP40BudgetActivity(Integer id) { //TOOD: build out P40 BudgetActivity
		return null;
	}
	
	@Override
	public BudgetActivityDto getR2BudgetActivity(Integer id) {
		log.trace("getBudgetActivity- id: " + id);
		BudgetActivityEntity r2BudgetActivityEntity;
		
		Optional<BudgetActivityEntity> budgetActivityEntityOptional = null;
		
		try {
			budgetActivityEntityOptional = budgetActivityRepository.findById(id);
		}
		catch(Exception e) {
			System.out.println("e: msg: " + e.getMessage());
		}
		
		if (!budgetActivityEntityOptional.isPresent()) {
			log.error("getBudgetActivity- failed to get budgetActivity from database with id: "+id);
			return null;
		}

		r2BudgetActivityEntity = budgetActivityEntityOptional.get();
		
		try {
			return r2BudgetActivityTransformer.transform(r2BudgetActivityEntity);
		}
		catch(TransformerException te) {
			log.error("getBudgetActivity- transformer exception msg: " + te.getMessage());
			return null;
		}
		
	}

}
