package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"","/","index","index.html"})
	public String index() {
		return "index";
	}
	
	@GetMapping({"oups"})
	public String errorHandler(Model model) {
		model.addAttribute("oups", "local errors");
		return "errors/errors";
	}


}
