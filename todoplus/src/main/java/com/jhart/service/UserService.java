package com.jhart.service;


import com.jhart.domain.User;

public interface UserService {
	
	Iterable<User> listAll();
	User save(User todo);
	void delete(User todo);
	User findById(Long id);
	

}
