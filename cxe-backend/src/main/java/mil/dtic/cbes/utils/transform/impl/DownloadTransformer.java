package mil.dtic.cbes.utils.transform.impl;

import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.model.dto.DownloadDto;
import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class DownloadTransformer implements Transformer {
    private final static String DOWNLOAD_DTO_NULL = "budget file dto is null";
    private final static String DOWNLOAD_ENTITY_NULL = "budget file entity is null";

	
    public DownloadTransformer() {}
    
    @Override
    public DownloadDto transform(IEntity entity) {
        UploadedBudgetFile budgetFile = (UploadedBudgetFile) entity;
        if (null == budgetFile) {
            throw new RuntimeException(DownloadTransformer.DOWNLOAD_ENTITY_NULL);
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
    public UploadedBudgetFile transform (IDto dDto) {
        DownloadDto downloadDto = (DownloadDto) dDto;
    	if (null == downloadDto) {
    		throw new TransformerException(DownloadTransformer.DOWNLOAD_DTO_NULL);
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
