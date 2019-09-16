package com.jhart.web;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		model.addAttribute("todo", todo);
		return "updateTodo";
	}
	
	@PostMapping("todo/update/save")
	public String save(Model model, Todo formTodo) {
		log.debug("save: formTodo: " + formTodo.toString());
		Todo updateTodo = todoService.findById(formTodo.getId());
		if (null == updateTodo.getCreateDate()){
			updateTodo.setCreateDate(new Date());
		}
		
		updateTodo.setTaskName(formTodo.getTaskName());
		updateTodo.setOwner(formTodo.getOwner());
		
		boolean formComplete = formTodo.isCompleted();
		boolean updateComplete = updateTodo.isCompleted();
		
		if (formComplete != updateComplete) {
			if (formComplete) {
				updateTodo.setCompleted(true);
				updateTodo.setCompletedDate(new Date());
			}
			else {
				updateTodo.setCompleted(false);
				updateTodo.setCompletedDate(null);
			}
		}
		
		updateTodo.setCompleted(formTodo.isCompleted());
		todoService.save(updateTodo);
		
		Iterable<Todo> todoItems = todoService.listAll();
		model.addAttribute("todos", todoItems);
		return "index";
	}

}
