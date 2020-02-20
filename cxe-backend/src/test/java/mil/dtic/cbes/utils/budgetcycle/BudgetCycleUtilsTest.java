package mil.dtic.cbes.utils.budgetcycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class BudgetCycleUtilsTest {
	
	@Test
	public void testMapCreationOfGeneratedBudgetYearAndCycle() throws Exception {
		Map<String,String> map = new HashMap<>();
		String cycle = "BES";
		map.put("CYCLE", cycle);
		int budgetYear = 2021;
		String by = String.valueOf(budgetYear);
		map.put("BY", by);
		
		assertNotNull(map.get("CYCLE"));
		assertEquals(map.get("CYCLE"),cycle);
		
		assertNotNull(map.get("BY"));
		assertEquals(map.get("BY"),by);
	}

}
