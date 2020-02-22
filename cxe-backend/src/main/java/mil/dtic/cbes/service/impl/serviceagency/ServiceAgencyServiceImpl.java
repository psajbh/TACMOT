package mil.dtic.cbes.service.impl.serviceagency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.serviceagency.R2ServiceAgencyDto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.user.UserDto;
import mil.dtic.cbes.model.entities.AppropriationEntity;
import mil.dtic.cbes.model.entities.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;
import mil.dtic.cbes.repositories.serviceagency.R2ServiceAgencyRepository;
import mil.dtic.cbes.repositories.serviceagency.ServiceAgencyRepository;
import mil.dtic.cbes.service.serviceagency.ServiceAgencyService;
import mil.dtic.cbes.service.user.UserEntityService;
import mil.dtic.cbes.utils.transform.impl.R2ServiceAgencyTransformer;
import mil.dtic.cbes.utils.transform.impl.ServiceAgencyTransformer;

@Service
public class ServiceAgencyServiceImpl implements ServiceAgencyService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final static String APP_MGR = "R2AppMgr";
	
	private ServiceAgencyRepository serviceAgencyRepository;
	private ServiceAgencyTransformer serviceAgencyTransformer;
	private UserEntityService userEntityService;
	private R2ServiceAgencyRepository r2ServiceAgencyRepository;
	private R2ServiceAgencyTransformer r2ServiceAgencyTransformer;
	
	
	
	public ServiceAgencyServiceImpl(ServiceAgencyRepository serviceAgencyRepository, ServiceAgencyTransformer serviceAgencyTransformer, 
			UserEntityService userEntityService, R2ServiceAgencyRepository r2ServiceAgencyRepository, R2ServiceAgencyTransformer r2ServiceAgencyTransformer) {
		this.userEntityService = userEntityService;
		this.serviceAgencyRepository = serviceAgencyRepository;
		this.serviceAgencyTransformer = serviceAgencyTransformer;
		this.r2ServiceAgencyRepository = r2ServiceAgencyRepository;
		this.r2ServiceAgencyTransformer = r2ServiceAgencyTransformer;
		
	}
	
	private UserDto validateUser(String ldapId) {
		return userEntityService.findUserDtoByUserLdapId(ldapId);
	}
	
	@Override
	public List<R2ServiceAgencyDto> getR2ServiceAgencies(String ldapId){
		log.trace("getR2ServiceAgencies- ldapId: " + ldapId);
		UserDto userDto = validateUser(ldapId);
		
		if (null == userDto) {
			log.warn("getServiceAgencies- user not authenticated ldapId: " + ldapId);
			return null;  // TODO: throw an exception.
		}

		Map<String, String> authorizedAgencyMap = new HashMap<>();
		populateAuthMap(authorizedAgencyMap, userDto);
		List<R2ServiceAgencyEntity> r2ServiceAgencyEntities = r2ServiceAgencyRepository.findAll();
		return processR2Entities(r2ServiceAgencyEntities, userDto, authorizedAgencyMap);
		
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
	
	public R2ServiceAgencyDto getR2Appropriations(Integer serviceAgencyId) {
		Optional<ServiceAgencyEntity> o = serviceAgencyRepository.findById(serviceAgencyId);
		if (o.isPresent()) {
			ServiceAgencyEntity serviceAgencyEntity = o.get();
			List<AppropriationEntity> approprations = serviceAgencyEntity.getAppropriations();
			
		}
		
		return null;
		
	}
	
	private List<R2ServiceAgencyDto> processR2Entities(List<R2ServiceAgencyEntity> r2ServiceAgencies, UserDto userDto, Map<String, String> authorizedAgencyMap) {
		log.trace("processR2Entities- ");
		List<R2ServiceAgencyDto> r2serviceAgencyDtos = new ArrayList<>();
		
		for (R2ServiceAgencyEntity r2ServiceAgencyEntity : r2ServiceAgencies) {
			R2ServiceAgencyDto r2ServiceAgencyDto = r2ServiceAgencyTransformer.transform(r2ServiceAgencyEntity);
			if (userDto.getRole().equals(APP_MGR)) { 
				r2serviceAgencyDtos.add(r2ServiceAgencyDto);
			}
			else {
				String authCode = authorizedAgencyMap.get(r2ServiceAgencyDto.getCode());
				if (null != authCode) {
					log.trace("added "+authCode+" for user "+userDto.getUserLdapId());
					r2serviceAgencyDtos.add(r2ServiceAgencyDto);
				}
			}
		}
		return r2serviceAgencyDtos;
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
