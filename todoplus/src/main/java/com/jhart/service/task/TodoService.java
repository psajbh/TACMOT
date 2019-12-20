package com.jhart.service.task;

import java.util.List;

import com.jhart.command.TodoBackBean;
import com.jhart.service.exception.NotFoundException;


public interface TodoService {
	List<TodoBackBean> listAll();
	TodoBackBean save(TodoBackBean todoBackBean);
	void delete(TodoBackBean todoBackBean);
	TodoBackBean findById(Long id) throws NotFoundException;
}
