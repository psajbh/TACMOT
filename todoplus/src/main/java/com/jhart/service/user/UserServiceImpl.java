package com.jhart.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.repo.user.UserRepository;
import com.jhart.service.exception.NotFoundException;
import com.jhart.transform.UserTransformer;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private UserTransformer userTransformer;
	
	public UserServiceImpl(UserRepository userRepository, UserTransformer userTransformer) {
		this.userRepository = userRepository;
		this.userTransformer = userTransformer;
	}
	
	@Transactional
	@Override
	public void delete(UserBackBean userBackBean) {
		User user = userTransformer.convertUserBackBeanToUser(userBackBean);
		userRepository.delete(user);
	}

	@Transactional
	@Override
	public UserBackBean save(UserBackBean userBackBean) {
		User user = userTransformer.convertUserBackBeanToUser(userBackBean);
		User savedUser = userRepository.save(user);
		return (userTransformer.convertUserToUserBackBean(savedUser));
	}
	
	@Override
	public Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserBackBean> listAllUserBackBeans() {
		List<UserBackBean> userBackBeanAccumulator = new ArrayList<>();
		Iterator<User> userIterator = userRepository.findAll().iterator();
		while(userIterator.hasNext()) {
			UserBackBean userBackBean = userTransformer.convertUserToUserBackBean(userIterator.next());
			userBackBeanAccumulator.add(userBackBean);
		}
		return userBackBeanAccumulator;
	}
	
	@Transactional(readOnly = true)
	@Override
	public UserBackBean findById(Long id) throws NotFoundException{
		Optional<User> optionalUser	= userRepository.findById(id);	
		if(!optionalUser.isPresent()) {
			throw new NotFoundException("Task not found for id: " + id.toString());
		}
		return userTransformer.convertUserToUserBackBean(optionalUser.get());
	}

}
