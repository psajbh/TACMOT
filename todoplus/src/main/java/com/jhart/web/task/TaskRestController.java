package com.jhart.web.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.service.TodoService;
import com.jhart.transform.UserTransformer;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TaskRestController {
	
	private TodoService todoService;
	private UserTransformer userTransformer;
	
	public TaskRestController(TodoService todoService, UserTransformer userTransformer){
		this.todoService = todoService;
		this.userTransformer = userTransformer;
	}
	
	@GetMapping({"todoDataTable"})
	public List<TodoBackBean> getAllTasks() {
		log.debug("getAllTasks: - start");
		Iterable<Todo> todoItems = todoService.listAll();
		List<TodoBackBean> beans = new ArrayList<>();
		
		Iterator<Todo> items = todoItems.iterator();
		while(items.hasNext()) {
			Todo todo = items.next();
			User user = todo.getUser();
			TodoBackBean todoBackingBean = new TodoBackBean();
			todoBackingBean.setId(todo.getId());
			todoBackingBean.setTaskName(todo.getTaskName());
			String createDate = DateFormatter.getStandardDate(todo.getCreateDate());
			todoBackingBean.setCreateDate(createDate);
			
			if (todo.isComplete()) {
				todoBackingBean.setComplete("Yes");
				todoBackingBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
			}
			else {
				todoBackingBean.setComplete("No");
				todoBackingBean.setCompleteDate(" ");
			}
			
			UserBackBean userBackBean;
			
			if (null != user) {
				userBackBean = userTransformer.convertUserToUserBackBean(user);
			}
			else {
				userBackBean = new UserBackBean();
			}
					
			todoBackingBean.setUser(userBackBean);		
			
			beans.add(todoBackingBean);
		}
		
		log.debug("getAllTasks: returning beans: " + beans);
		return beans;
	}

}
