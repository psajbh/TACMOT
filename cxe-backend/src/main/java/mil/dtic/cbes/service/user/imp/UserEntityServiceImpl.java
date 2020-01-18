package mil.dtic.cbes.service.user.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
//import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.repositories.UserEntityRepository;
import mil.dtic.cbes.service.user.api.UserEntityService;
import mil.dtic.cbes.utils.exceptions.rest.DataAccessException;
import mil.dtic.cbes.utils.exceptions.rest.InvalidLdapException;
import mil.dtic.cbes.utils.exceptions.rest.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;

//TODO: refactor - move private methods to an extended class.
@Service
public class UserEntityServiceImpl implements UserEntityService{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final int APP_MGR = 1;
    private static final int SITE_MGR = 4;
    private static final int LOCAL_SITE_MGR = 3;
    private static final int[] MANAGED_USER_ID_ROLES = {UserEntityServiceImpl.APP_MGR, UserEntityServiceImpl.SITE_MGR, UserEntityServiceImpl.LOCAL_SITE_MGR};
    private static final String[] SITE_MGR_FILTER = {UserCredentialDto.GROUP_R2_APP_ADMIN, UserCredentialDto.GROUP_R2_SITEADMIN};
    private static final String[] LOCAL_SITE_MGR_FILTER = {UserCredentialDto.GROUP_R2_APP_ADMIN, UserCredentialDto.GROUP_R2_SITEADMIN, UserCredentialDto.GROUP_R2_LOCALSITEADMIN};
            
    private UserEntityRepository userEntityRepository;
    
