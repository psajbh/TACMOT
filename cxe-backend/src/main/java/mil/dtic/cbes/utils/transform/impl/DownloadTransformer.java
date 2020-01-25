package mil.dtic.cbes.utils.transform.impl;

import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.model.dto.DownloadDto;
import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.utils.exceptions.rest.ExceptionMessageUtil;
import mil.dtic.cbes.utils.exceptions.rest.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class DownloadTransformer implements Transformer {
	
    public DownloadTransformer() {}
    
    @Override
    public DownloadDto transform(IEntity entity) throws TransformerException{
        UploadedBudgetFile budgetFile = (UploadedBudgetFile) entity;
        if (null == budgetFile) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);
        }
        DownloadDto downloadDto = new DownloadDto();
        downloadDto.setId(budgetFile.getId());
        downloadDto.setName(budgetFile.getName());
        downloadDto.setDescription(budgetFile.getDescription());
        downloadDto.setDateCreated(budgetFile.getDateCreated());
        downloadDto.setCreatedBy(budgetFile.getCreatedBy());
        downloadDto.setSize(budgetFile.getSize());
        return downloadDto;
    }
    
    @Override
    public UploadedBudgetFile transform (IDto dDto) throws TransformerException{
        DownloadDto downloadDto = (DownloadDto) dDto;
    	if (null == downloadDto) {
    		throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);
    	}
    	UploadedBudgetFile budgetFile = new UploadedBudgetFile();
    	
    	budgetFile.setId(downloadDto.getId());
    	budgetFile.setName(downloadDto.getName());
    	budgetFile.setDescription(downloadDto.getDescription());
    	budgetFile.setDateCreated(downloadDto.getDateCreated());
    	budgetFile.setCreatedBy(downloadDto.getCreatedBy());
    	budgetFile.setSize(downloadDto.getSize());
    	return budgetFile;
    }
    
}
