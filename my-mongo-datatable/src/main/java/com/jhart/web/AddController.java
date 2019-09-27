package com.jhart.web;

import java.util.Date;

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
	
	TodoService todoService;
	
	public AddController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("todo/add")
	public String addNewTodo(Model model) {
		model.addAttribute("todo", new Todo());
		return "newtodo";
		
	}
	
	//todo call this from ajax.
	@PostMapping("todo/add")
	public String saveNewTodo(Todo todo,  Model model) {
//		if (StringUtils.isEmpty(todo)) {
//			
//		}
		if (StringUtils.isEmpty(todo.getTaskName())){
			log.warn("saveNewTodo: cannot persist task without a task name");
		}
		else {
			todo.setComplete(false);
			todo.setCreateDate(new Date());
			todoService.save(todo);
			
		}
		//todoService.listAll();
		//model.addAttribute("todos", todoService.listAll());
		return "index";
		//return "redirect:index";
		
	}

	
//	 @GetMapping("recipe/new")
//	    public String newRecipe(Model model){
//	        model.addAttribute("recipe", new RecipeCommand());
//
//	        return "recipe/recipeform";
//	    }

}
