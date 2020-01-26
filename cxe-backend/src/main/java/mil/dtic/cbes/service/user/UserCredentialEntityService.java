package mil.dtic.cbes.service.user;

import mil.dtic.cbes.model.dto.UserCredentialDto;

public interface UserCredentialEntityService {
    
    UserCredentialDto getCredentials(String ldapId);
    
}
