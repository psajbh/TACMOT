package mil.dtic.cbes.service.impl.exhibit;

import java.util.List;

import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.entities.views.P40AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.P40ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.R2AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;
import mil.dtic.cbes.service.exhibit.ExhibitProjectionService;

@Service
public class ExhibitProjectionServiceImpl implements ExhibitProjectionService {
	
	@Override
	public List<R2ServiceAgencyEntity> getR2ServiceAgencies() {
		return null;
	}
	
	@Override
	public List<P40ServiceAgencyEntity> getP40ServiceAgencies() {
		return null;
	}
	
	@Override
	public List<R2AppnBudgetActivityEntity> getR2AppnBudgetActivities() {
		return null;
	}
	
	@Override
	public List<P40AppnBudgetActivityEntity> getP40AppnBudgetActivities(){
		return null;
	}


}
