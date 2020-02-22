//package mil.dtic.cbes.service.impl;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.ContentDisposition;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import mil.dtic.cbes.model.UploadedBudgetFile;
//import mil.dtic.cbes.model.dto.DownloadDto;
//import mil.dtic.cbes.model.enums.UploadFileType;
//import mil.dtic.cbes.repositories.UploadedBudgetFileRepository;
//import mil.dtic.cbes.service.BudgetFileUploadService;
//import mil.dtic.cbes.service.config.ConfigurationService;
//import mil.dtic.cbes.utils.virus_scan.VirusScanManager;
//import mil.dtic.cbes.utils.Constants;
//import mil.dtic.cbes.utils.exceptions.rest.BudgetFileProcessingException;
//import mil.dtic.cbes.utils.transform.Transformer;
//
//import mil.dtic.cbes.utils.virus_scan.VirusScanException;
//import mil.dtic.vscan.PartitionException;
//import mil.dtic.vscan.SizeExceededException;
//
//@Service
//public class BudgetFileUploadServiceImpl implements BudgetFileUploadService {
//	@Autowired
//	private UploadedBudgetFileRepository budgetFileRepo;
//
//	@Autowired
//	private VirusScanManager virusScanManager;
//
//	@Autowired
//	private ConfigurationService configService;
//	
//	private MessageSource messageSource;
//	
//	@Autowired
//	private Transformer downloadTransformer;
//
//	// TODO: get max number of files from config
//	protected Integer maxNumberOfFiles;
//
//	private String uploadDirectoryLocation;
//
//	private static final Logger log = LoggerFactory.getLogger(BudgetFileUploadServiceImpl.class);
//
//	/*
//	 * Saves list of files from client to the server and adds database entries to
//	 * donwloads table
//	 */
//	@Override
//	public void saveFiles(List<MultipartFile> filesToUpload, List<String> descriptions, String username) {
//		for (int i = 0; i < filesToUpload.size(); i++) {
//			MultipartFile fileToUpload = filesToUpload.get(i);
//			String fileDescription = descriptions.get(i).trim();
//			saveFile(fileToUpload, fileToUpload.getOriginalFilename(), username, fileDescription, UploadFileType.SYS);
//		}
//	}
//
//	/*
//	 * Save individual file to server after virus scan, add database entry to
//	 * Downloads table
//	 */
//	@Override
//	public void saveFile(MultipartFile fileToUpload, String fileName, String userName, String description, UploadFileType type) {
//		
//		File uploadDirectory = new File(configService.getUploadStoragePath() + File.separator);
//		if (!uploadDirectory.exists()) {
//			uploadDirectory.mkdir();
//		}
//		File newFile = new File(uploadDirectory.getAbsolutePath() + File.separator + type + File.separator + generateFileName(fileName, userName, type));
//		try {
//			newFile.createNewFile();
//		} catch (IOException e) {
//			throw new BudgetFileProcessingException(BudgetFileProcessingException.FILE_CREATION);
//		}
//
//		try {
//			if (virusScanFile(fileToUpload.getBytes())) {
//				log.error(fileToUpload.getOriginalFilename() + ":" + description + ":Upload failure");
//			} else {
//				storeFile(fileToUpload, newFile, fileName, userName, description, type);
//				removeOldFiles();
//				log.info(fileToUpload.getOriginalFilename() + " - Upload success");
//			}
//		} catch (IOException e) {
//			throw new BudgetFileProcessingException(BudgetFileProcessingException.FILE_OPEN_ERROR);
//		}
//	}
//
//	/*
//	 * return response to client to allow user to download file from server
//	 */
//	@Override
//	public ResponseEntity<byte[]> downloadFile(Integer id) {
//		UploadedBudgetFile download = budgetFileRepo.findById(id).orElse(null);
//
//		if (download == null || StringUtils.isEmpty(download.getName())) {
//			throw new BudgetFileProcessingException(BudgetFileProcessingException.UPLOAD_NOT_FOUND);
//		}
//
//		final HttpHeaders headers = new HttpHeaders();
//		headers.setContentDisposition(ContentDisposition.builder("inline").filename(download.getName()).build());
//
//		File fileOnServer = new File(download.getFileURI());
//		byte[] newBytes;
//		try {
//			newBytes = Files.readAllBytes(fileOnServer.toPath());
//		} catch (IOException e) {
//			throw new BudgetFileProcessingException(BudgetFileProcessingException.FILE_NOT_FOUND);
//		}
//		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(newBytes, headers, HttpStatus.OK);
//
//		return response;
//	}
//
//	@Override
//	public List<DownloadDto> getAllFiles() {
//		return getAllFiles(false);
//	}
//
//	@Override
//	public List<DownloadDto> getAllFiles(Boolean isRfr) {
//		if (isRfr == null) {
//			isRfr = false;
//		}
//
//		List<DownloadDto> files = getRfrFiles(isRfr);
//		return files;
//	}
//
//	/*
//	 * Generate filename for file to store on server
//	 */
//	protected String generateFileName(String fileName, String userName, UploadFileType type) {
//		String extension = FilenameUtils.EXTENSION_SEPARATOR_STR + FilenameUtils.getExtension(fileName).toLowerCase();
//		StringBuffer newFileName = new StringBuffer();
//		newFileName.append(type + "-");
//		newFileName.append(userName);
//		newFileName.append("-");
//		Date date = new Date();
//		long time = date.getTime();
//		newFileName.append(time);
//		newFileName.append(extension);
//		return newFileName.toString();
//	}
//
//	/*
//	 * Save file to server and create new database entry in downloads table
//	 */
//	protected void storeFile(MultipartFile fileToUpload, File file, String fileName, String userName,
//			String description, UploadFileType type) {
//		try {
//			fileToUpload.transferTo(file);
//		} catch (IllegalStateException | IOException e) {
//			throw new BudgetFileProcessingException(BudgetFileProcessingException.SAVE_ERROR);
//		}
//		UploadedBudgetFile sysFile = new UploadedBudgetFile();
//		sysFile.setUrl(file.toURI().toString());
//		sysFile.setName(fileName);
//		sysFile.setCreatedBy(userName);
//		sysFile.setDateCreated(new Date());
//		sysFile.setDescription(description);
//		sysFile.setType(type.getVal());
//		budgetFileRepo.save(sysFile);
//	}
//
//	/**
//	 * Scans the byte array which contains a PDF for viruses. If virus is detected,
//	 * deletes the contents of the array.
//	 * 
//	 * @param fileToScan
//	 * @return true if the file contains a virus, otherwise returns false.
//	 * 
//	 */
//	private boolean virusScanFile(byte[] fileToScan) {
//		try {
//			virusScanManager.scanFile(fileToScan, "tempfile");
//			log.info("Virus scan completed successfully - no threats detected.");
//		} catch (FileNotFoundException | VirusScanException | SizeExceededException | PartitionException e) {
//			log.warn("Virus scan found a potential threat. Removing...");
//			byte nullByte = 0;
//			Arrays.fill(fileToScan, nullByte);
//			log.warn("Threat removed.");
//			return true;
//		}
//		return false;
//	}
//
//	/*
//	 * Delete file from server and database entry in downlaods table
//	 */
//	public void deleteFile(Integer id) {
//		Optional<UploadedBudgetFile> budgetFile = budgetFileRepo.findById(id);
//		File file = new File(budgetFile.get().getFileURI());
//		if (file != null) {
//			file.delete();
//		}
//		budgetFileRepo.deleteById(id);
//	}
//
//	/*
//	 * remove old files from database if limit is reached
//	 */
//	private void removeOldFiles() {
//		if (maxNumberOfFiles != null) {
//			List<UploadedBudgetFile> files = budgetFileRepo.findAllByOrderByDateCreatedDesc();
//			if (files.size() > maxNumberOfFiles) {
//				List<UploadedBudgetFile> filesToRemove = files.subList(maxNumberOfFiles / 2, files.size());
//				for (UploadedBudgetFile uploadedFile : filesToRemove) {
//					File file = new File(uploadedFile.getFileURI());
//					file.delete();
//					budgetFileRepo.delete(uploadedFile);
//				}
//			}
//		}
//	}
//
//	/**
//	 * process download files for R2Analsyst or Other users.
//	 * 
//	 * @param rfr
//	 *            - Boolean
//	 * @return files - List<F>
//	 */
//	private List<DownloadDto> getRfrFiles(Boolean rfr) {
//		List<DownloadDto> downloadDtoAccumulator = new ArrayList<>();
//		List<UploadedBudgetFile> files = budgetFileRepo.findAllByOrderByDateCreatedDesc();
//		
//		if (files.size() == 0) {
//			return downloadDtoAccumulator;
//		}
//
//		List<UploadedBudgetFile> rfrFiles = new ArrayList<>();
//		List<UploadedBudgetFile> nonRfrFiles = new ArrayList<>();
//
//		for (UploadedBudgetFile file : files) {
//			if (isRfr(file)) {
//				rfrFiles.add(file);
//			} else {
//				nonRfrFiles.add(file);
//			}
//		}
//
//		if (rfr && rfrFiles.size() > 0) {
//			return checkFilesExist(rfrFiles, downloadDtoAccumulator);
//		} else {
//			return checkFilesExist(nonRfrFiles, downloadDtoAccumulator);
//		}
//
//	}
//
//	/**
//	 * Inspect file to determine if it is RFR type.
//	 * 
//	 * @param F
//	 *            - file
//	 * @return boolean -
//	 */
//	private boolean isRfr(UploadedBudgetFile file) {
//		boolean rfr = false;
//		char symbol1;
//		char symbol2;
//		char[] cArray;
//
//		if (null == file.getDescription()) {
//			return false;
//		} else {
//			cArray = file.getDescription().toCharArray();
//		}
//
//		if (cArray.length > 1) {
//			if (cArray.length == 2) {
//				symbol1 = cArray[0];
//				symbol2 = cArray[1];
//			} else {
//				symbol1 = cArray[cArray.length - 2];
//				symbol2 = cArray[cArray.length - 1];
//			}
//
//			if (BudgetFileUploadService.RFR_DECRYPT_VALUE == symbol1 + symbol2) {
//				rfr = true;
//			}
//		}
//
//		return rfr;
//
//	}
//
//	private List<DownloadDto> checkFilesExist(List<UploadedBudgetFile> uploadedBudgetFiles, List<DownloadDto> downloadDtoAccumulator) {
//		for (UploadedBudgetFile uploadedBudgetFile : uploadedBudgetFiles) {
//			File file = new File(uploadedBudgetFile.getFileURI());
//			uploadedBudgetFile.setAvailableOnFilesystem(file.exists());
//			if (file.exists()) {
//				uploadedBudgetFile.setSize(file.length());
//				downloadDtoAccumulator.add((DownloadDto) downloadTransformer.transform(uploadedBudgetFile));
//			}
//		}
//		return downloadDtoAccumulator;
//	}
//
//	public int getMaxNumberOfFiles() {
//		return maxNumberOfFiles;
//	}
//
//	public void setMaxNumberOfFiles(int maxNumberOfFiles) {
//		this.maxNumberOfFiles = maxNumberOfFiles;
//	}
//
//	public String getUploadDirectoryLocation() {
//		return uploadDirectoryLocation;
//	}
//
//	public void setUploadDirectoryLocation(String uploadDirectoryLocation) {
//		this.uploadDirectoryLocation = uploadDirectoryLocation;
//	}
//
//}
