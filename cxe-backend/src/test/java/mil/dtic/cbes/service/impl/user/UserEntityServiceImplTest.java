package mil.dtic.cbes.service.impl.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.model.enums.StatusFlag;
import mil.dtic.cbes.repositories.user.UserEntityRepository;
import mil.dtic.cbes.utils.transform.impl.UserTransformer;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceImplTest {
	
	@Mock
	UserEntityRepository userEntityRepository;
	
	@Mock
	UserTransformer userTransformer;
	
	@InjectMocks
	UserEntityServiceImpl service;
	
	List<UserEntity> userEntities;
	
	UserCredentialDto userCredentialDto;

	@BeforeEach
	void setUp() throws Exception {
		userEntities = new ArrayList<>();
		populateUserEntities();
	}

	@DisplayName("Test Find All Users for an AppMgr")
	@Test
	void findManagedUsers_forAppMgr_Test() {
		//given
		userCredentialDto = getUserCredential("1");
		
		//when
		when(userEntityRepository.findAll()).thenReturn(userEntities);
		
		//then
		List<UserDto> users = service.findManagedUsers(userCredentialDto);
		verify(userEntityRepository).findAll(); //verifies that only 1 call was made with findAll().
		assertThat(users).isNotNull();
		assertThat(users).hasSize(2);
	}
	
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
