package mil.dtic.cbes.utils.transform.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.user.UserDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.model.enums.BooleanFlag;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;
import mil.dtic.cbes.utils.transform.impl.serviceagency.ServiceAgencyTransformer;


@Component
public class UserTransformer implements Transformer{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    ServiceAgencyTransformer serviceAgencyTransformer;
    
    public UserTransformer(ServiceAgencyTransformer serviceAgencyTransformer) {
        this.serviceAgencyTransformer = serviceAgencyTransformer;
    }
    
    @Override
    public UserDto transform(IEntity entity) {
        UserEntity userEntity = (UserEntity) entity;
        
        if (null == userEntity) {
            log.error("transform- entity->dto: entity is null");
            throw new TransformerException(TransformerException.TRANSFORM_ENTITY_NULL);
        }
        
        if (null != userEntity.getId()) {
            log.trace("transform- start transforming userEntity: " + userEntity.getId() + " to a userDto object");
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
            userDto.setCreatePeAllowed(userEntity.isCreatePeAllowed().isYes() ? true : false);
            userDto.setCreateLiAllowed(userEntity.isCreateLiAllowed().isYes() ? true : false);
        
            List<ServiceAgencyDto> serviceAgencies = new ArrayList<>();
            for (ServiceAgencyEntity serviceAgencyEntity : userEntity.getServiceAgencies()) {
            	ServiceAgencyDto serviceAgencyDto = serviceAgencyTransformer.transform(serviceAgencyEntity);
            	serviceAgencies.add(serviceAgencyDto);
            }
        
            userDto.setServiceAgencies(serviceAgencies);
            return userDto;
        }
        return null;
        
        
    }
    
    @Override
    public UserEntity transform (IDto dDto){
        UserDto userDto = (UserDto) dDto;
        
        if (null == userDto) {
            log.error("transform- dto->entity: userDto is null");
            throw new TransformerException(TransformerException.TRANSFORM_DTO_NULL);   
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
        userEntity.setCreatePeAllowed(userDto.isCreatePeAllowed() ? BooleanFlag.Y : BooleanFlag.N);
        userEntity.setCreateLiAllowed(userDto.isCreateLiAllowed() ? BooleanFlag.Y : BooleanFlag.N);
        
        List<ServiceAgencyEntity> serviceAgencies = new ArrayList<>();
        for (ServiceAgencyDto serviceAgencyDto : userDto.getServiceAgencies()) {
            ServiceAgencyEntity serviceAgencyEntity = serviceAgencyTransformer.transform(serviceAgencyDto);
            serviceAgencies.add(serviceAgencyEntity);
        }
        
        userEntity.setServiceAgencies(serviceAgencies);
        return userEntity;
    }
}
