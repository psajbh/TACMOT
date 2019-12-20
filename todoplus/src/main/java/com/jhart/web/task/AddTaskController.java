package com.jhart.web.task;

import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AddTaskController {
	
	private TodoService todoService;
	private UserService userService;
	
	public AddTaskController(TodoService todoService, UserService userService) {
		this.todoService = todoService;
		this.userService = userService;
	}
	
	@GetMapping("todo/add")
	public String addNewTodo(Model model) {
		log.debug("addNewTodo- start");
		model.addAttribute("users", userService.listAllUsers());
		model.addAttribute("todo", new Todo());
		log.debug("addNewTodo- end");
		return "task/newtodo";
	}

	@GetMapping(value="/todo/add",params="cancel")
	public String cancelNewTodo(Todo todo) {
		log.debug("cancelNewTodo- redirect:/index");
		return "redirect:/task/index";
	}
	
	@RequestMapping(value="/todo/add", params="submit", method=RequestMethod.POST)
	//@PostMapping(value="/todo/add", params="submit")
	public String saveNewTodo(Todo todo) {
		log.debug("saveNewTodo- start");
		if (!exists(todo)) {
			return "redirect:/task/index";
		}
		
		if (!isNotDuplicate(todo)){
			return "redirect:/task/index";
		}

		todo.setComplete("No");
		todo.setCreateDate(DateFormatter.getStandardDate(new Date()));
		
		Todo savedTodo = todoService.save(todo);
		log.debug("saveNewTodo- saved todo: " + savedTodo.toString());
		return "redirect:/task/index";		
	}
	
	private boolean exists(Todo todo) {
		if (StringUtils.isEmpty(todo.getTaskName()) || StringUtils.isEmpty(todo.getUser())){
			log.warn("saveNewTodo- cannot persist task without a task name or a task owner (user)");
			return false;
		}
		return true;
	}
	
	private boolean isNotDuplicate(Todo todo) {
		
		Iterator<TodoBackBean> items = todoService.listAll().iterator();
		while(items.hasNext()) {
			Todo existingTodo = items.next();
			if (existingTodo.getTaskName().equals(todoBackBean.getTaskName())) {
				if (existingTodo.getUser().getName().contentEquals(todoBackBean.getUser().getName())) {
					log.warn("attempting to add a duplicate todo");
					return false;
				}
			}
		}
		return true;
	}

}
