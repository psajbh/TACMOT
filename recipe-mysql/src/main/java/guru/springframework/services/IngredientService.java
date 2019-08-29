package guru.springframework.services;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;

public interface IngredientService {
	
	IngredientBean getIngredientById(Long id, RecipeBean recipeBean);
	IngredientBean saveIngredient(IngredientBean ingredientBean) throws Exception;
	void deleteById(Long recipeId, Long ingredientId);
	

}
