package guru.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.transform.ingredient.IngredientBeanTransformer;
import guru.springframework.transform.ingredient.IngredientTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientTransformer ingredientTransformer;
	private final IngredientBeanTransformer ingredientBeanTransformer;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientTransformer ingredientTransformer, IngredientBeanTransformer ingredientBeanTransformer) {
		this.recipeRepository = recipeRepository;
		this.ingredientTransformer = ingredientTransformer;
		this.ingredientBeanTransformer = ingredientBeanTransformer;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
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

	@Override
	@Transactional
	public IngredientBean saveIngredient(IngredientBean ingredientBean) {
		// get the recipe
		Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientBean.getRecipeId());

		if (!recipeOptional.isPresent()) {
			// todo toss error if not found!
			log.error("Recipe not found for id: " + ingredientBean.getRecipeId());
			return new IngredientBean();
		} 
		else {
			Recipe recipe = recipeOptional.get();

			// get the ingredient from the recipe ingredients.
			// Optional<Ingredient> ingredientOptional = recipe.getIngredients()
			// .stream().filter(ingredient ->
			// ingredient.getId().equals(ingredientBean.getId()))
			// .findFirst();

			Ingredient ingredientFound = null;

			Set<Ingredient> ingredients = recipe.getIngredients();
			for (Ingredient ingredient : ingredients) {
				if (ingredient.getId().equals(ingredientBean.getId())) {
					ingredientFound = ingredient;
					break;
				}
			}

			if (null != ingredientFound) {
				ingredientFound.setDescription(ingredientBean.getDescription());
				ingredientFound.setAmount(ingredientBean.getAmount());

				UnitOfMeasureBean uomb = ingredientBean.getUom();
				Optional<UnitOfMeasure> oUnitOfMeasure = unitOfMeasureRepository.findById(uomb.getId());
				if (oUnitOfMeasure.isPresent()) {
					UnitOfMeasure uom = oUnitOfMeasure.get();
					ingredientFound.setUom(uom);
				} else {
					// add new Ingredient
					recipe.addIngredient(ingredientBeanTransformer.convert(ingredientBean));
				}

				Recipe savedRecipe = recipeRepository.save(recipe);

				// to do check for fail
				IngredientBean savedIngredientBean = ingredientTransformer.convert(savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientBean.getId()))
						.findFirst().get());

				return savedIngredientBean;
			}
			else {
				return null;
			}
		}
	}
}