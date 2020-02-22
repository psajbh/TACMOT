package mil.dtic.cbes.controllers.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mil.dtic.cbes.model.dto.user.UserCredentialDto;
import mil.dtic.cbes.utils.security.Authorization;
import mil.dtic.cbes.utils.security.LoginManager;
import mil.dtic.cbes.utils.security.UserSecurity;

@ExtendWith(MockitoExtension.class)
public class CxeSecurityControllerTest {
	
	@InjectMocks
	CxeSecurityController cxeSecurityController;
	
	@Mock
	Authorization auth;
	
	@Mock
	LoginManager loginManager;
	
	@Test
	public void testGetUser() {
		//given
		String username = "aname0000";
		UserSecurity userSecurity = getUserSecurity(username);
		Principal principal = getPrincipal(username);
		
		//when
		when(loginManager.getLoggedInUser()).thenReturn(userSecurity);
		
		//then
		Map<String, Object> map  = cxeSecurityController.getUser(principal).getBody();
		assertThat(map.get("loginId")).isEqualTo(userSecurity.getUsername());
	}
	
	private Principal getPrincipal(String userName) {
		UserCredentialDto userCredentialDto = new UserCredentialDto();
		userCredentialDto.setName(userName);
		return userCredentialDto;
	}
	
	private UserSecurity getUserSecurity(String username) {
		UserSecurity userDetails = new UserSecurity();
        userDetails.setUsername(username);
        userDetails.setCommonName("Joe Tester");
        userDetails.setPassword("password");
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setEnabled(true);
        userDetails.setRole("R2AppMgr");
        return userDetails;
	}

}
