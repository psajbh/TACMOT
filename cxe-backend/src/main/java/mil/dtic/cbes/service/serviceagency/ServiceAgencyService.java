package mil.dtic.cbes.service.serviceagency;

import java.util.List;

import mil.dtic.cbes.model.dto.ServiceAgencyDto;

public interface ServiceAgencyService {
	
	List<ServiceAgencyDto> getServiceAgencies(String ldapId);

}
