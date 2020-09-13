package com.jhart.orchestration.task.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jhart.domain.Todo;
import com.jhart.dto.TodoBackBean;
import com.jhart.dto.UserBackBean;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.TodoTransformer;
import com.jhart.transform.UserTransformer;
import com.jhart.util.DateFormatter;
import com.jhart.web.task.UpdateTaskController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class TaskBaseConductor {
	
	protected TodoService todoService;
	protected TodoTransformer todoTransformer;
	protected UserService userService;
	protected UserTransformer userTransformer;
	
	protected List<TodoBackBean> getTodoList() {
		log.debug("getTodoList- start");
		Iterable<Todo> todoItems = todoService.listAll();
		List<TodoBackBean> todoBackBeanAccumulator = new ArrayList<>();
		
		Iterator<Todo> items = todoItems.iterator();
		while(items.hasNext()) {
			Todo todo = items.next();
			
			TodoBackBean todoBackingBean = new TodoBackBean();
			todoBackingBean.setId(todo.getId());
			todoBackingBean.setTaskName(todo.getTaskName());
			
			if (null != todo.getUser()) {
				UserBackBean userBackBean = userTransformer.convertUserToUserBackBean(todo.getUser());
				todoBackingBean.setUser(userBackBean);
			}
			
			String createDate = DateFormatter.getStandardDate(todo.getCreateDate());
			todoBackingBean.setCreateDate(createDate);
			
			if (todo.isComplete()) {
				todoBackingBean.setComplete(UpdateTaskController.YES);
				todoBackingBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
			}
			else {
				todoBackingBean.setComplete(UpdateTaskController.NO);
				todoBackingBean.setCompleteDate(UpdateTaskController.EMPTY);
			}
			
			todoBackBeanAccumulator.add(todoBackingBean);
		
		}
		log.debug("getTodoList- done");
		return todoBackBeanAccumulator;
	}


}
