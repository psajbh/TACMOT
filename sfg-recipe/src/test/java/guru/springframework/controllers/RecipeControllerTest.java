package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).
					setControllerAdvice(new ControllerExceptionHandler()).build();
	}

	@Test
	public void testGetRecipeById() throws Exception {
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(1L);

		when(recipeService.getRecipeById(anyLong())).thenReturn(recipeBean);

		mockMvc.perform(get("/recipe/1/show/")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetNewRecipe() throws Exception {

		mockMvc.perform(get("/recipe/new")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		RecipeBean backingBean = new RecipeBean();
		backingBean.setId(2L);

		when(recipeService.saveRecipeBean(any())).thenReturn(backingBean);

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string")
				.param("directions", "some directions"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/show"));
	}

	@Test
	public void testUpdateRecipe() throws Exception {
		RecipeBean backingBean = new RecipeBean();
		backingBean.setId(2L);

		when(recipeService.getRecipeById(anyLong())).thenReturn(backingBean);

		mockMvc.perform(get("/recipe/1/update")).andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testDeleteById() throws Exception {
		// since returns void, don't need to mock the service i.e. when()
		mockMvc.perform(get("/recipe/1/delete")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
		verify(recipeService, times(1)).deleteById(anyLong());

	}

	@Test
	public void testGetRecipeNotFound() throws Exception {

		when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isNotFound()).andExpect(view().name("404Error"));
	}

	@Test
	public void testGetRecipeNumberFormatException() throws Exception {

		mockMvc.perform(get("/recipe/asdf/show")).
		andExpect(status().isBadRequest()).andExpect(view().name("400Error"));
	}
	
	@Test
	public void testHttpStatusForClientError() throws Exception{
		HttpStatus.Series x = HttpStatus.Series.valueOf(HttpStatus.BAD_REQUEST);
		assertEquals(x, HttpStatus.Series.CLIENT_ERROR);
		
		HttpStatus.Series y = HttpStatus.Series.valueOf(HttpStatus.NOT_FOUND);
		assertEquals(y, HttpStatus.Series.CLIENT_ERROR);
		
	}
	
	@Test
    public void testPostNewRecipeFormValidationFail() throws Exception {
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setId(2L);

        when(recipeService.saveRecipeBean(any())).thenReturn(recipeBean);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeform"));
    }	

}
