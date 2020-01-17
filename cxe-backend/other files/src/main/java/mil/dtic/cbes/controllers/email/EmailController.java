package mil.dtic.cbes.controllers.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.ContactUsEmail;
import mil.dtic.cbes.service.email.EmailService;

@RestController
public class EmailController extends BaseRestController {
	
	@Autowired
	private EmailService emailService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path="/email/sendemail")
	public void sendEmail(@RequestBody ContactUsEmail contactUsEmail) {
		emailService.sendAnEmail(contactUsEmail);
	}
}
