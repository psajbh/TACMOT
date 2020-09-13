package com.jhart.web.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhart.domain.Todo;
import com.jhart.orchestration.task.TaskConductor;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AddTaskController {
	
	private UserService userService;
	private TaskConductor conductor;
	
	public AddTaskController(UserService userService, TaskConductor conductor) {
		this.userService = userService;
		this.conductor = conductor;
	}
	
	@GetMapping("todo/add")
	public String addNewTodo(Model model) {
		log.debug("addNewTodo- start");
		model.addAttribute("users", userService.listAll());
		model.addAttribute("todo", new Todo());
		return "task/newtodo";
	}

	@RequestMapping(value="/todo/add",params="cancel",method=RequestMethod.POST)
	public String cancelNewTodo(Todo todo) {
		log.debug("cancelNewTodo- redirect:/index");
		return "redirect:/task/index";
	}
	
	@RequestMapping(value="/todo/add", params="submit", method=RequestMethod.POST)
	public String saveNewTodo(Todo todo) {
		
		if (StringUtils.isEmpty(todo.getTaskName()) || StringUtils.isEmpty(todo.getUser())){
			log.warn("saveNewTodo- cannot persist task without a task name or a task owner (user)");
			return "redirect:/task/index";
		}
		
		Todo savedTodo = conductor.save(todo);
		if (null == savedTodo) {
			log.warn("attempting to add a duplicate todo");
		}
		else {
			log.debug("saveNewTodo- saved todo: " + savedTodo.toString());
		}

		return "redirect:/task/index";		
	}

}
