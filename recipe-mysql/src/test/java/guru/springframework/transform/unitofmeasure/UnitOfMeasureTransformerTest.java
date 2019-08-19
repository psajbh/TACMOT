package guru.springframework.transform.unitofmeasure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.transform.category.CategoryTransformer;

public class UnitOfMeasureTransformerTest {

	public static final Long ID_VALUE = 1L;
	public static final String UOM_VALUE = "Uom Value";
	UnitOfMeasureTransformer unitOfMeasureTransformer;


	@Before
	public void setUp() throws Exception {
		unitOfMeasureTransformer = new UnitOfMeasureTransformer(); 
	}
	
	@Test
	public void testConvertUnitOfMeasureEntityNull() {
		UnitOfMeasure unitOfMeasureEntity = null;
		UnitOfMeasureBean unitOfMeasureBean = unitOfMeasureTransformer.convert(unitOfMeasureEntity);
		assertNull(unitOfMeasureBean);
	}
	
	@Test
	public void testConvertUnitOfMeasureEntityEmpty() {
		UnitOfMeasure unitOfMeasureEntity = new UnitOfMeasure();
		UnitOfMeasureBean unitOfMeasureBean = unitOfMeasureTransformer.convert(unitOfMeasureEntity);
		assertNotNull(unitOfMeasureBean);
	}

	@Test
	public void testConvertUnitOfMeasureEntity() {
		UnitOfMeasure unitOfMeasureEntity = new UnitOfMeasure();
		unitOfMeasureEntity.setId(ID_VALUE);
		unitOfMeasureEntity.setUom(UOM_VALUE);
		UnitOfMeasureBean unitOfMeasureBean = unitOfMeasureTransformer.convert(unitOfMeasureEntity); 
		assertNotNull(unitOfMeasureBean);
		assertEquals(ID_VALUE, unitOfMeasureBean.getId());
		assertEquals(UOM_VALUE, unitOfMeasureBean.getUom());
	}

}
