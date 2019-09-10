package com.jhart.web;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jhart.domain.Todo;
import com.jhart.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateController {
	
	TodoService todoService;
	
	public UpdateController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("todo/update/{id}")
	public String updateTodo(Model model, @PathVariable ObjectId id) {
		log.debug("updateTodo: start");

		
		Todo todo = todoService.findById(id);
		//model.addAttribute("todos", todoItems);
		return "index";
	}

}
