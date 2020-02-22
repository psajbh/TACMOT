package mil.dtic.cbes.service.impl.budgetcycle;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.model.dto.budgetcycle.SubmissionDateDto;
import mil.dtic.cbes.service.budgetcycle.BudgetCycleService;

public class BudgetCycleServiceImpl implements BudgetCycleService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public static final String[] BUDGET_CYCLE_BES_PBR_POM = {"BES", "PBR", "POM"};
	public static final String BUDGET_CYCLE_BES ="BES";
	public static final String BUDGET_CYCLE_PB ="PB";
	public static final String BUDGET_CYCLE_POM ="POM";
	public static final String BUDGET_CYCLE_PBR = "PBR";
	public static final String BUDGET_CYCLE_PB_AMENDED = "PBAmended";
	public static final String BUDGET_CYCLE_PB_SUPPLEMENTAL = "PBSupplemental";
	public static final String SUBMISSION_DATE_FORMAT = "MMMM yyyy";

	public static final String DATETIME_FORMAT = "MMM dd, yyyy HH:mm z";
	public static final String INITIAL_SUBMISSION_CYCLE = BUDGET_CYCLE_BES;
	public static final String FINAL_SUBMISSION_CYCLE = BUDGET_CYCLE_PB;
	public static final int FINAL_SUBMISSION_MONTH = Calendar.FEBRUARY;
	public static final int FINAL_SUBMISSION_YEAR_OFFSET = 1;
	
	//TODO: determine if Java 8 DateTime could replace Calendar
	@Override
	public BudgetCycleDto getCurrentBudgetCycle(Calendar currentDate) {
		
		BudgetCycleDto bc;
		
		// configService call to get a default budgetCycle
		// if exists return that value, else compute it.
		
		Calendar initSubmissionDate = currentDate;
		
		if (null == currentDate) {
			initSubmissionDate = Calendar.getInstance();
			currentDate = (Calendar) initSubmissionDate.clone();
		}
		else {
			initSubmissionDate = (Calendar) currentDate.clone();
		}
		// now initSubmissionDate and currentDate both exist.
		
	    initSubmissionDate.clear();
	    initSubmissionDate.set(currentDate.get(Calendar.YEAR), Calendar.SEPTEMBER, 1);
	    
	    // Get start of window for BES
	    Calendar initSubmissionWindowStart = (Calendar) initSubmissionDate.clone();
	    initSubmissionWindowStart.add(Calendar.MONTH, -4);

	    // Get end of window for BES
	    Calendar initSubmissionWindowEnd = (Calendar) initSubmissionDate.clone();
	    initSubmissionWindowEnd.add(Calendar.MONTH, 1);
	    
	    DateFormat dateTimeFormat = new SimpleDateFormat(BudgetCycleServiceImpl.DATETIME_FORMAT);
	    
	    log.debug(String.format("Computing current budget cycle based on current date %s, BES Date [%s], BES window = [%s - %s]",
	            dateTimeFormat.format(currentDate.getTime()),
	            dateTimeFormat.format(initSubmissionDate.getTime()),
	            dateTimeFormat.format(initSubmissionWindowStart.getTime()),
	            dateTimeFormat.format(initSubmissionWindowEnd.getTime())));

	    String cycle = null;
	    
	    int budgetYear = initSubmissionDate.get(Calendar.YEAR) + 1;
	    
	    if (currentDate.after(initSubmissionWindowStart) && currentDate.before(initSubmissionWindowEnd))
	    {
	      // BES cycle
	      cycle = INITIAL_SUBMISSION_CYCLE;
	      ++budgetYear;
	    }
	    else
	    {
	      // PB cycle
	      cycle = FINAL_SUBMISSION_CYCLE;

	      // Get start of window for BES
	      Calendar finalSubmissionWindowStart = (Calendar) initSubmissionWindowEnd.clone();
	      finalSubmissionWindowStart.add(Calendar.DAY_OF_MONTH, -1);

	      // Get start of following year
	      Calendar newYear = Calendar.getInstance();
	      newYear.clear();
	      newYear.set(currentDate.get(Calendar.YEAR) + 1, Calendar.JANUARY, 1);
	      
	      log.debug(String.format("Computing PB cycle, end of BES to new year is [%s - %s]",
	              dateTimeFormat.format(finalSubmissionWindowStart.getTime()),
	              dateTimeFormat.format(newYear.getTime())));

	      // PB's year is only + 2 just like BES unless current month is January or
	      // later
	      if (currentDate.after(finalSubmissionWindowStart) && currentDate.before(newYear))
	      {
	        ++budgetYear;
	      }
	    }

	    log.debug("Looking up BudgetCycle object for computed default budget cycle of " + cycle + " " + budgetYear);
	    // mock this out, REPO call will require a translation.
//	    bc = BudgesContext.getBudgetCycleDAO().findByCycleAndBudgetYear(cycle, budgetYear);
//
//	    //If BudgesContext.getBudgetCycleDAO().findByCycleAndBudgetYear(cycle, budgetYear); returns null then look for it using a reverse chronological order search.
//	    //return the first future budget cycle.
//	    if (bc == null) {
//	        log.debug("Could not find BudgetCycle object for computed default cycle, searching for cycle past current date");
//	        
//	        List<BudgetCycle> chronologicalBcList = BudgesContext.getBudgetCycleDAO().getBudgetCycles();
//	        
//	        Collections.reverse(chronologicalBcList);
//	        
//	        for (BudgetCycle chronCycle : chronologicalBcList) {
//	            SubmissionDateDto submissionDateDto = chronCycle.getSubmissionDates().iterator().next();
//	            
//	            Calendar calendarSubDate = Calendar.getInstance();
//	            String dateValue = submissionDateDto.getValue();
//	            calendarSubDate.setTime(new SimpleDateFormat(SUBMISSION_DATE_FORMAT).parse(dateValue, new ParsePosition(0)));
//	            //calendarSubDate.setTime(subdate.getDate());
//	            
//	            
//	            if (currentDate.before(calendarSubDate))
//	            {
//	                return chronCycle;
//	            }
//	        }
//	     }
//
//	    if (bc != null)
//	    {
//	      log.debug("Found BudgetCycle object for computed default budget cycle of: " + bc.getLabel());
//	      return bc;
//	    }
//
//	    log.error("Default BudgetCycle object not found! Falling back to hardcoded default BES 2010");
//
//	    bc = BudgesContext.getBudgetCycleDAO().findByCycleAndBudgetYear("BES", 2010);
//	    
//	    if (bc != null){
//	        return bc;
//	    }
//	    
//	    log.error("Uh-oh, could not find BudgetCycle object for BES 2010. You can probably guess what happens next after I return this null ;).");
	    
	    return null;
		
	}

}
