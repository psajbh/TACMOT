package com.jhart.web;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhart.domain.Todo;
import com.jhart.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteController {
	
	TodoService todoService;
	AddController addController;
	
	public DeleteController(TodoService todoService, AddController addController) {
		this.todoService = todoService;
		this.addController = addController;
	}
	
	@PostMapping("todo/delete/{id}")  
	public String deleteTodo(@PathVariable ObjectId id) {
		try {
			log.debug("deleteTodo: deleting todo id: " + id.toString());
			String msg = "succesful delete";
			Todo todo = new Todo();
			todo.setId(id);
			todoService.delete(todo);
			log.debug("deleteTodo: " + msg);
			return "index";
		}
		catch(Exception e) {
			log.error("deletTodo: " + e.getMessage());
			//return new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			return "index";
		}
		
	}

}
