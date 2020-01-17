package mil.dtic.cbes.controllers.userguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.service.config.ConfigurationService;

@RestController
public class UserGuideController extends BaseRestController {
	
	@Autowired
	private ConfigurationService configService;
	
	@GetMapping("/userguide")
	public ResponseEntity<String> getUserGuideHTML() {
		String userGuideText = configService.getConfigValueByName(ConfigurationService.R2_HELP);
		if (userGuideText != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userGuideText);
		}
	    return ResponseEntity.status(500).body(null);
	}

}
