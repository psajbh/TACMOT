package guru.springframework.transform.recipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.NotesBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Recipe;
import guru.springframework.transform.category.CategoryBeanTransformer;
import guru.springframework.transform.ingredient.IngredientBeanTransformer;
import guru.springframework.transform.note.NotesBeanTransformer;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureBeanTransformer;

public class RecipeBeanTransformerTest {
	
	public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
	
	CategoryBeanTransformer categoryBeanTransformer;
	IngredientBeanTransformer ingredientBeanTransformer;
	NotesBeanTransformer notesBeanTransformer;
	UnitOfMeasureBeanTransformer unitOfMeasureBeanTransformer;
	RecipeBeanTransformer recipeBeanTransformer;

	@Before
	public void setUp() throws Exception {
		categoryBeanTransformer = new CategoryBeanTransformer();
		unitOfMeasureBeanTransformer = new UnitOfMeasureBeanTransformer();
		ingredientBeanTransformer = new IngredientBeanTransformer(unitOfMeasureBeanTransformer);
		notesBeanTransformer = new NotesBeanTransformer();
		recipeBeanTransformer = new RecipeBeanTransformer(categoryBeanTransformer, ingredientBeanTransformer, notesBeanTransformer);
	}
	
	@Test
	public void testConvertRecipeBeanNull() {
		RecipeBean bean = null;
		Recipe entity = recipeBeanTransformer.convert(bean);
		assertNull(entity);
	}
	
	
	@Test
	public void testConvertRecipeBeanEmpty() {
		RecipeBean bean = new RecipeBean();
		Recipe entity = recipeBeanTransformer.convert(bean);
		assertNotNull(entity);
	}

	@Test
	public void testConvertRecipeBean() {
		RecipeBean recipeBean = new RecipeBean();
		recipeBean.setId(RECIPE_ID);
		recipeBean.setCookTime(COOK_TIME);
		recipeBean.setPrepTime(PREP_TIME);
		recipeBean.setDescription(DESCRIPTION);
		recipeBean.setDirections(DIRECTIONS);
		recipeBean.setDifficulty(DIFFICULTY);
		recipeBean.setServings(SERVINGS);
		recipeBean.setSource(SOURCE);
		recipeBean.setUrl(URL);
		
		CategoryBean categoryBean1 = new CategoryBean();
		categoryBean1.setId(CAT_ID_1);
		CategoryBean categoryBean2 = new CategoryBean();
		categoryBean2.setId(CAT_ID_2);
		recipeBean.getCategories().add(categoryBean1);
		recipeBean.getCategories().add(categoryBean2);
		
		IngredientBean ingredientBean1 = new IngredientBean();
		ingredientBean1.setId(INGRED_ID_1);
		IngredientBean ingredientBean2 = new IngredientBean();
		ingredientBean2.setId(INGRED_ID_2);
		recipeBean.getIngredients().add(ingredientBean1);
		recipeBean.getIngredients().add(ingredientBean2);
		
		NotesBean notesBean = new NotesBean();
		notesBean.setId(NOTES_ID);
		recipeBean.setNotes(notesBean);
		
		Recipe entity = recipeBeanTransformer.convert(recipeBean);
		assertNotNull(entity);
		assertEquals(RECIPE_ID, entity.getId());
        assertEquals(COOK_TIME, entity.getCookTime());
        assertEquals(PREP_TIME, entity.getPrepTime());
        assertEquals(DESCRIPTION, entity.getDescription());
        assertEquals(DIFFICULTY, entity.getDifficulty());
        assertEquals(DIRECTIONS, entity.getDirections());
        assertEquals(SERVINGS, entity.getServings());
        assertEquals(SOURCE, entity.getSource());
        assertEquals(URL, entity.getUrl());
        assertEquals(NOTES_ID, entity.getNotes().getId());
        assertEquals(2, entity.getCategories().size());
        assertEquals(2, entity.getIngredients().size());
	}

}
