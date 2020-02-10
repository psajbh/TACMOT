//package mil.dtic.cbes.service;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.multipart.MultipartFile;
//
//import mil.dtic.cbes.model.UploadedBudgetFile;
//import mil.dtic.cbes.model.dto.DownloadDto;
//
//public interface BudgetFileUploadService {
//	public static int RFR_DECRYPT_VALUE = 22;
//	 
//	void saveFiles(List<MultipartFile> filesToUpload, List<String> descriptions, String username) throws IOException;
//
//	void deleteFile(Integer id);
//	
//	List<DownloadDto> getAllFiles();
//
//	List<DownloadDto> getAllFiles(Boolean isRfr);
//
//	ResponseEntity<byte[]> downloadFile(Integer id) throws IOException;
//
//	void saveFile(MultipartFile fileToUpload, String fileName, String userName, String description, String type) throws IOException;
//	
//}
