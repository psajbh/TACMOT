package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository); 
	}

	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();
		Set<Recipe> recipeData = new HashSet<>();
		recipeData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipeData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(),1);
		
		// use verify to insure the actions in the class are as expected.
		Mockito.verify(recipeRepository, times(1)).findAll();
		
	}

}
