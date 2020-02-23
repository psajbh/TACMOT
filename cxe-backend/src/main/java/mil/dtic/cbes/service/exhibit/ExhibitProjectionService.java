package mil.dtic.cbes.service.exhibit;

import java.util.List;

import mil.dtic.cbes.model.entities.views.P40AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.P40ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.R2AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;

public interface ExhibitProjectionService {
	
	List<R2ServiceAgencyEntity> getR2ServiceAgencies();
	List<P40ServiceAgencyEntity> getP40ServiceAgencies();
	List<R2AppnBudgetActivityEntity> getR2AppnBudgetActivities();
	List<P40AppnBudgetActivityEntity> getP40AppnBudgetActivities();
}
