package mil.dtic.cbes.controllers.userguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.service.userguide.UserGuideService;
import mil.dtic.cbes.utils.exceptions.rest.ResourceNotFoundException;

@RestController
public class UserGuideController extends BaseRestController {
	
	@Autowired
	private UserGuideService userGuideService;
	
	@GetMapping("/userguide")
	public ResponseEntity<String> getUserGuideHTML() throws ResourceNotFoundException {
		String userGuideText = userGuideService.getUserGuideHTML();
		if (!StringUtils.isEmpty(userGuideText)) {
			return ResponseEntity.status(HttpStatus.OK).body(userGuideText);
		}
	    return ResponseEntity.status(500).body(null);
	}

}
