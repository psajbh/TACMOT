package com.jhart.service.user;


import java.util.List;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.service.exception.NotFoundException;

public interface UserService {
	
	List<UserBackBean> listAllUserBackBeans();
	UserBackBean save(UserBackBean userBackBean);
	void delete(UserBackBean userBackBean);
	UserBackBean findById(Long id) throws NotFoundException;
	
	Iterable<User> listAllUsers();
	
	

}
