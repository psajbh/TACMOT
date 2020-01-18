//package mil.dtic.cbes.controllers.prcp;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import mil.dtic.cbes.controllers.BaseRestController;
//import mil.dtic.cbes.model.P1Data;
//import mil.dtic.cbes.model.R1Data;
//import mil.dtic.cbes.service.PRCPDataFileService;
//import mil.dtic.cbes.service.PRCPDataService;
//import mil.dtic.cbes.service.impl.PRCPDataServiceImpl.PrcpType;
//import mil.dtic.cbes.utils.exceptions.CxeException;
//
//@RestController
//public class PrcpDataController extends BaseRestController {
//	@Autowired
//	private PRCPDataService prcpDataService;
//
//	@Autowired
//	private PRCPDataFileService prcpDataFileService;
//
//	// TODO: Filter R1 data by permission, add PreAuthorize
//	@GetMapping("prcp/getR1data")
//	public List<R1Data> getR1Data() {
//		return prcpDataService.getR1Data();
//	}
//
//	// TODO: Filter P1 data by permission, add PreAuthorize
//	@GetMapping("prcp/getP1data")
//	public List<P1Data> getP1Data() {
//		return prcpDataService.getP1Data();
//	}
//
//	// TODO: Filter R1 data by permission, add PreAuthorize
//	@GetMapping("prcp/getR1fileData")
//	public HashMap<String, String> getR1fileData() throws FileNotFoundException {
//		return prcpDataFileService.getPrcpFileData(PRCPDataFileService.r1filter);
//	}
//
//	// TODO: Filter P1 data by permission, add PreAuthorize
//	@GetMapping("prcp/getP1fileData")
//	public HashMap<String, String> getP1fileData() throws FileNotFoundException {
//		return prcpDataFileService.getPrcpFileData(PRCPDataFileService.p1filter);
//	}
//
//	// TODO: add permission control
//	@PostMapping("prcp/addPrcpFile")
//	public int addPRCPFile(@RequestParam MultipartFile uploadFile) throws CxeException {
//		return prcpDataService.addPrcpFile(uploadFile);
//	}
//
//	@DeleteMapping("prcp/deleteR1data")
//	public void deleteR1data() {
//		prcpDataService.deletePRCPdata(PrcpType.R1);
//	}
//
//	@DeleteMapping("prcp/deleteP1data")
//	public void deleteP1data() {
//		prcpDataService.deletePRCPdata(PrcpType.P1);
//	}
//
//	@GetMapping("prcp/getR1file")
//	public ResponseEntity<byte[]> getPrcpR1file() throws IOException {
//		return prcpDataFileService.getPrcpDataFile(PrcpType.R1);
//	}
//
//	@GetMapping("prcp/getP1file")
//	public ResponseEntity<byte[]> getPrcpP1file() throws IOException {
//		return prcpDataFileService.getPrcpDataFile(PrcpType.P1);
//	}
//}
