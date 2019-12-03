package com.jhart.web.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController {
	
	private UserService userService;
	private UserTransformer userTransformer;
	
	public UserRestController(UserService userService, UserTransformer userTransformer) {
		this.userService = userService;
		this.userTransformer = userTransformer;
		
	}
	
	@GetMapping({"userDataTable"})
	public List<UserBackBean> getAllUsers(){
		Iterable<User> userEntities = userService.listAll();
		List<UserBackBean> userBackingBeans = new ArrayList<>();
		Iterator<User> userItems = userEntities.iterator();
		
		UserBackBean userBackBean;
		while(userItems.hasNext()) {
			User user = userItems.next();
			userBackBean = userTransformer.convertUserToUserBackBean(user);
			userBackingBeans.add(userBackBean);
		}
		return userBackingBeans;
		
		
	}
	
	

}
