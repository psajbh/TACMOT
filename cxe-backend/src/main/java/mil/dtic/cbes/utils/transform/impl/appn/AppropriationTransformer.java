package mil.dtic.cbes.utils.transform.impl.appn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.appropriation.AppropriationDto;
import mil.dtic.cbes.model.entities.AppropriationEntity;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class AppropriationTransformer implements Transformer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    final static String APPROPIATION_DTO_NULL = "appropriation dto is null";
    final static String APPROPRIATION_ENTITY_NULL  = "appropriation entity is null";

	
	@Override
	public AppropriationDto transform(IEntity entity) {
		AppropriationEntity appropriationEntity = (AppropriationEntity) entity;
		
        if (null == appropriationEntity) {
            throw new RuntimeException(AppropriationTransformer.APPROPRIATION_ENTITY_NULL);
        }
        
        if (null != appropriationEntity.getId()) {
            log.trace("transform- start transforming AppropriationEntity: "+appropriationEntity.getId()+" to a appropriationDto object");
            AppropriationDto appropriationDto = new AppropriationDto();
            appropriationDto.setId(appropriationEntity.getId());
            appropriationDto.setName(appropriationEntity.getName());
            appropriationDto.setCode(appropriationEntity.getCode());
            return appropriationDto;
        }
        
        return null;
	}
	
	@Override
	public AppropriationEntity transform (IDto idto) {
		AppropriationDto appropriationDto = (AppropriationDto) idto;
		
		if (null == appropriationDto) {
			throw new RuntimeException(AppropriationTransformer.APPROPIATION_DTO_NULL);
		}
		
		if (null != appropriationDto.getId()) {
			log.trace("transform- start transforming AppropriationDto: "+appropriationDto.getId()+" to a appropriationEntity object");
        	AppropriationEntity appropriationEntity = new AppropriationEntity();
        	appropriationEntity.setId(appropriationDto.getId());
        	appropriationEntity.setCode(appropriationDto.getCode());
        	appropriationEntity.setName(appropriationEntity.getName());
        	return appropriationEntity;
		}
	
		return null;
	}

}
