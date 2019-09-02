package com.jhart.service;

import org.springframework.stereotype.Service;

import com.jhart.domain.Todo;
import com.jhart.repo.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	TodoRepository todoRepository;
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public Iterable<Todo> listAll() {
		
		return todoRepository.findAll();
		
	}

}
