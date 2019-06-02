package guru.springframework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import guru.springframework.services.QuotesService;

@Controller
public class JokesController {
	
	private QuotesService quotesService;

	@Autowired
	public JokesController(QuotesService quotesService) {
		this.quotesService = quotesService;
	}
	
	@GetMapping({"/",""})
	public String setup(Model model) {
		model.addAttribute("url", "/quote");
		model.addAttribute("quote", "");
		return "quote";
	}
	
	@GetMapping({"/quote"})
	public String getQuote(Model model) {
		String quote = quotesService.getQuote();
		model.addAttribute("url", "/quote");
		model.addAttribute("quote", quote);
		return "quote";
	}

}
