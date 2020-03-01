package mil.dtic.cbes.service.core;

import java.util.List;

import mil.dtic.cbes.model.dto.core.ServiceAgencyDto;

public interface ServiceAgencyService {
	
	List<ServiceAgencyDto> getServiceAgencies(String ldapId);

}
