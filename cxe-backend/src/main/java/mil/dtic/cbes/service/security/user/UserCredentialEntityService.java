package mil.dtic.cbes.service.security.user;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;

public interface UserCredentialEntityService {
    
    UserCredentialDto getCredentials(String ldapId);
    
}
