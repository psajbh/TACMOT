package com.jhart.web.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController {
	
	private UserService userService;
	private UserTransformer userTransformer;
	
	public UserRestController(UserService userService, UserTransformer userTransformer) {
		this.userService = userService;
		this.userTransformer = userTransformer;
	}
	
	@GetMapping({"userDataTable"})
	public ResponseEntity<Object> getAllUsers(){
		log.debug("getAllUsers - start");
		boolean success = false;
		List<UserBackBean> userBackBeanAccumulator = new ArrayList<>();
		try {
			Iterator<User> users = userService.listAll().iterator();
			while(users.hasNext()) {
				UserBackBean userBackBean = userTransformer.convertUserToUserBackBean(users.next());
				userBackBeanAccumulator.add(userBackBean);
			}
			success = true;	
		}
		catch(Exception e) {
			log.error("getAllUsers - " + e.getMessage(), e);
		}
		
		log.debug("getAllUsers - return success: " + success);
		
		if (success) {
			return new ResponseEntity<Object>(userBackBeanAccumulator,HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);
	}

}
