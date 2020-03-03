package mil.dtic.cbes.utils.transform.impl.budgetactivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class R2BudgetActivityTransformer implements Transformer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    final static String R2_BUDGEST_ACTIVITY_DTO_NULL = "R2 budget activity dto is null";
    final static String R2_BUDGEST_ACTIVITY_ENTITY_NULL  = "R2 budget activity entity is null";


	@Override
	public BudgetActivityDto transform(IEntity entity) throws TransformerException {
		BudgetActivityEntity budgetActivityEnity = (BudgetActivityEntity) entity;
		if (null  == budgetActivityEnity) {
			throw new TransformerException(R2BudgetActivityTransformer.R2_BUDGEST_ACTIVITY_ENTITY_NULL);
		}
		
		if (null != budgetActivityEnity.getId()) {
			BudgetActivityDto budgetActivityDto = new BudgetActivityDto();
			budgetActivityDto.setId(budgetActivityEnity.getId());
			budgetActivityDto.setNum(budgetActivityEnity.getBudgetNumber());
			budgetActivityDto.setName(budgetActivityEnity.getBudgetActivityTitle());
			return budgetActivityDto;
		}
		
		log.error("budgetActivityEnity id is null");
		return null;
	}
	
	@Override
	public BudgetActivityEntity transform(IDto idto) throws TransformerException {
		BudgetActivityDto budgetActivityDto = (BudgetActivityDto) idto;
		
		if (null  == budgetActivityDto) {
			throw new TransformerException(R2BudgetActivityTransformer.R2_BUDGEST_ACTIVITY_DTO_NULL);
		}

		if (null != budgetActivityDto.getId()) {
			BudgetActivityEntity budgetActivityEntity = new BudgetActivityEntity();
			budgetActivityEntity.setId(budgetActivityDto.getId());
			budgetActivityEntity.setBudgetNumber(budgetActivityDto.getNum());
			budgetActivityEntity.setBudgetActivityTitle(budgetActivityDto.getName());
			budgetActivityEntity.setProcFlag(0);
			budgetActivityEntity.setRdteFlag(1);
			return budgetActivityEntity;
		}
		log.error("budgetActivityDto id is null");
		return null;
	}
	
	
}
