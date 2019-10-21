package com.jhart.web;

import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhart.domain.Todo;
import com.jhart.service.TodoService;
import com.jhart.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AddController {
	
	private TodoService todoService;
	private UserService userService;
	
	public AddController(TodoService todoService, UserService userService) {
		this.todoService = todoService;
		this.userService = userService;
	}
	
	@GetMapping("todo/add")
	public String addNewTodo(Model model) {
		model.addAttribute("users", userService.listAll());
		model.addAttribute("todo", new Todo());
		
		return "newtodo";
	}
	
	@PostMapping("todo/add")
	public String saveNewTodo(Todo todo) {
		
		if (StringUtils.isEmpty(todo.getTaskName()) || StringUtils.isEmpty(todo.getUser())){
			log.warn("saveNewTodo: cannot persist task without a task name or a task owner (user)");
			return "index";
		}

		Iterator<Todo> items = todoService.listAll().iterator();
		while(items.hasNext()) {
			Todo existingTodo = items.next();
			if (existingTodo.getTaskName().equals(todo.getTaskName())) {
				if (existingTodo.getUser().getName().contentEquals(todo.getUser().getName())) {
					log.warn("attempting to add a duplicate todo");
					return "index";
				}
			}
		}
			
		todo.setComplete(false);
		todo.setCreateDate(new Date());
		Todo savedTodo = todoService.save(todo);
		log.debug("saveNewTodo: - saved todo: " + savedTodo.toString());
		return "index";		
	}

}
