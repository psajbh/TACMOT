package mil.dtic.cbes.service.impl.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.dto.BudgetCycleDto;
import mil.dtic.cbes.model.dto.SubmissionDateDto;
import mil.dtic.cbes.model.xml.BudgetCycle;
import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDate;
import mil.dtic.cbes.model.xml.SubmissionDates;
import mil.dtic.cbes.service.config.AppDefaultsService;

@Service
public class AppDefaultsServiceImpl implements AppDefaultsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	static final String BUDGET_CYCLE = "src/main/resources/xml/budgetcycles.xml";
	static final String SUBMISSION_DATES = "src/main/resources/xml/submissiondates.xml";
	
	private BudgetCycles budgetCycles;
	private SubmissionDates submissionDates;
	private List<BudgetCycleDto> budgetCycleDtos;
	private List<SubmissionDateDto> submissionDateDtos;
	
	@PostConstruct
	private void postConstruct() {
		log.trace("postConstruct- ");
		getXmlToSubmissionDates();
		getXmlToBudgetCycles();
		buildDtoContainers();
	}
	
	private void getXmlToBudgetCycles() {
		log.trace("getXmlToBudgetCycles- ");
		try {
	        File file = new File(AppDefaultsServiceImpl.BUDGET_CYCLE);
	        JAXBContext jaxbContext = JAXBContext.newInstance(BudgetCycles.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        budgetCycles = (BudgetCycles) unmarshaller.unmarshal(file);
	        log.trace("getXmlToBudgetCycles- generated " + budgetCycles.getBudgetCycles().size() + " budget cycles");
		}
		catch (JAXBException jaxbException) {
			log.error("getXmlToBudgetCycles- failure: " + jaxbException.getMessage());
		}
		catch (Exception e) {
			log.error("getXmlToBudgetCycles- failure: " + e.getMessage());
		}
	}
	
	private void getXmlToSubmissionDates() {
		log.trace("getXmlToSubmissionDates- ");
		try {
	        File file = new File(AppDefaultsServiceImpl.SUBMISSION_DATES);
	        JAXBContext jaxbContext = JAXBContext.newInstance(SubmissionDates.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        submissionDates = (SubmissionDates) unmarshaller.unmarshal(file);
	        log.trace("getXmlToSubmissionDates- generated " + submissionDates.getSubmissionDates().size() + " submission dates"); 
		}
		catch(JAXBException jaxbException) {
			log.error("getXmlToSubmissionDates- failure: " + jaxbException.getMessage(), jaxbException);
			
		}
		catch(Exception e) {
			log.error("getXmlToSubmissionDates- failure: " + e.getMessage(), e);
		}
	}
	
	private void buildDtoContainers() {
		log.trace("buildDtoContainers-");
		submissionDateDtos = new ArrayList<SubmissionDateDto>();
		budgetCycleDtos = new ArrayList<BudgetCycleDto>();
		
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
				submissonDateDtoList.add(submissionDateDto);
				//budgetCycleDto.getSubmissionDates().add(submissionDateDto);
			}
			budgetCycleDto.setSubmissionDates(submissonDateDtoList);
			
			budgetCycleDtos.add(budgetCycleDto);
		}
		log.trace("buildDtoContainers- added " + budgetCycleDtos.size() + " to budgetCycleDtos");
		
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

}
