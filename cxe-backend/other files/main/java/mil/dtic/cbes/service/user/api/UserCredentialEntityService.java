package mil.dtic.cbes.service.user.api;

import mil.dtic.cbes.model.dto.UserCredential;

public interface UserCredentialEntityService {
    
    UserCredential getCredentials(String ldapId);
    
}
