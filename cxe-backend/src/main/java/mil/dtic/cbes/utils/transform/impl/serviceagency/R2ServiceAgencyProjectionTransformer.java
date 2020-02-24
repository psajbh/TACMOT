package mil.dtic.cbes.utils.transform.impl.serviceagency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class R2ServiceAgencyProjectionTransformer implements Transformer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public R2ServiceAgencyProjectionTransformer() {}

	
	public ServiceAgencyProjectionDto transform(IEntity entity) throws TransformerException {
		R2ServiceAgencyEntity r2ServiceAgencyEntity = (R2ServiceAgencyEntity) entity;
		
        if (null == r2ServiceAgencyEntity) {
            throw new RuntimeException(ServiceAgencyTransformer.SERVICE_AGENCY_ENTITY_NULL);
        }

        if (null != r2ServiceAgencyEntity.getServiceAgencyId()) {
        	log.trace("transform- start transforming simpleServiceAgencyEntity: "+r2ServiceAgencyEntity.getServiceAgencyId()+" to a simpleServiceAgencyDto object");
        	ServiceAgencyProjectionDto r2ServiceAgencyDto = new ServiceAgencyProjectionDto();
        	r2ServiceAgencyDto.setId(r2ServiceAgencyEntity.getServiceAgencyId());
        	r2ServiceAgencyDto.setCode(r2ServiceAgencyEntity.getCode());
        	r2ServiceAgencyDto.setName(r2ServiceAgencyEntity.getName());
        	return r2ServiceAgencyDto;
        }
        return null;
	}
	
	public R2ServiceAgencyEntity transform(IDto idto) throws TransformerException{
		ServiceAgencyProjectionDto r2ServiceAgencyDto = (ServiceAgencyProjectionDto) idto;
		
		if (null == r2ServiceAgencyDto) {
			throw new RuntimeException(ServiceAgencyTransformer.SERVICE_AGENCY_DTO_NULL);
		}
		
		R2ServiceAgencyEntity r2ServiceAgencyEntity = new R2ServiceAgencyEntity();
		r2ServiceAgencyEntity.setServiceAgencyId(r2ServiceAgencyDto.getId());
		r2ServiceAgencyEntity.setCode(r2ServiceAgencyDto.getCode());
		r2ServiceAgencyEntity.setName(r2ServiceAgencyDto.getName());
		return r2ServiceAgencyEntity;
	}
        
        

}