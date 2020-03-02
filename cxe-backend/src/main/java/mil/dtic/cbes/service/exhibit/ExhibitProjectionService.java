package mil.dtic.cbes.service.exhibit;

import java.util.List;

import mil.dtic.cbes.model.dto.core.PeSuffixDto;
import mil.dtic.cbes.model.dto.exhibit.AppnBudgetActivityProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.ServiceAgencyProjectionDto;
import mil.dtic.cbes.model.dto.exhibit.r2.R2AppropriationDto;

public interface ExhibitProjectionService {
	List<ServiceAgencyProjectionDto> getR2ServiceAgencies();
	List<R2AppropriationDto> getR2AppnBudgetActivities(Integer serviceAgencyId);
	List<ServiceAgencyProjectionDto> getP40ServiceAgencies();
	List<AppnBudgetActivityProjectionDto> getP40AppnBudgetActivities(Integer serviceAgencyId);
	List<PeSuffixDto> getPeSuffixFromServiceAgencyId(Integer serviceAgencyId);
}
