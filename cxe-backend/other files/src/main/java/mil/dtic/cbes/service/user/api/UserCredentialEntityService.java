package mil.dtic.cbes.service.user.api;

import mil.dtic.cbes.model.dto.UserCredentialDto;

public interface UserCredentialEntityService {
    
    UserCredentialDto getCredentials(String ldapId);
    
}
