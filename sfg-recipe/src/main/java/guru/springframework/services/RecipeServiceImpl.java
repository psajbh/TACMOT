package guru.springframework.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Set<Recipe> getRecipes() {
		log.debug("I'm a service");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	@Override
	public Recipe getRecipeById(String id) {
		Long recipeId = Long.valueOf(id);
		Optional<Recipe> o =  recipeRepository.findById(recipeId);
		
		if(!o.isPresent()) {
			throw new RuntimeException("Recipe not found.");
		}
		return o.get();
	}

}
