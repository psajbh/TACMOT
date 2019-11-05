package com.jhart.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhart.domain.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>{

}
