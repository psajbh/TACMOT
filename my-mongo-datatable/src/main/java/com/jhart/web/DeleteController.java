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
	
	public DeleteController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	//@ResponseBody
	@PostMapping("todo/delete/{id}")  //usting @DeleteMapping generates an exception here.
	//public ResponseEntity<String> deleteTodo(@PathVariable ObjectId id) {
	public String deleteTodo(@PathVariable ObjectId id) {
		try {
			log.debug("deleteTodo: deleting todo id: " + id.toString());
			String msg = "succesful delete";
			Todo todo = new Todo();
			todo.setId(id);
			todoService.delete(todo);
			log.debug("deleteTodo: " + msg);
			//return new ResponseEntity(msg, HttpStatus.OK);
			return "index";
		}
		catch(Exception e) {
			log.error("deletTodo: " + e.getMessage());
			//return new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			return "index";
		}
		
	}

}
