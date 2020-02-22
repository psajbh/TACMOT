package mil.dtic.cbes.service.user;

import mil.dtic.cbes.model.dto.user.UserCredentialDto;

public interface UserCredentialEntityService {
    
    UserCredentialDto getCredentials(String ldapId);
    
}
