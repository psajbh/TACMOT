package mil.dtic.cbes.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.config.CxeFilter;
import mil.dtic.cbes.config.MutableHttpServletRequest;
import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.service.user.api.UserEntityService;

@RestController
public class UserLoginController extends BaseRestController{
	private UserEntityService userEntityService;
	
	public UserLoginController(UserEntityService userEntityService) {
	    this.userEntityService = userEntityService;
	}
	
	@GetMapping(path="login")
	public ResponseEntity<UserDto> login(MutableHttpServletRequest mutableRequest) {
    	
    	UserDto userDto = userEntityService.findUserDtoByUserLdapId(mutableRequest.getHeader(CxeFilter.KEY_ELEMENT));
    	if (null != userDto) {
    		userDto.setHttpSessionId(mutableRequest.getSession().getId());
    		return ResponseEntity.status(HttpStatus.OK).body(userDto);
    		
    	}
    	else {
    		UserDto dto = new UserDto();
    		dto.setHttpSessionId(mutableRequest.getSession().getId());
    		dto.setMessage("Login Failure");
    		return ResponseEntity.status(401).body(userDto);
    	}
	    
	}
	
}
