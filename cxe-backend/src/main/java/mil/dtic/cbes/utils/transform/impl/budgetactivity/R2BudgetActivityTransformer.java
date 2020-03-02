package mil.dtic.cbes.utils.transform.impl.budgetactivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.utils.transform.Transformer;

public class R2BudgetActivityTransformer implements Transformer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public BudgetActivityDto transform(IEntity entity) {
		return null;
	}
	
	public BudgetActivityEntity transform(IDto idto) {
		return null;
	}
	
	
}
