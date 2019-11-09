package com.jhart.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jhart.domain.Todo;
import com.jhart.service.TodoService;

@Controller
public class IndexController {
	
	//private TodoService todoService;
	
//	public IndexController(TodoService todoService) {
//		this.todoService = todoService;
//	}
	
	@GetMapping({"/task/index"})
	public String index(Model model) {
		return "task/index";
	}

}
