package mil.dtic.cbes.utils.transform.impl.serviceagency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.core.PeSuffixDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.views.r2.PeSuffixEntity;
import mil.dtic.cbes.utils.exceptions.transform.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class PeSuffixProjectionTransformer implements Transformer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public PeSuffixProjectionTransformer() {}
	
	public PeSuffixDto transform(IEntity entity) throws TransformerException {
		log.trace("transform- peSuffixEntity");
		PeSuffixEntity peSuffixEntity = (PeSuffixEntity)entity;
		
		if (null == peSuffixEntity) {
			throw new TransformerException(TransformerException.TRANSFORM_ENTITY_NULL);
		}
		PeSuffixDto peSuffixDto = new PeSuffixDto();
		peSuffixDto.setPeSuffix(peSuffixEntity.getPeSuffix());
		peSuffixDto.setServiceAgencyId(peSuffixEntity.getServiceAgencyId());
		return peSuffixDto;
	}
	
	public PeSuffixEntity transform(IDto dto) throws TransformerException {
		log.trace("transform- PeSuffixDto");
		PeSuffixDto peSuffixDto = (PeSuffixDto)dto;
		
		if (null == peSuffixDto) {
			throw new TransformerException(TransformerException.TRANSFORM_DTO_NULL);
		}
		
		PeSuffixEntity peSuffixEntity = new PeSuffixEntity();
		peSuffixEntity.setPeSuffix(peSuffixDto.getPeSuffix());
		peSuffixEntity.setServiceAgencyId(peSuffixDto.getServiceAgencyId());
		return peSuffixEntity;
		
		
	}

}
