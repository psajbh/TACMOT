package guru.springframework.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.transform.recipe.RecipeBeanTransformer;
import guru.springframework.transform.recipe.RecipeTransformer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplIT {
	
	public static final String NEW_DESCRIPTION = "New Description";
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RecipeBeanTransformer recipeBeanTranformer;
	
	@Autowired
	RecipeTransformer recipeTransformer;


	@Transactional
	@Test
	public void testSaveOfDescription() {
		//given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
        RecipeBean testRecipeBean = recipeTransformer.convert(testRecipe);

        //when
        testRecipeBean.setDescription(NEW_DESCRIPTION);
        RecipeBean savedRecipeBean = recipeService.saveRecipeBean(testRecipeBean);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeBean.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeBean.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeBean.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeBean.getIngredients().size()); 
	}

}
