package com.jhart.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhart.domain.Todo;
import com.jhart.service.task.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteTaskController {
	
	TodoService todoService;
	//AddTaskController addController;
	
	public DeleteTaskController(TodoService todoService/* , AddTaskController addController */) {
		this.todoService = todoService;
		//this.addController = addController;
	}
	
	@PostMapping("todo/delete/{id}")  
	public String deleteTodo(@PathVariable Long id) {
		try {
			log.debug("deleteTodo: deleting todo id: " + id.toString());
			String msg = "succesful delete";
			Todo todo = todoService.findById(id);
			if (null != todo) {
				todoService.delete(todo);
			}
			
			log.debug("deleteTodo: " + msg);
			return "task/index";
		}
		catch(Exception e) {
			log.error("deletTodo: " + e.getMessage());
			//return new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			return "task/index";
		}
		
	}

}
