package com.jhart.transform;

import org.springframework.stereotype.Component;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;

@Component
public class UserTransformerImpl implements UserTransformer{
	
	public UserBackBean convertUserToUserBackBean(User user) {
		UserBackBean userBackingBean = new UserBackBean();
		userBackingBean.setId(user.getId());
		userBackingBean.setName(user.getName());
		userBackingBean.setFirstName(user.getFirstName());
		userBackingBean.setLastName(user.getLastName());
		userBackingBean.setPhone(user.getPhone());
		userBackingBean.setEmail(user.getEmail());
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
