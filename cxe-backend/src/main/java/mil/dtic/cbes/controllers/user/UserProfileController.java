package mil.dtic.cbes.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.service.user.UserCredentialEntityService;
import mil.dtic.cbes.service.user.UserEntityService;

@RestController
public class UserProfileController extends BaseRestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private UserEntityService userEntityService;
	
	@Autowired
	private UserCredentialEntityService userCredentialEntityService;
	
	public UserProfileController() {}
	
	public UserProfileController(UserEntityService userEntityService) {
	    this.userEntityService = userEntityService;
	}
	
    @GetMapping(path="user/profile")
	public ResponseEntity<UserDto> getProfile() {
        log.trace("getProfile-");
        UserCredentialDto userCredentialDto = userCredentialEntityService.getCredentials();
        UserDto userDto = new UserDto();
        userDto.setRole(userCredentialDto.getRoleId());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
        
	}
	
}
