package mil.dtic.cbes.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.model.dto.DownloadDto;
import mil.dtic.cbes.model.enums.UploadFileType;

public interface BudgetFileUploadService {
	public static int RFR_DECRYPT_VALUE = 22;
	 
	void saveFiles(List<MultipartFile> filesToUpload, List<String> descriptions, String username);

	void deleteFile(Integer id);
	
	List<DownloadDto> getAllFiles();

	List<DownloadDto> getAllFiles(Boolean isRfr);

	ResponseEntity<byte[]> downloadFile(Integer id);

	void saveFile(MultipartFile fileToUpload, String fileName, String userName, String description, UploadFileType type);
	
}
