package guru.springframework.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.exceptions.BadRequestException;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
	
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {
    	try {
    		model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
    	}
    	catch(NumberFormatException e) {
    		throw new BadRequestException("Invalid input for id: " + id);
    	}
        return "recipe/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return RecipeController.RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/new")
    public String getNewRecipe(Model model) {
        model.addAttribute("recipe", new RecipeBean());
        return RecipeController.RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    //Lesson 240
    //note: the @ModelAttribute recipe will be returned to the RECIPE_RECIPEFORM_URL if there are errors.
    // this model will have the errors that will be displayed on the page.
    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeBean recipeBean, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		
    		bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
    		
    		return RecipeController.RECIPE_RECIPEFORM_URL;
    		
    	}
        RecipeBean savedBean = recipeService.saveRecipeBean(recipeBean);
        return "redirect:/recipe/" + savedBean.getId() + "/show";
    }
    

}
