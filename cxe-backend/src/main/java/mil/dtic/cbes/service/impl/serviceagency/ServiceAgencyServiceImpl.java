package mil.dtic.cbes.service.impl.serviceagency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.user.UserDto;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.repositories.serviceagency.R2ServiceAgencyRepository;
import mil.dtic.cbes.repositories.serviceagency.ServiceAgencyRepository;
import mil.dtic.cbes.service.serviceagency.ServiceAgencyService;
import mil.dtic.cbes.service.user.UserEntityService;
import mil.dtic.cbes.utils.transform.impl.serviceagency.R2ServiceAgencyProjectionTransformer;
import mil.dtic.cbes.utils.transform.impl.serviceagency.ServiceAgencyTransformer;

@Service
public class ServiceAgencyServiceImpl implements ServiceAgencyService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final static String APP_MGR = "R2AppMgr";
	
	private ServiceAgencyRepository serviceAgencyRepository;
	private ServiceAgencyTransformer serviceAgencyTransformer;
	private UserEntityService userEntityService;
	
	public ServiceAgencyServiceImpl(ServiceAgencyRepository serviceAgencyRepository, ServiceAgencyTransformer serviceAgencyTransformer, 
			UserEntityService userEntityService, R2ServiceAgencyRepository r2ServiceAgencyRepository, R2ServiceAgencyProjectionTransformer r2ServiceAgencyTransformer) {
		this.userEntityService = userEntityService;
		this.serviceAgencyRepository = serviceAgencyRepository;
		this.serviceAgencyTransformer = serviceAgencyTransformer;
		
	}
	
	private UserDto validateUser(String ldapId) {
		return userEntityService.findUserDtoByUserLdapId(ldapId);
	}
	
	@Override
	public List<ServiceAgencyDto> getServiceAgencies(String ldapId) {
		log.trace("getServiceAgencies- ldapId: " + ldapId);
		
		Map<String, String> authorizedAgencyMap = new HashMap<>();
		UserDto userDto = validateUser(ldapId);
		
		if (null == userDto) {
			log.warn("getServiceAgencies- user not authenticated ldapId: " + ldapId);
			return null;  // TODO: throw an exception.
		}
		
		populateAuthMap(authorizedAgencyMap, userDto);
		List<ServiceAgencyEntity> serviceAgencyEntities = serviceAgencyRepository.findAll();
		return processEntities(serviceAgencyEntities, userDto, authorizedAgencyMap);
		
	}

	private List<ServiceAgencyDto> processEntities(List<ServiceAgencyEntity> serviceAgencyEntities, UserDto userDto, Map<String, String> authorizedAgencyMap) {
		log.trace("processEntities- ");
		List<ServiceAgencyDto> serviceAgencyDtos = new ArrayList<>();
		
		for (ServiceAgencyEntity serviceAgencyEntity : serviceAgencyEntities) {
			ServiceAgencyDto serviceAgencyDto = serviceAgencyTransformer.transform(serviceAgencyEntity);
			if (userDto.getRole().equals(APP_MGR)) { 
				serviceAgencyDtos.add(serviceAgencyDto);
			}
			else {
				String authCode = authorizedAgencyMap.get(serviceAgencyDto.getCode());
				if (null != authCode) {
					log.trace("added "+authCode+" for user "+userDto.getUserLdapId());
					serviceAgencyDtos.add(serviceAgencyDto);
				}
			}
		}
		return serviceAgencyDtos;
		
	}
	
	private void populateAuthMap(Map<String, String> authorizedAgencyMap, UserDto userDto) {
		log.trace("populateAuthMap-");
		if (!userDto.getRole().equals(APP_MGR)) {
			for (ServiceAgencyDto userServiceAgency : userDto.getServiceAgencies()) {
				String code = userServiceAgency.getCode();
				authorizedAgencyMap.put(code, code);
			}
		}
	}

}
