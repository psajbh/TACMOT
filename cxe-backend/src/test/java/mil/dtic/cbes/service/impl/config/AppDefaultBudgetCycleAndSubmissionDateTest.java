package mil.dtic.cbes.service.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mil.dtic.cbes.model.xml.BudgetCycle;
import mil.dtic.cbes.model.xml.BudgetCycles;
import mil.dtic.cbes.model.xml.SubmissionDate;
import mil.dtic.cbes.model.xml.SubmissionDates;

public class AppDefaultBudgetCycleAndSubmissionDateTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	BudgetCycles budgetCycles;
	SubmissionDates submissionDates;
	
	@BeforeEach
	public void setUp() {
		log.trace("setup-");
		budgetCycles = new BudgetCycles();
		submissionDates = new SubmissionDates();
		buildSubmissionDates(submissionDates);
		log.trace("setup- submissionDates created");
		buildBudgetCycles(budgetCycles, submissionDates);
		log.trace("setup- budgetCycles created");
	}
	
	@Test
	public void testBudgetCyclesToXml() throws JAXBException, FileNotFoundException {
		log.debug("testBudgetCyclesToXml-");
		JAXBContext jaxbContext = JAXBContext.newInstance(BudgetCycles.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(budgetCycles, new File(AppDefaultsServiceImpl.BUDGET_CYCLE));
        log.debug("testBudgetCyclesToXml- budgetCycles marshalled");
	}
	
	@Test
	public void testSubmissionDatesToXml() throws JAXBException, FileNotFoundException {
		log.debug("testSubmissionDatesToXml-");
		JAXBContext jaxbContext = JAXBContext.newInstance(SubmissionDates.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(submissionDates, new File(AppDefaultsServiceImpl.SUBMISSION_DATES));
        log.debug("testSubmissionDatesToXml- submissionDates marshalled");
	}
	
	@Test
	public void testXmlToBudgetCycles() throws JAXBException, FileNotFoundException {
		log.debug("testXmlToBudgetCycles-");
        File file = new File(AppDefaultsServiceImpl.BUDGET_CYCLE);
        JAXBContext jaxbContext = JAXBContext.newInstance(BudgetCycles.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        budgetCycles = (BudgetCycles) unmarshaller.unmarshal(file);
        log.debug("testXmlToBudgetCycles- budgetCycles unmarshalled");
	}
	
	@Test
	public void testXmlToSubmissionDates() throws JAXBException, FileNotFoundException {
		log.debug("testXmlToSubmissionDates-");
        File file = new File(AppDefaultsServiceImpl.SUBMISSION_DATES);
        JAXBContext jaxbContext = JAXBContext.newInstance(SubmissionDates.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        submissionDates = (SubmissionDates) unmarshaller.unmarshal(file);
        log.debug("testXmlToSubmissionDates- submissionDates unmarshalled");
	}
	
	private void buildSubmissionDates(SubmissionDates submissionDates) {
		submissionDates.add(new SubmissionDate("Aug2007", "Aug2007", "August 2007"));
		submissionDates.add(new SubmissionDate("Sep2007", "Sep2007", "September 2007"));
		submissionDates.add(new SubmissionDate("Feb2008", "Feb2008", "February 2008"));
		submissionDates.add(new SubmissionDate("Jul2008", "Jul2008", "July 2008"));
		submissionDates.add(new SubmissionDate("Sep2008", "Sep2008", "September 2008"));
		submissionDates.add(new SubmissionDate("Feb2009", "Feb2009", "February 2009"));
		submissionDates.add(new SubmissionDate("Apr2009", "Apr2009", "April 2009"));
		submissionDates.add(new SubmissionDate("May2009", "May2009", "May 2009"));
		submissionDates.add(new SubmissionDate("Aug2009", "Aug2009", "August 2009"));
		submissionDates.add(new SubmissionDate("Feb2010", "Feb2010", "February 2010"));
		submissionDates.add(new SubmissionDate("Sep2010", "Sep2010", "September 2010"));
		submissionDates.add(new SubmissionDate("Feb2011", "Feb2011", "February 2011"));
		submissionDates.add(new SubmissionDate("Sep2011", "Sep2011", "September 2011"));
		submissionDates.add(new SubmissionDate("Feb2012", "Feb2012", "February 2012"));
		submissionDates.add(new SubmissionDate("Mar2012", "Mar2012", "March 2012"));
		submissionDates.add(new SubmissionDate("Apr2012", "Apr2012", "April 2012"));
		submissionDates.add(new SubmissionDate("May2012", "May2012", "May 2012"));
		submissionDates.add(new SubmissionDate("Jun2012", "Jun2012", "June 2012"));
		submissionDates.add(new SubmissionDate("Jul2012", "Jul2012", "July 2012"));
		submissionDates.add(new SubmissionDate("Aug2012", "Aug2012", "August 2012"));
		submissionDates.add(new SubmissionDate("Sep2012", "Sep2012", "September 2012"));
		submissionDates.add(new SubmissionDate("Apr2013", "Apr2013", "April 2013"));
		submissionDates.add(new SubmissionDate("May2013", "May2013", "May 2013"));
		submissionDates.add(new SubmissionDate("Oct2013", "Oct2013", "October 2013"));
		submissionDates.add(new SubmissionDate("Mar2014", "Mar2014", "March 2014"));
		submissionDates.add(new SubmissionDate("Jun2014", "Jun2014", "June 2014"));
		submissionDates.add(new SubmissionDate("Sep2014", "Sep2014", "September 2014"));
		submissionDates.add(new SubmissionDate("Feb2015", "Feb2015", "February 2015"));
		submissionDates.add(new SubmissionDate("Sep2015", "Sep2015", "September 2015"));
		submissionDates.add(new SubmissionDate("Feb2016", "Feb2016", "February 2016"));
		submissionDates.add(new SubmissionDate("Sep2016", "Sep2016", "September 2016"));
		submissionDates.add(new SubmissionDate("Nov2016", "Nov2016", "November 2016"));
		submissionDates.add(new SubmissionDate("Dec2016", "Dec2016", "December 2016"));
		submissionDates.add(new SubmissionDate("Feb2017", "Feb2017", "February 2017"));
		submissionDates.add(new SubmissionDate("Mar2017", "Mar2017", "March 2017"));
		submissionDates.add(new SubmissionDate("Sep2017", "Sep2017", "September 2017"));
		submissionDates.add(new SubmissionDate("Feb2018", "Feb2018", "February 2018"));
		submissionDates.add(new SubmissionDate("Sep2018", "Sep2018", "September 2018"));
		submissionDates.add(new SubmissionDate("Feb2019", "Feb2019", "February 2019"));
		submissionDates.add(new SubmissionDate("Sep2019", "Sep2019", "September 2019"));
		submissionDates.add(new SubmissionDate("Feb2020", "Feb2020", "February 2020"));
	}
	  
	private void buildBudgetCycles(BudgetCycles budgetCycles,  SubmissionDates submissionDates) {
		Map<String, SubmissionDate> submissionDateMap = new HashMap<>();
		
		for (SubmissionDate submissionDate : submissionDates.getSubmissionDates()) {
			submissionDateMap.put(submissionDate.getSubmissionDateId(), submissionDate);
		}
		
		BudgetCycle budgetCycle = new BudgetCycle("BES2009", "BES 2009","BES", "2009", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Aug2007"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2007"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("PBR2009", "PBR 2009","PBR","2009", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2008"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("BES2010", "BES 2010","BES","2010", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Jul2008"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2008"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("PBR2010", "PBR 2010","PBR","2010", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2009"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Apr2009"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("May2009"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("PB2010", "PB 2010","PB","2010", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2009"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Apr2009"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("May2009"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("POM2011", "POM 2011","POM","2011", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Aug2009"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2009"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("BES2011", "BES 2011","BES","2011", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2009"));
	    budgetCycles.add(budgetCycle);
	    
		budgetCycle = new BudgetCycle("PB2011", "PB 2011","PB","2011", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2010"));
	    budgetCycles.add(budgetCycle);

	    budgetCycle = new BudgetCycle("POM2012", "POM 2012","POM","2012", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2010"));
	    budgetCycles.add(budgetCycle);

	    budgetCycle = new BudgetCycle("BES2012", "BES 2012","BES","2012", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2010"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2012", "PB 2012","PB","2012", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2011"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("BES2013", "BES 2013","BES","2013", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2011"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2013", "PB 2013","PB","2013", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2012"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("POM2014", "POM 2014","POM","2014", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Mar2012"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Apr2012"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("May2012"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Jun2012"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Jul2012"));
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Aug2012"));
	    budgetCycles.add(budgetCycle);

	    budgetCycle = new BudgetCycle("BES2014", "BES 2014","BES","2014", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2012"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2014", "PB 2014","PB","2014", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Apr2013"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2014Amended", "PB 2014 Amended","PBAmended","2014", "1");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("May2013"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("BES2015", "BES 2015","BES","2015", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Oct2013"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("POM2015", "POM 2015","POM","2015", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Oct2013"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2015", "PB 2015","PB","2015", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Mar2014"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2015Amended", "PB 2015 Amended","PBAmended","2015", "1");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Jun2014"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("BES2016", "BES 2016","BES","2016", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2014"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("POM2016", "POM 2016","POM","2016", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2014"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2016", "PB 2016","PB","2016", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2015"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("BES2017", "BES 2017","BES","2017", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2015"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2017", "PB 2017","PB","2017", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2016"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2017Amended", "PB 2017 Amended","PBAmended","2017", "1");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Mar2017"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("BES2018", "BES 2018","BES","2018", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Mar2017"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2018", "PB 2018","PB","2018", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("May2017"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2018Amended", "PB 2018 Amended","PBAmended","2018", "1");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2017"));
	    budgetCycles.add(budgetCycle);

	    budgetCycle = new BudgetCycle("BES2019", "BES 2019","BES","2019", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2017"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2019", "PB 2019","PB","2019", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2017"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("BES2020", "BES 2020","BES","2020", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2018"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2020", "PB 2020","BES","2020", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2018"));
	    budgetCycles.add(budgetCycle);

	    budgetCycle = new BudgetCycle("BES2021", "BES 2021","BES","2021", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Sep2019"));
	    budgetCycles.add(budgetCycle);
	    
	    budgetCycle = new BudgetCycle("PB2021", "PB 2021","PB","2021", "");
	    budgetCycle.getSubmissionDates().add(submissionDateMap.get("Feb2020"));
	    budgetCycles.add(budgetCycle);
	}

}
