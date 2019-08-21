package guru.springframework.transform.ingredient;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.Ingredient;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureBeanTransformer;

public class IngredientBeanTransformerTest {
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);
	
	IngredientBeanTransformer ingredientBeanTransformer;
	UnitOfMeasureBeanTransformer unitOfMeasureBeanTransformer;

	@Before
	public void setUp() throws Exception {
		UnitOfMeasureBeanTransformer unitOfMeasureBeanTransformer = new UnitOfMeasureBeanTransformer();
		ingredientBeanTransformer = new IngredientBeanTransformer(unitOfMeasureBeanTransformer); 
	}
	
	@Test
	public void testConvertNullIngredientBean() {
		IngredientBean ingredientBean = null;
		Ingredient entity = ingredientBeanTransformer.convert(ingredientBean);
		assertNull(entity);
		
	}
	
	
	@Test
	public void testConvertEmptyIngredientBean() {
		IngredientBean ingredientBean = new IngredientBean();
		Ingredient entity = ingredientBeanTransformer.convert(ingredientBean);
		assertNull(entity);
	}

	@Test
	public void testConvertIngredientBeantoEntity() {
		IngredientBean ingredientBean = new IngredientBean();
		ingredientBean.setId(ID_VALUE);
		ingredientBean.setAmount(AMOUNT);
		ingredientBean.setDescription(DESCRIPTION);
		
		UnitOfMeasureBean unitOfMeasureBean = new UnitOfMeasureBean();
		unitOfMeasureBean.setId(UOM_ID);
		ingredientBean.setUom(unitOfMeasureBean);
		
		Ingredient entity = ingredientBeanTransformer.convert(ingredientBean);
		assertNotNull(entity);
		assertEquals(ID_VALUE, entity.getId());
		assertEquals(DESCRIPTION, entity.getDescription());
		assertEquals(AMOUNT, entity.getAmount());
		assertEquals(UOM_ID, entity.getUom().getId());
	}

}
