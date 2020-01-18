//package mil.dtic.cbes.controllers.downloads;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
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
//import mil.dtic.cbes.model.UploadedBudgetFile;
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
//	@GetMapping("/downloads/getDownloadsList")
//	public List<UploadedBudgetFile> getDownloadsList() {
//		return bfuService.getAllFiles();
//	}
//	
//	@PostMapping("/downloads/delete") 
//	public void deleteFile(@RequestBody Integer id) {
//		bfuService.deleteFile(id);
//	}
//	
//	@PostMapping("/downloads/uploadFile")
//	public void uploadFile(@RequestParam List<MultipartFile> file, @RequestParam List<String> descriptions) throws IOException {
//		bfuService.saveFiles(file, descriptions);
//	}
//	
//	@GetMapping("/downloads/downloadFile/{id}")
//	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id) throws IOException {
//		return bfuService.downloadFile(id);
//	}
//}
