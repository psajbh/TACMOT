package mil.dtic.cbes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.PRCPP1DataTableDTO;
import mil.dtic.cbes.model.dto.PRCPR1DataTableDTO;
import mil.dtic.cbes.model.dto.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.entities.P1DataEntity;
import mil.dtic.cbes.model.entities.R1DataEntity;
import mil.dtic.cbes.model.enums.PRCPType;
import mil.dtic.cbes.model.enums.Role;
import mil.dtic.cbes.repositories.P1DataRepository;
import mil.dtic.cbes.repositories.R1DataRepository;
import mil.dtic.cbes.service.BudgetFileUploadService;
import mil.dtic.cbes.service.prcp.PRCPDataService;
import mil.dtic.cbes.service.prcp.PRCPFileService;
import mil.dtic.cbes.utils.exceptions.rest.PRCPDataProcessingException;
import mil.dtic.cbes.utils.exceptions.rest.PRCPUnknownTypeException;
import mil.dtic.cbes.utils.exceptions.rest.user.NotAuthorizedException;
import mil.dtic.cbes.utils.exceptions.service.TransformerException;
import mil.dtic.cbes.utils.processors.PRCPFileProcessor;
import mil.dtic.cbes.utils.transform.impl.PRCPP1DataTableTransformer;
import mil.dtic.cbes.utils.transform.impl.PRCPR1DataTableTransformer;

@Service
public class PRCPDataServiceImpl implements PRCPDataService {

	private static final Logger log = LoggerFactory.getLogger(PRCPDataServiceImpl.class);

	private BudgetFileUploadService budgetFileUploadService;

	private MessageSource messageSource;

	@Qualifier("PRCPP1DataTableTransformer")
	private PRCPP1DataTableTransformer prcpP1DataTableTransformer;

	@Qualifier("PRCPR1DataTableTransformer")
	private PRCPR1DataTableTransformer prcpR1DataTableTransformer;

	private R1DataRepository r1Repo;

	private P1DataRepository p1Repo;
	
	private PRCPFileProcessor prcpFileProcessor;


	public PRCPDataServiceImpl(BudgetFileUploadService budgetFileUploadService, MessageSource messageSource,
			PRCPP1DataTableTransformer prcpP1DataTableTransformer,
			PRCPR1DataTableTransformer prcpR1DataTableTransformer, R1DataRepository r1Repo, P1DataRepository p1Repo,
			PRCPFileProcessor prcpFileProcessor) {
		this.budgetFileUploadService = budgetFileUploadService;
		this.messageSource = messageSource;
		this.prcpP1DataTableTransformer = prcpP1DataTableTransformer;
		this.prcpR1DataTableTransformer = prcpR1DataTableTransformer;
		this.r1Repo = r1Repo;
		this.p1Repo = p1Repo;
		this.prcpFileProcessor = prcpFileProcessor;
	}

	@Override
	public List<PRCPR1DataTableDTO> getR1Data(UserDto user) {
		// TODO: Filter data based on permission
		
		List<PRCPR1DataTableDTO> result = new ArrayList<>();

		List<R1DataEntity> entityList = new ArrayList<>();
		
		if(Role.GROUP_R2_APP_ADMIN == Role.getByName(user.getRole())) {
			entityList = r1Repo.findAll();
		}
		else {			
			entityList = r1Repo.findAllForYearAndOrganization(2020,
					user.getServiceAgencies().stream().map(s -> s.getCode()).collect(Collectors.toList()));
		}

		if (CollectionUtils.isEmpty(entityList)) {
			throw new PRCPDataProcessingException(messageSource.getMessage("prcp.data.processing.not.found",
					new String[] { PRCPType.R1.getVal() }, StringUtils.EMPTY, Locale.US));
		}

		try {
			for (R1DataEntity r : entityList) {
				result.add((PRCPR1DataTableDTO) prcpR1DataTableTransformer.transform(r));
			}
		} catch (TransformerException e) {
			throw new PRCPDataProcessingException(messageSource.getMessage("prcp.data.processing.failure",
					new String[] { PRCPType.R1.getVal() }, StringUtils.EMPTY, Locale.US));
		}

		return result;
	}

	@Override
	public List<PRCPP1DataTableDTO> getP1Data(UserDto user) {
		// TODO: filter data based on permission

		List<PRCPP1DataTableDTO> result = new ArrayList<>();

		List<P1DataEntity> entityList = p1Repo.findAll();

		if(Role.GROUP_R2_APP_ADMIN == Role.getByName(user.getRole())) {
			entityList = p1Repo.findAll();
		}
		else {			
			entityList = p1Repo.findAllForYearAndOrganization(2020,
					user.getServiceAgencies().stream().map(s -> s.getCode()).collect(Collectors.toList()));
		}
		
		if (CollectionUtils.isEmpty(entityList)) {
			throw new PRCPDataProcessingException(messageSource.getMessage("prcp.data.processing.not.found",
					new String[] { PRCPType.P1.getVal() }, StringUtils.EMPTY, Locale.US));
		}

		try {
			for (P1DataEntity p : entityList) {
				result.add((PRCPP1DataTableDTO) prcpP1DataTableTransformer.transform(p));
			}
		} catch (TransformerException e) {
			throw new PRCPDataProcessingException(messageSource.getMessage("prcp.data.processing.failure",
					new String[] { PRCPType.P1.getVal() }, StringUtils.EMPTY, Locale.US));
		}

		return result;
	}

	@Override
	public void deletePRCPData(PRCPType type, UserDto userDto) {
		if (Role.GROUP_R2_APP_ADMIN != Role.getByName(userDto.getRole())) {
			throw new NotAuthorizedException(
					messageSource.getMessage("prcp.authorization.failed.delete", null, StringUtils.EMPTY, Locale.US));
		}

		switch (type) {
			case R1:
				r1Repo.deleteAll();
				break;
			case P1:
				p1Repo.deleteAll();
				break;
			default:
				throw new PRCPUnknownTypeException(
						messageSource.getMessage("prcp.type.unknown", null, StringUtils.EMPTY, Locale.US));
			}

		budgetFileUploadService.deleteFile(prcpFileProcessor.getPrcpDataFor(type).getId());
	}

}

