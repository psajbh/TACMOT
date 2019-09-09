package com.jhart.web;

import org.springframework.stereotype.Controller;

import com.jhart.service.TodoService;

@Controller
public class AddController {
	
	TodoService todoService;
	
	public AddController(TodoService todoService) {
		this.todoService = todoService;
	}
	
//	 @GetMapping("recipe/new")
//	    public String newRecipe(Model model){
//	        model.addAttribute("recipe", new RecipeCommand());
//
//	        return "recipe/recipeform";
//	    }

}
