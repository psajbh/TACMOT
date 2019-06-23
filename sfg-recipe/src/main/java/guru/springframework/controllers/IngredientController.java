package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use backing bean object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }
    
    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, 
    		@PathVariable String ingredientId, Model model) {
    	RecipeBean recipeBean = recipeService.getRecipeById(Long.valueOf(recipeId));
    	model.addAttribute("ingredient", ingredientService.getIngredientById(Long.valueOf(ingredientId),recipeBean));
    	return "recipe/ingredient/show";
    }

}