    @Qualifier("UserTransformer")
    private Transformer userTransformer;
    
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, Transformer userTransformer) {
        this.userEntityRepository = userEntityRepository;
        this.userTransformer = userTransformer;
    }
    
    @Override
    public List<UserDto> findManagedUsers(UserCredentialDto userCredential) {
        if (validateCredentialForManagedUsers(userCredential)) {
            return getUsersForManager(userCredential);  
        }
        return null;
    }
    
    @Override
    public UserEntity findByUserLdapId(String ldapId) throws DataAccessException {
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
    public UserDto findUserDtoByUserLdapId(String ldapId)  
            throws InvalidLdapException,TransformerException, DataAccessException {
        log.debug("findUserDtoByuserLdapId- start ldapId: " + ldapId);
        UserDto userDto = null;
        UserEntity userEntity = null;
        
        try {
            userEntity = userEntityRepository.findByUserLdapId(ldapId);
        }
        catch(Exception e) {
            if (e instanceof DataAccessException) {
                throw e;
            }
            else {
                throw new DataAccessException(e.getMessage());
            }
        }
        
        if (null == userEntity) {
           throw new InvalidLdapException("ldapId: " + ldapId + " is not valid"); 
        }
        
        try {
            userDto = (UserDto) userTransformer.transform(userEntity);
            return userDto;
        }
        catch(Exception e) {
            throw new TransformerException(e.getMessage());
         }
    }
    
    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) throws RuntimeException{
        log.info("updateUser- start userDto: " + userDto);
        if (null == userDto) {
            throw new NullPointerException("UserDto object is null");
        }
        //technically, only agencies and ability to create PE/LI are editable. this restriction is not yet implemented.
        if (userEntityRepository.existsById(userDto.getId())) {
            UserEntity userEntity = (UserEntity) userTransformer.transform(userDto);
            userEntity = userEntityRepository.save(userEntity);
            UserDto updatedUserDto = (UserDto) userTransformer.transform(userEntity);
            return updatedUserDto;
        }
            
        userDto.setMessage("The user does not exist in database, try creating a new user");
        return userDto;
    }
    
    @Transactional
    @Override
    public boolean deleteUser(UserDto userDto) throws RuntimeException{
        log.info("deleteUser- start userDto: " + userDto);
        boolean rValue = false;
        if (null == userDto.getId()) {
            return rValue;
        }

        try {
            userEntityRepository.deleteById(userDto.getId());
            rValue = true;
        }
        catch(RuntimeException e){
            log.error("deleteUser failure to delete: " + e.getMessage());
            throw e;
        }
        
        return rValue;
    }
    
    @Transactional
    @Override
    public UserDto addUser(UserDto userDto) {
        if(!validateAddUserDto(userDto)) {
            return null;
        }
        
        UserEntity savedUserEntity = userEntityRepository.save((UserEntity) userTransformer.transform(userDto));
        return (UserDto) userTransformer.transform(savedUserEntity);
    }
    
    private boolean validateAddUserDto(UserDto userDto) {
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
    
    
    @Override
    public UserEntity findUserById(Integer id) {
        // TODO: implement this wrt EditUser
        return null;
    }
    
    private List<UserDto> getUsersForManager(UserCredentialDto userCredential) {
        List<UserDto> userDtoAccumulator = new ArrayList<>();
        
        if (Integer.parseInt(userCredential.getRoleId()) == UserEntityServiceImpl.APP_MGR) { 
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    userDtoAccumulator.add((UserDto) userTransformer.transform(userEntity));
                }
            }
        }
        
        if (Integer.parseInt(userCredential.getRoleId()) == UserEntityServiceImpl.SITE_MGR) {  
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    if (!filterCredentialsForManagedUsers(userCredential, userEntity) && userEntity.getServiceAgencies().size() > 0){
                        populateServiceAgencies(userDtoAccumulator, userEntity, userCredential);
                    }
                }
            }
        }
        
        if (Integer.parseInt(userCredential.getRoleId()) == UserEntityServiceImpl.LOCAL_SITE_MGR) { 
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    if (!filterCredentialsForManagedUsers(userCredential, userEntity) && userEntity.getServiceAgencies().size() > 0){
                        populateServiceAgencies(userDtoAccumulator, userEntity, userCredential);
                    }
                }
            }
        }
        
        return userDtoAccumulator;
    }
    
    private void populateServiceAgencies(List<UserDto> userDtoAccumulator, UserEntity userEntity, UserCredentialDto userCredential) {
        for (ServiceAgencyEntity serviceAgencyEntity : userEntity.getServiceAgencies()) {
            String code = serviceAgencyEntity.getCode();
            if (userCredential.getStrAgencies().contains(code)) {
                userDtoAccumulator.add((UserDto) userTransformer.transform(userEntity));
                break;
            }
        }
    }
    
    private String[] getFilter(String userRole) {
        String[] rFilter = null;
        
        switch (userRole) {
            case UserCredentialDto.GROUP_R2_SITEADMIN:
                rFilter = UserEntityServiceImpl.SITE_MGR_FILTER;
                break;   
             
            case UserCredentialDto.GROUP_R2_LOCALSITEADMIN:
                rFilter = UserEntityServiceImpl.LOCAL_SITE_MGR_FILTER;
                break;
            
            default:
                break;
        }
        
        return rFilter;
    }
    
    // true means the userEntity is filtered out out and will not be processed, false means the user will be managed. 
    private boolean filterCredentialsForManagedUsers(UserCredentialDto userCredential, UserEntity userEntity) {
        String[] filter = getFilter(userCredential.getUserRole());
        if (null == filter) {
            return true;
        }
        
        List<String> filterList = Arrays.asList(filter);
        if(filterList.contains(userEntity.getRole())) {
            return true;
        }
        return false;
    }
    
    // filter out users who do not have rights to view ManageUsers (Users, Analysts)
    private boolean validateCredentialForManagedUsers(UserCredentialDto userCredential) {
        if(contains(MANAGED_USER_ID_ROLES, Integer.parseInt(userCredential.getRoleId()))){
            return true;
        }
        return false;
    }
    
    private static boolean contains(final int[] array, int roleId) {
        boolean result = false;
        
        for (int i : array) {
            if (i == roleId) {
                result = true;
                break;
            }
        }
        
        return result;
    }
    

}
