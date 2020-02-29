package mil.dtic.cbes.service.announcements;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import mil.dtic.cbes.model.Announcements;

@Service
public class AnnouncementService {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	private Announcements announcements = new Announcements();
	
	public Announcements getAnnouncement() throws IOException {
		Resource resource = resourceLoader.getResource("file:///d2/config/cxe/announcements.html");
		
		String announcementHtml = StreamUtils.copyToString(resource.getInputStream(), Charset.forName("UTF-8"));
		int r2TitleIndex = announcementHtml.indexOf("||| R2 Title ||| ==");
		int r2HeaderIndex = announcementHtml.indexOf("||| R2 Header ||| ==");
		int r2TextIndex = announcementHtml.indexOf("||| R2 Text ||| ==");
		int p40TitleIndex = announcementHtml.indexOf("||| P40 Title ||| ==");
		int p40HeaderIndex = announcementHtml.indexOf("||| P40 Header ||| ==");
		int p40TextIndex = announcementHtml.indexOf("||| P40 Text ||| ==");
		
		announcements.setR2_title(announcementHtml.substring(r2TitleIndex + 20, r2HeaderIndex));
		announcements.setR2_header(announcementHtml.substring(r2HeaderIndex + 20, r2TextIndex));
		announcements.setR2_text(announcementHtml.substring(r2TextIndex + 19, p40TitleIndex));
		
		announcements.setP40_title(announcementHtml.substring(p40TitleIndex + 21, p40HeaderIndex));
		announcements.setP40_header(announcementHtml.substring(p40HeaderIndex + 21, p40TextIndex));
		announcements.setP40_text(announcementHtml.substring(p40TextIndex + 19, announcementHtml.length()));

		return announcements;
	}
}
