//package mil.dtic.cbes.service.impl;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ContentDisposition;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import mil.dtic.cbes.model.UploadedBudgetFile;
//import mil.dtic.cbes.service.BudgetFileUploadService;
//import mil.dtic.cbes.service.PRCPDataFileService;
//import mil.dtic.cbes.service.ValidationException;
//import mil.dtic.cbes.service.config.ConfigurationService;
//import mil.dtic.cbes.service.impl.PRCPDataServiceImpl.PrcpType;
//
//@Service
//public class PRCPDataFileServiceImpl implements PRCPDataFileService {
//	private static final Logger log = LoggerFactory.getLogger(PRCPDataFileServiceImpl.class);
//
//	@Autowired
//	private BudgetFileUploadService bfuService;
//
//	@Autowired
//	private ConfigurationService configService;
//
//	/**
//	 * Discovers all prcp files on the server and selects the most recently
//	 * uploaded.
//	 * 
//	 * @param type - the PRCP type to get
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	@Override
//	public ResponseEntity<byte[]> getPrcpDataFile(PrcpType type) throws IOException {
//		if (type == PrcpType.R1) {
//			return getPrcpFile(getPrcpDbEntry(type));
//		} else if (type == PrcpType.P1) {
//			return getPrcpFile(getPrcpDbEntry(type));
//		}
//		return null;
//	}
//
//	/**
//	 * Gets the database entry for the PRCP file
//	 * 
//	 * @param type - the PRCP type to get
//	 * 
//	 * @return the UploadedBudgetFile object that corresponds to the PRCP file
//	 * @throws FileNotFoundException
//	 */
//	@Override
//	public UploadedBudgetFile getPrcpDbEntry(PrcpType type) throws FileNotFoundException {
//		if (type == PrcpType.R1) {
//			return getPrcpDataFor(r1filter);
//		} else if (type == PrcpType.P1) {
//			return getPrcpDataFor(p1filter);
//		}
//		return null;
//	}
//
//	/**
//	 * Gets the filename and timepstamp of the PRCP file
//	 * 
//	 * @param filter - the filter for grabbing the PRCP file (R1 or P1)
//	 * @return hashmap containing filename and timestamp
//	 * 
//	 * @throws FileNotFoundException
//	 */
//	@Override
//	public HashMap<String, String> getPrcpFileData(String filter) throws FileNotFoundException {
//		UploadedBudgetFile prcpFile = getPrcpDataFor(filter);
//		HashMap<String, String> response = new HashMap<String, String>();
//		DateFormat format = DateFormat.getDateTimeInstance();
//		response.put("filename", prcpFile.getName());
//		response.put("timestamp", format.format(prcpFile.getDateCreated()));
//		return response;
//	}
//
//	/*
//	 * returns bytes of the Prcp file grabbed from the server
//	 */
//	private ResponseEntity<byte[]> getPrcpFile(UploadedBudgetFile prcpFile) throws IOException {
//		if (prcpFile == null || StringUtils.isEmpty(prcpFile.getName())) {
//			return new ResponseEntity<byte[]>(new byte[0], HttpStatus.NOT_FOUND);
//		}
//
//		final HttpHeaders headers = new HttpHeaders();
//		headers.setContentDisposition(ContentDisposition.builder("inline").filename(prcpFile.getName()).build());
//
//		File fileOnServer = new File(prcpFile.getUrl());
//		byte[] newBytes = Files.readAllBytes(fileOnServer.toPath());
//
//		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(newBytes, headers, HttpStatus.OK);
//
//		return response;
//	}
//
//	public UploadedBudgetFile getPrcpDataFor(String filter) throws FileNotFoundException {
//		List<UploadedBudgetFile> allFiles = bfuService.getAllFiles();
//		UploadedBudgetFile prcpFile = null;
//
//		// find all excel files matching the prcp string filter
//		List<UploadedBudgetFile> allMatchingFiles = new ArrayList<UploadedBudgetFile>();
//		for (UploadedBudgetFile aFile : allFiles) {
//			String name = aFile.getName();
//			String type = aFile.getType();
//			if (!aFile.isDelete() && type != null && (type.equalsIgnoreCase("xlsx") || type.equalsIgnoreCase("xls"))) {
//				if (name != null && name.contains(filter)) {
//					allMatchingFiles.add(aFile);
//				}
//			}
//		}
//		log.debug("Found " + allMatchingFiles.size() + " " + filter + " files");
//		if (allMatchingFiles.isEmpty()) {
//			throw new FileNotFoundException("No prcp files found in system upload directory.");
//		}
//
//		// select the most recently uploaded
//		Comparator<UploadedBudgetFile> comparator = new Comparator<UploadedBudgetFile>() {
//			public int compare(UploadedBudgetFile o1, UploadedBudgetFile o2) {
//				return o2.getDateCreated().compareTo(o1.getDateCreated());
//			}
//		};
//		Collections.sort(allMatchingFiles, comparator);
//		prcpFile = allMatchingFiles.get(0);
//		log.debug("Most recent prcp " + filter + " file: " + prcpFile.getUrl() + " uploaded on "
//				+ prcpFile.getDateString());
//
//		return prcpFile;
//	}
//
//	/**
//	 * Store the uploaded prcp update file in long term storage.
//	 * 
//	 * @param upFile
//	 * @return
//	 */
//	@Override
//	public boolean storeUploadedPRCPUpdateFile(MultipartFile sandboxFile, String fileName, String userName) {
//		boolean result = true;
//		try {
//			bfuService.saveFile(sandboxFile, fileName, userName, "User uploaded PRCP update", "PRCP");
//		} catch (ValidationException | IOException e) {
//			result = false;
//			log.error("Unable to store uploaded prcp update file.", e);
//		}
//		return result;
//	}
//
//	/**
//	 * Write a file to our temporary sandbox.
//	 *
//	 * @param upFile
//	 * @return
//	 */
//	@Override
//	public File writeFileToSandbox(MultipartFile upFile) {
//		File sandboxDir = new File(configService.getSandboxPath());
//		File sandboxFile = null;
//		if (upFile != null) {
//			try {
//				// move file to sandbox
//				String sfx = FilenameUtils.getExtension(upFile.getOriginalFilename());
//				if (sfx.length() > 0)
//					sfx = "." + sfx;
//				else
//					sfx = null; // .tmp
//				sandboxFile = File.createTempFile("BudgetUpload", sfx, sandboxDir);
//
//				// write to temp file in sandbox
//				InputStream is = upFile.getInputStream();
//				FileUtils.copyInputStreamToFile(is, sandboxFile);
//
//			} catch (IOException e) {
//				log.error("Unable to save uploaded file to sandbox", e);
//			}
//		}
//		return sandboxFile;
//	}
//
//}
