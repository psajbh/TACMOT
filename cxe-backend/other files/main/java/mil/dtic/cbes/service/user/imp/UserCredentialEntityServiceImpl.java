package mil.dtic.cbes.service.user.imp;


import java.util.List;

import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.UserCredential;
import mil.dtic.cbes.model.entities.UserCredentialsEntity;
import mil.dtic.cbes.repositories.UserCredentialsEntityRepository;
import mil.dtic.cbes.service.user.api.UserCredentialEntityService;

@Service
public class UserCredentialEntityServiceImpl implements UserCredentialEntityService{
    
    UserCredentialsEntityRepository userCredentialsEntityRepository;
    
    public UserCredentialEntityServiceImpl(UserCredentialsEntityRepository userCredentialsEntityRepository) {
        this.userCredentialsEntityRepository = userCredentialsEntityRepository;
    }
    
    @Override
    public UserCredential getCredentials(String ldapId) {
        UserCredential userCredential = new UserCredential();
        List<UserCredentialsEntity> userCredentials = userCredentialsEntityRepository.findByLdapId(ldapId);
        
        if (null == userCredentials || userCredentials.size() == 0) {
            userCredential.setStatus("Not Authorized");
            userCredential.setValid(false);
            return userCredential;
        }
        
        userCredential.setUserId(userCredentials.get(0).getUserId());
        userCredential.setLdapId(userCredentials.get(0).getLdapId());
        userCredential.setUserRole(userCredentials.get(0).getUserRole());
        userCredential.setCreate_pe_priv(userCredentials.get(0).getCreate_pe_priv());
        userCredential.setCreate_li_priv(userCredentials.get(0).getCreate_li_priv());
        userCredential.setRole_id(userCredentials.get(0).getRole_id());
        userCredential.setRole_name(userCredentials.get(0).getRole_name());
        
        StringBuilder sb = new StringBuilder();
        
        for (UserCredentialsEntity userCredentialsEntity : userCredentials) {
            String agency = userCredentialsEntity.getAgency();
            sb.append(agency + ", ");
            userCredential.getAgencies().add(agency);
        }
        
        userCredential.setStrAgencies(sb.toString());
        userCredential.setStatus("OK");
        userCredential.setValid(true);
        
        return userCredential;
        
    }

}
