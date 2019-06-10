package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.model.Person;

public abstract class  AbstractMapService<T extends BaseEntity, ID extends Long> {
	
	protected Map<Long, T> map = new HashMap<>();
	
	int getMapSize() {
		return map.size();
	}
	
	Set<T> findAll(){
	    // iterate through the map
	    // then insert into a LinkedHashSet
	    //for (Map.Entry<Long, T> entry : map.entrySet()) {
	        //Person person = (Person)entry.getValue();
	        //System.out.println("person: " + person);
	    //}
		return new HashSet<>(map.values());
	}
	
	T findById(ID id) {
		return map.get(id);
	}
	
	T save(T object) {
		if(object != null) {
            if(object.getId() == null){
                object.setId(getNextId());
            }

            map.put(object.getId(), object);
        } 
		else {
            throw new RuntimeException("Object cannot be null");
        }
		
        return object;
	}
	
	void deleteById(ID id) {
		map.remove(id);
	}
	
	void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	
	private Long getNextId(){

        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        }
        catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }

}
