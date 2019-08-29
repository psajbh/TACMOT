package guru.springframework.transform.ingredient;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.model.Ingredient;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureTransformer;

public class IngredientTransformerTest {
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);
    
	IngredientTransformer ingredientTransformer;
	UnitOfMeasureTransformer unitOfMeasureTransformer;


	@Before
	public void setUp() throws Exception {
		UnitOfMeasureTransformer unitOfMeasureTransformer = new UnitOfMeasureTransformer();
		ingredientTransformer = new IngredientTransformer(unitOfMeasureTransformer); 
	}

	@Test
	public void test() {
		Ingredient entity = new Ingredient();
		entity.setId(ID_VALUE);
		entity.setDescription(DESCRIPTION);
		entity.setAmount(AMOUNT);
		UnitOfMeasure uomEntity = new UnitOfMeasure();
		uomEntity.setId(UOM_ID);
		entity.setUom(uomEntity);
		IngredientBean bean = ingredientTransformer.convert(entity);
		assertNotNull(bean);
		assertEquals(AMOUNT, bean.getAmount());
		assertEquals(DESCRIPTION, bean.getDescription());
		assertEquals(ID_VALUE, bean.getId());
		assertEquals(UOM_ID, bean.getUom().getId());
		
	}

}
