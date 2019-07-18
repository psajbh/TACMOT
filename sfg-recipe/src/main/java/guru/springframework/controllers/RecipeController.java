package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "recipe/recipeform";
    }

    @GetMapping("recipe/new")
    public String getNewRecipe(Model model) {
        model.addAttribute("recipe", new RecipeBean());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeBean recipeBean) {
        RecipeBean savedBean = recipeService.saveRecipeBean(recipeBean);
        return "redirect:/recipe/" + savedBean.getId() + "/show";
    }
    
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ModelAndView handleNotFound(Exception e){
//    	log.error("handleNotFound: Handling not found exception");
//    	log.error(e.getMessage());
//    	ModelAndView mav = new ModelAndView();
//    	mav.setViewName("404Error");
//    	mav.addObject("exception", e);
//    	return mav;
//    }
//    
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BadRequestException.class)
//    public ModelAndView handleBadRequest(Exception e){
//    	log.error("handleInvalidInput: Handling invalid input exception");
//    	log.error(e.getMessage());
//    	ModelAndView mav = new ModelAndView();
//    	mav.setViewName("400Error");
//    	mav.addObject("exception", e);
//    	return mav;
//    	
//    }

}
