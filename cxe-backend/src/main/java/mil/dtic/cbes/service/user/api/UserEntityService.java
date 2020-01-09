package mil.dtic.cbes.service.user.api;


import java.util.List;

import mil.dtic.cbes.model.dto.UserCredential;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.UserEntity;

public interface UserEntityService {
    UserEntity findUserById(Integer id);
    List<UserDto> findManagedUsers(UserCredential userCredential);
    UserEntity findByUserLdapId(String ldapId);
    UserDto findUserDtoByUserLdapId(String ldapId);
    UserDto updateUser(UserDto userDto);
    UserDto addUser(UserDto userDto);
    boolean deleteUser(UserDto userDto);
    
    
}
