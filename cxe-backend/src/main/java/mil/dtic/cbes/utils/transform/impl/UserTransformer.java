package mil.dtic.cbes.utils.transform.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@Component
public class UserTransformer implements Transformer{
    
    ServiceAgencyTransformer serviceAgencyTransformer;
    
    public UserTransformer(ServiceAgencyTransformer serviceAgencyTransformer) {
        this.serviceAgencyTransformer = serviceAgencyTransformer;
    }
    
    @Override
    public UserDto transform(IEntity entity) throws TransformerException{
        UserEntity userEntity = (UserEntity) entity; 
        //UserEntity userEntity = buildOutEntity(entity);
        if (null == userEntity) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);
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
    public UserEntity transform (IDto iDto) throws TransformerException{
        UserDto userDto = (UserDto) iDto; 
        //UserDto userDto = buildOutDto(dDto);
        if (null == userDto) {
            throw new TransformerException(ExceptionMessageUtil.TRANSFORM_ENTITY_FAILURE_MSG);   
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
    
//    @Deprecated
//    private UserEntity buildOutEntity(IEntity entity) {
//        //log.trace("buildOutEntity: - start generate an object from the interface");
//        UserEntity userEntity = (UserEntity) entity;
//        
//        try {
//            Object o = userEntity.clone();
//            return (UserEntity)o;
//        }
//        catch(CloneNotSupportedException cnse) {
//            //log.error("buildOutEntity: " + cnse.getMessage(), cnse);
//        }
//        catch(Exception e) {
//            //log.error("buildOutEntity: " + e.getMessage(), e);
//        }
//        return null;
//    }

//    @Deprecated
//    private UserDto buildOutDto(IDto dto) {
//        UserDto userDto = (UserDto) dto;
//        
//        try {
//            Object o = userDto.clone();
//            return (UserDto) o;
//        }
//        catch(CloneNotSupportedException cnse) {
//            System.out.println("cnse: " + cnse.getMessage());
//        }
//        
//        return null;
//    }


}
