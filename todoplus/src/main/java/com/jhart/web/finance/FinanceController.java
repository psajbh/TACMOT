package com.jhart.web.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinanceController {

	@GetMapping({"/finance/finance"})
	public String finance() {
		return "finance/finance";
	}

}
