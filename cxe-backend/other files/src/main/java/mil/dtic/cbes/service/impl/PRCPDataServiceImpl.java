package mil.dtic.cbes.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mil.dtic.cbes.model.Mapping;
import mil.dtic.cbes.model.P1Data;
import mil.dtic.cbes.model.P1SpreadsheetColumn;
import mil.dtic.cbes.model.R1Data;
import mil.dtic.cbes.model.R1SpreadsheetColumn;
import mil.dtic.cbes.repositories.MappingRepository;
import mil.dtic.cbes.repositories.P1DataRepository;
import mil.dtic.cbes.repositories.P1SpreadsheetColumnRepository;
import mil.dtic.cbes.repositories.R1DataRepository;
import mil.dtic.cbes.repositories.R1SpreadsheetColumnRepository;
import mil.dtic.cbes.service.BudgetFileUploadService;
import mil.dtic.cbes.service.PRCPDataFileService;
import mil.dtic.cbes.service.PRCPDataService;
import mil.dtic.cbes.utils.exceptions.CxeException;

/**
 * @author dpham
 *
 */
/**
 * @author dpham
 *
 */
@Service
public class PRCPDataServiceImpl implements PRCPDataService {

	private static final Logger log = LoggerFactory.getLogger(PRCPDataServiceImpl.class);

	@Autowired
	private PRCPDataFileService prcpDataFileService;

	@Autowired
	BudgetFileUploadService bfuService;

	@Autowired
	private R1DataRepository r1Repo;

	@Autowired
	private P1DataRepository p1Repo;

	@Autowired
	private MappingRepository mappingRepo;

	@Autowired
	private P1SpreadsheetColumnRepository p1spreadsheetColRepo;

	@Autowired
	private R1SpreadsheetColumnRepository r1spreadsheetColRepo;

	Workbook workbook = null;

	List<P1Data> p1DataValues = new ArrayList<P1Data>();
	List<R1Data> r1DataValues = new ArrayList<R1Data>();

	HashMap<String, String> mapFields = new HashMap<String, String>();
	FileInputStream excelFile;
	FileInputStream p1r1Spreadsheet;
	int p1r1Type;
	int budget_year;
	private final int columnTitleRow = 1;

	/**
	 * instantiating for populateData();
	 */
	private final static String myDocs = System.getProperty("user.home");

	private String targetPath;
	private File targetFile;
	private FileInputStream targetFis;
	private XSSFWorkbook targetWorkbook;
	private XSSFSheet targetSpreadsheet;

	private File mapFromFile;
	private FileInputStream mapFromFis;
	private XSSFWorkbook mapFromWorkbook;
	private XSSFSheet mapFromSpreadsheet;

	private Row addToRow;
	private Row copyFromRow;

	private Cell addToCell;
	private Cell copyFromCell;

	private int columnIndex;

