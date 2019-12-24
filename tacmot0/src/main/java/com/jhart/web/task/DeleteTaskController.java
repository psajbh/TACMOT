package com.jhart.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhart.domain.Todo;
import com.jhart.orchestration.task.TaskConductor;
import com.jhart.service.task.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteTaskController {
	
	private TaskConductor conductor;
	
	public DeleteTaskController(TaskConductor conductor) {
		this.conductor = conductor;
	}
	
	@PostMapping("todo/delete/{id}")  
	public String deleteTodo(@PathVariable Long id) {
		log.debug("deleteTodo-  start todo id: " + id);
		conductor.deleteTodo(id);
		return "task/index";
	}

}
