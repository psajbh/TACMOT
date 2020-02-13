package mil.dtic.cbes.controllers.user;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.service.user.UserEntityService;

@ExtendWith(MockitoExtension.class)
class UserProfileControllerTest {
	
	@InjectMocks
	UserProfileController userProfileController;
	
	@Mock
	UserEntityService userEntityService;


//	@Test
//	void testGetProfile() {
//		//fail("Not yet implemented");
//		UserProfileController spy = Mockito.spy(new UserProfileController());
//		//Mockito.doNothing().when((BaseRestController)spy).getCredential();
//		when((BaseRestController)spy).getCredential();
//		System.out.println();
//
//	}

}

//@Test
//void testUserProfileController() throws Exception{
//	mockMvc.perform(get("api/user/profile"))
//    .andExpect(status().isOk());
//}


//@BeforeEach
//void setUp() {
//	UserDto userDto = new UserDto();
//	when(userEntityService.findUserDtoByUserLdapId(any())
//	
//	//given(clinicService.findVets()).willReturn(vetsList);
//
//    mockMvc = MockMvcBuilders.standaloneSetup(controller
//).build();
//
//    vetsList.add(new Vet());
//    given(clinicService.findVets()).willReturn(vetsList);
//    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//}
