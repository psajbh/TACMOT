package com.jhart.web;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jhart.domain.Todo;
import com.jhart.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteController {
	
	TodoService todoService;
	
	public DeleteController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("todo/delete/{id}")  //usting @DeleteMapping generates an exception here.
	public String deleteTodo(Model model, @PathVariable ObjectId id) {
		log.debug("deleteTodo: start");
		
		if (null != id) { 
			Todo todo = new Todo();
			todo.setId(id);
			todoService.delete(todo);
		}
		else {
			log.error("deleteTodo: failed to delete Todo with id: " +  id);
		}
			
		Iterable<Todo> todoItems = todoService.listAll();
		model.addAttribute("todos", todoItems);
		return "index";
	}

}
