package guru.springframework.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;

	@Before
	public void setUp() throws Exception {
		category = new Category();
	}

	@Test
	public void testGetId() {
		Long idValue = 4L;
		category.setId(idValue);
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() {
		String description = "category description";
		category.setDescription(description);
		assertEquals(description, category.getDescription());
	}
	
	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();
		String testDescription = "bullshit";
		recipe.setDescription(testDescription);
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(recipe);
		category.setRecipes(recipes);
		
		assertEquals(category.getRecipes().size(),1);
		assertEquals(category.getRecipes().iterator().next().getDescription(),testDescription);
		assertEquals((category.getRecipes()).stream().findFirst().get().getDescription(),testDescription);
	}
	

}
