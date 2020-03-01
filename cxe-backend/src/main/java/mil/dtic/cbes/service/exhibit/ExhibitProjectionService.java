package mil.dtic.cbes.service.exhibit;

import java.util.List;

import mil.dtic.cbes.model.dto.core.AppropriationDto;
import mil.dtic.cbes.model.dto.core.PeSuffixDto;
import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;

public interface ExhibitProjectionService {
	List<ServiceAgencyProjectionDto> getR2ServiceAgencies();
	List<AppropriationDto> getR2AppnBudgetActivities(Integer serviceAgencyId);
	List<ServiceAgencyProjectionDto> getP40ServiceAgencies();
	List<AppnBudgetActivityProjectionDto> getP40AppnBudgetActivities(Integer serviceAgencyId);
	List<PeSuffixDto> getPeSuffixFromServiceAgencyId(Integer serviceAgencyId);
}
