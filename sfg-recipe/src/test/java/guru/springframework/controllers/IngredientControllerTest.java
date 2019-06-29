package guru.springframework.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import guru.springframework.transform.ingredient.IngredientBeanTransformer;
import guru.springframework.transform.ingredient.IngredientTransformer;

public class IngredientControllerTest {
	
	IngredientController controller;

	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testListIngredients() throws Exception {

		// given
		RecipeBean recipeBean = new RecipeBean();

		// when
		when(recipeService.getRecipeById(anyLong())).thenReturn(recipeBean);
		mockMvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/list")).andExpect(model().attributeExists("recipe"));

		// then
		verify(recipeService, times(1)).getRecipeById(anyLong());
	}

	@Test
	public void testShowIngredient() throws Exception {
		// given
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(1L);
		IngredientBean ingredient = new IngredientBean();
		ingredient.setId(2L);
		recipeBean.getIngredients().add(ingredient);

		// when
		when(recipeService.getRecipeById(anyLong())).thenReturn(recipeBean);
		when(ingredientService.getIngredientById(anyLong(),any())).thenReturn(ingredient);

		// then
		mockMvc.perform(get("/recipe/1/ingredient/2/show")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show")).andExpect(model().attributeExists("ingredient"));
	}
	
	@Test
	public void testUpdateRecipeIngredient() throws Exception{
		 //given
        IngredientBean ingredientBean = new IngredientBean();
        ingredientBean.setId(3L);
        ingredientBean.setRecipeId(1L);
        
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setId(1L);
        recipeBean.getIngredients().add(ingredientBean);
        
        Set<UnitOfMeasureBean> uomSet = new HashSet<>();
        UnitOfMeasureBean uom1 = new UnitOfMeasureBean();
        uomSet.add(uom1);

        //when
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipeBean);
        when(ingredientService.getIngredientById(anyLong(), any())).thenReturn(ingredientBean);
        when(unitOfMeasureService.listAllUoms()).thenReturn(uomSet);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));		
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		// given
		IngredientBean bean = new IngredientBean();
		bean.setId(3L);
		bean.setRecipeId(2L);

		// when
		when(ingredientService.saveIngredient(any())).thenReturn(bean);

		// then
		mockMvc.perform(post("/recipe/2/ingredient")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

	}	
	
	@Test
    public void testNewIngredientForm() throws Exception {
        //given
        RecipeBean recipeBean= new RecipeBean();
        recipeBean.setId(1L);

        //when
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipeBean);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(recipeService, times(1)).getRecipeById(anyLong());
	}

	 
}



