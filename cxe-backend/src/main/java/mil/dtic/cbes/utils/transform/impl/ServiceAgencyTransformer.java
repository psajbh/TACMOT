package mil.dtic.cbes.utils.transform.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.ServiceAgencyDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Component
public class ServiceAgencyTransformer implements Transformer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String SERVICE_AGENCY_DTO_NULL = "service agency dto is null";
    private final static String SERVICE_AGENCY_ENTITY_NULL  = "service agency entity is null";

 
    @Override
    public ServiceAgencyDto transform(IEntity entity) throws TransformerException{
        ServiceAgencyEntity serviceAgencyEntity = (ServiceAgencyEntity) entity; 
        
        if (null == serviceAgencyEntity) {
            throw new RuntimeException(ServiceAgencyTransformer.SERVICE_AGENCY_ENTITY_NULL);
        }
        
        if (null != serviceAgencyEntity.getId()) {
            log.trace("transform- start transforming serviceAgencyEntity: "+serviceAgencyEntity.getId()+" to a serviceAgencyDto object");
        }
        
        ServiceAgencyDto serviceAgencyDto = new ServiceAgencyDto();
        serviceAgencyDto.setId(serviceAgencyEntity.getId());
        serviceAgencyDto.setCode(serviceAgencyEntity.getCode());
        serviceAgencyDto.setName(serviceAgencyEntity.getName());
        return serviceAgencyDto;
    }
     
    @Override
    public ServiceAgencyEntity transform(IDto idto) {
        
        ServiceAgencyDto serviceAgencyDto = (ServiceAgencyDto) idto;
        
        if(null == serviceAgencyDto) {
            throw new RuntimeException(ServiceAgencyTransformer.SERVICE_AGENCY_DTO_NULL);
        }
        
        if (null != serviceAgencyDto.getId()) {
            log.trace("transform- start transforming serviceAgencyDdto: "+serviceAgencyDto.getId()+" to a serviceAgencyEntity object");
        }
        
        ServiceAgencyEntity serviceAgencyEntity = new ServiceAgencyEntity();
        serviceAgencyEntity.setId(serviceAgencyDto.getId());
        serviceAgencyEntity.setCode(serviceAgencyDto.getCode());
        serviceAgencyEntity.setName(serviceAgencyDto.getName());
        return serviceAgencyEntity;
    }
    
}
