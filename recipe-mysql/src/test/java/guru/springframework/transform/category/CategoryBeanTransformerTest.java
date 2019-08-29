package guru.springframework.transform.category;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.model.Category;

public class CategoryBeanTransformerTest {
	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "description";
	
	CategoryBeanTransformer categoryBeanTransformer;

	@Before
	public void setUp() throws Exception {
		categoryBeanTransformer = new CategoryBeanTransformer();
	}

	@Test
	public void testConvertCategoryBean() {
		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setId(CategoryBeanTransformerTest.ID_VALUE);
		categoryBean.setDescription(CategoryBeanTransformerTest.DESCRIPTION);
		Category category = categoryBeanTransformer.convert(categoryBean);
		assertNotNull(category);
		assertEquals(CategoryBeanTransformerTest.ID_VALUE, category.getId());
	}
	
	@Test
	public void testEmptyCategoryBean() {
		CategoryBean categoryBean = new CategoryBean();
		Category category = categoryBeanTransformer.convert(categoryBean);
		assertNotNull(category);
	}
	
	@Test
	public void testNullCategoryBean() {
		CategoryBean categoryBean = null;
		Category category = categoryBeanTransformer.convert(categoryBean);
		assertNull(category);
	}
	

}
