package mil.dtic.cbes.service.impl.core.security.user;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.model.enums.StatusFlag;
import mil.dtic.cbes.repositories.security.user.UserEntityRepository;
import mil.dtic.cbes.service.impl.security.user.UserEntityServiceImpl;
import mil.dtic.cbes.utils.transform.impl.UserTransformer;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceImplTest {
	
	@Mock
	UserEntityRepository userEntityRepository;
	
	@Mock
	UserTransformer userTransformer;
	
	@InjectMocks
	UserEntityServiceImpl userEntity;
	
	List<UserEntity> userEntities;
	
	UserCredentialDto userCredentialDto;

	@BeforeEach
	void setUp() throws Exception {
		userEntities = new ArrayList<>();
		populateUserEntities();
	}

//	@DisplayName("Test Find All Users for an AppMgr")
//	@Disabled  //enable after sorting out how to get userCredential for service.findManagedUser()
//	void findManagedUsers_forAppMgr_Test() {
//		//given
//		userCredentialDto = getUserCredential("1");
//		
//		//when
//		when(userEntityRepository.findAll()).thenReturn(userEntities);
//		
//		//then
//		List<UserDto> users = service.findManagedUsers();
//		verify(userEntityRepository).findAll(); //verifies that only 1 call was made with findAll().
//		assertThat(users).isNotNull();
//		assertThat(users).hasSize(2);
//		
//	}
	
	
	private UserCredentialDto getUserCredential(String role) {
		UserCredentialDto userCredentialDto = new UserCredentialDto();
		userCredentialDto.setName("test");
		userCredentialDto.setRoleId(role);
		return userCredentialDto;
	}
	
	private void populateUserEntities() {
		UserEntity user1 = new UserEntity();
		user1.setId(1);
		user1.setUserLdapId("lincolna0000");
		user1.setFullName("Abe Lincoln");
		user1.setRole(UserCredentialDto.GROUP_R2_APP_ADMIN);
		user1.setStatusFlag(StatusFlag.A);
		userEntities.add(user1);
		
		UserEntity user2 = new UserEntity();
		user1.setId(2);
		user1.setUserLdapId("leer0000");
		user1.setFullName("Robert Lee");
		user1.setRole(UserCredentialDto.GROUP_R2_USER);
		user1.setStatusFlag(StatusFlag.A);
		userEntities.add(user2);
	}
	

}
