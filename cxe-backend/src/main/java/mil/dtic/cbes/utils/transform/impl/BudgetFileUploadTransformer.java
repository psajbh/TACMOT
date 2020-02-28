package mil.dtic.cbes.utils.transform.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.model.dto.BudgetFileUploadDTO;
import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class BudgetFileUploadTransformer implements Transformer {
	private MessageSource messageSource;
	
	@Autowired
    public BudgetFileUploadTransformer(MessageSource messageSource) {
    	this.messageSource = messageSource;
    }
    
    @Override
    public BudgetFileUploadDTO transform(IEntity entity) {
        UploadedBudgetFile budgetFile = (UploadedBudgetFile) entity;
        
        if (null == budgetFile) {
        	throw new TransformerException(messageSource.getMessage("transformer.processing.transformation.entity.null",
					null, StringUtils.EMPTY, Locale.US));
        }
        
        BudgetFileUploadDTO budgetFileUploadDTO = new BudgetFileUploadDTO();
        budgetFileUploadDTO.setId(budgetFile.getId());
        budgetFileUploadDTO.setName(budgetFile.getName());
        budgetFileUploadDTO.setDescription(budgetFile.getDescription());
        budgetFileUploadDTO.setDateCreated(budgetFile.getDateCreated());
        budgetFileUploadDTO.setCreatedBy(budgetFile.getCreatedBy());
        budgetFileUploadDTO.setSize(budgetFile.getSize());
        
        return budgetFileUploadDTO;
    }
    
    @Override
    public UploadedBudgetFile transform (IDto dDto) {
        BudgetFileUploadDTO budgetFileUploadDTO = (BudgetFileUploadDTO) dDto;
    	
        if (null == budgetFileUploadDTO) {
        	throw new TransformerException(messageSource.getMessage("transformer.processing.transformation.dto.failure",
					null, StringUtils.EMPTY, Locale.US));
    	}
    	
        UploadedBudgetFile budgetFile = new UploadedBudgetFile();
    	
    	budgetFile.setId(budgetFileUploadDTO.getId());
    	budgetFile.setName(budgetFileUploadDTO.getName());
    	budgetFile.setDescription(budgetFileUploadDTO.getDescription());
    	budgetFile.setDateCreated(budgetFileUploadDTO.getDateCreated());
    	budgetFile.setCreatedBy(budgetFileUploadDTO.getCreatedBy());
    	budgetFile.setSize(budgetFileUploadDTO.getSize());
    	
    	return budgetFile;
    }
}
