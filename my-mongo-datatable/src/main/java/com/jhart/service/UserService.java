package com.jhart.service;

import org.bson.types.ObjectId;

import com.jhart.domain.User;

public interface UserService {
	
	Iterable<User> listAll();
	User save(User todo);
	void delete(User todo);
	User findById(String string);
	

}
