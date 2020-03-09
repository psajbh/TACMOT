package mil.dtic.cbes.service.impl.security.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.repositories.security.user.UserEntityRepository;
import mil.dtic.cbes.service.security.user.UserEntityService;
import mil.dtic.cbes.utils.exceptions.security.DataAccessException;
import mil.dtic.cbes.utils.exceptions.security.user.ManageUserException;
import mil.dtic.cbes.utils.exceptions.transform.TransformerException;
import mil.dtic.cbes.utils.security.UserInfo;
import mil.dtic.cbes.utils.transform.Transformer;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    
    @Qualifier("UserTransformer")
    private Transformer userTransformer;
    
    private UserEntityRepository userEntityRepository;
    private UserInfo userInfo;
        
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, Transformer userTransformer, UserInfo userInfo) {
        this.userEntityRepository = userEntityRepository;
        this.userTransformer = userTransformer;
        this.userInfo = userInfo;
    }
    
    @Override
    public UserEntity findUserEntityByLdapId() throws DataAccessException {
  	  String ldapId = userInfo.getUserLdapId();
        log.debug("findByUserLdapId- start ldapId: " + ldapId);
        UserEntity userEntity;
        
        try {
            userEntity = userEntityRepository.findByUserLdapId(ldapId);
            if (null != userEntity) {
              return userEntity;  
            }
        }
        catch(Exception e) {
            log.error("failed to create userEntity msg: " + e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }
   
    @Override
    public UserDto findUserDto() {
    	UserCredentialDto userCredentialDto = userInfo.getCredential();
        
        if (null != userCredentialDto) { 
        	return findUserDtoByUserLdapId(userCredentialDto.getLdapId());
        }
        else {
        	return null;
        }
    }
    
    @Override
    public UserDto findUserDtoByUserLdapId(String ldapId) {
        log.debug("findUserDtoByuserLdapId- start ldapId: " + ldapId);
        UserDto userDto = null;
        UserEntity userEntity = null;
        
        try {
            userEntity = userEntityRepository.findByUserLdapId(ldapId);
            userDto = (UserDto) userTransformer.transform(userEntity);
            return userDto;
        }
        catch(Exception e) {
        	log.error("findUserDtoByUserLdapId- " + e.getMessage(),e);
            if (e instanceof TransformerException) {
                throw e;
            }
            throw new TransformerException(TransformerException.TRANSFORM_ENTITY_TO_DTO_EXCEPTION);
         }
    }
    
    @Override
    public UserDto findUserDtoByUserLdapId() {
    	String ldapId = userInfo.getUserLdapId();
    	return findUserDtoByUserLdapId(ldapId);
    }
    
    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto){
    	
        if (null == userDto) {
        	log.error("updateUser- userDto is null");
            throw new ManageUserException(ManageUserException.UPDATE_USER_DTO_NULL);
        }
        
        log.debug("updateUser- userDto: " + userDto);
        
        try {
            if (userEntityRepository.existsById(userDto.getId())) {
                UserEntity userEntity = userEntityRepository.findByUserLdapId(userDto.getUserLdapId());
                if (null == userEntity) {
                    log.error("updateuser- attempt made to modify a user with invalid ldapId of "+userDto.getUserLdapId()+" by: "+
                            SecurityContextHolder.getContext().getAuthentication().getName());
                    throw new ManageUserException(ManageUserException.UPDATE_USER_INVALID_LDAPID);
                }
                userEntity = (UserEntity) userTransformer.transform(userDto);
                userEntity = userEntityRepository.save(userEntity);
                UserDto updatedUserDto = (UserDto) userTransformer.transform(userEntity);
                return updatedUserDto;
            }
        }
        catch (Exception e) {
            log.error("udateUser- " + e.getMessage());
            throw new ManageUserException(e.getMessage());
        }
        
        throw new ManageUserException(ManageUserException.UPDATE_USER_FAILED_TO_PROCESS);
    }
    
    //WIP: adding a user requires validating in NetLdap and a mocked replication for dev users.
    @Transactional
    @Override
    public UserDto addUser(UserDto userDto) {
        
        if(!validateAddUserDto(userDto)) {
            return null;
        }
        
        UserEntity savedUserEntity = userEntityRepository.save((UserEntity) userTransformer.transform(userDto));
        return (UserDto) userTransformer.transform(savedUserEntity);
    }
    
    protected boolean validateAddUserDto(UserDto userDto) {
        UserEntity userEntity = null;
        
        if (null != userDto.getId()){
            return false;
        }
        
        if (null == userDto.getUserLdapId()) {
            return false;
        }
        
        userEntity = userEntityRepository.findByUserLdapId(userDto.getUserLdapId());
        if(null != userEntity) {
            return false;
        }

        return true;
    }
}
