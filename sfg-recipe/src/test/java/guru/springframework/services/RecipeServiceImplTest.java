package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.backbeans.RecipeBean;
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
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setId(1L);
        
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipeData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipeData);
		when(recipeTransformer.convert(recipe)).thenReturn(recipeBean);
		
		Set<RecipeBean> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(),1);
		
		// use verify to insure the actions in the class are as expected.
		Mockito.verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetRecipeById() throws Exception{
	    RecipeBean recipeBean = new RecipeBean();
	    recipeBean.setId(1L);
	    
		Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeTransformer.convert(recipe)).thenReturn(recipeBean);
        
        RecipeBean recipeReturned = recipeService.getRecipeById(String.valueOf(recipe.getId()));

        assertNotNull(recipeReturned);
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();		
	}
	
	@Test
	public void testDeleteById() throws Exception {
	    //given 
	    Long idToDelete = Long.valueOf(2L);
	    
	    //when
	    //no 'when', since method has void return type
	    
	    recipeService.deleteById(idToDelete);
	     
	    //then 
	    verify(recipeRepository, times(1)).deleteById(anyLong()); 
	}

	@Test
	public void testSaveRecipeBean() throws Exception{
	    String test = "test";
	    Long two = 2L;
	    
	    RecipeBean recipeBean = new RecipeBean();
	    recipeBean.setDescription(test);
	    
	    Recipe recipe = new Recipe();
	    recipe.setDescription(test);
	    
	    Recipe savedRecipe = new Recipe();
	    savedRecipe.setId(two);
	    savedRecipe.setDescription(test);
	    
	    RecipeBean savedRecipeBean = new RecipeBean();
	    savedRecipeBean.setId(two);
	    savedRecipeBean.setDescription(test);
	    
	    when(recipeBeanTransformer.convert(recipeBean)).thenReturn(recipe);
	    when(recipeRepository.save(any())).thenReturn(savedRecipe);
	    when(recipeTransformer.convert(savedRecipe)).thenReturn(savedRecipeBean);
	    
	    savedRecipeBean = recipeService.saveRecipeBean(savedRecipeBean);
	    assertNotNull(savedRecipeBean);
	}
	
	
	

}
