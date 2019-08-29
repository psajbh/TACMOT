package guru.springframework.transform.recipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.NotesBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Category;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Notes;
import guru.springframework.model.Recipe;
import guru.springframework.transform.category.CategoryTransformer;
import guru.springframework.transform.ingredient.IngredientTransformer;
import guru.springframework.transform.note.NotesTransformer;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureTransformer;

public class RecipeTransformerTest {
	
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
	
    CategoryTransformer categoryTransformer;
    IngredientTransformer ingredientTransformer;
    NotesTransformer notesTransformer;
    UnitOfMeasureTransformer unitOfMeasureTransformer;
    RecipeTransformer recipeTransformer;


	@Before
	public void setUp() throws Exception {
	    categoryTransformer = new CategoryTransformer();
	    notesTransformer = new NotesTransformer();
	    unitOfMeasureTransformer = new UnitOfMeasureTransformer();
	    ingredientTransformer = new IngredientTransformer(unitOfMeasureTransformer);
	    recipeTransformer = new RecipeTransformer(categoryTransformer, ingredientTransformer, notesTransformer);
	}

	@Test
	public void testConvertNullRecipe() {
		Recipe recipeEntity = null;
		RecipeBean recipeBean = recipeTransformer.convert(recipeEntity);
		assertNull(recipeBean);
	}

	
	@Test
	public void testConvertEmptyRecipe() {
		Recipe recipeEntity = new Recipe();
		RecipeBean recipeBean = recipeTransformer.convert(recipeEntity);
		assertNotNull(recipeBean);
	}

	@Test
	public void testConvertRecipe() {
		Recipe recipeEntity = new Recipe();
		recipeEntity.setId(RECIPE_ID);
		recipeEntity.setCookTime(COOK_TIME);
		recipeEntity.setPrepTime(PREP_TIME);
		recipeEntity.setDescription(DESCRIPTION);
		recipeEntity.setDirections(DIRECTIONS);
		recipeEntity.setDifficulty(DIFFICULTY);
		recipeEntity.setServings(SERVINGS);
		recipeEntity.setSource(SOURCE);
		recipeEntity.setUrl(URL);
		
		Category categoryEntity1 = new Category();
		categoryEntity1.setId(CAT_ID_1);
		Category categoryEntity2 = new Category();
		categoryEntity2.setId(CAT_ID_2);
		recipeEntity.getCategories().add(categoryEntity1);
		recipeEntity.getCategories().add(categoryEntity2);
		
		Ingredient ingredientEntity1 = new Ingredient();
		ingredientEntity1.setId(INGRED_ID_1);
		Ingredient ingredientEntity2 = new Ingredient();
		ingredientEntity2.setId(INGRED_ID_2);
		recipeEntity.getIngredients().add(ingredientEntity1);
		recipeEntity.getIngredients().add(ingredientEntity2);
		
		Notes notesEntity = new Notes();
		notesEntity.setId(NOTES_ID);
		recipeEntity.setNotes(notesEntity);
		
		RecipeBean recipeBean = recipeTransformer.convert(recipeEntity);
		assertNotNull(recipeBean);
		assertEquals(RECIPE_ID, recipeBean.getId());
        assertEquals(COOK_TIME, recipeBean.getCookTime());
        assertEquals(PREP_TIME, recipeBean.getPrepTime());
        assertEquals(DESCRIPTION, recipeBean.getDescription());
        assertEquals(DIFFICULTY, recipeBean.getDifficulty());
        assertEquals(DIRECTIONS, recipeBean.getDirections());
        assertEquals(SERVINGS, recipeBean.getServings());
        assertEquals(SOURCE, recipeBean.getSource());
        assertEquals(URL, recipeBean.getUrl());
        assertEquals(NOTES_ID, recipeBean.getNotes().getId());
        assertEquals(2, recipeBean.getCategories().size());
        assertEquals(2, recipeBean.getIngredients().size());

		
	}

}
