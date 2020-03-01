package mil.dtic.cbes.service.impl.exhibit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.R2BudgetActivityDto;
import mil.dtic.cbes.model.dto.core.AppropriationDto;
import mil.dtic.cbes.model.dto.core.PeSuffixDto;
import mil.dtic.cbes.model.dto.core.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.exhibit.R2ServiceAgencyAppnActivityEntity;
import mil.dtic.cbes.model.entities.views.p40.P40AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.p40.P40ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.views.r2.PeSuffixEntity;
import mil.dtic.cbes.model.entities.views.r2.R2AppnBudgetActivityEntity;
import mil.dtic.cbes.model.entities.views.r2.R2ServiceAgencyEntity;
import mil.dtic.cbes.repositories.core.r2.PeSuffixRepository;
import mil.dtic.cbes.repositories.core.r2.R2ServiceAgencyAppnActivityRepository;
import mil.dtic.cbes.repositories.exhibit.p40.P40AppnBudgetActivityProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.p40.P40ServiceAgencyProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.r2.R2AppnBudgetActivityProjectionRepository;
import mil.dtic.cbes.repositories.exhibit.r2.R2ServiceAgencyProjectionRepository;
import mil.dtic.cbes.service.exhibit.ExhibitProjectionService;
import mil.dtic.cbes.service.security.user.UserEntityService;
import mil.dtic.cbes.utils.security.CxeHeaderAuthenticationFilter;
import mil.dtic.cbes.utils.transform.impl.appn.R2AppnBudgetActivityProjectionTransformer;
import mil.dtic.cbes.utils.transform.impl.serviceagency.PeSuffixProjectionTransformer;
import mil.dtic.cbes.utils.transform.impl.serviceagency.R2ServiceAgencyProjectionTransformer;

