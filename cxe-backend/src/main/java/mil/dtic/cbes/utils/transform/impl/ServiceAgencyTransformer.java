package mil.dtic.cbes.utils.transform.impl;

import org.springframework.stereotype.Component;

//import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.ServiceAgencyDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.utils.exceptions.rest.ExceptionMessageUtil;
import mil.dtic.cbes.utils.exceptions.rest.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

//@Slf4j
@Component
public class ServiceAgencyTransformer implements Transformer{
 
    @Override
    public ServiceAgencyDto transform(IEntity entity) throws TransformerException{
        ServiceAgencyEntity serviceAgencyEntity = (ServiceAgencyEntity) entity;
        //ServiceAgencyEntity serviceAgencyEntity = buildOutEntity(entity);
        if (null == serviceAgencyEntity) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);
        }
        ServiceAgencyDto serviceAgencyDto = new ServiceAgencyDto();
        serviceAgencyDto.setId(serviceAgencyEntity.getId());
        serviceAgencyDto.setCode(serviceAgencyEntity.getCode());
        serviceAgencyDto.setName(serviceAgencyEntity.getName());
        return serviceAgencyDto;
    }
     
    //TODO: build out transfrom serviceagency DTO->Entity
    @Override
    public ServiceAgencyEntity transform(IDto idto) {
        ServiceAgencyDto serviceAgencyDto = (ServiceAgencyDto) idto;
        //ServiceAgencyDto serviceAgencyDto = buildOutDto(idto);
        if(null == serviceAgencyDto) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);
        }
        ServiceAgencyEntity serviceAgencyEntity = new ServiceAgencyEntity();
        serviceAgencyEntity.setId(serviceAgencyDto.getId());
        serviceAgencyEntity.setCode(serviceAgencyDto.getCode());
        serviceAgencyEntity.setName(serviceAgencyDto.getName());
        return serviceAgencyEntity;
    }
    
    @Deprecated
    private ServiceAgencyEntity buildOutEntity(IEntity entity) {
        ServiceAgencyEntity serviceAgencyEntity = (ServiceAgencyEntity) entity;
        
        try {
            Object o = serviceAgencyEntity.clone();
            return (ServiceAgencyEntity)o;
        }
        catch(CloneNotSupportedException cnse) {
            //log.error("buildOutEntity: " + cnse.getMessage(), cnse);
        }
        catch(Exception e) {
            //log.error("buildOutEntity: " + e.getMessage(), e);
        }
        return null;
        
    }

    @Deprecated
    private ServiceAgencyDto buildOutDto(IDto dto) {
        ServiceAgencyDto serviceAgencyDto = (ServiceAgencyDto) dto;
        
        try {
            Object o = serviceAgencyDto.clone();
            return (ServiceAgencyDto) o;
            
        }
        catch(CloneNotSupportedException cnse) {
            //log.error("buildOutDto: " + cnse.getMessage(), cnse)
        }
        catch(Exception e) {
          //log.error("buildOutDto: " + cnse.getMessage(), cnse)
        }
        
        return null;
    }
    
    
    

}
