package guru.springframework.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.exceptions.NotFoundException;
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
	public List<RecipeBean> getRecipes() {
	    Set<RecipeBean> recipeBeanSet = new HashSet<>();
		Set<Recipe> recipeSet = new HashSet<>();
		
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		
		if (recipeSet.size() > 0) {
		      for (Recipe recipe: recipeSet) {
		            RecipeBean recipeBean = recipeTransformer.convert(recipe);
		            recipeBeanSet.add(recipeBean);
		        }
		}
		//How to sort a Set in Java example|https://studiofreya.com/java/how-to-sort-a-set-in-java-example/
		List<RecipeBean> recipeBeanSorted = recipeBeanSet.stream().collect(Collectors.toList());
		Collections.sort(recipeBeanSorted, (o1, o2) -> o1.getDescription().compareTo(o2.getDescription()));
		return recipeBeanSorted;
	}
	
	@Override
	public RecipeBean getRecipeById(Long id) {
	    RecipeBean recipeBean = null;
		Optional<Recipe> o =  recipeRepository.findById(id);
		
		if(!o.isPresent()) {
			throw new NotFoundException("Recipe not found for id: " + id.toString());
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
	
	@Override
	public void deleteById(Long id) {
	    recipeRepository.deleteById(id);
	}

}
