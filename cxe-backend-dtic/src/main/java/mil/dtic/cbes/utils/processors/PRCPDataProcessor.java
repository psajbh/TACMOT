package mil.dtic.cbes.utils.processors;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.entities.P1DataEntity;
import mil.dtic.cbes.model.entities.R1DataEntity;
import mil.dtic.cbes.model.enums.PRCPType;
import mil.dtic.cbes.repositories.ConfigRepository;
import mil.dtic.cbes.repositories.P1DataRepository;
import mil.dtic.cbes.repositories.R1DataRepository;
import mil.dtic.cbes.utils.exceptions.rest.PRCPFileProcessingException;
import mil.dtic.cbes.utils.exceptions.rest.PRCPUnknownTypeException;

@Component
public class PRCPDataProcessor {
	private static final Logger log = LoggerFactory.getLogger(PRCPDataProcessor.class);

	private R1DataRepository r1Repo;

	private P1DataRepository p1Repo;
	
	private ConfigRepository configRepo;
	
	private MessageSource messageSource;

	@Autowired
	public PRCPDataProcessor(R1DataRepository r1Repo, P1DataRepository p1Repo, ConfigRepository configRepo, MessageSource messageSource) {
		this.r1Repo = r1Repo;
		this.p1Repo = p1Repo;
		this.configRepo = configRepo;
		this.messageSource = messageSource;
	}

