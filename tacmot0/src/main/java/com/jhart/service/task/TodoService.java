package com.jhart.service.task;

import com.jhart.domain.Todo;

public interface TodoService {
	Iterable<Todo> listAll();
	Todo save(Todo todo);
	void delete(Todo todo);
	Todo findById(Long id);
	
	
	

}
