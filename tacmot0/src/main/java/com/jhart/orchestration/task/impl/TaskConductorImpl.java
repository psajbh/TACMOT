package com.jhart.orchestration.task.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jhart.orchestration.task.TaskConductor;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.TodoTransformer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Qualifier("TaskConductor")
@Component
public class TaskConductorImpl extends TaskBaseConductor implements TaskConductor {
	
//	private TodoService todoService;
//	private UserService userService;
//	TodoTransformer todoTransformer;
	
	public TaskConductorImpl(TodoService todoService, UserService userService, 
			TodoTransformer todoTransformer) {
		
		super.todoService = todoService;
		super.userService = userService;
		super.todoTransformer = todoTransformer;
		
	}

}
