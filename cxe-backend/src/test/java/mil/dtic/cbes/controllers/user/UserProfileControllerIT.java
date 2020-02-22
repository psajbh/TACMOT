//package mil.dtic.cbes.controllers.user;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import javax.servlet.ServletException;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
////import mil.dtic.cbes.config.CxeFilter;
//import mil.dtic.cbes.controllers.user.UserProfileController;
//import mil.dtic.cbes.model.dto.UserDto;
//import mil.dtic.cbes.model.entities.UserEntity;
//import mil.dtic.cbes.service.config.ConfigurationService;
//import mil.dtic.cbes.service.user.UserEntityService;
//
//@WebMvcTest(controllers = UserProfileController.class)
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//public class UserProfileControllerIT {
//    
//	private final static String REQ_PATH  = "/api/user/profile";
//	
//	@Autowired
//	MockMvc mockMvc;
//	
//	@Mock
//	UserEntityService userEntityService;
//    
//	@InjectMocks
//    UserProfileController userProfileController;
//    
//    
//	@Disabled
//    public void getProfileTest() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(2);
//        UserDto userDto = new UserDto();
//        userDto.setId(2);
//        
//        Mockito.when(userEntityService.findUserDtoByUserLdapId(Mockito.anyString())).thenReturn(userDto);
//        
////        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(UserProfileControllerIT.REQ_PATH);
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////        
////        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
//        
//    }
//    
//}
//
////[[Testing Spring MVC Web Controllers with @WebMvcTest|https://reflectoring.io/spring-boot-web-controller-test/]]
