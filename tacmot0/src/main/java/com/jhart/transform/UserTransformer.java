package com.jhart.transform;

import com.jhart.domain.User;
import com.jhart.dto.UserBackBean;

public interface UserTransformer {
	UserBackBean convertUserToUserBackBean(User user);
	User convertUserBackBeanToUser(UserBackBean userBackBean);
}
