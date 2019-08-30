package guru.springframework.transform.category;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.model.Category;

public class CategoryTransformerTest {
	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "description";
	
	CategoryTransformer categoryTransformer;


	@Before
	public void setUp() throws Exception {
		categoryTransformer = new CategoryTransformer();
	}

	@Test
	public void testCategory() {
		Category category = new Category();
		category.setId(ID_VALUE);
		category.setDescription(DESCRIPTION);
		CategoryBean categoryBean = categoryTransformer.convert(category);
		assertNotNull(categoryBean);
		assertEquals(ID_VALUE, categoryBean.getId());
		assertEquals(DESCRIPTION, categoryBean.getDescription());
	}
	
	@Test
	public void testEmptyCategory() {
		Category category = new Category();
		CategoryBean categoryBean = categoryTransformer.convert(category);
		assertNotNull(categoryBean);
	}
	
	@Test
	public void testNullCategory() {
		Category category = null;
		CategoryBean categoryBean = categoryTransformer.convert(category);
		assertNull(categoryBean);
	}
	

}
