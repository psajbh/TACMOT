package mil.dtic.cbes.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.service.impl.PRCPDataServiceImpl.PrcpType;

public interface PRCPDataFileService {
	
	public final String r1filter = "_R1";
	public final String p1filter = "_P1";

	ResponseEntity<byte[]> getPrcpDataFile(PrcpType type) throws IOException;

	HashMap<String, String> getPrcpFileData(String filter) throws FileNotFoundException;

	boolean storeUploadedPRCPUpdateFile(MultipartFile sandboxFile, String fileName, String userName);

	File writeFileToSandbox(MultipartFile upFile);

	UploadedBudgetFile getPrcpDbEntry(PrcpType type) throws FileNotFoundException;

}