	private static final List<String> spreadsheetColumnLetters = Collections.unmodifiableList(
			Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
					"T", "U", "V", "W", "X", "Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AI", "AJ", "AK",
					"AL", "AM", "AN", "AO", "AP", "AQ", "AR", "AS", "AT", "AU", "AV", "AW", "AX", "AY", "AZ"));

	public enum PrcpType {
		P1, R1
	}

	@Override
	public List<R1Data> getR1Data() {
		// TODO: Filter data based on permission
		return r1Repo.findAll();
	}

	@Override
	public List<P1Data> getP1Data() {
		// TODO: filter data based on permission
		return p1Repo.findAll();
	}

	/*
	 * Upload PRCP file to downloads and database in database
	 *
	 * @return prcp_type let client know which data table to reload
	 */
	@Override
	public int addPrcpFile(MultipartFile uploadFile) {
		File sandboxFile = prcpDataFileService.writeFileToSandbox(uploadFile);
		if (sandboxFile == null) {
			throw new CxeException("Unable to save uploaded file to sandbox on server for verification.");
		}

		// Save the P1/R1 file to the database
		int prcp_type = 0;
		String fname = uploadFile.getOriginalFilename();
		if (fname.substring(5, fname.indexOf(".")).toLowerCase().equals("p1")) {
			prcp_type = 1;
		} else {
			if (fname.substring(5, fname.indexOf(".")).toLowerCase().equals("r1")) {
				prcp_type = 2;
			}
		}
		int budget_year = Integer.parseInt(fname.substring((fname.lastIndexOf(".") - 7), (fname.lastIndexOf(".") - 3)));

		process(prcp_type, sandboxFile.getAbsolutePath(), budget_year);

		// record the upload in long term storage
		if (!prcpDataFileService.storeUploadedPRCPUpdateFile(uploadFile, uploadFile.getOriginalFilename(),
				"prcpUploadUser")) {
			throw new CxeException("PRCP data updated, but spreadsheet file could not be saved on the server.");
		}

		sandboxFile.delete();

		return prcp_type;
	}

	@Override
	public void deletePRCPdata(PrcpType type) {
		if (type == PrcpType.R1) {
			// delete database data
			r1Repo.deleteAll();
			try {
				// remove corresponding file from server and db entry in downloads table
				bfuService.deleteFile(prcpDataFileService.getPrcpDbEntry(type).getId());
			} catch (FileNotFoundException e) {
				log.warn("R1 data file doesn't exist");
			}
		} else if (type == PrcpType.P1) {
			// delete database data
			p1Repo.deleteAll();
			try {
				// remove corresponding file from server and db entry in downloads table
				bfuService.deleteFile(prcpDataFileService.getPrcpDbEntry(type).getId());
			} catch (FileNotFoundException e) {
				log.warn("P1 data file doesn't exist");
			}
		}
	}

	/**
	 * Entry point to the P1R1 Process.
	 *
	 * @method process
	 * @param type
	 *            - 1 - P1 File, 2 - R1 File
	 * @param filename
	 *            - the location of the uploaded file
	 * @return void
	 */
	@Override
	public void process(int type, String filename, int budgetYr) {

		p1r1Type = type;
		budget_year = budgetYr;

		try (FileInputStream input = new FileInputStream(filename)) {

			p1r1Spreadsheet = input;

			workbook = new XSSFWorkbook(p1r1Spreadsheet);
			deleteP1R1Data();

			updateP1R1Mapping(p1r1Type, filename);
			mapFields = loadMapping(type);
			if (mapFields == null) {
				log.error("mapFields - Error loading Map Data");
			} else {
				loadData();
			}

		} catch (FileNotFoundException e) {
			log.error("process - Input File Not Found");
		} catch (IOException e) {
			log.error("process -IO Error With Input File");
		}

	}

	/**
	 * Updates the new P1 and R1 mappings on the db based on the uploaded file
	 *
	 * @method updateP1R1Mapping
	 * @param validPRStringValues
	 *            - HashMap<String, String> of the uploaded P1 text data values
	 * @param validPRNumericValues
	 *            - HashMap<String, BigDecimal> of the uploaded P1 numeric data
	 *            values
	 * @return void
	 */
	@Override
	public void updateP1R1Mapping(int type, String filename) {

		List<String> mapHeaderValues = new ArrayList<String>();

		Sheet datatypeSheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = datatypeSheet.iterator();
		Iterator<Cell> cellIterator;

		Row currentRow;

		Cell currentCell;

		int cnt2 = 0;
		int cnt = 0;

		// adds the column titles from the uploaded spreadsheet to an array
		while (rowIterator.hasNext()) {
			cnt2 = 0;
			currentRow = rowIterator.next();
			cellIterator = currentRow.iterator();

			// columnTitleRow is the row on the spreadsheet where the titles exist;
			// this code iterates through the rows until it gets to columnTitleRow in the
			// event that the titles aren't in the first row
			if (currentRow.getRowNum() < columnTitleRow + 1) {
				cnt2 = 0;
				while (cellIterator.hasNext()) {
					currentCell = cellIterator.next();

					if (cnt == 2) {
						mapHeaderValues.add(currentCell.getSheet().getRow(1).getCell(cnt2).getRichStringCellValue()
								.toString().replaceAll("\\n", " "));

					}
					cnt2 += 1;
				}

				if (cnt == 2) {
					setMappingObject(mapHeaderValues);
					break;
				}
				cnt++;

			}

		}
	}

	/**
	 * Loads the new P1 and R1 data mappings from the db into HashMap<String,
	 * String> validPRStringValues and HashMap<String, BigDecimal>
	 * validPRNumericValues
	 *
	 * @method loadData
	 * @param none
	 * @return void
	 */
	public void loadData() {

		HashMap<String, String> validPRStringValues = new HashMap<String, String>();
		HashMap<String, BigDecimal> validPRNumericValues = new HashMap<String, BigDecimal>();

		Sheet datatypeSheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = datatypeSheet.iterator();
		Iterator<Cell> cellIterator;

		Row currentRow;

		Cell currentCell;

		int cnt2 = 0;
		int cnt = 0;

		while (rowIterator.hasNext()) {

			currentRow = rowIterator.next();
			cellIterator = currentRow.iterator();

			cnt2 = 0;

			while (cellIterator.hasNext()) {
				currentCell = cellIterator.next();

				// iterate to the row where data begins (the row after the titles) and persist
				// all data to the database
				if (cnt > columnTitleRow - 1) {
					if (currentCell.getSheet().getRow(cnt).getCell(cnt2).getCellType() == 1) {

						validPRStringValues.put(
								currentCell.getSheet().getRow(0).getCell(cnt2).getRichStringCellValue().toString()
										.replaceAll("\\n", " "),
								currentCell.getSheet().getRow(cnt).getCell(cnt2).getRichStringCellValue().toString());
					} else {
						if (currentCell.getSheet().getRow(0).getCell(cnt2).getRichStringCellValue().toString()
								.replaceAll("\\n", " ").equals("Line Number")) {
							validPRNumericValues.put(
									currentCell.getSheet().getRow(0).getCell(cnt2).getRichStringCellValue().toString()
											.replaceAll("\\n", " "),
									BigDecimal.valueOf(
											currentCell.getSheet().getRow(cnt).getCell(cnt2).getNumericCellValue()));
						} else {
							validPRNumericValues.put(
									currentCell.getSheet().getRow(0).getCell(cnt2).getRichStringCellValue().toString()
											.replaceAll("\\n", " "),
									BigDecimal
											.valueOf(currentCell.getSheet().getRow(cnt).getCell(cnt2)
													.getNumericCellValue())
											.abs().setScale(0, BigDecimal.ROUND_DOWN).movePointLeft(3));
						}
					}
				}
				cnt2 = cnt2 + 1;
			}
			cnt++;
			if (cnt > columnTitleRow) {
				if (p1r1Type == 1) {
					setP1Object(validPRStringValues, validPRNumericValues);
				} else {
					setR1Object(validPRStringValues, validPRNumericValues);
				}
			}

		}
		saveMappingValues();
	}

	/**
	 * Saves the new P1 data values to the db
	 *
	 * @method setP1Object
	 * @param validPRStringValues
	 *            - HashMap<String, String> of the uploaded P1 text data values
	 * @param validPRNumericValues
	 *            - HashMap<String, BigDecimal> of the uploaded P1 numeric data
	 *            values
	 * @return void
	 */
	public void setP1Object(HashMap<String, String> validPRStringValues,
			HashMap<String, BigDecimal> validPRNumericValues) {

		P1Data p1 = new P1Data();
		BigDecimal baseHold = null;
		BigDecimal ocoHold = null;
		BigDecimal by1Hold = null;

		for (String key : validPRStringValues.keySet()) {

			switch (mapFields.get(key)) {

			case "account":
				p1.setAccount(validPRStringValues.get(key));
				break;
			case "accountTitle":
				p1.setAccountTitle(validPRStringValues.get(key));
				break;
			case "organization":
				p1.setOrganization(validPRStringValues.get(key));
				break;
			case "budgetActivity":
				p1.setBudgetActivity(validPRStringValues.get(key));
				break;
			case "budgetActivityTitle":
				p1.setBudgetActivityTitle(validPRStringValues.get(key));
				break;
			case "lineNumber":
				p1.setLineNumber(validPRStringValues.get(key));
				break;
			case "budgetSubActivity":
				p1.setBudgetSubActivity(validPRStringValues.get(key));
				break;
			case "budgetSubActivityTitle":
				p1.setBudgetSubActivityTitle(validPRStringValues.get(key));
				break;
			case "liNumber":
				p1.setLiNumber(validPRStringValues.get(key));
				break;
			case "liTitle":
				p1.setLiTitle(validPRStringValues.get(key));
				break;
			case "costType":
				p1.setCostType(validPRStringValues.get(key));
				break;
			case "costTypeTitle":
				p1.setCostTypeTitle(validPRStringValues.get(key));
				break;
			case "addNonAdd":
				p1.setAddNonAdd(validPRStringValues.get(key));
				break;
			case "classification":
				p1.setClassification(validPRStringValues.get(key));
				break;
			}
		}

		int cnt = 0;

		for (String key : validPRNumericValues.keySet()) {

			if (key == null) {
			} else {

				cnt = cnt + 1;

				switch (mapFields.get(key)) {

				case "lineNumber":
					p1.setLineNumber(BigDecimal.valueOf(validPRNumericValues.get(key).intValue()).toBigIntegerExact()
							.toString());
					break;
				case "pyQuantity":
					p1.setPyQuantity(validPRNumericValues.get(key));
					break;
				case "pyAmount":
					p1.setPyAmount(validPRNumericValues.get(key));
					break;
				case "cyQuantity":
					p1.setCyQuantity(validPRNumericValues.get(key));
					break;
				case "cyAmount":
					p1.setCyAmount(validPRNumericValues.get(key));
					break;
				case "by1BaseAmount":
					baseHold = validPRNumericValues.get(key);
					break;
				case "by1OCOAmount":
					ocoHold = validPRNumericValues.get(key);
					p1.setBy1OCOAmount(validPRNumericValues.get(key));
					break;
				case "by1Quantity":
					p1.setBy1Quantity(validPRNumericValues.get(key));
					break;
				case "by1Amount":
					by1Hold = validPRNumericValues.get(key);
					p1.setBy1Amount(validPRNumericValues.get(key));
					break;
				case "by2Quantity":
					p1.setBy2Quantity(validPRNumericValues.get(key));
					break;
				case "by2Amount":
					p1.setBy2Amount(validPRNumericValues.get(key));
					break;
				case "by3Quantity":
					p1.setBy3Quantity(validPRNumericValues.get(key));
					break;
				case "by3Amount":
					p1.setBy3Amount(validPRNumericValues.get(key));
					break;
				case "by4Quantity":
					p1.setBy4Quantity(validPRNumericValues.get(key));
					break;
				case "by4Amount":
					p1.setBy4Amount(validPRNumericValues.get(key));
					break;
				case "by5Quantity":
					p1.setBy5Quantity(validPRNumericValues.get(key));
					break;
				case "by5Amount":
					p1.setBy5Amount(validPRNumericValues.get(key));
					break;
				}
			}
		}

		if (baseHold.doubleValue() == 0.00 && ocoHold.doubleValue() == 0.00) {
			p1.setBy1BaseAmount(by1Hold);
		} else {
			p1.setBy1BaseAmount(baseHold);
		}

		p1.setBudgetYear(budget_year);
		p1DataValues.add(p1);

	}

	/**
	 * Saves the new R1 data values to the db
	 *
	 * @method setR1Object
	 * @param validPRStringValues
	 *            - HashMap<String, String> of the uploaded P1 text data values
	 * @param validPRNumericValues
	 *            - HashMap<String, BigDecimal> of the uploaded R1 numeric data
	 *            values
	 * @return void
	 */
	public void setR1Object(HashMap<String, String> validPRStringValues,
			HashMap<String, BigDecimal> validPRNumericValues) {

		R1Data r1 = new R1Data();
		BigDecimal baseHold = null;
		BigDecimal ocoHold = null;
		BigDecimal by1Hold = null;

		for (String key : validPRStringValues.keySet()) {

			switch (mapFields.get(key)) {
			case "account":
				r1.setAccount(validPRStringValues.get(key));
				break;
			case "accountTitle":
				r1.setAccountTitle(validPRStringValues.get(key));
				break;
			case "organization":
				r1.setOrganization(validPRStringValues.get(key));
				break;
			case "budgetActivity":
				r1.setBudgetActivity(validPRStringValues.get(key));
				break;
			case "budgetActivityTitle":
				r1.setBudgetActivityTitle(validPRStringValues.get(key));
				break;
			case "lineNumber":
				r1.setLineNumber(validPRStringValues.get(key));
				break;
			case "peNumber":
				r1.setPeNumber(validPRStringValues.get(key));
				break;
			case "peTitle":
				r1.setPeTitle(validPRStringValues.get(key));
				break;
			case "projectNumber":
				r1.setProjectNumber(validPRStringValues.get(key));
				break;
			case "projectTitle":
				r1.setProjectTitle(validPRStringValues.get(key));
				break;
			case "fundCategory":
				r1.setFundCategory(validPRStringValues.get(key));
				break;
			case "classification":
				r1.setClassification(validPRStringValues.get(key));
				break;
			}
		}

		for (String key : validPRNumericValues.keySet()) {

			if (key == null) {
			} else {

				switch (mapFields.get(key)) {

				case "lineNumber":
					r1.setLineNumber(BigDecimal.valueOf(validPRNumericValues.get(key).intValue()).toBigIntegerExact()
							.toString());
					break;
				case "pyAmount":
					r1.setPyAmount(validPRNumericValues.get(key));
					break;
				case "cyAmount":
					r1.setCyAmount(validPRNumericValues.get(key));
					break;
				case "by1BaseAmount":
					baseHold = validPRNumericValues.get(key);
					break;
				case "by1OCOAmount":
					ocoHold = validPRNumericValues.get(key);
					r1.setBy1OCOAmount(validPRNumericValues.get(key));
					break;
				case "by1Amount":
					by1Hold = validPRNumericValues.get(key);
					r1.setBy1Amount(validPRNumericValues.get(key));
					break;
				case "by2Amount":
					r1.setBy2Amount(validPRNumericValues.get(key));
					break;
				case "by3Amount":
					r1.setBy3Amount(validPRNumericValues.get(key));
					break;
				case "by4Amount":
					r1.setBy4Amount(validPRNumericValues.get(key));
					break;
				case "by5Amount":
					r1.setBy5Amount(validPRNumericValues.get(key));
					break;
				}
			}
		}

		if (baseHold.doubleValue() == 0.00 && ocoHold.doubleValue() == 0.00) {
			r1.setBy1BaseAmount(by1Hold);
		} else {
			r1.setBy1BaseAmount(baseHold);
		}

		r1.setBudgetYear(budget_year);

		r1DataValues.add(r1);

	}

	/**
	 * Saves the new P1 and R1 mapping values to the db
	 *
	 * @method saveMappingValues
	 * @param none
	 * @return void
	 */
	public void saveMappingValues() {

		if (p1r1Type == 1) {
			p1Repo.saveAll(p1DataValues);
		} else {
			if (p1r1Type == 2) {
				r1Repo.saveAll(r1DataValues);
			}
		}

		p1DataValues.clear();
		r1DataValues.clear();

	}

	/**
	 * Updates the header names of the P1 and R1 to the db
	 *
	 * @method setMappingObject
	 * @param List<String>
	 *            - A list of the uploaded header names
	 * @return void
	 */
	public void setMappingObject(List<String> mapHeaderValues) {
		updateP1R1(p1r1Type, mapHeaderValues);
	}

	/**
	 * Deletes the current P1 and R1 data from the db
	 *
	 * @method deleteP1R1Data
	 * @param none
	 * @return void
	 */
	public void deleteP1R1Data() {

		if (p1r1Type == 1) {
			p1Repo.deleteAll();
		} else {
			r1Repo.deleteAll();
		}

	}

	/**
	 * Loads the current P1 and R1 mappings.
	 *
	 * @method loadMapping
	 * @param type
	 *            - 1 - P1 File, 2 - R1 File
	 * @return HashMap<String, String>
	 */
	@Override
	public HashMap<String, String> loadMapping(int p1r1Type) {

		HashMap<String, String> mapFields = new HashMap<String, String>();
		List<Mapping> mappings = mappingRepo.findByTypeId(p1r1Type);
		for (Iterator<Mapping> iterator = mappings.iterator(); iterator.hasNext();) {
			Mapping mp = iterator.next();
			mapFields.put(mp.getRaw_column(), mp.getValid_column());
		}

		if (mapFields.isEmpty()) {
			return null;
		} else {
			return mapFields;
		}
	}

	/**
	 * Updates the P1 and R1 mappings from the uploaded file.
	 *
	 * @method updateP1R1
	 * @param type
	 *            - 1 - P1 File, 2 - R1 File
	 * @param mapHeaderValues
	 *            - List<String> of uploaded header values
	 * @return void
	 */
	@Override
	public void updateP1R1(int p1r1Type, List<String> mapHeaderValues) {

		int i = 0;
		List<Mapping> mappings = mappingRepo.findByTypeId(p1r1Type);
		for (Iterator<Mapping> iterator = mappings.iterator(); iterator.hasNext();) {
			Mapping mp = iterator.next();

			mp.setRaw_column(mapHeaderValues.get(i));
			mappingRepo.save(mp);

			i += 1;
		}

	}

	public void spreadsheetMapping(PrcpType prcpType, String columnTitle, int columnOrder, String columnLetter) {
		if (prcpType == PrcpType.P1) {

			P1SpreadsheetColumn p1Column = new P1SpreadsheetColumn();

			p1Column.setColumnTitle(columnTitle);

			p1Column.setColumnLetter(columnLetter);

			p1Column.setColumnOrder(columnOrder);

			p1spreadsheetColRepo.saveAndFlush(p1Column);

		} else if (prcpType == PrcpType.R1) {

			R1SpreadsheetColumn r1Column = new R1SpreadsheetColumn();

			r1Column.setColumnTitle(columnTitle);

			r1Column.setColumnLetter(columnLetter);

			r1Column.setColumnOrder(columnOrder);

			r1spreadsheetColRepo.saveAndFlush(r1Column);

		} else {
			log.warn("process - invalid PrcpType");
		}
	}

	public List<P1SpreadsheetColumn> getP1Columns() {
		List<P1SpreadsheetColumn> p1Columns = p1spreadsheetColRepo.findAllByOrderByColumnOrderAsc();
		return p1Columns;
	}

	public List<R1SpreadsheetColumn> getR1Columns() {
		List<R1SpreadsheetColumn> r1Columns = r1spreadsheetColRepo.findAllByOrderByColumnOrderAsc();
		return r1Columns;
	}

	public void updateColumn(PrcpType prcpType, Object column) {

		if (prcpType == PrcpType.P1) {

			p1spreadsheetColRepo.save((P1SpreadsheetColumn) column);

		} else if (prcpType == PrcpType.R1) {

			r1spreadsheetColRepo.save((R1SpreadsheetColumn) column);

		} else {

			log.warn("process - invalid PrcpType");
		}

	}

	public void deleteColumn(PrcpType prcpType, int id) {

		if (prcpType == PrcpType.P1) {

			P1SpreadsheetColumn p1Column = (P1SpreadsheetColumn) findOneP1R1Row(prcpType, id);

			if (null != p1Column) {

				p1spreadsheetColRepo.delete(p1Column);

			} else {

				log.warn("process - p1Column not found for id: " + id);
			}
		} else if (prcpType == PrcpType.R1) {

			R1SpreadsheetColumn r1Column = (R1SpreadsheetColumn) findOneP1R1Row(prcpType, id);

			if (null != r1Column) {

				r1spreadsheetColRepo.delete(r1Column);

			} else {

				log.warn("process - r1Column not found for id: " + id);
			}
		} else {

			log.warn("process - invalid PrcpType");
		}

	}

	public Object findOneP1R1Row(PrcpType prcpType, int id) {

		if (prcpType == PrcpType.P1) {

			return p1spreadsheetColRepo.findById(id);

		} else if (prcpType == PrcpType.R1) {

			return r1spreadsheetColRepo.findById(id);

		} else {

			log.warn("process - invalid PrcpType");
			return null;
		}
	}

	/**
	 * CXE-6552 populates data into the new workbook from the workbook we receive
	 * from PRCP download
	 */
	public void populateData(PrcpType prcpType, String copyFromFile, String budgetYear, Integer dataBeginsRow) {

		buildSkeleton(prcpType, budgetYear);

		int p1ColumnIterator;
		int p1RowIterator;

		int r1ColumnIterator;
		int r1RowIterator;

		// open workbook that you will be writing to and copying from
		log.debug("opening workbooks in populateData()");
		openWorkbooks(budgetYear, prcpType, copyFromFile);

		// remove rows that aren't necessary, basically everything except the data to be
		// copied
		for (int i = 0; i < dataBeginsRow - 1; i++) {
			mapFromSpreadsheet.removeRow(mapFromSpreadsheet.getRow(i));
		}

		// CXE-6553 take the most recent save date of the copy from workbook and save it
		// to the metadata of the copy to workbook for display on the viewprcp page
		log.debug("prcp download date saving to metadata");
		saveDownloadDate();

		if (prcpType == PrcpType.P1) {
			// Get PRCP file
			log.debug("populating P1 data into target spreadsheet");

			List<P1SpreadsheetColumn> p1Columns = p1spreadsheetColRepo.findAllByOrderByColumnOrderAsc();

			Iterator<Row> rowIterator;

			p1ColumnIterator = -1;

			for (P1SpreadsheetColumn column : p1Columns) {
				p1RowIterator = 1;
				p1ColumnIterator++;

				rowIterator = mapFromSpreadsheet.iterator();

				while (rowIterator.hasNext()) {
					copyFromRow = rowIterator.next();

					if (null == targetSpreadsheet.getRow(p1RowIterator)) {

						addToRow = targetSpreadsheet.createRow(p1RowIterator);

					} else {

						addToRow = targetSpreadsheet.getRow(p1RowIterator);

					}

					addToCell = addToRow.createCell(p1ColumnIterator);

					columnIndex = getExcelColumnNumber(column.getColumnLetter());

					copyFromCell = copyFromRow.getCell(columnIndex);

					if (copyFromCell.getCellType() == 1) {

						addToCell.setCellValue(copyFromCell.getRichStringCellValue());

					} else if (copyFromCell.getCellType() == 0) {

						addToCell.setCellValue(copyFromCell.getNumericCellValue());
					}

					p1RowIterator++;
				}
			}

		} else if (prcpType == PrcpType.R1) {
			// Get PRCP file
			log.debug("populating R1 data into target spreadsheet");

			List<R1SpreadsheetColumn> r1Columns = r1spreadsheetColRepo.findAllByOrderByColumnOrderAsc();

			R1SpreadsheetColumn temp = new R1SpreadsheetColumn();

			temp.setColumnTitle("Fund Category");

			r1Columns.add(10, temp);

			int count = 0;

			Iterator<Row> rowIterator = mapFromSpreadsheet.iterator();

			r1ColumnIterator = -1;

			for (R1SpreadsheetColumn column : r1Columns) {

				r1RowIterator = 1;

				r1ColumnIterator++;

				rowIterator = mapFromSpreadsheet.iterator();

				while (rowIterator.hasNext()) {

					copyFromRow = rowIterator.next();

					if (null == targetSpreadsheet.getRow(r1RowIterator)) {

						addToRow = targetSpreadsheet.createRow(r1RowIterator);

						count++;

					} else {

						addToRow = targetSpreadsheet.getRow(r1RowIterator);
					}

					if (column.getColumnTitle().equalsIgnoreCase("Fund Category")) {

						int y = 0;

						while (y < count) {

							addToRow = targetSpreadsheet.getRow(r1RowIterator);
							addToCell = addToRow.createCell(r1ColumnIterator);
							addToCell.setCellValue("FY " + budgetYear + " PB");

							r1RowIterator++;
							y++;
						}
						break;
					}

					addToCell = addToRow.createCell(r1ColumnIterator);

					columnIndex = getExcelColumnNumber(column.getColumnLetter());

					copyFromCell = copyFromRow.getCell(columnIndex);

					if (copyFromCell.getCellType() == 1) {

						addToCell.setCellValue(copyFromCell.getRichStringCellValue());

					} else if (copyFromCell.getCellType() == 0) {

						addToCell.setCellValue(copyFromCell.getNumericCellValue());
					}

					r1RowIterator++;
				}
			}

		} else {
			log.warn("process - invalid PrcpType");
		}

		// autosize all columns so all data is visible and does not overflow
		log.debug("target spreadsheet formatting so all data will be visible and top row is frozen");
		autosizeColumns();

		// save and close workbooks and delete copied mapping file
		log.debug("target spreadsheet closing and saving");
		saveAndClose();

	}

	/**
	 * CXE-6552 builds a new workbook using the column titles just added to the
	 * database
	 */
	public void buildSkeleton(PrcpType prcpType, String budgetYear) {

		File directory = new File(myDocs + File.separator + "completedPRCP");

		directory.mkdir();

		File file = new File(myDocs + File.separator + "completedPRCP" + File.separator + budgetYear + "_"
				+ prcpType.toString().toUpperCase() + ".xlsx");

		FileOutputStream out;

		targetWorkbook = new XSSFWorkbook();

		try {

			out = new FileOutputStream(file);

			targetWorkbook.write(out);

			targetWorkbook.close();

			out.close();

			targetFis = new FileInputStream(file);

			targetWorkbook = new XSSFWorkbook(targetFis);

		} catch (IOException e) {

			log.warn("process - problem creating workbook in buildSkeleton(");
		}

		XSSFCellStyle headerStyle = targetWorkbook.createCellStyle();

		XSSFFont font = targetWorkbook.createFont();
		font.setItalic(true);
		font.setBold(true);
		font.setFontName("ARIAL");
		font.setFontHeightInPoints((short) 10);

		headerStyle.setFont(font);
		headerStyle.setWrapText(true);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);

		XSSFColor blue = new XSSFColor(new java.awt.Color(153, 204, 255));
		headerStyle.setFillForegroundColor(blue);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		log.debug("skeleton workbook opened and style set");

		XSSFSheet spreadsheet = targetWorkbook.createSheet("Exhibit " + prcpType.toString().toUpperCase());

		XSSFRow row = spreadsheet.createRow(0);

		int cellNumber = 0;

		if (prcpType == PrcpType.P1) {
			List<P1SpreadsheetColumn> p1Columns = p1spreadsheetColRepo.findAll();

			for (P1SpreadsheetColumn headerRow : p1Columns) {

				for (int i = 1; i < p1Columns.size() + 1; i++) {

					if (headerRow.getColumnOrder() == i) {

						XSSFCell cell = row.createCell(cellNumber++);

						cell.setCellValue(headerRow.getColumnTitle());

						cell.setCellStyle(headerStyle);
					}
				}
			}
		} else if (prcpType == PrcpType.R1) {

			List<R1SpreadsheetColumn> r1Columns = r1spreadsheetColRepo.findAll();

			for (R1SpreadsheetColumn headerRow : r1Columns) {

				for (int i = 1; i < r1Columns.size() + 1; i++) {

					if (headerRow.getColumnOrder() == i) {

						XSSFCell cell = row.createCell(cellNumber++);

						cell.setCellValue(headerRow.getColumnTitle());

						cell.setCellStyle(headerStyle);

						if (headerRow.getColumnTitle().equalsIgnoreCase("RDT&E Project Title")) {

							cell = row.createCell(cellNumber++);

							cell.setCellValue("Fund Category");

							cell.setCellStyle(headerStyle);
						}
					}
				}
			}
		} else {
			log.warn("process - invalid PrcpType");
		}
		log.debug("skeleton workbook created");
	}

	private void openWorkbooks(String budgetYear, PrcpType prcpType, String copyFromFile) {

		targetPath = myDocs + File.separator + "completedPRCP" + File.separator + budgetYear + "_"
				+ prcpType.toString().toUpperCase() + ".xlsx";

		targetFile = new File(targetPath);

		mapFromFile = new File(copyFromFile);

		try {

			mapFromFis = new FileInputStream(mapFromFile);

			mapFromWorkbook = new XSSFWorkbook(mapFromFis);

		} catch (IOException e) {

			log.warn("process - error in openWorkbooks()");
		}

		targetSpreadsheet = targetWorkbook.getSheetAt(0);

		mapFromSpreadsheet = mapFromWorkbook.getSheetAt(0);
	}

	private void saveDownloadDate() {

		POIXMLProperties props = mapFromWorkbook.getProperties();

		CoreProperties coreProp = props.getCoreProperties();

		Date lastSave = coreProp.getModified();

		props = targetWorkbook.getProperties();

		CustomProperties custProp = props.getCustomProperties();

		custProp = props.getCustomProperties();
		custProp.addProperty("prcpDownloadDate", lastSave.toString());

	}

	private void autosizeColumns() {
		Row row;

		Cell cell;

		if (targetSpreadsheet.getPhysicalNumberOfRows() > 0) {

			row = targetSpreadsheet.getRow(targetSpreadsheet.getFirstRowNum());

			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {

				cell = (XSSFCell) cellIterator.next();

				columnIndex = cell.getColumnIndex();

				targetSpreadsheet.autoSizeColumn(columnIndex);
			}
		}

		targetSpreadsheet.createFreezePane(0, 1);

	}

	private void saveAndClose() {

		FileOutputStream out;

		try {

			out = new FileOutputStream(targetFile);
			targetWorkbook.write(out);
			out.close();

			targetWorkbook.close();

			mapFromWorkbook.close();

			targetFis.close();

			mapFromFis.close();

			File directory = new File(myDocs + File.separator + "copyFromPRCP");

			FileUtils.deleteDirectory(directory);

		} catch (IOException e) {

			log.warn("process - errors from saveAndClose(");
		}

	}

	/**
	 * CXE-6552 so the user doesn't have to figure out the index number for the
	 * column letters
	 */
	private int getExcelColumnNumber(String column) {

		for (int i = 0; i < spreadsheetColumnLetters.size(); i++) {

			if (spreadsheetColumnLetters.get(i).equalsIgnoreCase(column)) {

				return i;
			}
		}
		return -1;
	}
}
