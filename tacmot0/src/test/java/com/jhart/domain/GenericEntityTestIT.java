package com.jhart.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhart.domain.GenericEntity;
import com.jhart.repo.GenericEntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenericEntityTestIT {
	
    @Autowired
    private GenericEntityRepository genericEntityRepository;
    
//    @Test
//    public void dummyTest() throws Exception {
//    	Integer five = 5;
//    	assertNotNull(five);
//    }
 
    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
    	GenericEntity genericEntity = new GenericEntity();
    	genericEntity.setValue("test");
    	
    	GenericEntity savedEntity = genericEntityRepository.save(genericEntity);
    	List<GenericEntity> foundEntities = genericEntityRepository.findAll();
  
        assertNotNull(savedEntity);
        
        //assertEquals(genericEntity.getValue(), foundEntity.getValue());
    }
    
    private List<GenericEntity> getGenericEntities() throws Exception {
    	List<GenericEntity> genericEntities = new ArrayList<>();
    	
    	GenericEntity ge1 = genericEntityRepository.save(new GenericEntity("north"));
    	genericEntities.add(ge1);
    	GenericEntity ge2 = genericEntityRepository.save(new GenericEntity("south"));
    	genericEntities.add(ge2);
    	GenericEntity ge3 = genericEntityRepository.save(new GenericEntity("east"));
    	genericEntities.add(ge3);
    	GenericEntity ge4 = genericEntityRepository.save(new GenericEntity("west"));
    	genericEntities.add(ge4);
    	return genericEntities;
    }
    
    //convert List to Map sample
    @Test
    public void convertListToMap() throws Exception {
    	List<GenericEntity> genericEntities = getGenericEntities();
    	
    	Map<Long, GenericEntity> map = genericEntities.stream().
    			collect(Collectors.toMap(
    						GenericEntity::getId, genericEntity -> genericEntity));
    	
    	assertEquals(map.size(), genericEntities.size());
    	
    }
    

}
