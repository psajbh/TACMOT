package com.jhart.service.user;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.repo.user.UserRepository;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@Override
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			log.warn("findById- id not found: " + id);
			// throw new NotFoundException("User Not Found. For ID value: " + id );
			return null;
		}

		return userOptional.get();
	}
	
	@Override
	public User findByLdapId(String ldapId) {
		//User user = userRepository.findByLdapId(ldapId);
		User user = userRepository.findByUsername(ldapId);
		return user;
	}

}
