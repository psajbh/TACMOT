package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureTransformer;

public class UnitOfMeasureServiceImplTest {
	
	UnitOfMeasureServiceImpl unitOfMeasureService;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Mock
	UnitOfMeasureTransformer unitOfMeasureTransformer;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureTransformer);
	}

	@Test
	public void testListAllUoms() {
		//given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

		UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);
        
        UnitOfMeasureBean uomb1 = new UnitOfMeasureBean();
        UnitOfMeasureBean uomb2 = new UnitOfMeasureBean();
        
        
		when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
		when(unitOfMeasureTransformer.convert(uom1)).thenReturn(uomb1);
		when(unitOfMeasureTransformer.convert(uom2)).thenReturn(uomb2);
		//when
		Set<UnitOfMeasureBean> beans = unitOfMeasureService.listAllUoms();
		
		
		//then
		assertEquals(2, beans.size());
		verify(unitOfMeasureRepository, times(1)).findAll();
		
	}
	
	

}
