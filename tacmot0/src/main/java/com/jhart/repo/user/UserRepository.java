package com.jhart.repo.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhart.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	//https://www.baeldung.com/spring-data-jpa-query
	
	User findByLdapId(String ldapId);
		
//	@Query(value = "SELECT * FROM Users u WHERE u.status = 1", nativeQuery = true)
//    Collection<User> findAllActiveUsersNative();

}
