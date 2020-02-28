//package mil.dtic.cbes.controllers.downloads;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import mil.dtic.cbes.controllers.BaseRestController;
//import mil.dtic.cbes.model.dto.BudgetFileUploadDTO;
//import mil.dtic.cbes.repositories.UploadedBudgetFileRepository;
//import mil.dtic.cbes.service.BudgetFileUploadService;
//
//@RestController
//public class DownloadsController extends BaseRestController{
//	@Autowired
//	private BudgetFileUploadService bfuService;
//	
//	@Autowired
//	private UploadedBudgetFileRepository downloadsRepo;
//	
//	@GetMapping("/downloads/downloadList")
//	public ResponseEntity<List<BudgetFileUploadDTO>> getDownloadsList(@RequestParam Boolean isRfr) {
//		return processDownload(isRfr);
//	}
//	
//	@PostMapping("/downloads/delete") 
//	public void deleteFile(@RequestBody Integer id) {
//		bfuService.deleteFile(id);
//	}
//	
//	@PostMapping("/downloads/upload")
//	public void uploadFile(@RequestParam List<MultipartFile> file, @RequestParam List<String> descriptions) {
//		bfuService.saveFiles(file, descriptions, getCredential().getLdapId());
//	}
//	
//	@GetMapping("/downloads/{id}")
//	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id) {
//		return bfuService.downloadFile(id);
//	}
//	
//	private ResponseEntity<List<BudgetFileUploadDTO>> processDownload(Boolean isRfr) {
//		List<BudgetFileUploadDTO> downloadDtos = bfuService.getAllFiles(isRfr);
//		
//		if (null != downloadDtos) {
//			return ResponseEntity.status(HttpStatus.OK).body(downloadDtos);
//		}
//		else {
//			return ResponseEntity.status(500).body(null);
//		}
//	}
//}
