package com.jhart.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jhart.domain.Todo;
import com.jhart.service.TodoService;

@Controller
public class IndexController {
	
	private TodoService todoService;
	
	public IndexController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping({"", "/", "/index"})
	public String index(Model model) {
		Iterable<Todo> todoItems = todoService.listAll();
		model.addAttribute("todos", todoItems);
		return "index";
	}

}
