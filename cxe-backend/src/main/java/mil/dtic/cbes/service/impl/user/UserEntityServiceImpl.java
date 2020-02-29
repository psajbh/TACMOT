package mil.dtic.cbes.service.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.user.UserCredentialDto;
import mil.dtic.cbes.model.dto.user.UserDto;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.repositories.user.UserEntityRepository;
import mil.dtic.cbes.utils.aspect.CredentialsAspect;
import mil.dtic.cbes.utils.exceptions.rest.user.ManageUserException;
import mil.dtic.cbes.utils.exceptions.security.DataAccessException;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

@Service
public class UserEntityServiceImpl extends ManageUserServices {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, Transformer userTransformer) {
        super.setUserEntityRepository(userEntityRepository);
        super.setUserTransformer(userTransformer);
    }
    
    @Override
    public List<UserDto> findManagedUsers(UserCredentialDto userCredentialDto) {
        if (validateCredentialForManagedUsers(userCredentialDto)) {
            return getUsersForManager(userCredentialDto);  
        }
        return null;        
    }
    
    
    @Override
    public UserEntity findUserEntityByLdapId(String ldapId) throws DataAccessException {
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
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        UserCredentialDto userCredentialDto = (UserCredentialDto) request.getAttribute(CredentialsAspect.CREDENTIAL_KEY_ATTRIBUTE);
        
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
        }
        catch(Exception e) {
            log.error("findUserDtoByUserLdapId- exception: "+e.getMessage(), e);
            throw new DataAccessException(DataAccessException.USER_DTO_CAPTURE_FAILURE);
        }
        
        try {
            userDto = (UserDto) userTransformer.transform(userEntity);
            return userDto;
        }
        catch(Exception e) {
            if (e instanceof TransformerException) {
                throw e;
            }
            log.error("findUserDtoByUserLdapId- " + e.getMessage());
            throw new TransformerException(TransformerException.TRANSFORM_ENTITY_TO_DTO_EXCEPTION);
         }
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
    
}
