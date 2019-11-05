package com.jhart.service;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Iterable<User> listAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id){
		
		Iterator<User> users = this.listAll().iterator();
		while(users.hasNext()){
			User user = users.next();
			if (user.getId() == id) {
				return user;
			}
			else {
				continue;
			}
		}
		
		return null;
	}

}
