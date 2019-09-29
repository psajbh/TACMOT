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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AddController {
	
	private TodoService todoService;
	
//	private Todo lastSavedTodo = null;
	
	public AddController(TodoService todoService) {
		this.todoService = todoService;
	}
	
//	public void justDeleted(String task, String owner) {
//		log.debug("justDeleted: - task: " +task + " owner: " + owner);
//		if (lastSavedTodo.getTaskName().equals(task)) {
//			if (lastSavedTodo.getOwner().equals(owner)){
//				lastSavedTodo = null;
//			}
//		}
//	}
	
	@GetMapping("todo/add")
	public String addNewTodo(Model model) {
		model.addAttribute("todo", new Todo());
		return "newtodo";
	}
	
	@PostMapping("todo/add")
	public String saveNewTodo(Todo todo) {
		
		if (StringUtils.isEmpty(todo.getTaskName()) || StringUtils.isEmpty(todo.getOwner())){
			log.warn("saveNewTodo: cannot persist task without a task name or owner");
			return "index";
		}

		Iterator<Todo> items = todoService.listAll().iterator();
		while(items.hasNext()) {
			Todo existingTodo = items.next();
			if (existingTodo.getTaskName().equals(todo.getTaskName())) {
				if (existingTodo.getOwner().contentEquals(todo.getOwner())) {
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