	public void process(int prcpType, File file, int budgetYear) {

		if (file.exists()) {
			OPCPackage opcPackage = null;
			
			Workbook workbook = null;
			
			JSONObject mappings = null;

			try {
				opcPackage = OPCPackage.open(file);

				workbook = new XSSFWorkbook(opcPackage);
				
				PRCPType type = PRCPType.getByType(prcpType);
				
				mappings = loadMappingJSONFile(type);
				
				switch(type) {
					case P1:
						List<P1DataEntity> p1EntityList = extractP1DataValues(workbook, mappings, budgetYear);
						
						p1Repo.deleteAll();
						p1Repo.saveAll(p1EntityList);
						
						break;
					case R1:
						List<R1DataEntity> r1EntityList = extractR1DataValues(workbook, mappings, budgetYear);
						
						r1Repo.deleteAll();
						r1Repo.saveAll(r1EntityList);
						
						break;
					default:
						throw new PRCPUnknownTypeException(messageSource.getMessage("prcp.type.unknown",
								new String[] {type.getVal()}, StringUtils.EMPTY, Locale.US));
				}

			} 
			catch (InvalidFormatException | IOException e) {
				throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.processing.internal.failure",
						null, StringUtils.EMPTY, Locale.US));
			} 
			catch(JSONException e) {
				throw new PRCPFileProcessingException(messageSource.getMessage("prcp.data.processiong.extraction.failure",
						null, StringUtils.EMPTY, Locale.US));
			}
			finally {
				IOUtils.closeQuietly(opcPackage);
				IOUtils.closeQuietly(workbook);
			}  
		}
		else {
			throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.not.found.exists",
					new String[] {PRCPType.getByType(prcpType).getVal()}, StringUtils.EMPTY, Locale.US));
		}
	}

	public JSONObject loadMappingJSONFile(PRCPType prcpType) {
		JSONObject result = new JSONObject();

		String filename = StringUtils.EMPTY;
		
		String filePath = configRepo.findByName("r2.prcpMappingFolder").getValue();
		
		switch(prcpType) {
			case P1 :
				filename = filePath + File.separator + "p1_mapping";
				break;
			case R1 :
				filename = filePath + File.separator + "r1_mapping";
				break;
			default : 
				throw new PRCPUnknownTypeException(messageSource.getMessage("prcp.type.unknown",
						new String[] {prcpType.getVal()}, StringUtils.EMPTY, Locale.US));
		}

		try {
			result = new JSONObject(new String(Files.readAllBytes(Paths.get(filename))));
			log.info("JSONObject: " + result);

		} catch (IOException | JSONException e) {
			throw new PRCPFileProcessingException(messageSource.getMessage("prcp.file.processing.internal.failure",
					null, StringUtils.EMPTY, Locale.US));
		}

		return result;
	}

	public List<P1DataEntity> extractP1DataValues(Workbook workbook, JSONObject mappings, int budgetYear) throws JSONException {
		List<P1DataEntity> p1DataList = new ArrayList<>();
		
		P1DataEntity p1Data = null;

		Sheet sheet = workbook.getSheetAt(0);
		
		DataFormatter dataFormatter = new DataFormatter(Locale.US);

		for (int i = 2; i < sheet.getLastRowNum(); i++) {
			Row r = sheet.getRow(i);

			if (null == r) {
				continue;
			}

			p1Data = new P1DataEntity();
			p1Data.setBudgetYear(budgetYear);

			for (int j = 0; j < Math.max(r.getLastCellNum(), 1); j++) {

				Cell c = r.getCell(j, Row.CREATE_NULL_AS_BLANK);

				if (null != c) {
							String val = mappings.getString(sheet.getRow(1).getCell(j).getRichStringCellValue().toString()
									.replaceAll("\\n", " "));

							switch (val) {
							case "account":
								p1Data.setAccount(c.getRichStringCellValue().toString());
								break;
							case "accountTitle":
								p1Data.setAccountTitle(c.getRichStringCellValue().toString());
								break;
							case "organization":
								p1Data.setOrganization(c.getRichStringCellValue().toString());
								break;
							case "budgetActivity":
								p1Data.setBudgetActivity(c.getRichStringCellValue().toString());
								break;
							case "budgetActivityTitle":
								p1Data.setBudgetActivityTitle(c.getRichStringCellValue().toString());
								break;
							case "budgetSubActivity":
								p1Data.setBudgetSubActivity(c.getRichStringCellValue().toString());
								break;
							case "budgetSubActivityTitle":
								p1Data.setBudgetSubActivityTitle(c.getRichStringCellValue().toString());
								break;
							case "liNumber":
								p1Data.setLiNumber(c.getRichStringCellValue().toString());
								break;
							case "liTitle":
								p1Data.setLiTitle(c.getRichStringCellValue().toString());
								break;
							case "costType":
								p1Data.setCostType(c.getRichStringCellValue().toString());
								break;
							case "costTypeTitle":
								p1Data.setCostTypeTitle(c.getRichStringCellValue().toString());
								break;
							case "addNonAdd":
								p1Data.setAddNonAdd(c.getRichStringCellValue().toString());
								break;
							case "classification":
								p1Data.setClassification(c.getRichStringCellValue().toString());
								break;
							case "lineNumber":
								p1Data.setLineNumber(dataFormatter.formatCellValue(c));
								break;
							case "pyQuantity":
								p1Data.setPyQuantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "pyAmount":
								p1Data.setPyAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "cyQuantity":
								p1Data.setCyQuantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "cyAmount":
								p1Data.setCyAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by1BaseAmount":
								p1Data.setBy1BaseAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by1OCOAmount":
								p1Data.setBy1OCOAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by1Quantity":
								p1Data.setBy1Quantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by1Amount":
								p1Data.setBy1Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by2Quantity":
								p1Data.setBy2Quantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by2Amount":
								p1Data.setBy2Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by3Quantity":
								p1Data.setBy3Quantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by3Amount":
								p1Data.setBy3Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by4Quantity":
								p1Data.setBy4Quantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by4Amount":
								p1Data.setBy4Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by5Quantity":
								p1Data.setBy5Quantity(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							case "by5Amount":
								p1Data.setBy5Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
								break;
							default:
								break;
							}

				}
			}

			p1DataList.add(p1Data);
		}

		return p1DataList;
	}
	
	public List<R1DataEntity> extractR1DataValues(Workbook workbook, JSONObject mappings, int budgetYear) throws JSONException{
		List<R1DataEntity> r1DataList = new ArrayList<>();
		
		R1DataEntity r1Data = null;
		
		Sheet sheet = workbook.getSheetAt(0);
		
		for (int i = 2; i < sheet.getLastRowNum(); i++) {
			Row r = sheet.getRow(i);

			if (null == r) {
				continue;
			}
			
			r1Data = new R1DataEntity();
			r1Data.setBudgetYear(budgetYear);
			
			for (int j = 0; j < Math.max(r.getLastCellNum(), 1); j++) {

				Cell c = r.getCell(j, Row.CREATE_NULL_AS_BLANK);
				
				if (null != c) {
						String val = mappings.getString(sheet.getRow(1).getCell(j).getRichStringCellValue().toString()
								.replaceAll("\\n", " "));
						
						switch(val) {
						case "account":
							r1Data.setAccount(c.getRichStringCellValue().toString());
							break;
						case "accountTitle":
							r1Data.setAccountTitle(c.getRichStringCellValue().toString());
							break;
						case "organization":
							r1Data.setOrganization(c.getRichStringCellValue().toString());
							break;
						case "budgetActivity":
							r1Data.setBudgetActivity(c.getRichStringCellValue().toString());
							break;
						case "budgetActivityTitle":
							r1Data.setBudgetActivityTitle(c.getRichStringCellValue().toString());
							break;
						case "lineNumber":
							r1Data.setLineNumber(c.getRichStringCellValue().toString());
							break;
						case "peNumber" :
							r1Data.setPeNumber(c.getRichStringCellValue().toString());
							break;
						case "peTitle" : 
							r1Data.setPeTitle(c.getRichStringCellValue().toString());
							break;
						case "projectNumber" : 
							r1Data.setProjectNumber(c.getRichStringCellValue().toString());
							break;
						case "projectTitle" : 
							r1Data.setProjectTitle(c.getRichStringCellValue().toString());
							break;
						case "fundCategory" : 
							r1Data.setFundCategory(c.getRichStringCellValue().toString());
							break;
						case "classification" :
							r1Data.setClassification(c.getRichStringCellValue().toString());
							break;
						case "pyAmount" :
							r1Data.setPyAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "cyAmount" : 
							r1Data.setCyAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by1BaseAmount" : 
							r1Data.setBy1BaseAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by1OCOAmount" : 
							r1Data.setBy1OCOAmount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by1Amount" : 
							r1Data.setBy1Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by2Amount" : 
							r1Data.setBy2Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by3Amount" : 
							r1Data.setBy3Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by4Amount" : 
							r1Data.setBy4Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						case "by5Amount" : 
							r1Data.setBy5Amount(BigDecimal.valueOf(c.getNumericCellValue()).abs()
										.setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
							break;
						default :
							break;
						}
				}
			}
			
			r1DataList.add(r1Data);
		}
		
		return r1DataList;
	}
}
