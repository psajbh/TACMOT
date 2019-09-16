package com.jhart.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;
import com.jhart.service.TodoService;

@RestController
public class TaskRestController {
	
	private TodoService todoService;
	
	public TaskRestController(TodoService todoService){
		this.todoService = todoService;
	}
	
	@GetMapping({"/todoDataTable"})
	public List<TodoBackBean> getAllTasks(Model model) {
		Iterable<Todo> todoItems = todoService.listAll();
		
		List<TodoBackBean> beans = new ArrayList<>();
		
		Iterator<Todo> items = todoItems.iterator();
		while(items.hasNext()) {
			Todo todo = items.next();
			TodoBackBean todoBackingBean = new TodoBackBean();
			todoBackingBean.setId(todo.getId().toString());
			todoBackingBean.setTaskName(todo.getTaskName());
			todoBackingBean.setOwner(todo.getOwner());
			todoBackingBean.setCreateDate(todo.getCreateDate().toString());
			if (todo.isComplete()) {
				todoBackingBean.setComplete("Yes");
				todoBackingBean.setCompleteDate(todo.getCompleteDate().toString());
			}
			else {
				todoBackingBean.setComplete("No");
				todoBackingBean.setCompleteDate(" ");
			}
			
			beans.add(todoBackingBean);
		}
		
		return beans;
	}
	

}
