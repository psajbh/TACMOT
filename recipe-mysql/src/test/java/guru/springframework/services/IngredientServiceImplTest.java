package guru.springframework.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

public class IngredientServiceImplTest {
	
	IngredientServiceImpl ingredientService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Mock
	IngredientTransformer ingredientTransformer;
	
	@Mock
	IngredientBeanTransformer ingredientBeanTransformer;
	

	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, 
				ingredientTransformer, ingredientBeanTransformer);
	}
	
	@Test
	public void testGetIngredientById() throws Exception{
		
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(1L);
		IngredientBean ingredientBean = new IngredientBean();
		ingredientBean.setId(2L);
		ingredientBean.setRecipeId(1L);
		recipeBean.getIngredients().add(ingredientBean);
		
		IngredientBean resultIngredientBean;
		resultIngredientBean = ingredientService.getIngredientById(ingredientBean.getId(), recipeBean);
		
		assertNotNull(resultIngredientBean);
		assertEquals(recipeBean.getId(),resultIngredientBean.getRecipeId());
		assertEquals(ingredientBean.getId(),resultIngredientBean.getId());
		
	}
	
	@Test
	public void testSaveUpdatedIngredient() throws Exception {
		
		//given
		IngredientBean ingredientBean = new IngredientBean();
		ingredientBean.setId(3L);
		ingredientBean.setRecipeId(1L);
		ingredientBean.setDescription("Test");
		ingredientBean.setAmount(new BigDecimal(1));
		UnitOfMeasureBean uomb = new UnitOfMeasureBean();
		uomb.setId(5L);
		ingredientBean.setUom(uomb);
		
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(1L);
		recipeBean.getIngredients().add(ingredientBean);
		//-------------------------
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(5L);
		Optional<UnitOfMeasure> oUnitOfMeasure = Optional.of(unitOfMeasure);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setUom(unitOfMeasure);
		recipe.getIngredients().add(ingredient);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		
		when(recipeRepository.findById(any())).thenReturn(recipeOptional);
		when(unitOfMeasureRepository.findById(anyLong())).thenReturn(oUnitOfMeasure);
		when(recipeRepository.save(any())).thenReturn(recipe);
		when(ingredientTransformer.convert(any())).thenReturn(ingredientBean);
		
		IngredientBean saveIngredientBean = ingredientService.saveIngredient(ingredientBean);
		
		assertNotNull(saveIngredientBean);
		assertEquals(Long.valueOf(3L),saveIngredientBean.getId());
		
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any(Recipe.class));
		
	}
	
	@Test
	public void testSaveNewIngredient() throws Exception {
		
		//given
		IngredientBean ingredientBean = new IngredientBean();
		ingredientBean.setRecipeId(1L);
		ingredientBean.setDescription("Test");
		ingredientBean.setAmount(new BigDecimal(1));
		UnitOfMeasureBean uomb = new UnitOfMeasureBean();
		uomb.setId(5L);
		ingredientBean.setUom(uomb);
		
		IngredientBean savedIngredientBean = ingredientBean;
		savedIngredientBean.setId(3L);
		
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(1L);
		recipeBean.getIngredients().add(ingredientBean);
		//-------------------------
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(5L);
		Optional<UnitOfMeasure> oUnitOfMeasure = Optional.of(unitOfMeasure);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		ingredient.setDescription(ingredientBean.getDescription());
		ingredient.setAmount(ingredientBean.getAmount());
		ingredient.setUom(oUnitOfMeasure.get());
		recipe.getIngredients().add(ingredient);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(any())).thenReturn(recipeOptional);
		when(unitOfMeasureRepository.findById(anyLong())).thenReturn(oUnitOfMeasure);
		when(ingredientBeanTransformer.convert(any())).thenReturn(ingredient);
		when(recipeRepository.save(any())).thenReturn(recipe);
		when(ingredientTransformer.convert(any())).thenReturn(savedIngredientBean);
		
		
		IngredientBean saveIngredientBean = ingredientService.saveIngredient(ingredientBean);
		
		assertNotNull(saveIngredientBean);
		assertEquals(Long.valueOf(3L),saveIngredientBean.getId());
		
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any(Recipe.class));
		
	}
	
	
	

}
