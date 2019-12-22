package com.jhart.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhart.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

}
