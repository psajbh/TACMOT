package mil.dtic.cbes.controllers.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.service.user.UserEntityService;

@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {
	
	@InjectMocks
	UserProfileController userProfileController;
	
    @Mock
    UserEntityService userEntityService;
    
    
    @Test
    public void getProfileTest() throws Exception {
    	//given
        UserDto userDto = new UserDto();
        userDto.setUserLdapId("ldap0000");
        
        //when 
        when(userEntityService.findUserDto()).thenReturn(userDto);

        //then
        userDto= userProfileController.getProfile().getBody();
        assertNotNull(userDto.getUserLdapId());
        assertNull(userDto.getEmail());
        
        String testToString = userDto.toString();
        assertNotNull(testToString);
        
        assertNotNull(userDto.toString());
        assertNotNull(userDto.hashCode());
        //assertNotNull()
    }


}
