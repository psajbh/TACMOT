package mil.dtic.cbes.service.userguide;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import mil.dtic.cbes.utils.exceptions.rest.ResourceNotFoundException;


@Service
public class UserGuideService {

	@Autowired
	ResourceLoader resourceLoader;

	private MessageSource messageSource;

	public String getUserGuideHTML() throws ResourceNotFoundException {
		Resource resource = resourceLoader.getResource("file:///d2/config/cxe/userGuide");

		try {
			return StreamUtils.copyToString(resource.getInputStream(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new ResourceNotFoundException(ResourceNotFoundException.USER_GUIDE_NOT_FOUND);
		}
	}
	 
}
