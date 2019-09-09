package com.jhart.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jhart.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateController {
	
	TodoService todoService;
	
	public UpdateController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping
	public String updateTodo(Model model) {
		
		return "";
	}

}
