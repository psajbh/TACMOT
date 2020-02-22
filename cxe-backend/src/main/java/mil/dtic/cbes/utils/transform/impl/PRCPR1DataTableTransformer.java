package mil.dtic.cbes.utils.transform.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.PRCPR1DataTableDTO;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.R1DataEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component("PRCPR1DataTableTransformer")
public class PRCPR1DataTableTransformer implements Transformer{

	private MessageSource messageSource;
	
	@Autowired
	public PRCPR1DataTableTransformer(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public IDto transform(IEntity entity) throws TransformerException {
		R1DataEntity r1DataEntity = (R1DataEntity)entity;
		
		if(null == r1DataEntity) {
			throw new TransformerException(messageSource.getMessage("transformer.processing.transformation.entity.null",
					null, StringUtils.EMPTY, Locale.US)); 
		}
		
		PRCPR1DataTableDTO prcpR1DataTableDTO = new PRCPR1DataTableDTO();
		
		prcpR1DataTableDTO.setOrganization(r1DataEntity.getOrganization());
		prcpR1DataTableDTO.setAccount(r1DataEntity.getAccount());
		prcpR1DataTableDTO.setBudgetActivity(r1DataEntity.getBudgetActivity());
		prcpR1DataTableDTO.setLineNumber(r1DataEntity.getLineNumber());
		prcpR1DataTableDTO.setPeNumber(r1DataEntity.getPeNumber());
		prcpR1DataTableDTO.setProjectNumber(r1DataEntity.getProjectNumber());
		prcpR1DataTableDTO.setPyAmount(r1DataEntity.getPyAmount());
		prcpR1DataTableDTO.setCyAmount(r1DataEntity.getPyAmount());
		prcpR1DataTableDTO.setBy1BaseAmount(r1DataEntity.getBy1BaseAmount());
		prcpR1DataTableDTO.setBy1OCOAmount(r1DataEntity.getBy1OCOAmount());
		prcpR1DataTableDTO.setBy1Amount(r1DataEntity.getBy1Amount());
		prcpR1DataTableDTO.setBy2Amount(r1DataEntity.getBy2Amount());
		prcpR1DataTableDTO.setBy3Amount(r1DataEntity.getBy3Amount());
		prcpR1DataTableDTO.setBy4Amount(r1DataEntity.getBy4Amount());
		prcpR1DataTableDTO.setBy5Amount(r1DataEntity.getBy5Amount());
		
		return prcpR1DataTableDTO;
	}

	@Override
	public IEntity transform(IDto dto) {
		return null;
	}
}
