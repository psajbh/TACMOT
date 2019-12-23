package com.jhart.orchestration.user;

import java.util.List;


import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;

public interface UserConductor {
	
	MyResponse<List<UserBackBean>> updateUser(UserBackBean userBackBean);
	List<User> getAllUsers();
	User save(User user);
	void deleteUser(Long id);
	List<UserBackBean> getAllUserBackBeans();
	

}
