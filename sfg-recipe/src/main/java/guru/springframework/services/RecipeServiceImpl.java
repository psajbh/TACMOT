package guru.springframework.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.transform.recipe.RecipeBeanTransformer;
import guru.springframework.transform.recipe.RecipeTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeBeanTransformer recipeBeanTransformer;
	private final RecipeTransformer recipeTransformer;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepository, 
	        RecipeBeanTransformer recipeBeanTransformer, RecipeTransformer recipeTransformer) {
		this.recipeRepository = recipeRepository;
		this.recipeBeanTransformer= recipeBeanTransformer;
		this.recipeTransformer = recipeTransformer;
	}

	@Override
	public Set<RecipeBean> getRecipes() {
	    Set<RecipeBean> recipeBeanSet = new HashSet<>();
		Set<Recipe> recipeSet = new HashSet<>();
		
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		if (recipeSet.size() > 0) {
		      for (Recipe recipe: recipeSet) {
		            RecipeBean recipeBean = recipeTransformer.convert(recipe);
		            recipeBeanSet.add(recipeBean);
		        }
		}
		return recipeBeanSet;
	}
	
	@Override
	public RecipeBean getRecipeById(String id) {
	    RecipeBean recipeBean = null;
		Long recipeId = Long.valueOf(id);
		Optional<Recipe> o =  recipeRepository.findById(recipeId);
		
		if(!o.isPresent()) {
			throw new RuntimeException("Recipe not found.");
		}
		recipeBean = recipeTransformer.convert(o.get());
		return recipeBean;
	}
	
	@Override
	@Transactional
	public RecipeBean saveRecipeBean(RecipeBean recipeBean) {
		Recipe detachedRecipeEntity = recipeBeanTransformer.convert(recipeBean);
		Recipe savedRecipe = recipeRepository.save(detachedRecipeEntity);
		log.debug("Saved recipeId: " + savedRecipe.getId());
		return recipeTransformer.convert(savedRecipe);
	}
	

}
