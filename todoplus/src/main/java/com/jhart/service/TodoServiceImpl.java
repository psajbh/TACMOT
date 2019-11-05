package com.jhart.service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;

import org.springframework.stereotype.Service;

import com.jhart.domain.Todo;
import com.jhart.repo.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@Override
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
	
	public Todo findById(Long id){
		
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
		
		return null;
	}

}
