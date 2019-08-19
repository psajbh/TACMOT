package guru.springframework.controllers;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import guru.springframework.model.Category;
//import guru.springframework.model.UnitOfMeasure;
//import guru.springframework.repositories.CategoryRepository;
//import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	private final RecipeService recipeService;
	
	@Autowired
	public IndexController(RecipeService recipeService) {
		log.debug("calling IndexController");
		this.recipeService = recipeService;
	}
	
	@GetMapping({"","/","/index"})
	public String getIndexPage(Model model) {
		
//		Optional<Category> categoryOptional = categoryRepository.findByDescription("Mexican");
//		System.out.println("** category: " + categoryOptional.get().getId());
//		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Cup");
//		unitOfMeasureOptional.get().getUom();
//		System.out.println("** unitOfMeasure: " + unitOfMeasureOptional.get().getId());
		model.addAttribute("recipes", recipeService.getRecipes());

		
		return "index";
	}

}
