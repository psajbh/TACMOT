package mil.dtic.cbes.controllers.prcp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.PRCPP1DataTableDTO;
import mil.dtic.cbes.model.dto.PRCPR1DataTableDTO;
import mil.dtic.cbes.model.enums.PRCPType;
import mil.dtic.cbes.service.impl.user.UserEntityServiceImpl;
import mil.dtic.cbes.service.prcp.PRCPDataService;
import mil.dtic.cbes.service.prcp.PRCPFileService;

@RestController
public class PrcpDataController extends BaseRestController {
	
	private PRCPDataService prcpDataService;

	private PRCPFileService prcpFileService;
	
	private UserEntityServiceImpl userEntityService;
	
	@Autowired
	public PrcpDataController(PRCPDataService prcpDataService, PRCPFileService prcpDataFileService, UserEntityServiceImpl userEntityService) {
		this.prcpDataService = prcpDataService;
		this.prcpFileService = prcpDataFileService;
		this.userEntityService = userEntityService;
	}

	// TODO: Filter R1 data by permission, add PreAuthorize
	@GetMapping("prcp/getR1data")
		public List<PRCPR1DataTableDTO> getR1Data() {
			return prcpDataService.getR1Data(userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
	}

	// TODO: Filter P1 data by permission, add PreAuthorize
	@GetMapping("prcp/getP1data")
	public List<PRCPP1DataTableDTO> getP1Data(){
		return prcpDataService.getP1Data(userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
	}

	// TODO: Filter R1 data by permission, add PreAuthorize
	@GetMapping("prcp/getR1fileData")
	public ResponseEntity<Object> getR1fileData() {
		Map<String, String> result = prcpFileService.getPrcpFileData(PRCPType.R1);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	// TODO: Filter P1 data by permission, add PreAuthorize
	@GetMapping("prcp/getP1fileData")
	public ResponseEntity<Object> getP1fileData() {
		Map<String, String> result = prcpFileService.getPrcpFileData(PRCPType.P1);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	// TODO: add permission control
	@PostMapping("prcp/addPrcpFile")
	public ResponseEntity<Object> addPRCPFile(@RequestParam MultipartFile uploadFile) {
		int result = prcpFileService.addPrcpFile(uploadFile, userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@DeleteMapping("prcp/deleteR1data")
	public void deleteR1data() {
		prcpDataService.deletePRCPData(PRCPType.R1, userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
	}

	@DeleteMapping("prcp/deleteP1data")
	public void deleteP1data() {
		prcpDataService.deletePRCPData(PRCPType.P1, userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
	}

	@GetMapping("prcp/getR1file")
	public ResponseEntity<byte[]> getPrcpR1file() {
		return prcpFileService.getPrcpDataFile(PRCPType.R1, userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
	}

	@GetMapping("prcp/getP1file")
	public ResponseEntity<byte[]> getPrcpP1file() {
		return prcpFileService.getPrcpDataFile(PRCPType.P1, userEntityService.findUserDtoByUserLdapId(getCredential().getLdapId()));
	}
}
