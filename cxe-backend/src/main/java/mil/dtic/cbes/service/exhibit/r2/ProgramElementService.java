package mil.dtic.cbes.service.exhibit.r2;

import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;
import mil.dtic.cbes.utils.exceptions.service.exhibit.r2.ProgramElementServiceException;

public interface ProgramElementService {
	ProgramElementDto createPe(ExhibitInitDto exhibitInitDto) throws ProgramElementServiceException;

}
