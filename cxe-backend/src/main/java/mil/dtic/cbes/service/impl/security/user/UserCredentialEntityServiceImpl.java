package mil.dtic.cbes.service.impl.security.user;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.entities.views.security.UserCredentialsEntity;
import mil.dtic.cbes.repositories.security.user.UserCredentialsEntityRepository;
import mil.dtic.cbes.service.security.user.UserCredentialEntityService;

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
        log.trace("getCredentials-start ldapId: " + ldapId);
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
        sb.append(userCredentials.stream().map(e -> e.getAgency()).reduce(UserCredentialEntityServiceImpl.COMMA_SEPARATOR,String::concat));
        log.trace("getCredentials- agency concatenation: " + sb.toString());
        
        userCredentialDto.setStrAgencies(sb.toString());
        userCredentialDto.setValid(true);
        log.trace("getCredentials-start returning userCredentialDto: " + userCredentialDto.toString());
        return userCredentialDto;
        
    }
    
    

}
