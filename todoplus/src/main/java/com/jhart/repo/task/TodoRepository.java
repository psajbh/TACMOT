package com.jhart.repo.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import com.jhart.domain.Todo;

@Repository("todoRepository")
public interface TodoRepository extends CrudRepository<Todo, Long> /* ,QuerydslPredicateExecutor<Todo>*/{

}

/**
	long 				count()  - Returns the number of entities available.
	void 				delete(T entity) - Deletes a given entity.
	void 				deleteAll() - Deletes all entities managed by the repository.
	void 				deleteAll(Iterable<? extends T> entities) - Deletes the given entities.
	void 				deleteById(ID id) - Deletes the entity with the given id.
	boolean 			existsById(ID id) - Returns whether an entity with the given id exists.
	Iterable<T> 		findAll() - Returns all instances of the type.
	Iterable<T> 		findAllById(Iterable<ID> ids) - Returns all instances of the type T with the given IDs.
	Optional<T> 		findById(ID id - Retrieves an entity by its id.
	<S extends T> S 	save(S entity) - Saves a given entity.
	<S extends T> Iterable<S> 	saveAll(Iterable<S> entities) - Saves all given entities.
*/