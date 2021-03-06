package com.jhart.web.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;
import com.jhart.service.task.TodoService;
import com.jhart.transform.TodoTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TaskRestController {
	
	private TodoService todoService;
	private TodoTransformer todoTransformer;
	
	public TaskRestController(TodoService todoService, TodoTransformer todoTransformer){
		this.todoService = todoService;
		this.todoTransformer = todoTransformer;
	}
	
	@GetMapping({"todoDataTable"})
	public ResponseEntity<Object> getAllTasks() {
		log.debug("getAllTasks - start");
		boolean success = false;
		List<TodoBackBean> todoBackBeanAccumlator = new ArrayList<>();
		try {
			Iterator<Todo> todos = todoService.listAll().iterator();
			while(todos.hasNext()) {
				TodoBackBean todoBackBean = todoTransformer.convertTodoToTodoBackBean(todos.next());
				todoBackBeanAccumlator.add(todoBackBean);
			}
			success = true;
		}
		catch(Exception e) {
			log.error("getAllTasks - " + e.getMessage(), e);
		}
		
		log.debug("getAllTasks - return success: " + success);
		
		if (success) {
			return new ResponseEntity<Object>(todoBackBeanAccumlator,HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);
	}

}
