package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhart.domain.GenericEntity;
import com.jhart.repo.GenericEntityRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
@SpringBootTest
public class GenericEntityTest {
	
    @Autowired
    private GenericEntityRepository genericEntityRepository;
 
    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
    	GenericEntity genericEntity = new GenericEntity();
    	genericEntity.setValue("test");
    	
    	GenericEntity savedEntity = genericEntityRepository.save(genericEntity);
    	List<GenericEntity> foundEntities = genericEntityRepository.findAll();
  
        assertNotNull(savedEntity);
        
        //assertEquals(genericEntity.getValue(), foundEntity.getValue());
    }	

}
