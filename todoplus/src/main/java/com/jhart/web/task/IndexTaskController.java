package com.jhart.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexTaskController {
	
	@GetMapping({"/task/index"})
	public String index(Model model) {
		return "task/index";
	}

}
