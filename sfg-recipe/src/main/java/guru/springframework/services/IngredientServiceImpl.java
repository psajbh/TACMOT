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
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(ingredientBean.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(ingredientBean.getDescription());
				ingredientFound.setAmount(ingredientBean.getAmount());

				Optional<UnitOfMeasure> oUnitOfMeasure = unitOfMeasureRepository.findById(ingredientBean.getId());
				if (oUnitOfMeasure.isPresent()) {
					ingredientFound.setUom(oUnitOfMeasure.get());
				} else {
					throw new RuntimeException("unassociated unit of measure");
				}
			} else {
				Ingredient ingredient = ingredientBeanTransformer.convert(ingredientBean);
				ingredient.setRecipe(recipe);
				recipe.getIngredients().add(ingredient);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			boolean test = true;

			if (test) {

				Set<Ingredient> ingredients = savedRecipe.getIngredients();
				for (Ingredient i : ingredients) {
					if (i.getDescription().equals(ingredientBean.getDescription())) {
						return ingredientTransformer.convert(i);
					}
				}
				return ingredientBean;
			} else {
				// this also works, but I think after putting in a stream yuu have to use
				// Optional
				Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientBean.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientBean.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(ingredientBean.getUom().getId()))
						.filter(recipeIngredients -> recipeIngredients.getRecipe().getId().equals(ingredientBean.getRecipeId()))
						.findFirst();  //findFirst returns an Optional.

				if (savedIngredientOptional.isPresent()) {
					Ingredient savedIngredient = savedIngredientOptional.get();
					return ingredientTransformer.convert(savedIngredient);
				} else {
					log.error("failed to save ingredient");
					return ingredientBean;

				}

			}
		}
	}
}