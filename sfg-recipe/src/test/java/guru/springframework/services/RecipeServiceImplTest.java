package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.transform.recipe.RecipeBeanTransformer;
import guru.springframework.transform.recipe.RecipeTransformer;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	RecipeBeanTransformer recipeBeanTransformer;
	
	@Mock
	RecipeTransformer recipeTransformer;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeBeanTransformer, recipeTransformer);
	}

	@Test
	public void testGetRecipes() {
		Set<Recipe> recipeData = new HashSet<>();
		Recipe recipe = new Recipe();
		recipeData.add(recipe);
		when(recipeRepository.findAll()).thenReturn(recipeData);
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(),1);
		
		// use verify to insure the actions in the class are as expected.
		Mockito.verify(recipeRepository, times(1)).findAll();
		
	}
	
	@Test
	public void testGetRecipeById() throws Exception{
		Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.getRecipeById(String.valueOf(recipe.getId()));

        assertNotNull(recipeReturned);
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();		
	}

}
