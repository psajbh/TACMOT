package mil.dtic.cbes.service.impl.security.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.repositories.security.user.UserEntityRepository;
import mil.dtic.cbes.service.security.manageduser.ManageUsersService;
import mil.dtic.cbes.utils.security.UserInfo;
import mil.dtic.cbes.utils.transform.Transformer;

@Service
public class ManageUsersServiceImpl implements ManageUsersService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    private static final int APP_MGR = 1;
    private static final int SITE_MGR = 4;
    private static final int LOCAL_SITE_MGR = 3;
    
    private static final int[] MANAGED_USER_ID_ROLES = {ManageUsersServiceImpl.APP_MGR, ManageUsersServiceImpl.SITE_MGR, ManageUsersServiceImpl.LOCAL_SITE_MGR};
    private static final String[] SITE_MGR_FILTER = {UserCredentialDto.GROUP_R2_APP_ADMIN, UserCredentialDto.GROUP_R2_SITEADMIN};
    private static final String[] LOCAL_SITE_MGR_FILTER = {UserCredentialDto.GROUP_R2_APP_ADMIN, UserCredentialDto.GROUP_R2_SITEADMIN, UserCredentialDto.GROUP_R2_LOCALSITEADMIN};

    @Qualifier("UserTransformer")
    private Transformer userTransformer;

	private UserInfo userInfo;
	private UserEntityRepository userEntityRepository;
	
	public ManageUsersServiceImpl(UserInfo userInfo, UserEntityRepository userEntityRepository, Transformer userTransformer) {
		this.userInfo = userInfo;
		this.userEntityRepository = userEntityRepository;
		this.userTransformer = userTransformer;
	}
	
	@Override
	public List<UserDto> findManagedUsers(){
		log.trace("findManagedUsers-");
    	UserCredentialDto userCredentialDto = userInfo.getCredential();
    	if (validateCredentialForManagedUsers(userCredentialDto)) {
    		return getUsersForManager(userCredentialDto);  
    	}

		return null;
	}
	
    private boolean validateCredentialForManagedUsers(UserCredentialDto userCredentialDto) {
        if(contains(ManageUsersServiceImpl.MANAGED_USER_ID_ROLES, Integer.parseInt(userCredentialDto.getRoleId()))){
            return true;
        }
        return false;
    }
    
    private List<UserDto> getUsersForManager(UserCredentialDto userCredentialDto) {
        List<UserDto> userDtoAccumulator = new ArrayList<>();
        
        if (Integer.parseInt(userCredentialDto.getRoleId()) == ManageUsersServiceImpl.APP_MGR) { 
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    userDtoAccumulator.add((UserDto) userTransformer.transform(userEntity));
                }
            }
        }
        
        if (Integer.parseInt(userCredentialDto.getRoleId()) == ManageUsersServiceImpl.SITE_MGR) {  
            List<UserEntity> userEntities = userEntityRepository.findAll();
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity) {
                    if (!filterCredentialsForManagedUsers(userCredentialDto, userEntity) && userEntity.getServiceAgencies().size() > 0){
                        populateServiceAgencies(userDtoAccumulator, userEntity, userCredentialDto);
                    }
                }
            }
        }
        
        if (Integer.parseInt(userCredentialDto.getRoleId()) == ManageUsersServiceImpl.LOCAL_SITE_MGR) { 
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
            return true;  //true means that the userEntity is filtered out and will not be processed
        }
        
        List<String> filterList = Arrays.asList(filter);
        if(filterList.contains(userEntity.getRole())) {
            return true;
        }
        return false; //false = the user will be managed
    }

    
    private String[] getFilter(String userRole) {
        String[] rFilter = null;
        
        switch (userRole) {
            case UserCredentialDto.GROUP_R2_SITEADMIN:
                rFilter = ManageUsersServiceImpl.SITE_MGR_FILTER;
                break;   
             
            case UserCredentialDto.GROUP_R2_LOCALSITEADMIN:
                rFilter = ManageUsersServiceImpl.LOCAL_SITE_MGR_FILTER;
                break;
            
            default:
                break;
        }
        
        return rFilter;
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

}
