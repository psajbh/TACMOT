package mil.dtic.cbes.controllers.announcements;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.Announcements;
import mil.dtic.cbes.service.announcements.AnnouncementService;

@RestController
public class AnnouncementController extends BaseRestController {
	
	@Autowired
	private AnnouncementService announcementService;
	
	@GetMapping(path="/announcement/getannouncement")
	public Announcements getAnnouncement() throws IOException {
		return announcementService.getAnnouncement();
	}

}
