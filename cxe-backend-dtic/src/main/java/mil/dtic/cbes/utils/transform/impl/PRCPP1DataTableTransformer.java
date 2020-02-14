package mil.dtic.cbes.utils.transform.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.PRCPP1DataTableDTO;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.P1DataEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component("PRCPP1DataTableTransformer")
public class PRCPP1DataTableTransformer implements Transformer {

	private MessageSource messageSource;

	@Autowired
	public PRCPP1DataTableTransformer(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public IDto transform(IEntity entity) throws TransformerException {
		P1DataEntity p1DataEntity = (P1DataEntity) entity;

		if (null == p1DataEntity) {
			throw new TransformerException(messageSource.getMessage("transformer.processing.transformation.entity.null",
					null, StringUtils.EMPTY, Locale.US));
		}

		PRCPP1DataTableDTO prcpP1DataTableDTO = new PRCPP1DataTableDTO();

		prcpP1DataTableDTO.setOrganization(p1DataEntity.getOrganization());
		prcpP1DataTableDTO.setAccount(p1DataEntity.getAccount());
		prcpP1DataTableDTO.setBudgetActivity(p1DataEntity.getBudgetActivity());
		prcpP1DataTableDTO.setBudgetSubActivity(p1DataEntity.getBudgetSubActivity());
		prcpP1DataTableDTO.setLineNumber(p1DataEntity.getLineNumber());
		prcpP1DataTableDTO.setLiNumber(p1DataEntity.getLiNumber());
		prcpP1DataTableDTO.setCostType(p1DataEntity.getCostType());
		prcpP1DataTableDTO.setPyAmount(p1DataEntity.getPyAmount());
		prcpP1DataTableDTO.setCyAmount(p1DataEntity.getPyAmount());
		prcpP1DataTableDTO.setBy1BaseAmount(p1DataEntity.getBy1BaseAmount());
		prcpP1DataTableDTO.setBy1OCOAmount(p1DataEntity.getBy1OCOAmount());
		prcpP1DataTableDTO.setBy1Amount(p1DataEntity.getBy1Amount());
		prcpP1DataTableDTO.setBy2Amount(p1DataEntity.getBy2Amount());
		prcpP1DataTableDTO.setBy3Amount(p1DataEntity.getBy3Amount());
		prcpP1DataTableDTO.setBy4Amount(p1DataEntity.getBy4Amount());
		prcpP1DataTableDTO.setBy5Amount(p1DataEntity.getBy5Amount());

		return prcpP1DataTableDTO;
	}

	@Override
	public IEntity transform(IDto dto) {
		return null;
	}
}
