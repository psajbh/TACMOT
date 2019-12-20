
package com.jhart.service.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;
import com.jhart.repo.task.TodoRepository;
import com.jhart.service.exception.NotFoundException;
import com.jhart.transform.TodoTransformer;

@Service
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	private TodoTransformer todoTransformer;

	public TodoServiceImpl(TodoRepository todoRepository, TodoTransformer todoTransformer) {
		this.todoRepository = todoRepository;
		this.todoTransformer = todoTransformer;
	}

	@Transactional
	@Override
	public void delete(TodoBackBean todoBackBean) {
		Todo todo = todoTransformer.convertTodoBackBeanToTodo(todoBackBean);
		todoRepository.delete(todo);
	}

	@Transactional
	@Override
	public TodoBackBean save(TodoBackBean todoBackBean) {
		Todo todo = todoTransformer.convertTodoBackBeanToTodo(todoBackBean);
		Todo savedTodo = todoRepository.save(todo);
		return todoTransformer.convertTodoToTodoBackBean(savedTodo);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TodoBackBean> listAll() {
		List<TodoBackBean> todoBackBeanAccumulator = new ArrayList<>();
		Iterator<Todo> todoIterator = todoRepository.findAll().iterator();
		while (todoIterator.hasNext()) {
			todoBackBeanAccumulator.add(todoTransformer.convertTodoToTodoBackBean(todoIterator.next()));
		}
		return todoBackBeanAccumulator;
	}

	@Transactional(readOnly = true)
	@Override
	public TodoBackBean findById(Long id) throws NotFoundException {
		Optional<Todo> optionalTodo = todoRepository.findById(id);
		if (!optionalTodo.isPresent()) {
			throw new NotFoundException("Task not found for id: " + id.toString());
		}
		return todoTransformer.convertTodoToTodoBackBean(optionalTodo.get());
	}

}
