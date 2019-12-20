package com.jhart.transform;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Component
public class UserTransformerImpl implements UserTransformer{
	 	
	public UserBackBean convertUserToUserBackBean(User user) {
		UserBackBean userBackBean = new UserBackBean();
		userBackBean.setId(user.getId());
		userBackBean.setName(user.getName());
		userBackBean.setLastName(user.getLastName());
		userBackBean.setFirstName(user.getFirstName());
		userBackBean.setPhone(user.getPhone());
		userBackBean.setEmail(user.getEmail());
		
		if (user.getTodos().size() > 0) {
			userBackBean.setHasTasks(true);
			log.debug("convertUserToUserBackBean- hasTasks set to true");
		}
		else {
			userBackBean.setHasTasks(false);
			log.debug("convertUserToUserBackBean- hasTasks set to false");
		}
		
		return userBackBean;
	}
	
	public User convertUserBackBeanToUser(UserBackBean userBackBean) {
		User user = new User();
		user.setId(userBackBean.getId());
		user.setName(userBackBean.getName());
		user.setFirstName(userBackBean.getFirstName());
		user.setLastName(userBackBean.getLastName());
		user.setPhone(userBackBean.getPhone());
		user.setEmail(userBackBean.getEmail());
		user.setDateCreated(new Date());
		return user;
	}
}
