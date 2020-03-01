package mil.dtic.cbes.controllers.security.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.service.security.user.UserEntityService;

@RestController
public class UserProfileController extends BaseRestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private UserEntityService userEntityService;
	
	public UserProfileController(UserEntityService userEntityService) {
	    this.userEntityService = userEntityService;
	}
	
    @GetMapping(path="user/profile")
	public ResponseEntity<UserDto> getProfile() {
        log.trace("getProfile-");
        return ResponseEntity.status(HttpStatus.OK).
        		body(userEntityService.findUserDto());
	}
	
}
