package mil.dtic.cbes.utils.transform.impl.serviceagency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.views.r2.R2ServiceAgencyEntity;
import mil.dtic.cbes.utils.exceptions.transform.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class R2ServiceAgencyProjectionTransformer implements Transformer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public R2ServiceAgencyProjectionTransformer() {}

	@Override
	public ServiceAgencyProjectionDto transform(IEntity entity) throws TransformerException {
		log.trace("transform- r2ServiceAgencyEntity");
		R2ServiceAgencyEntity r2ServiceAgencyEntity = (R2ServiceAgencyEntity) entity;
		
        if (null == r2ServiceAgencyEntity) {
            throw new TransformerException(TransformerException.TRANSFORM_ENTITY_NULL);
        }

        if (null != r2ServiceAgencyEntity.getServiceAgencyId()) {
        	ServiceAgencyProjectionDto r2ServiceAgencyDto = new ServiceAgencyProjectionDto();
        	r2ServiceAgencyDto.setId(r2ServiceAgencyEntity.getServiceAgencyId());
        	r2ServiceAgencyDto.setCode(r2ServiceAgencyEntity.getCode());
        	r2ServiceAgencyDto.setName(r2ServiceAgencyEntity.getName());
        	return r2ServiceAgencyDto;
        }
        log.error("transform- r2ServiceAgencyEntity id is null, could not transform");
        return null;
	}
	
	public R2ServiceAgencyEntity transform(IDto idto) throws TransformerException{
		log.trace("transform- r2ServiceAgencyDto");
		ServiceAgencyProjectionDto r2ServiceAgencyDto = (ServiceAgencyProjectionDto) idto;
		
		if (null == r2ServiceAgencyDto) {
			throw new TransformerException(TransformerException.TRANSFORM_DTO_NULL);
		}
		
		R2ServiceAgencyEntity r2ServiceAgencyEntity = new R2ServiceAgencyEntity();
		r2ServiceAgencyEntity.setServiceAgencyId(r2ServiceAgencyDto.getId());
		r2ServiceAgencyEntity.setCode(r2ServiceAgencyDto.getCode());
		r2ServiceAgencyEntity.setName(r2ServiceAgencyDto.getName());
		return r2ServiceAgencyEntity;
	}
        
        

}
