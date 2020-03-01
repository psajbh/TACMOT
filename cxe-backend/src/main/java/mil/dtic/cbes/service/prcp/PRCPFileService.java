package mil.dtic.cbes.service.prcp;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.enums.PRCPType;

public interface PRCPFileService {

	ResponseEntity<byte[]> getPrcpDataFile(PRCPType type, UserDto userDto);

	HashMap<String, String> getPrcpFileData(PRCPType type);
	
	int addPrcpFile(MultipartFile uploadFile, UserDto userDto);
}
