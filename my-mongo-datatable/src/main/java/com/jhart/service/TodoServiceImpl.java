package com.jhart.service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.jhart.domain.Todo;
import com.jhart.repo.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	TodoRepository todoRepository;
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public void delete(Todo todo) {
		todoRepository.delete(todo);
	}

	@Override
	public Todo save(Todo todo) {
		
		//todoRepository
		return todoRepository.save(todo);
	}

	@Override
	public Iterable<Todo> listAll() {
		return todoRepository.findAll();
	}
	
	public Todo findById(ObjectId id){
		
		Iterator<Todo> todos = this.listAll().iterator();
		while(todos.hasNext()){
			Todo todo = todos.next();
			if (todo.getId().equals(id)) {
				return todo;
			}
			else {
				continue;
			}
		}
		
//		while(itr.hasNext()) {
//	         Object element = itr.next();
//	         System.out.print(element + " ");
//	      }
//		
//		
//		//Iterable<Todo> todos = this.listAll();
//		//Spliterator<Todo> todos = this.listAll().spliterator(),false}.map(Todo);
//		
////		return StreamSupport.stream(this.listAll().spliterator()
////                .spliterator(), false)
////                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
////                .collect(Collectors.toSet());
		
		return new Todo();
	}

}
