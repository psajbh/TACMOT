package mil.dtic.cbes.utils.budgetcycle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;
import mil.dtic.cbes.service.config.AppDefaultsService;

@Component
public class BudgetCycleUtils {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static final String DATETIME_FORMAT = "MMM dd, yyyy HH:mm z";
	public static final String[] BUDGET_CYCLE_BES_PBR_POM = {"BES", "PBR", "POM"};
	public static final String BUDGET_CYCLE_BES ="BES";
	public static final String BUDGET_CYCLE_PB ="PB";
	public static final String BUDGET_CYCLE_POM ="POM";
	public static final String BUDGET_CYCLE_PBR = "PBR";
	public static final String BUDGET_CYCLE_PB_AMENDED = "PBAmended";
	public static final String BUDGET_CYCLE_PB_SUPPLEMENTAL = "PBSupplemental";
	public static final String FINAL_SUBMISSION_CYCLE = BUDGET_CYCLE_PB;
	public static final String INITIAL_SUBMISSION_CYCLE = BUDGET_CYCLE_BES;
	public static final int FINAL_SUBMISSION_MONTH = Calendar.FEBRUARY;
	public static final int FINAL_SUBMISSION_YEAR_OFFSET = 1;
	public static final String CYCLE = "CYCLE";
	public static final String BY = "BY";
	
	private AppDefaultsService appDefaultsService; 
	
	public BudgetCycleUtils(AppDefaultsService appDefaultsService) {
		this.appDefaultsService = appDefaultsService;
	}
	
	public BudgetCycleDto getCurrentBudgetCycle(Calendar currdate) {
		Map<String,String> generatedBudgetCycle = getBudgetCycleAndYear(currdate);
		return processBudgetCycles(generatedBudgetCycle);
	}
	
	private BudgetCycleDto processBudgetCycles(Map<String,String> budgetCycleAndYear) {
		
		BudgetCycleDto match = findCycleAndBudgetYearMatch(budgetCycleAndYear.get(CYCLE), budgetCycleAndYear.get(BY));
		if (null != match) {
			return match;
		}
		BudgetCycleDto budgetCycleDto = new BudgetCycleDto();
		budgetCycleDto.setBudgetCycleId(budgetCycleAndYear.get(CYCLE)+budgetCycleAndYear.get(BY));
		budgetCycleDto.setLabel(budgetCycleAndYear.get(CYCLE)+" "+budgetCycleAndYear.get(BY));
		budgetCycleDto.setBudgetYear(budgetCycleAndYear.get(BY));
		budgetCycleDto.setCycle(budgetCycleAndYear.get(CYCLE));
		return budgetCycleDto;
	}
	
	private BudgetCycleDto findCycleAndBudgetYearMatch(String cycle, String budgetYear) {
		
		for (BudgetCycleDto budgetCycleDto : appDefaultsService.getBudgetCycleDtos()) {
			log.trace("findCycleAndBudgetYearMatch- cycle:"+budgetCycleDto.getCycle()+" budgetyear:"+ budgetCycleDto.getBudgetYear()); 
			if (budgetCycleDto.getBudgetYear().equals(budgetYear)) {
				if (budgetCycleDto.getCycle().equals(cycle)){
					return budgetCycleDto;
				}
			}
		}
		return null;
	}
	
		
	private Map<String,String> getBudgetCycleAndYear(Calendar currdate) {
		Calendar initSubmissionDate = currdate;
		
		if (null == currdate) {
		      initSubmissionDate = Calendar.getInstance();
		      currdate = (Calendar) initSubmissionDate.clone();
		}
		else {
			initSubmissionDate = (Calendar) currdate.clone();
		}
		
	    initSubmissionDate.clear();
	    initSubmissionDate.set(currdate.get(Calendar.YEAR), Calendar.SEPTEMBER, 1);

	    // Get start of window for BES
	    Calendar initSubmissionWindowStart = (Calendar) initSubmissionDate.clone();
	    initSubmissionWindowStart.add(Calendar.MONTH, -4);

	    // Get end of window for BES
	    Calendar initSubmissionWindowEnd = (Calendar) initSubmissionDate.clone();
	    initSubmissionWindowEnd.add(Calendar.MONTH, 1);
	    
	    DateFormat dateTimeFormat = new SimpleDateFormat(BudgetCycleUtils.DATETIME_FORMAT);
	    
	    log.debug(String.format("Computing current budget cycle based on current date %s, BES Date [%s], BES window = [%s - %s]",
	            dateTimeFormat.format(currdate.getTime()),
	            dateTimeFormat.format(initSubmissionDate.getTime()),
	            dateTimeFormat.format(initSubmissionWindowStart.getTime()),
	            dateTimeFormat.format(initSubmissionWindowEnd.getTime())));

	    String cycle = null;
	    
	    int budgetYear = initSubmissionDate.get(Calendar.YEAR) + 1;
	    
	    if (currdate.after(initSubmissionWindowStart) && currdate.before(initSubmissionWindowEnd)) {
	      // BES cycle
	      cycle = BudgetCycleUtils.INITIAL_SUBMISSION_CYCLE;
	      ++budgetYear;
	    }
	    else {
	      // PB cycle
	      cycle = BudgetCycleUtils.FINAL_SUBMISSION_CYCLE;

	      // Get start of window for BES
	      Calendar finalSubmissionWindowStart = (Calendar) initSubmissionWindowEnd.clone();
	      finalSubmissionWindowStart.add(Calendar.DAY_OF_MONTH, -1);

	      // Get start of following year
	      Calendar newYear = Calendar.getInstance();
	      newYear.clear();
	      newYear.set(currdate.get(Calendar.YEAR) + 1, Calendar.JANUARY, 1);
	      
	      log.debug(String.format("Computing PB cycle, end of BES to new year is [%s - %s]",
	              dateTimeFormat.format(finalSubmissionWindowStart.getTime()),
	              dateTimeFormat.format(newYear.getTime())));

	      // PB's year is only + 2 just like BES unless current month is January or
	      // later
	      if (currdate.after(finalSubmissionWindowStart) && currdate.before(newYear))
	      {
	        ++budgetYear;
	      }
	    }
	    
	    String year = String.valueOf(budgetYear);
	    
	    log.debug("Looking up BudgetCycle object for computed default budget cycle of " + cycle + " " + year);
	    
	    Map<String,String> generatedBudgetCycleValues = new HashMap<>();
	    generatedBudgetCycleValues.put(BudgetCycleUtils.CYCLE, cycle);
	    generatedBudgetCycleValues.put(BudgetCycleUtils.BY, year);
	    return generatedBudgetCycleValues;
	}

}


