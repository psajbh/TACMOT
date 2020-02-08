package mil.dtic.cbes.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.enums.PRCPType;
import mil.dtic.cbes.model.enums.Role;
import mil.dtic.cbes.service.prcp.PRCPFileService;
import mil.dtic.cbes.utils.exceptions.rest.PRCPFileProcessingException;
import mil.dtic.cbes.utils.exceptions.rest.user.NotAuthorizedException;
import mil.dtic.cbes.utils.processors.PRCPDataProcessor;
import mil.dtic.cbes.utils.processors.PRCPFileProcessor;

@Service
public class PRCPFileServiceImpl implements PRCPFileService {
	private static final Logger log = LoggerFactory.getLogger(PRCPFileServiceImpl.class);
	
	private PRCPFileProcessor prcpFileProcessor;
	
	private MessageSource messageSource;
	
	private PRCPDataProcessor prcpDataProcessor;
	
	@Autowired
	public PRCPFileServiceImpl(PRCPFileProcessor prcpFileProcessor, MessageSource messageSource, PRCPDataProcessor prcpDataProcessor) {
		this.prcpFileProcessor =prcpFileProcessor;
		this.messageSource = messageSource;
		this.prcpDataProcessor = prcpDataProcessor;
	}

	@Override
	public ResponseEntity<byte[]> getPrcpDataFile(PRCPType type, UserDto userDto) {
		log.info("Retrieving data file for PRCP type {}", type.getVal());
		
		if(Role.GROUP_R2_APP_ADMIN != Role.getByName(userDto.getRole())) {
			throw new NotAuthorizedException(
					messageSource.getMessage("prcp.authorization.failed.download.prcpFile", new String[] {type.getVal()}, StringUtils.EMPTY, Locale.US));
		}
		
		ResponseEntity<byte[]> response = null;
		
		UploadedBudgetFile uploadedBudgetFile = prcpFileProcessor.getPrcpDataFor(type);
		
		response = prcpFileProcessor.getPrcpFile(uploadedBudgetFile);
		
		if(null == response) {
			throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.processing.download.failure",
					null, StringUtils.EMPTY, Locale.US));
		}
		
		return response;
	}

	@Override
	public HashMap<String, String> getPrcpFileData(PRCPType type){
		UploadedBudgetFile prcpFile = prcpFileProcessor.getPrcpDataFor(type);
		
		HashMap<String, String> response = new HashMap<String, String>();
		
		DateFormat format = DateFormat.getDateTimeInstance();
		
		response.put("filename", prcpFile.getName());
		response.put("timestamp", format.format(prcpFile.getDateCreated()));
		
		return response;
	}
	
	/*
	 * Upload PRCP file to downloads and database in database
	 *
	 * @return prcp_type let client know which data table to reload
	 */
	@Override
	public int addPrcpFile(MultipartFile uploadFile, UserDto userDto) {
		if (Role.GROUP_R2_APP_ADMIN != Role.getByName(userDto.getRole())) {
			log.warn("User role {} not permitted to upload PRCP file", userDto.getRole());
			
			throw new NotAuthorizedException(
					messageSource.getMessage("prcp.authorization.failed.add.prcpFile", null, StringUtils.EMPTY, Locale.US));
		}
		
		File sandboxFile = null;
		
		int prcp_type = 0;
		
		sandboxFile = prcpFileProcessor.writeFileToSandbox(uploadFile);
				
		if (sandboxFile == null) {
			throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.prcp.upload.failure",
					null, StringUtils.EMPTY, Locale.US));
		}
		
		else 
		{	
			String fname = uploadFile.getOriginalFilename();
			
			if (fname.substring(5, fname.indexOf(".")).equalsIgnoreCase(PRCPType.P1.getVal())) {
				prcp_type = PRCPType.P1.getType();
			} 
			else if (fname.substring(5, fname.indexOf(".")).equalsIgnoreCase(PRCPType.R1.getVal())) {
				prcp_type = PRCPType.R1.getType();
			}
	
			int budget_year = Integer.parseInt(fname.substring((fname.lastIndexOf(".") - 7), (fname.lastIndexOf(".") - 3)));
	
			prcpDataProcessor.process(prcp_type, sandboxFile, budget_year);
	
			// record the upload in long term storage
	
			if (!prcpFileProcessor.storeUploadedPRCPUpdateFile(uploadFile, uploadFile.getOriginalFilename(), "prcpUploadUser")) {
				throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.prcp.upload.failure",
						null, StringUtils.EMPTY, Locale.US));
			}
		
			sandboxFile.delete();
		}

		return prcp_type;
	}

}
