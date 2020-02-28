package mil.dtic.cbes.service.exhibit;

import java.util.List;

import mil.dtic.cbes.model.dto.appropriation.AppropriationDto;
import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;
import mil.dtic.cbes.model.dto.serviceagency.PeSuffixDto;

public interface ExhibitProjectionService {
	List<ServiceAgencyProjectionDto> getR2ServiceAgencies(String ldapId);
	List<AppropriationDto> getR2AppnBudgetActivities(String ldapId, Integer serviceAgencyId);
	List<ServiceAgencyProjectionDto> getP40ServiceAgencies(String ldapId);
	List<AppnBudgetActivityProjectionDto> getP40AppnBudgetActivities(String ldapId, Integer serviceAgencyId);
	List<PeSuffixDto> getPeSuffixFromServiceAgencyId(Integer serviceAgencyId);
}
