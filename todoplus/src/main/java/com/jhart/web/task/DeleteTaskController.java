package com.jhart.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;
import com.jhart.service.task.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteTaskController {
	
	TodoService todoService;
	
	public DeleteTaskController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping("todo/delete/{id}")  
	public String deleteTodo(@PathVariable Long id) {
		log.debug("deleteTodo-  start todo id: " + id);
		
		try {
			TodoBackBean todoBackBean = todoService.findById(id);
			if (null != todoBackBean) {
				todoService.delete(todoBackBean);
			}
			else {
				log.error("deleteTodo- failure to delete user with id: " + id);
			}
		}
		catch(Exception e) {
			log.error("deleteTodo-  " + e.getMessage());
		}
		return "task/index";
		
	}

}
