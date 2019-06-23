package guru.springframework.services;

import java.util.Set;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Recipe;

public interface RecipeService {
	Set<RecipeBean> getRecipes();
	RecipeBean getRecipeById(Long id);
	RecipeBean saveRecipeBean(RecipeBean bean);
	void deleteById(Long id);
}
