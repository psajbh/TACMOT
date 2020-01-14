package mil.dtic.cbes.service;

import java.util.List;

import mil.dtic.cbes.model.User;

public interface UserService {
	User findUserById(Integer id);

	List<User> findAll();
}
