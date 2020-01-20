package mil.dtic.cbes.utils.transform.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.utils.exceptions.rest.ExceptionMessageUtil;
import mil.dtic.cbes.utils.exceptions.rest.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;


@Component
public class UserTransformer implements Transformer{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    ServiceAgencyTransformer serviceAgencyTransformer;
    
    public UserTransformer(ServiceAgencyTransformer serviceAgencyTransformer) {
        this.serviceAgencyTransformer = serviceAgencyTransformer;
    }
    
    @Override
    public UserDto transform(IEntity entity) throws TransformerException{
        UserEntity userEntity = (UserEntity) entity;
        
        if (null == userEntity) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);
        }
        
        if (null != userEntity.getId()) {
            log.trace("transform- start transforming userEntity: " + userEntity.getId() + " to a userDto object");
        }
        
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUserLdapId(userEntity.getUserLdapId());
        userDto.setFullName(userEntity.getFullName());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setMiddleInitial(userEntity.getMiddleInitial());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPhoneNum(userEntity.getPhoneNum());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRole(userEntity.getRole());
        userDto.setStatusFlag(userEntity.getStatusFlag());
        userDto.setCreatePeAllowed(userEntity.isCreatePeAllowed());
        userDto.setCreateLiAllowed(userEntity.isCreateLiAllowed());
        
        Set<ServiceAgencyDto> serviceAgencies = new HashSet<>();
        for (ServiceAgencyEntity serviceAgencyEntity : userEntity.getServiceAgencies()) {
            ServiceAgencyDto serviceAgencyDto = serviceAgencyTransformer.transform(serviceAgencyEntity);
            serviceAgencies.add(serviceAgencyDto);
        }
        
        userDto.setServiceAgencies(serviceAgencies);
        return userDto;
    }
    
    @Override
    public UserEntity transform (IDto dDto) throws TransformerException{
        UserDto userDto = (UserDto) dDto;
        
        if (null == userDto) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);   
        }
        
        if (null != userDto.getId()) {
            log.trace("transform- start transforming userDto: " + userDto.getId() + " to a user entity object");
        }
        
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUserLdapId(userDto.getUserLdapId());
        userEntity.setFullName(userDto.getFullName());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setMiddleInitial(userDto.getMiddleInitial());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhoneNum(userDto.getPhoneNum());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setRole(userDto.getRole());
        userEntity.setStatusFlag(userDto.getStatusFlag());
        userEntity.setCreatePeAllowed(userDto.isCreatePeAllowed());
        userEntity.setCreateLiAllowed(userDto.isCreateLiAllowed());
        
        Set<ServiceAgencyEntity> serviceAgencies = new HashSet<>();
        for (ServiceAgencyDto serviceAgencyDto : userDto.getServiceAgencies()) {
            ServiceAgencyEntity serviceAgencyEntity = serviceAgencyTransformer.transform(serviceAgencyDto);
            serviceAgencies.add(serviceAgencyEntity);
        }
        
        userEntity.setServiceAgencies(serviceAgencies);
        return userEntity;
    }
    
    
}
