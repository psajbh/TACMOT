package mil.dtic.cbes.utils.transform.impl.appn;

import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.exhibit.R2ServiceAgencyAppnActivityEntity;
import mil.dtic.cbes.model.entities.views.R2AppnBudgetActivityEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class R2AppnBudgetActivityProjectionTransformer implements Transformer {
	
	@Override
	public AppnBudgetActivityProjectionDto transform(IEntity entity) throws TransformerException {
		R2ServiceAgencyAppnActivityEntity r2AppnBudgetActivityEntity = (R2ServiceAgencyAppnActivityEntity) entity; 
		
        if (null == r2AppnBudgetActivityEntity) {
            throw new RuntimeException(AppropriationTransformer.APPROPRIATION_ENTITY_NULL);
        }
        
        if (null != r2AppnBudgetActivityEntity.getServiceAgencyId()) {
        	AppnBudgetActivityProjectionDto appnBudgetActivityProjectionDto = new AppnBudgetActivityProjectionDto();
        	appnBudgetActivityProjectionDto.setServiceAgencyId(r2AppnBudgetActivityEntity.getServiceAgencyId());
        	appnBudgetActivityProjectionDto.setAppropriationId(r2AppnBudgetActivityEntity.getAppropriationId());
        	appnBudgetActivityProjectionDto.setBudgetActivityId(r2AppnBudgetActivityEntity.getBudgetActivityId());
        	appnBudgetActivityProjectionDto.setBudgetSubActivityId(r2AppnBudgetActivityEntity.getBudgetSubActivityId());
        	appnBudgetActivityProjectionDto.setAppnCode(r2AppnBudgetActivityEntity.getAppnCode());
        	appnBudgetActivityProjectionDto.setAppnName(r2AppnBudgetActivityEntity.getAppnName());
        	appnBudgetActivityProjectionDto.setBudgetActivityNum(r2AppnBudgetActivityEntity.getBudgetActivityNum());
        	appnBudgetActivityProjectionDto.setBudgetActivityTitle(r2AppnBudgetActivityEntity.getBudgetActivityTitle());
        	appnBudgetActivityProjectionDto.setBudgetSubActivityNum(r2AppnBudgetActivityEntity.getBudgetSubActivityNum());
        	appnBudgetActivityProjectionDto.setBudgetSubActivityTitle(r2AppnBudgetActivityEntity.getBudgetSubActivityTitle());
        	return appnBudgetActivityProjectionDto;
        }
		return null;
	}
	
	@Override
	public R2AppnBudgetActivityEntity transform(IDto idto) throws TransformerException {
		AppnBudgetActivityProjectionDto appnBudgetActivityProjectionDto = (AppnBudgetActivityProjectionDto) idto;
		
		if (null == appnBudgetActivityProjectionDto) {
			throw new RuntimeException(AppropriationTransformer.APPROPIATION_DTO_NULL);
		}
		
		R2AppnBudgetActivityEntity r2AppnBudgetActivityEntity = new R2AppnBudgetActivityEntity();
		r2AppnBudgetActivityEntity.setServiceAgencyId(appnBudgetActivityProjectionDto.getServiceAgencyId());
		r2AppnBudgetActivityEntity.setAppropriationId(appnBudgetActivityProjectionDto.getAppropriationId());
		r2AppnBudgetActivityEntity.setBudgetActivityId(appnBudgetActivityProjectionDto.getBudgetActivityId());
		r2AppnBudgetActivityEntity.setBudgetSubActivityId(appnBudgetActivityProjectionDto.getBudgetSubActivityId());
		r2AppnBudgetActivityEntity.setAppnCode(appnBudgetActivityProjectionDto.getAppnCode());
		r2AppnBudgetActivityEntity.setAppnName(appnBudgetActivityProjectionDto.getAppnName());
		r2AppnBudgetActivityEntity.setBudgetActivityNum(appnBudgetActivityProjectionDto.getBudgetActivityNum());
		r2AppnBudgetActivityEntity.setBudgetActivityTitle(appnBudgetActivityProjectionDto.getBudgetActivityTitle());
		r2AppnBudgetActivityEntity.setBudgetSubActivityNum(appnBudgetActivityProjectionDto.getBudgetSubActivityNum());
		r2AppnBudgetActivityEntity.setBudgetSubActivityTitle(appnBudgetActivityProjectionDto.getBudgetSubActivityTitle());
		return r2AppnBudgetActivityEntity;
	}

}
