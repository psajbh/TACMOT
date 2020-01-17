package mil.dtic.cbes.controllers;


import static org.junit.Assert.assertTrue;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import mil.dtic.cbes.config.CxeFilter;
import mil.dtic.cbes.controllers.user.UserProfileController;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.service.config.ConfigurationService;
import mil.dtic.cbes.service.user.api.UserEntityService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserProfileController.class)
public class UserProfileControllerTest {
    
    private final static String REQ_PATH  = "/api/user/profile"; 
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    UserEntityService userEntityService;
    
    @MockBean
    ConfigurationService configurationService;
    
    @MockBean
    CxeFilter cxeFilter;
    
    UserProfileController userProfileController;
    
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        userProfileController = new UserProfileController(userEntityService);
    }
    
    @Test
    public void getProfileHappyPathTest() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2);
        UserDto userDto = new UserDto();
        userDto.setId(2);
        
        Mockito.when(userEntityService.findUserDtoByUserLdapId(Mockito.anyString())).thenReturn(userDto);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(UserProfileControllerTest.REQ_PATH);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value());
        
    }
    
//    @Test(expected=Exception.class)
//    public void getProfileInvalidLdapIdTest() throws Exception {
//        String invalidLdapId = "nogood";
//        Mockito.when(userEntityService.findByUserLdapId(invalidLdapId)).thenThrow(new Exception());
//        
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(UserProfileControllerTest.REQ_PATH);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        
//        //assertTrue(result.getResponse().getStatus() == 500);
//        //System.out.println();
//    }
}
