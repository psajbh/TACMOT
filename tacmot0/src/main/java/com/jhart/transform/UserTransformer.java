package com.jhart.transform;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;

public interface UserTransformer {
	UserBackBean convertUserToUserBackBean(User user);
	User convertUserBackBeanToUser(UserBackBean userBackBean);
}
