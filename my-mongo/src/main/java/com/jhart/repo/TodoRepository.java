package com.jhart.repo;

import org.springframework.data.repository.CrudRepository;

import com.jhart.domain.Todo;

public interface TodoRepository extends CrudRepository<Todo, String>{

}
