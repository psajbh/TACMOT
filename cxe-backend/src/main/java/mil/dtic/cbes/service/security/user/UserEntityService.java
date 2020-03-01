package mil.dtic.cbes.service.security.user;


import java.util.List;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.security.UserEntity;

public interface UserEntityService {
    List<UserDto> findManagedUsers(UserCredentialDto userCredentialDto);
    UserEntity findUserEntityByLdapId(String ldapId);
    UserDto findUserDtoByUserLdapId(String ldapId);
    UserDto updateUser(UserDto userDto);
    UserDto addUser(UserDto userDto);
    UserDto findUserDto();
}
