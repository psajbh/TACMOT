package mil.dtic.cbes.controllers.budgetcycle;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.budgetcycle.BudgetCycleDto;

@RestController
@RequestMapping(path="/budgetcycle")
public class BudgetCycleController extends BaseRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @GetMapping("/current")
    public ResponseEntity<BudgetCycleDto> getCurrentBudgetCycle() {
        log.trace("getCurrentBudgetCycle-");
        
        //return processUser();
        return null;
    }


}