@SuppressWarnings("unused")
@Service
public class ExhibitProjectionServiceImpl implements ExhibitProjectionService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final static String APP_MGR = "R2AppMgr";
	
	private R2ServiceAgencyAppnActivityRepository r2ServiceAgencyAppnActivityRepository;
	private R2ServiceAgencyProjectionRepository r2ServiceAgencyProjectionRepo;
	private PeSuffixRepository peSuffixRepo;
	//private P40AppnBudgetActivityProjectionRepository p40AppnBudgetActivityRepo; //TODO: temporarily commented icw Fortify
	//private P40ServiceAgencyProjectionRepository p40ServiceAgencyRepo;  //TODO: temporarily commented icw Fortify
	
	private UserEntityService userEntityService;
	
	private R2ServiceAgencyProjectionTransformer r2ServiceAgencyProjectionTransformer;
	private R2AppnBudgetActivityProjectionTransformer r2AppnBudgetActivityProjectionTransformer;
	private PeSuffixProjectionTransformer peSuffixProjectionTransformer;
	
	
	public ExhibitProjectionServiceImpl(UserEntityService userEntityService,
			R2ServiceAgencyProjectionRepository r2ServiceAgencyProjectionRepo,
			R2ServiceAgencyProjectionTransformer r2ServiceAgencyProjectionTransformer,
			R2ServiceAgencyAppnActivityRepository r2ServiceAgencyAppnActivityRepository,
			R2AppnBudgetActivityProjectionTransformer r2AppnBudgetActivityProjectionTransformer,
			PeSuffixRepository peSuffixRepo, PeSuffixProjectionTransformer peSuffixProjectionTransformer) {
		
		this.userEntityService = userEntityService;
		this.r2ServiceAgencyProjectionRepo = r2ServiceAgencyProjectionRepo;
		this.r2ServiceAgencyAppnActivityRepository = r2ServiceAgencyAppnActivityRepository;
		this.r2ServiceAgencyProjectionTransformer = r2ServiceAgencyProjectionTransformer;
		this.r2AppnBudgetActivityProjectionTransformer = r2AppnBudgetActivityProjectionTransformer;
		this.peSuffixRepo = peSuffixRepo;
		this.peSuffixProjectionTransformer = peSuffixProjectionTransformer;
	}
	
	
	@Override
	public List<ServiceAgencyProjectionDto> getR2ServiceAgencies() {
		log.trace("getR2ServiceAgencies-");
		UserDto userDto = validateUser();
		
		if (null == userDto) {
			log.warn("getServiceAgencies- user not authenticated ldapId: " + 
					ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME));
			return null;  // TODO: throw an exception.
		}
		
		List<R2ServiceAgencyEntity> r2ServiceAgencyEntities = r2ServiceAgencyProjectionRepo.findAll();
		return processR2ServiceAgencyEntities(r2ServiceAgencyEntities, userDto, populateAuthMap(userDto));
	}
	
	@Override
	public List<AppropriationDto> getR2AppnBudgetActivities(Integer serviceAgencyId) {
		log.trace("getR2AppnBudgetActivities- serviceAgencyId: "+serviceAgencyId);
		UserDto userDto = validateUser();
		
		if (null == userDto) {
			log.warn("getServiceAgencies- user not authenticated ldapId: " 
					+ ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME));
			return null; 
		}
		
		List<R2ServiceAgencyAppnActivityEntity> r2AppBudgetActivityEntities = 
				r2ServiceAgencyAppnActivityRepository.findByServiceAgencyId(serviceAgencyId);
		
		return processR2AppnBudgetActivityEntities(r2AppBudgetActivityEntities);
	}
	
	@Override
	public List<ServiceAgencyProjectionDto> getP40ServiceAgencies() {
		return null; //TODO: build out getP40ServiceAgencies method.
	}
	
	@Override
	public List<AppnBudgetActivityProjectionDto> getP40AppnBudgetActivities(Integer serviceAgencyId){
		return null; //TODO: build out getP40AppnBudgetActivities method.
	}
	
	@Override
	public List<PeSuffixDto> getPeSuffixFromServiceAgencyId(Integer serviceAgencyId){
		List<PeSuffixDto> peSuffixDtos = new ArrayList<>();
		List<PeSuffixEntity> suffixEntities = peSuffixRepo.findByServiceAgencyId(serviceAgencyId);
		for (PeSuffixEntity peSuffixEntity : suffixEntities) {
			PeSuffixDto peSuffixDto = peSuffixProjectionTransformer.transform(peSuffixEntity);
			peSuffixDtos.add(peSuffixDto);
		}
		return peSuffixDtos;
	}
	
	private UserDto validateUser() {
		return userEntityService.findUserDtoByUserLdapId(ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME));
	}
	
	private Map<String, String> populateAuthMap(UserDto userDto) {
		log.trace("populateAuthMap- userDto: " + userDto.toString());
		Map<String, String> map = new HashMap<>();
		if (!userDto.getRole().equals(APP_MGR)) {
			
			map = userDto.getServiceAgencies().stream().
					collect(Collectors.toMap(ServiceAgencyDto::getCode, ServiceAgencyDto::getCode));
		}
		return map;
	}
	
	private List<ServiceAgencyProjectionDto> processR2ServiceAgencyEntities(List<R2ServiceAgencyEntity> r2ServiceAgencies, UserDto userDto, 
			Map<String, String> authorizedAgencyMap) {
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
	
	private List<AppropriationDto> processR2AppnBudgetActivityEntities(List<R2ServiceAgencyAppnActivityEntity> r2ServiceAgencyAppnActivities){
		log.trace("processR2AppnBudgetActivityEntities- ");
		Map<Integer, AppropriationDto> appropiationBudgetActivityMap = new HashMap<>(); 
		
		for (R2ServiceAgencyAppnActivityEntity r2ServiceAgencyAppnActivity : r2ServiceAgencyAppnActivities) {
			AppnBudgetActivityProjectionDto appnBudgetActivityProjectionDto = 
					r2AppnBudgetActivityProjectionTransformer.transform(r2ServiceAgencyAppnActivity);
			
			Integer appropriationId = appnBudgetActivityProjectionDto.getAppropriationId();
			AppropriationDto appropriationDto  = appropiationBudgetActivityMap.get(appropriationId);
			if (null == appropriationDto) {
				AppropriationDto newAppropriationDto = new AppropriationDto();
				newAppropriationDto.setId(appropriationId);
				newAppropriationDto.setCode(appnBudgetActivityProjectionDto.getAppnCode());
				newAppropriationDto.setName(appnBudgetActivityProjectionDto.getAppnName());
				if (null != appnBudgetActivityProjectionDto.getBudgetActivityId()) {
					R2BudgetActivityDto budgetActivityDto = new R2BudgetActivityDto();
					budgetActivityDto.setId(appnBudgetActivityProjectionDto.getBudgetActivityId());
					budgetActivityDto.setNum(appnBudgetActivityProjectionDto.getBudgetActivityNum());
					budgetActivityDto.setName(appnBudgetActivityProjectionDto.getBudgetActivityTitle());
					newAppropriationDto.getBudgetActivities().add(budgetActivityDto);
				}
				appropiationBudgetActivityMap.put(appropriationId,newAppropriationDto);
			}
			else {
				if (null != appnBudgetActivityProjectionDto.getBudgetActivityId()) {
					
					Integer targetBudgetAcdtivityId = appnBudgetActivityProjectionDto.getBudgetActivityId();
					boolean duplicateBudgetActivity = false;
					
					for(R2BudgetActivityDto r2BudgetActivityDto : appropriationDto.getBudgetActivities()) {
						if (r2BudgetActivityDto.getId().equals(targetBudgetAcdtivityId)) {
							duplicateBudgetActivity = true;
						}
					}
					
					if (!duplicateBudgetActivity) {
						R2BudgetActivityDto budgetActivityDto = new R2BudgetActivityDto();
						budgetActivityDto.setId(appnBudgetActivityProjectionDto.getBudgetActivityId());
						budgetActivityDto.setNum(appnBudgetActivityProjectionDto.getBudgetActivityNum());
						budgetActivityDto.setName(appnBudgetActivityProjectionDto.getBudgetActivityTitle());
						appropriationDto.getBudgetActivities().add(budgetActivityDto);
					}
				}
			}
		}
		return appropiationBudgetActivityMap.values().stream().collect(Collectors.toList());
		
	}

}
