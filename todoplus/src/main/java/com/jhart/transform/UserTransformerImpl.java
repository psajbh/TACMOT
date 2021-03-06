package com.jhart.transform;

import org.springframework.stereotype.Component;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserTransformerImpl implements UserTransformer{
	
	UserService userService;
	
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

		try {
			if(newUser.getTodos().size() > 0) {  //this is the issue.
				userBackingBean.setHasTasks(true);
				log.debug("convertUserToUserBackBean- hasTasks set to true");
			}
			else {
				userBackingBean.setHasTasks(false);
				log.debug("convertUserToUserBackBean- hasTasks set to true");
			}
		}
		catch(Exception e) {
			log.error("convertUserToUserBackBean- exception capturing hasTasks, msg: " + e.getMessage(),e);
			userBackingBean.setHasTasks(null);
			log.debug("convertUserToUserBackBean- hasTasks set to NULL (Unknown");
			
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
