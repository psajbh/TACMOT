package mil.dtic.cbes.service.impl.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import mil.dtic.cbes.model.dto.core.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.core.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.model.xml.BudgetCycle;
import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDate;
import mil.dtic.cbes.model.xml.SubmissionDates;
import mil.dtic.cbes.service.core.BudgetCycleDefaultsService;

@Service
public class BudgetCycleDefaultsServiceImpl implements BudgetCycleDefaultsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	static final String BUDGET_CYCLE = "classpath:xml/budgetcycles.xml";
	static final String SUBMISSION_DATES = "classpath:xml/submissiondates.xml";
	
	private BudgetCycles budgetCycles;
	private SubmissionDates submissionDates;
	private List<BudgetCycleDto> budgetCycleDtos;
	private List<SubmissionDateDto> submissionDateDtos;
	private Map<String, BudgetCycleDto> budgetCycleDtoMap;
	
	@PostConstruct
	private void postConstruct() {
		log.trace("postConstruct- ");
		getXmlToSubmissionDates();
		getXmlToBudgetCycles();
		buildDtoContainers();
	}
	
	@Override
	public SubmissionDates getSubmissionDates(){
		return submissionDates;
	}
	
	@Override
	public BudgetCycles getBudgetCycles(){
		return budgetCycles;
	}
	
	@Override
	public List<SubmissionDateDto> getSubmissionDateDtos(){
		return submissionDateDtos;
	}
	
	@Override
	public List<BudgetCycleDto> getBudgetCycleDtos(){
		return budgetCycleDtos;
	}
	
	@Override
	public BudgetCycleDto getBudgetCycleById(String budgetCycleId) {
		return budgetCycleDtoMap.get(budgetCycleId);
	}
	
	private void getXmlToBudgetCycles() {
		try {
			log.trace("getXmlToBudgetCycles- ");
			File file = ResourceUtils.getFile(BUDGET_CYCLE);
			JAXBContext jaxbContext = JAXBContext.newInstance(BudgetCycles.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			budgetCycles = (BudgetCycles) unmarshaller.unmarshal(file);
			log.trace("getXmlToBudgetCycles- generated " + budgetCycles.getBudgetCycles().size() + " budget cycles");
		}
		catch (JAXBException | FileNotFoundException jfe ) {
			log.error("getXmlToBudgetCycles- failure: " + jfe.getMessage(),jfe);
		}
	}
	
	private void getXmlToSubmissionDates() {
		try {
			log.trace("getXmlToSubmissionDates- ");
			File file = ResourceUtils.getFile(SUBMISSION_DATES);
	        JAXBContext jaxbContext = JAXBContext.newInstance(SubmissionDates.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        submissionDates = (SubmissionDates) unmarshaller.unmarshal(file);
	        log.trace("getXmlToSubmissionDates- generated " + submissionDates.getSubmissionDates().size() + " submission dates"); 
		}
		catch (JAXBException | FileNotFoundException jfe ) {
			log.error("getXmlToBudgetCycles- failure: " + jfe.getMessage(),jfe);
		}
	}
	
	private void buildDtoContainers() {
		log.trace("buildDtoContainers-");
		submissionDateDtos = new ArrayList<SubmissionDateDto>();
		budgetCycleDtos = new ArrayList<BudgetCycleDto>();
		budgetCycleDtoMap = new HashMap<String, BudgetCycleDto>();
		
		for (SubmissionDate submissionDate : submissionDates.getSubmissionDates()){
			SubmissionDateDto submissionDateDto = new SubmissionDateDto();
			submissionDateDto.setCode(submissionDate.getCode());
			submissionDateDto.setLabel(submissionDate.getLabel());
			submissionDateDto.setSubmissionDateId(submissionDate.getSubmissionDateId());
			submissionDateDtos.add(submissionDateDto);
		}
		
		log.trace("buildDtoContainers- added " + submissionDateDtos.size() + " to submissiondDateDtos");
				
		for (BudgetCycle budgetCycle : budgetCycles.getBudgetCycles()) {
			BudgetCycleDto budgetCycleDto = new BudgetCycleDto();
			budgetCycleDto.setAmended(budgetCycle.getAmended());
			budgetCycleDto.setBudgetCycleId(budgetCycle.getBudgetCycleId());
			budgetCycleDto.setBudgetYear(budgetCycle.getBudgetYear());
			budgetCycleDto.setCycle(budgetCycle.getCycle());
			budgetCycleDto.setLabel(budgetCycle.getLabel());
			
			List<SubmissionDateDto> submissonDateDtoList = new ArrayList<>();
			
			for (SubmissionDate submissionDate : budgetCycle.getSubmissionDates()) {
				SubmissionDateDto submissionDateDto = new SubmissionDateDto();
				submissionDateDto.setSubmissionDateId(submissionDate.getSubmissionDateId());
				submissionDateDto.setCode(submissionDate.getCode());
				submissionDateDto.setLabel(submissionDate.getLabel());
				
				submissionDateDto.setRank(getRank(submissionDateDto.getSubmissionDateId()));
				submissonDateDtoList.add(submissionDateDto);
			}
			budgetCycleDto.setSubmissionDates(submissonDateDtoList);
			
			budgetCycleDtos.add(budgetCycleDto);
			budgetCycleDtoMap.put(budgetCycleDto.getBudgetCycleId(),budgetCycleDto);
		}
		log.trace("buildDtoContainers- added " + budgetCycleDtos.size() + " to budgetCycleDtos");
	}
	
	private Integer getRank(String submissionDateId) {
		return getMonthValue(submissionDateId.substring(0, 3));
	}
	
	private Date getSubmissonDate(String submissionDateId) {
		String month = submissionDateId.substring(0, 3);
		String year = submissionDateId.substring(3);
		return null;
	}
	
	
	private Integer getMonthValue(String month) {
		Integer i = null;
		switch(month) {
			case "Jan":
				i = 1;
				break;
				
			case "Feb":
				i = 2;
				break;
				
			case "Mar":
				i = 3;
				break;
				
			case "Apr":
				i = 4;
				break;
				
			case "May":
				i = 5;
				break;
				
			case "Jun":
				i = 6;
				break;
				
			case "Jul":
				i = 7;
				break;
				
			case "Aug":
				i = 8;
				break;
				
			case "Sep":
				i = 9;
				break;
				
			case "Oct":
				i = 10;
				break;
				
			case "Nov":
				i = 11;
				break;
				
			case "Dec":
				i = 12;
				break;
				
			default :
				i = 0;
		}
		
		return i;
	}

}
