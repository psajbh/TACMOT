package mil.dtic.cbes.service.user.imp;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.model.entities.UserCredentialsEntity;
import mil.dtic.cbes.repositories.UserCredentialsEntityRepository;
import mil.dtic.cbes.service.user.api.UserCredentialEntityService;

@Service
public class UserCredentialEntityServiceImpl implements UserCredentialEntityService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private static final String NOT_AUTHORIZED = "Not Authorized";
    private static final String COMMA_SEPARATOR = ", ";
    
    private UserCredentialsEntityRepository userCredentialsEntityRepository;
    
    public UserCredentialEntityServiceImpl(UserCredentialsEntityRepository userCredentialsEntityRepository) {
        this.userCredentialsEntityRepository = userCredentialsEntityRepository;
    }
    
    @Override
    public UserCredentialDto getCredentials(String ldapId) {
        log.debug("getCredentials-start ldapId: " + ldapId);
        UserCredentialDto userCredentialDto = new UserCredentialDto();
        List<UserCredentialsEntity> userCredentials = userCredentialsEntityRepository.findByLdapId(ldapId);
        
        if (null == userCredentials || userCredentials.size() == 0) {
            userCredentialDto.setStatus(UserCredentialEntityServiceImpl.NOT_AUTHORIZED);
            userCredentialDto.setValid(false);
            return userCredentialDto;
        }
        
        userCredentialDto.setUserId(userCredentials.get(0).getUserId());
        userCredentialDto.setLdapId(userCredentials.get(0).getLdapId());
        userCredentialDto.setUserRole(userCredentials.get(0).getUserRole());
        userCredentialDto.setRoleId(userCredentials.get(0).getRoleId());
        userCredentialDto.setRoleName(userCredentials.get(0).getRoleName());
        
        StringBuilder sb = new StringBuilder();
        
        for (UserCredentialsEntity userCredentialsEntity : userCredentials) {
            String agency = userCredentialsEntity.getAgency();
            sb.append(agency + UserCredentialEntityServiceImpl.COMMA_SEPARATOR);
        }
        
        userCredentialDto.setStrAgencies(sb.toString());
        userCredentialDto.setValid(true);
        
        return userCredentialDto;
        
    }

}
