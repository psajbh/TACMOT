package com.jhart.orchestration.task.impl;

import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.TodoTransformer;

abstract class TaskBaseConductor {
	
	protected TodoService todoService;
	protected TodoTransformer todoTransformer;
	protected UserService userService;

}
