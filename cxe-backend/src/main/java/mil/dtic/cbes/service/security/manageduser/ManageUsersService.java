package mil.dtic.cbes.service.security.manageduser;

import java.util.List;

import mil.dtic.cbes.model.dto.security.UserDto;

public interface ManageUsersService {
	
	List<UserDto> findManagedUsers();

}
