package mil.dtic.cbes.utils.transform.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import mil.dtic.cbes.model.ApplicationSettings;
import mil.dtic.cbes.model.dto.ApplicationSettingsDTO;
import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

public class ApplicationSettingsTransformer implements Transformer{

	private MessageSource messageSource;
	
	@Autowired
	public ApplicationSettingsTransformer(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public IDto transform(IEntity entity) {
		ApplicationSettings applicationSettings = (ApplicationSettings) entity;
		
		if(null == applicationSettings) {
			throw new TransformerException(messageSource.getMessage("transformer.processing.transformation.entity.null",
					null, StringUtils.EMPTY, Locale.US));
		}
		
		ApplicationSettingsDTO applicationSettingsDTO = new ApplicationSettingsDTO();
		
		applicationSettingsDTO.setSettingName(applicationSettings.getSettingName());
		applicationSettingsDTO.setSetting_value(applicationSettings.getSettingValue());
		applicationSettingsDTO.setSetting_description(applicationSettings.getSettingDescription());
		
		return applicationSettingsDTO;
	}

	@Override
	public IEntity transform(IDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
