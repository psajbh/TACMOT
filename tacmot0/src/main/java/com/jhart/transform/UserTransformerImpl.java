package com.jhart.transform;

import org.springframework.stereotype.Component;

import com.jhart.domain.User;
import com.jhart.dto.UserBackBean;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserTransformerImpl implements UserTransformer {

	private UserService userService;

	public UserTransformerImpl(UserService userService) {
		this.userService = userService;
	}

	public UserBackBean convertUserToUserBackBean(User user) {
		UserBackBean userBackingBean = new UserBackBean();
		User newUser = userService.findById(user.getId());
		userBackingBean.setId(newUser.getId());
		userBackingBean.setName(newUser.getName());
		userBackingBean.setFirstName(newUser.getFirstName());
		userBackingBean.setLastName(newUser.getLastName());
		userBackingBean.setPhone(newUser.getPhone());
		userBackingBean.setEmail(newUser.getEmail());

		if (null != newUser.getTodos() && newUser.getTodos().size() > 0) {
			userBackingBean.setHasTasks(true);
			log.trace("convertUserToUserBackBean- hasTasks set to true for user: " + newUser.getName());
		} 
		else {
			userBackingBean.setHasTasks(false);
			log.trace("convertUserToUserBackBean- hasTasks set to false for user: " + newUser.getName());
		}

		return userBackingBean;
	}

	public User convertUserBackBeanToUser(UserBackBean userBackBean) {
		User user = new User();
		user.setId(userBackBean.getId());
		user.setName(userBackBean.getName());
		user.setFirstName(userBackBean.getFirstName());
		user.setLastName(userBackBean.getLastName());
		user.setPhone(userBackBean.getPhone());
		user.setEmail(userBackBean.getEmail());
		return user;
	}
}
