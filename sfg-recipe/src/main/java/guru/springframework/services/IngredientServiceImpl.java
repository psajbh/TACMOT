package guru.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.transform.ingredient.IngredientTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final IngredientTransformer ingredientTransformer;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientTransformer ingredientTransformer) {
		this.recipeRepository = recipeRepository;
		this.ingredientTransformer = ingredientTransformer;
	}

	@Override
	public IngredientBean getIngredientById(Long ingredientId, RecipeBean recipeBean) {

		List<IngredientBean> ingredientBeans = recipeBean.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId)).collect(Collectors.toList());
		if (ingredientBeans.size() > 0) {
			return ingredientBeans.get(0);
		}
		return null;

	}

}
