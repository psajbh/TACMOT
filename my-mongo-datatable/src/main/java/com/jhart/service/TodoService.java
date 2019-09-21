package com.jhart.service;

import java.util.Optional;

import org.bson.types.ObjectId;

import com.jhart.domain.Todo;

public interface TodoService {
	Iterable<Todo> listAll();
	Todo save(Todo todo);
	void delete(Todo todo);
	Todo findById(ObjectId objectId);

}
