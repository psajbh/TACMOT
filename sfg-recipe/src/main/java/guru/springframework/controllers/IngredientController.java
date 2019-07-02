package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
            UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use backing bean object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        RecipeBean recipeBean = recipeService.getRecipeById(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientService.getIngredientById(Long.valueOf(ingredientId), recipeBean));
        return "recipe/ingredient/show";
    }
    
    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId,
            Model model) {
        //RecipeBean recipeBean = recipeService.getRecipeById(Long.valueOf(recipeId));
        IngredientBean ingredientBean = ingredientService.getIngredientById(Long.valueOf(ingredientId), 
                recipeService.getRecipeById(Long.valueOf(recipeId)));
        
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(recipeId)));
        
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId,
            Model model) {
        model.addAttribute("ingredient", ingredientService.getIngredientById(Long.valueOf(ingredientId),
                recipeService.getRecipeById(Long.valueOf(recipeId))));
        model.addAttribute("uomList", uomService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientBean ingredientBean) {
        IngredientBean savedBean = null;

        try {
            savedBean = ingredientService.saveIngredient(ingredientBean);
        } catch (Exception e) {
            // go to error page.
            // return to error page.
        }

        if (null == savedBean || null == savedBean.getRecipeId() || null == savedBean.getId()) {
            // return to error page.
        }

        log.debug("saved receipe id:" + savedBean.getRecipeId());
        log.debug("saved ingredient id:" + savedBean.getId());

        return "redirect:/recipe/" + savedBean.getRecipeId() + "/ingredient/" + savedBean.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {

        // make sure we have a good id value
        RecipeBean recipeBean = recipeService.getRecipeById(Long.valueOf(recipeId));
        if (null == recipeBean) {
            throw new RuntimeException("failed to get Recipe");
        }

        // need to return back parent id for hidden form property
        IngredientBean ingredientBean = new IngredientBean();
        ingredientBean.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientBean);

        // init uom
        ingredientBean.setUom(new UnitOfMeasureBean());

        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }    
    
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
