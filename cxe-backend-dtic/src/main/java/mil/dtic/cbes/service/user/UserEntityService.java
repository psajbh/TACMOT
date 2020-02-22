package mil.dtic.cbes.service.user;


import java.util.List;

import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.UserEntity;

public interface UserEntityService {
    List<UserDto> findManagedUsers(UserCredentialDto userCredentialDto);
    UserEntity findUserEntityByLdapId(String ldapId);
    UserDto findUserDtoByUserLdapId(String ldapId);
    UserDto updateUser(UserDto userDto);
    UserDto addUser(UserDto userDto);
    UserDto findUserDto();
}
