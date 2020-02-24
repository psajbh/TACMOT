package mil.dtic.cbes.service.impl.exhibit;

import java.util.List;

import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.entities.views.P40AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.P40ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.R2AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;
import mil.dtic.cbes.repositories.exhibit.projection.p40.P40AppnBudgetActivityProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.projection.p40.P40ServiceAgencyProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.projection.r2.R2AppnBudgetActivityProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.projection.r2.R2ServiceAgencyProjectionRepository;
import mil.dtic.cbes.service.exhibit.ExhibitProjectionService;

@Service
public class ExhibitProjectionServiceImpl implements ExhibitProjectionService {
	
	private R2AppnBudgetActivityProjectionRepository r2AppnBudgetActivityRepo;
	private R2ServiceAgencyProjectionRepository r2ServiceAgencyRepo;
	private P40AppnBudgetActivityProjectionRepository p40AppnBudgetActivityRepo;
	private P40ServiceAgencyProjectionRepository p40ServiceAgencyRepo;
	
	public ExhibitProjectionServiceImpl(R2AppnBudgetActivityProjectionRepository r2AppnBudgetActivityRepo,
			R2ServiceAgencyProjectionRepository r2ServiceAgencyRepo, 
			P40AppnBudgetActivityProjectionRepository p40AppnBudgetActivityRepo, 
			P40ServiceAgencyProjectionRepository p40ServiceAgencyRepo) {
		this.r2AppnBudgetActivityRepo = r2AppnBudgetActivityRepo;
		this.r2ServiceAgencyRepo = r2ServiceAgencyRepo;
		this.p40AppnBudgetActivityRepo = p40AppnBudgetActivityRepo;
		this.p40ServiceAgencyRepo = p40ServiceAgencyRepo;
	}
	
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
