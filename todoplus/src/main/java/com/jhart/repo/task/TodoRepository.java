package com.jhart.repo.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import com.jhart.domain.Todo;

@Repository("todoRepository")
public interface TodoRepository extends CrudRepository<Todo, Long> /* ,QuerydslPredicateExecutor<Todo>*/{

}
