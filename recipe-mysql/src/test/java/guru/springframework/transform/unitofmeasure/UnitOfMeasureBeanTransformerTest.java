package guru.springframework.transform.unitofmeasure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;

public class UnitOfMeasureBeanTransformerTest {
	
	public static final Long ID_VALUE = 1L;
	public static final String UOM_VALUE = "Uom Value";
	
	UnitOfMeasureBeanTransformer unitOfMeasureBeanTransformer;

	@Before
	public void setUp() throws Exception {
		unitOfMeasureBeanTransformer = new UnitOfMeasureBeanTransformer(); 
	}
	
	@Test
	public void testConvertofNullUnitOfMeasure() {
		UnitOfMeasureBean unitOfMeasureBean = null;
		UnitOfMeasure unitOfMeasureEntity = unitOfMeasureBeanTransformer.convert(unitOfMeasureBean);
		assertNull(unitOfMeasureEntity);
	}
	
	@Test
	public void testConvertOfEmptyUnitOfMeasure() {
		UnitOfMeasureBean unitOfMeasureBean = new UnitOfMeasureBean();
		UnitOfMeasure unitOfMeasureEntity = unitOfMeasureBeanTransformer.convert(unitOfMeasureBean);
		assertNotNull(unitOfMeasureEntity);
	}

	@Test
	public void testConvertUnitOfMeasure() {
		UnitOfMeasureBean unitOfMeasureBean = new UnitOfMeasureBean();
		unitOfMeasureBean.setId(ID_VALUE);
		unitOfMeasureBean.setUom(UOM_VALUE);
		UnitOfMeasure unitOfMeasureEntity = unitOfMeasureBeanTransformer.convert(unitOfMeasureBean);
		assertNotNull(unitOfMeasureEntity);
		assertEquals(ID_VALUE, unitOfMeasureEntity.getId());
		assertEquals(UOM_VALUE, unitOfMeasureEntity.getUom());
		
	}

}
