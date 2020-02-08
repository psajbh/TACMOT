package mil.dtic.cbes.utils.processors;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.model.UploadedBudgetFile;
import mil.dtic.cbes.model.enums.PRCPType;
import mil.dtic.cbes.repositories.UploadedBudgetFileRepository;
import mil.dtic.cbes.service.BudgetFileUploadService;
import mil.dtic.cbes.service.ValidationException;
import mil.dtic.cbes.service.config.ConfigurationService;
import mil.dtic.cbes.utils.exceptions.rest.PRCPFileProcessingException;

@Component
public class PRCPFileProcessor {

	private static final Logger log = LoggerFactory.getLogger(PRCPFileProcessor.class);
	
	private BudgetFileUploadService bfuService;
	
	private MessageSource messageSource;
	
	private ConfigurationService configService;
	
	private UploadedBudgetFileRepository budgetFileRepo; 
	
	@Autowired
	public PRCPFileProcessor(BudgetFileUploadService bfuService, MessageSource messageSource,
			ConfigurationService configService, UploadedBudgetFileRepository budgetFileRepo) {
		this.bfuService = bfuService;
		this.messageSource = messageSource;
		this.configService = configService;
		this.budgetFileRepo = budgetFileRepo;
	}

	public UploadedBudgetFile getPrcpDataFor(PRCPType type) {
		List<UploadedBudgetFile> allFiles = getPRCPFiles();
		
		UploadedBudgetFile prcpFile = new UploadedBudgetFile();;

		// find all excel files matching the prcp string filter
		List<UploadedBudgetFile> allMatchingFiles = new ArrayList<UploadedBudgetFile>();
		
		for (UploadedBudgetFile aFile : allFiles) {
			String name = aFile.getName();
			if (!aFile.isDelete()) {
				if (name != null && name.contains(type.getFilter())) {
					allMatchingFiles.add(aFile);
				}
			}
		}
		
		log.debug("Found " + allMatchingFiles.size() + " " + type.getFilter() + " files");
		
		if (allMatchingFiles.isEmpty()) {
			throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.processing.not.found",
					null, StringUtils.EMPTY, Locale.US));
		}

		// select the most recently uploaded
		Comparator<UploadedBudgetFile> comparator = new Comparator<UploadedBudgetFile>() {
			public int compare(UploadedBudgetFile o1, UploadedBudgetFile o2) {
				return o2.getDateCreated().compareTo(o1.getDateCreated());
			}
		};
		
		Collections.sort(allMatchingFiles, comparator);
		
		prcpFile = allMatchingFiles.get(0);
		
		log.debug("Most recent prcp " + type.getFilter() + " file: " + prcpFile.getUrl() + " uploaded on "
				+ prcpFile.getDateString());

		return prcpFile;
	}
	
	public boolean storeUploadedPRCPUpdateFile(MultipartFile sandboxFile, String fileName, String userName) {
		boolean result = true;
		
		if(null != sandboxFile && StringUtils.isNoneBlank(fileName, userName)) {
			try {
				bfuService.saveFile(sandboxFile, fileName, userName, "User uploaded PRCP update", "PRCP");
			} 
			catch (ValidationException | IOException e) {
				throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.prcp.upload.failure",
						null, StringUtils.EMPTY, Locale.US));
			}
		}

		return result;
	}
	
	public File writeFileToSandbox(MultipartFile upFile) {
		File sandboxDir = new File(configService.getSandboxPath());
		
		File sandboxFile = null;
		
		if (null != upFile) {
			InputStream is = null;
			
			try {
				// move file to sandbox
				String sfx = FilenameUtils.getExtension(upFile.getOriginalFilename());
				
				if (sfx.length() > 0) {
					sfx = "." + sfx;
				}
				else {
					sfx = null; // .tmp
				}
				
				sandboxFile = File.createTempFile("BudgetUpload", sfx, sandboxDir);

				// write to temp file in sandbox
				is = upFile.getInputStream();
				
				FileUtils.copyInputStreamToFile(is, sandboxFile);

			} 
			catch (IOException e) {
				throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.sandbox.upload.failure",
						null, StringUtils.EMPTY, Locale.US));
			}
			finally {
				IOUtils.closeQuietly(is);
			}
		}
		
		return sandboxFile;
	}

	public ResponseEntity<byte[]> getPrcpFile(UploadedBudgetFile prcpFile){
		ResponseEntity<byte[]> response = null;

		if (prcpFile != null && StringUtils.isNotEmpty(prcpFile.getName())) {
			final HttpHeaders headers = new HttpHeaders();
			
			headers.setContentDisposition(ContentDisposition.builder("inline").filename(prcpFile.getName()).build());

			byte[] newBytes;

			try {
				newBytes = Files.readAllBytes(Paths.get(prcpFile.getFileURI()));
			} 
			catch (IOException e) {
				throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.processing.download.failure",
						null, StringUtils.EMPTY, Locale.US));
			}

			response = new ResponseEntity<byte[]>(newBytes, headers, HttpStatus.OK);
		}
		
		return response;
	}
	
	//Temporary way of retrieving PRCP files
	private List<UploadedBudgetFile> getPRCPFiles(){
		return budgetFileRepo.findAllByType("PRCP");
	}
}
