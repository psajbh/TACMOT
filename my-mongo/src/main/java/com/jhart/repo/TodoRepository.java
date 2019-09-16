package com.jhart.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jhart.domain.Todo;

@Repository("todoRepository")
public interface TodoRepository extends MongoRepository<Todo, String>{

}
