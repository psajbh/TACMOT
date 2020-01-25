package mil.dtic.cbes.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.service.user.api.UserEntityService;

@RestController
public class UserProfileController extends BaseRestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private UserEntityService userEntityService;
	
	public UserProfileController(UserEntityService userEntityService) {
	    this.userEntityService = userEntityService;
	}
	
    @GetMapping(path="user/profile", produces="application/json")
	public ResponseEntity<UserDto> getProfile() {
        log.trace("getProfile-");
        return ResponseEntity.status(HttpStatus.OK).
                body(userEntityService.findUserDtoByUserLdapId(super.getCredential().getLdapId()));
        
	}
	
}