package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.services.RecipeService;

@Controller
public class RecipeController {
	
	private final RecipeService recipeService;
	
	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/{id}/show")
	public String getRecipeById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeById(id));
		return "recipe/show";
	}
	
	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeBean());
		return "recipe/recipeForm";
	}
	
	@PostMapping(path = "recipe")
	public String saveOrUpdate(@ModelAttribute RecipeBean recipeBean) {
		RecipeBean savedBean = recipeService.saveRecipeBean(recipeBean);
		return "redirect:/recipe/show/" + savedBean.getId();
	}
	
	 @PostMapping("recipe/{id}/update")
	 public String updateRecipe(@PathVariable String id, Model model){
	        model.addAttribute("recipe", recipeService.getRecipeById(id));
	        return  "recipe/recipeform";
	}
	
	
}
