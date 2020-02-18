package mil.dtic.cbes.service.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.xml.BudgetCycle;
import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDate;
import mil.dtic.cbes.model.xml.SubmissionDates;
import mil.dtic.cbes.service.config.AppDefaultsService;

@Service
public class AppDefaultsServiceImpl implements AppDefaultsService {
	
	private static final String BUDGET_CYCLE = "src/main/resources/xml/budgetcycles.xml";
	private static final String SUBMISSION_DATES = "src/main/resources/xml/submissiondates.xml";
	
	private BudgetCycles budgetCycles;
	private SubmissionDates submissionDates;
	
	@PostConstruct
	private void postConstruct() {
		getXmlToSubmissionDates();
		getXmlToBudgetCycles();
	}
	
	private void getXmlToBudgetCycles() {
		try {
	        File file = new File(BUDGET_CYCLE);
	        JAXBContext jaxbContext = JAXBContext.newInstance(BudgetCycles.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        budgetCycles = (BudgetCycles) unmarshaller.unmarshal(file);
		}
		catch (JAXBException jaxbException) {
			
		}
		catch (Exception e) {
			
		}
	}
	
	private void getXmlToSubmissionDates() {
		try {
	        File file = new File(SUBMISSION_DATES);
	        JAXBContext jaxbContext = JAXBContext.newInstance(SubmissionDates.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        submissionDates = (SubmissionDates) unmarshaller.unmarshal(file);
		}
		catch(JAXBException jaxbException) {
			System.out.println("jaxbException");
			
		}
	}
	
	
	@Override
	public SubmissionDates getSubmissionDates(){
		return submissionDates;
	}
	
	@Override
	public BudgetCycles getBudgetCycles(){
		return budgetCycles;
	}

}
