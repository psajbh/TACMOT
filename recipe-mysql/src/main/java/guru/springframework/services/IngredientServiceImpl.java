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
		Ingredient savedIngredient = null; 

		if (!recipeOptional.isPresent()) {
			// todo toss error if not found!
			log.error("Recipe not found for id: " + ingredientBean.getRecipeId());
			return null;
		} 
		else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(ingredientBean.getId())).findFirst();

			if (ingredientOptional.isPresent()) { //updated ingredient
				savedIngredient = processUpdateIngredient(ingredientOptional.get(), ingredientBean);
			} 
			else { // new ingredient
				processNewIngredient(ingredientBean, recipe);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);
			
			// used for processing result in different ways
			// old fashion vs. Java 8 way to process.
			boolean test = true;
			
			return processSavedIngredient(savedRecipe, test, ingredientBean);

			//return null;
		}
		
	}
	
	private IngredientBean processSavedIngredient(Recipe savedRecipe, boolean processType, IngredientBean ingredientBean) {
		
		if (processType) {
			
			for (Ingredient i : savedRecipe.getIngredients()) {
				if (i.getId() == ingredientBean.getId()) { //update
					return ingredientTransformer.convert(i);
				}
				
				if (null != i.getDescription() && i.getDescription().equals(ingredientBean.getDescription())) {
					if (null != i.getAmount() && i.getAmount().equals(ingredientBean.getAmount())){
						if (null != i.getUom() && i.getUom().getId().equals(ingredientBean.getUom().getId())){
							return ingredientTransformer.convert(i);
						}
					}
				}
			}
			return null;
		} 
		else {
			Optional<Ingredient> savedIngredientOptional = null;
			Ingredient savedIngredient = null;
			
			if (null != ingredientBean.getId()) {
				savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientBean.getId()))
					.findFirst();  //findFirst returns an Optional.
			}
			else { //new
				
				savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientBean.getDescription()))
					.filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientBean.getAmount()))
					.filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(ingredientBean.getUom().getId()))
					.filter(recipeIngredients -> recipeIngredients.getRecipe().getId().equals(ingredientBean.getRecipeId()))
					.findFirst();  //findFirst returns an Optional.
			}

			if (savedIngredientOptional.isPresent()) {
				savedIngredient = savedIngredientOptional.get();
				return ingredientTransformer.convert(savedIngredient);
			} 
			else {
				return null;
//				log.error("failed to save ingredient");
//				throw new RuntimeException("failed to save ingredient: " + ingredientBean.toString());
			}
		}
	}
	
	private void processNewIngredient(IngredientBean ingredientBean, Recipe recipe) {
		Ingredient savedIngredient = ingredientBeanTransformer.convert(ingredientBean);
		savedIngredient.setRecipe(recipe);
		recipe.getIngredients().add(savedIngredient);
	}
	
	private Ingredient processUpdateIngredient(Ingredient ingredientFound, IngredientBean ingredientBean){
		ingredientFound.setDescription(ingredientBean.getDescription());
		ingredientFound.setAmount(ingredientBean.getAmount());

		Optional<UnitOfMeasure> oUnitOfMeasure = unitOfMeasureRepository.findById(ingredientBean.getId());
		if (oUnitOfMeasure.isPresent()) {
			ingredientFound.setUom(oUnitOfMeasure.get());
		} 
		else {
			throw new RuntimeException("unassociated unit of measure");
		}
		return ingredientFound;
	}
	
	@Override
	public void deleteById(Long recipeId, Long ingredientId) {
		 log.debug("Deleting ingredient - recipeId: " + recipeId + " ingredientId:" + ingredientId);

	        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

	        if(recipeOptional.isPresent()){
	            Recipe recipe = recipeOptional.get();
	            log.debug("found recipe");

	            Optional<Ingredient> ingredientOptional = recipe
	                    .getIngredients()
	                    .stream()
	                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
	                    .findFirst();

	            if(ingredientOptional.isPresent()){
	                log.debug("found Ingredient");
	                Ingredient ingredientToDelete = ingredientOptional.get();
	                ingredientToDelete.setRecipe(null);
	                recipe.getIngredients().remove(ingredientOptional.get());
	                recipeRepository.save(recipe);
	            }
	        } else {
	            log.debug("Recipe Id Not found. Id:" + recipeId);
	        }		
		
	}
}