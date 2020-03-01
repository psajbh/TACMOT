package mil.dtic.cbes.service.impl.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.repositories.user.UserEntityRepository;
import mil.dtic.cbes.service.user.UserEntityService;
import mil.dtic.cbes.utils.transform.Transformer;

public abstract class ManageUserServices implements UserEntityService{
    
    private static final int APP_MGR = 1;
    private static final int SITE_MGR = 4;
    private static final int LOCAL_SITE_MGR = 3;
    
    private static final int[] MANAGED_USER_ID_ROLES = {ManageUserServices.APP_MGR, ManageUserServices.SITE_MGR, ManageUserServices.LOCAL_SITE_MGR};
    private static final String[] SITE_MGR_FILTER = {UserCredentialDto.GROUP_R2_APP_ADMIN, UserCredentialDto.GROUP_R2_SITEADMIN};
    private static final String[] LOCAL_SITE_MGR_FILTER = {UserCredentialDto.GROUP_R2_APP_ADMIN, UserCredentialDto.GROUP_R2_SITEADMIN, UserCredentialDto.GROUP_R2_LOCALSITEADMIN};

    
    protected UserEntityRepository userEntityRepository;
    
    @Qualifier("UserTransformer")
    protected Transformer userTransformer;
    
    
    protected boolean validateAddUserDto(UserDto userDto) {
        UserEntity userEntity = null;
        
        if (null != userDto.getId()){
            return false;
        }
        
        if (null == userDto.getUserLdapId()) {
            return false;
        }
        
        //TODO: we need to integrate capturing ldap users before continuing the process.
        //we also need to mock this out for developement users.
        
        userEntity = userEntityRepository.findByUserLdapId(userDto.getUserLdapId());
        if(null != userEntity) {
            return false;
        }

        return true;
    }
    
    protected List<UserDto> getUsersForManager(UserCredentialDto userCredentialDto) {
        
        List<UserDto> userDtoAccumulator = new ArrayList<>();
        
        if (Integer.parseInt(userCredentialDto.getRoleId()) == ManageUserServices.APP_MGR) { 
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    userDtoAccumulator.add((UserDto) userTransformer.transform(userEntity));
                }
            }
        }
        
        if (Integer.parseInt(userCredentialDto.getRoleId()) == ManageUserServices.SITE_MGR) {  
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    if (!filterCredentialsForManagedUsers(userCredentialDto, userEntity) && userEntity.getServiceAgencies().size() > 0){
                        populateServiceAgencies(userDtoAccumulator, userEntity, userCredentialDto);
                    }
                }
            }
        }
        
        if (Integer.parseInt(userCredentialDto.getRoleId()) == ManageUserServices.LOCAL_SITE_MGR) { 
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    if (!filterCredentialsForManagedUsers(userCredentialDto, userEntity) && userEntity.getServiceAgencies().size() > 0){
                        populateServiceAgencies(userDtoAccumulator, userEntity, userCredentialDto);
                    }
                }
            }
        }
        
        return userDtoAccumulator;
    }
    
    protected boolean filterCredentialsForManagedUsers(UserCredentialDto userCredential, UserEntity userEntity) {
        String[] filter = getFilter(userCredential.getUserRole());
        if (null == filter) {
            return true;  //true = the userEntity is filtered out and will not be processed
        }
        
        List<String> filterList = Arrays.asList(filter);
        if(filterList.contains(userEntity.getRole())) {
            return true;
        }
        return false; //false = the user will be managed
    }
    
    protected boolean validateCredentialForManagedUsers(UserCredentialDto userCredentialDto) {
        if(contains(MANAGED_USER_ID_ROLES, Integer.parseInt(userCredentialDto.getRoleId()))){
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
                rFilter = ManageUserServices.SITE_MGR_FILTER;
                break;   
             
            case UserCredentialDto.GROUP_R2_LOCALSITEADMIN:
                rFilter = ManageUserServices.LOCAL_SITE_MGR_FILTER;
                break;
            
            default:
                break;
        }
        
        return rFilter;
    }

    public void setUserEntityRepository(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public void setUserTransformer(Transformer userTransformer) {
        this.userTransformer = userTransformer;
    }

    

}
