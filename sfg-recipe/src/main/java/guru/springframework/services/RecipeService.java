package guru.springframework.services;

import java.util.Set;

import guru.springframework.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
	Recipe getRecipeById(String id);
}
