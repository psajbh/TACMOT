package mil.dtic.cbes.service.security.user;

import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.security.UserEntity;

public interface UserEntityService {
    UserEntity findUserEntityByLdapId();
    UserDto findUserDto();
    UserDto findUserDtoByUserLdapId();
    UserDto findUserDtoByUserLdapId(String ldapId);
    UserDto updateUser(UserDto userDto);
    UserDto addUser(UserDto userDto);
    
}
