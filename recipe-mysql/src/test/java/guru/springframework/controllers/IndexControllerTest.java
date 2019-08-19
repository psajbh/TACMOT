package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.services.RecipeServiceImpl;

public class IndexControllerTest {
	
	@Mock
	Model model;

	@Mock
	RecipeServiceImpl recipeService;
	
	IndexController indexController;
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}
	
	@Test
	public void testMockMvc() throws Exception{
		// standaloneSetup does not bring up spring context. appropriate for unit test.
		// webAppContextSetup brings up context, appropriate for integration test.
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetIndexPage() throws Exception{
		//given
		List<RecipeBean> recipeBeans = new ArrayList<>();
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(1L);
		recipeBeans.add(recipeBean);
		recipeBean = new RecipeBean();
		recipeBean.setId(2L);
		recipeBeans.add(recipeBean);
		
		//when
		
		when(recipeService.getRecipes()).thenReturn(recipeBeans);
		// use this to validate values in an argument
		ArgumentCaptor<List<RecipeBean>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		String viewName = indexController.getIndexPage(model);
		
		//then
		assertEquals(viewName,"index");
		
		verify(recipeService, times(1)).getRecipes();
		//verify(model, times(1)).addAttribute(eq("recipes"), anySet());
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		List<RecipeBean> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
		
	}

}