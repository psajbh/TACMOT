package mil.dtic.cbes.service.exhibit.r2;

import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;

public interface CreateR2Service {
	ProgramElementDto createPe(ExhibitInitDto exhibitInitDto);

}
