package mil.dtic.cbes.controllers.exhibit.r2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.exhibit.ExhibitInitDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;
import mil.dtic.cbes.service.exhibit.r2.ProgramElementService;
import mil.dtic.cbes.utils.exceptions.CxeException;
import mil.dtic.cbes.utils.exceptions.security.InvalidCredentialException;

@RestController
public class ProgramElementController extends BaseRestController{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ProgramElementService programElementService;
	
	public ProgramElementController(ProgramElementService programElementService) {
		this.programElementService = programElementService;
	}
	
	@PostMapping(value = "/exhibit/create/pe", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProgramElementDto> createProgramElement(@RequestBody ExhibitInitDto exhibitInitDto){
		log.trace("createProgramElement-");
		
//		if (null == getLdapId()) {
//			log.error("createProgramElement- ldapId is null");
//			throw new InvalidCredentialException(InvalidCredentialException.INVALID_USER_CREDENTIAL_MSG);
//		}
		
//		exhibitInitDto.setUserId(getCredential().getUserId());
		return ResponseEntity.status(HttpStatus.OK).body(programElementService.createPe(exhibitInitDto));
	}

	 @ExceptionHandler(CxeException.class)
	 public final ResponseEntity<Exception> handleAllExceptions(CxeException ex) {
		 	ex.setStackTrace(null);
	        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	 }

}
