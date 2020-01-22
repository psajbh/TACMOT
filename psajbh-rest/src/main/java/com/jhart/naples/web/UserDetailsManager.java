package com.jhart.naples.web;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jhart.naples.rest.Role;
import com.jhart.naples.rest.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDetailsManager implements UserDetailsService {
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		log.debug("loadUserByUsername: ");
		User user = new User();
		user.setName("John");
		Role role = new Role();
		role.setName("R2AppMgr");
		user.setRole(role);
		
		
		
		return null;
	}
	

}
