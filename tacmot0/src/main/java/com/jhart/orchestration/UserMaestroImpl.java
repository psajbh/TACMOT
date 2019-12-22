package com.jhart.orchestration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Qualifier("UserConductor")
@Component
public class UserMaestroImpl implements Conductor {
	
	private UserService userService;
	private UserTransformer userTransformer;

	
	public UserMaestroImpl(UserService userService, UserTransformer userTransformer) {
		this.userService = userService;
		this.userTransformer = userTransformer;
	}

	@Override
	public MyResponse<List<UserBackBean>> updateUser(UserBackBean userBackBean) {
		List<UserBackBean> userBeanAccumulator = new ArrayList<>();
		MyResponse<List<UserBackBean>> response = new MyResponse<>("success", userBeanAccumulator);
		return response;
	}
	
	private List<UserBackBean> getUserList(){
		List<UserBackBean> userBeanAccumulator = new ArrayList<>();
		Iterable<User> users = userService.listAll();
		
		Iterator<User> userItems = users.iterator();
		while(userItems.hasNext()) {
			userBeanAccumulator.add(userTransformer.convertUserToUserBackBean(userItems.next()));
		}
		
		return userBeanAccumulator;
		
	}

	
	

}
