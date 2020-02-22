package mil.dtic.cbes.service.serviceagency;

import java.util.List;

import mil.dtic.cbes.model.dto.serviceagency.R2ServiceAgencyDto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;

public interface ServiceAgencyService {
	
	List<ServiceAgencyDto> getServiceAgencies(String ldapId);
	List<R2ServiceAgencyDto> getR2ServiceAgencies(String ldapId);

}
