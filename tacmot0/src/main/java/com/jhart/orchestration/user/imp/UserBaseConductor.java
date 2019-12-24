package com.jhart.orchestration.user.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;

abstract class UserBaseConductor {
	
	protected UserService userService;
	protected UserTransformer userTransformer;
	protected TodoService todoService;
	
	protected List<UserBackBean> getUserList(){
		List<UserBackBean> userBeanAccumulator = new ArrayList<>();
		Iterable<User> users = userService.listAll();
		
		Iterator<User> userItems = users.iterator();
		while(userItems.hasNext()) {
			userBeanAccumulator.add(userTransformer.convertUserToUserBackBean(userItems.next()));
		}
		
		return userBeanAccumulator;
	}
}
