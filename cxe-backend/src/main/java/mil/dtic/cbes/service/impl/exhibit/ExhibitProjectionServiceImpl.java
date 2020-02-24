package mil.dtic.cbes.service.impl.exhibit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.user.UserDto;
import mil.dtic.cbes.model.entities.views.P40AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.P40ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.R2AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.R2ServiceAgencyEntity;
import mil.dtic.cbes.repositories.exhibit.projection.p40.P40AppnBudgetActivityProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.projection.p40.P40ServiceAgencyProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.projection.r2.R2AppnBudgetActivityProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.projection.r2.R2ServiceAgencyProjectionRepository;
import mil.dtic.cbes.service.exhibit.ExhibitProjectionService;
import mil.dtic.cbes.service.user.UserEntityService;
import mil.dtic.cbes.utils.transform.impl.appn.R2AppnBudgetActivityProjectionTransformer;
import mil.dtic.cbes.utils.transform.impl.serviceagency.R2ServiceAgencyProjectionTransformer;

@SuppressWarnings("unused")
@Service
public class ExhibitProjectionServiceImpl implements ExhibitProjectionService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final static String APP_MGR = "R2AppMgr";
	
	private R2AppnBudgetActivityProjectionRepository r2AppnBudgetActivityRepo;
	private R2ServiceAgencyProjectionRepository r2ServiceAgencyProjectionRepo;
	private P40AppnBudgetActivityProjectionRepository p40AppnBudgetActivityRepo;
	private P40ServiceAgencyProjectionRepository p40ServiceAgencyRepo;
	private UserEntityService userEntityService;
	private R2ServiceAgencyProjectionTransformer r2ServiceAgencyProjectionTransformer;
	private R2AppnBudgetActivityProjectionTransformer r2AppnBudgetActivityProjectionTransformer;
	
	public ExhibitProjectionServiceImpl(UserEntityService userEntityService,
			R2AppnBudgetActivityProjectionRepository r2AppnBudgetActivityRepo,
			R2ServiceAgencyProjectionRepository r2ServiceAgencyProjectionRepo, 
			P40AppnBudgetActivityProjectionRepository p40AppnBudgetActivityRepo, 
			P40ServiceAgencyProjectionRepository p40ServiceAgencyRepo,
			R2ServiceAgencyProjectionTransformer r2ServiceAgencyProjectionTransformer,
			R2AppnBudgetActivityProjectionTransformer r2AppnBudgetActivityProjectionTransformer) {
		this.userEntityService = userEntityService;
		this.r2AppnBudgetActivityRepo = r2AppnBudgetActivityRepo;
		this.r2ServiceAgencyProjectionRepo = r2ServiceAgencyProjectionRepo;
		this.p40AppnBudgetActivityRepo = p40AppnBudgetActivityRepo;
		this.p40ServiceAgencyRepo = p40ServiceAgencyRepo;
		this.r2ServiceAgencyProjectionTransformer = r2ServiceAgencyProjectionTransformer;
		this.r2AppnBudgetActivityProjectionTransformer = r2AppnBudgetActivityProjectionTransformer;
	}
	
	@Override
	public List<ServiceAgencyProjectionDto> getR2ServiceAgencies(String ldapId) {
		log.trace("getR2ServiceAgencies- ldapId: " + ldapId);
		UserDto userDto = validateUser(ldapId);
		
		if (null == userDto) {
			log.warn("getServiceAgencies- user not authenticated ldapId: " + ldapId);
			return null;  // TODO: throw an exception.
		}
		
		Map<String, String> authorizedAgencyMap = new HashMap<>();
		populateAuthMap(authorizedAgencyMap, userDto);
		List<R2ServiceAgencyEntity> r2ServiceAgencyEntities = r2ServiceAgencyProjectionRepo.findAll();
		return processR2ServiceAgencyEntities(r2ServiceAgencyEntities, userDto, authorizedAgencyMap);
	}
	
	@Override
	public List<AppnBudgetActivityProjectionDto> getR2AppnBudgetActivities(String ldapId, Integer serviceAgencyId) {
		log.trace("getR2AppnBudgetActivities- ldapId: "+ldapId+" serviceAgencyId: "+serviceAgencyId);
		UserDto userDto = validateUser(ldapId);
		
		if (null == userDto) {
			log.warn("getServiceAgencies- user not authenticated ldapId: " + ldapId);
			return null;  // TODO: throw an exception.
		}
		
		List<R2AppnBudgetActivityEntity> r2AppBudgetActivityEntities = r2AppnBudgetActivityRepo.findByServiceAgencyId(serviceAgencyId);
		
		//testing useing log repo methods
		//R2AppnBudgetActivityEntity rabae = r2AppBudgetActivityEntities.get(0);
		//List<R2AppnBudgetActivityEntity> test = r2AppnBudgetActivityRepo.findByServiceAgencyIdAndAppropriationIdAndBudgetActivityIdAndBudgetSubActivityId(rabae.getServiceAgencyId(), 
		//		rabae.getAppropriationId(), rabae.getBudgetActivityId(), rabae.getBudgetSubActivityId());
		//endTest
		
		return processR2AppnBudgetActivityEntities(r2AppBudgetActivityEntities);
	}

	@Override
	public List<ServiceAgencyProjectionDto> getP40ServiceAgencies(String ldapId) {
		return null;
	}
	
	@Override
	public List<AppnBudgetActivityProjectionDto> getP40AppnBudgetActivities(String ldapId, Integer serviceAgencyId){
		return null;
	}
	
	private UserDto validateUser(String ldapId) {
		return userEntityService.findUserDtoByUserLdapId(ldapId);
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
	
	private List<ServiceAgencyProjectionDto> processR2ServiceAgencyEntities(List<R2ServiceAgencyEntity> r2ServiceAgencies, UserDto userDto, Map<String, String> authorizedAgencyMap) {
		log.trace("processR2ServiceAgencyEntities- ");
		List<ServiceAgencyProjectionDto> r2serviceAgencyProjectionDtos = new ArrayList<>();
		
		for (R2ServiceAgencyEntity r2ServiceAgencyEntity : r2ServiceAgencies) {
			ServiceAgencyProjectionDto r2ServiceAgencyDto = r2ServiceAgencyProjectionTransformer.transform(r2ServiceAgencyEntity);
			if (userDto.getRole().equals(APP_MGR)) { 
				r2serviceAgencyProjectionDtos.add(r2ServiceAgencyDto);
			}
			else {
				String authCode = authorizedAgencyMap.get(r2ServiceAgencyDto.getCode());
				if (null != authCode) {
					log.trace("added "+authCode+" for user "+userDto.getUserLdapId());
					r2serviceAgencyProjectionDtos.add(r2ServiceAgencyDto);
				}
			}
		}
		return r2serviceAgencyProjectionDtos;
	}
	
	private List<AppnBudgetActivityProjectionDto> processR2AppnBudgetActivityEntities(List<R2AppnBudgetActivityEntity> r2AppnBudgetActivities){
		log.trace("processR2AppnBudgetActivityEntities- ");
		List<AppnBudgetActivityProjectionDto> r2AppBuddgetActivityProjectionDtos = new ArrayList<>();
		
		for (R2AppnBudgetActivityEntity r2AppnBudgetActivityEntity : r2AppnBudgetActivities) {
			r2AppBuddgetActivityProjectionDtos.add(r2AppnBudgetActivityProjectionTransformer.transform(r2AppnBudgetActivityEntity));
		}
		
		return r2AppBuddgetActivityProjectionDtos;
		
	}

}
