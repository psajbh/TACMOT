package com.jhart.service.user;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.repo.user.UserRepository;

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
	
	//TODO: call repo directly.
	
	@Override
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
	
	@Override
	public User findByLdapId(String ldapId) {
		//List<User> users = userRepository.findByLdapId(ldapId);
		User user = userRepository.findByLdapId(ldapId);
		System.out.println();
		//return users.get(0);
		return user;
		
//		Iterator<User> users = this.listAll().iterator();
//		while(users.hasNext()){
//			User user = users.next();
//			if (user.getLdapId().equals(ldapId)) {
//				return user;
//			}
//			else {
//				continue;
//			}
//		}
//		
//		return null;
		
	}

}
