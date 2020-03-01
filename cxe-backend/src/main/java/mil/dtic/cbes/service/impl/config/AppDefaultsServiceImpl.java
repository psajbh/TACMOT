package mil.dtic.cbes.service.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
import mil.dtic.cbes.service.config.AppDefaultsService;

@Service
public class AppDefaultsServiceImpl implements AppDefaultsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	static final String BUDGET_CYCLE = "classpath:xml/budgetcycles.xml";
	static final String SUBMISSION_DATES = "classpath:xml/submissiondates.xml";
	
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
