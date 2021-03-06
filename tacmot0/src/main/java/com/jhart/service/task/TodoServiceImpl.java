package com.jhart.service.task;

import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.domain.Todo;
import com.jhart.repo.task.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	private TodoRepository todoRepository;
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@Transactional
	@Override
	public void delete(Todo todo) {
		todoRepository.delete(todo);
	}

	@Transactional
	@Override
	public Todo save(Todo todo) {
		return todoRepository.save(todo);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<Todo> listAll() {
		return todoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Todo findById(Long id){
		
		Iterator<Todo> todos = this.listAll().iterator();
		while(todos.hasNext()){
			Todo todo = todos.next();
			if (todo.getId().equals(id)) {
				return todo;
			}
		}
		
		return null;
	}
	

}
