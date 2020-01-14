package mil.dtic.cbes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.User;
import mil.dtic.cbes.repositories.UserRepository;
import mil.dtic.cbes.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	
	// TODO: Update user service with CXE structure
	@Override
	public User findUserById(Integer id) {
		return userRepo.getById(id);
	}
	
	@Override
	public List<User> findAll(){
		return userRepo.findAll();
	}
}
