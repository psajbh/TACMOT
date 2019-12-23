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
import com.jhart.orchestration.user.UserConductor;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRestController {
	
	private UserConductor conductor;
	
	public UserRestController(UserConductor conductor) {
		this.conductor = conductor;
	}
	
	@GetMapping({"userDataTable"})
	public ResponseEntity<Object> getAllUsers(){
		boolean success = false;
		List<UserBackBean> userBackBeans = conductor.getAllUserBackBeans();
		if (null != userBackBeans) {
			success = true;
		}
		
		if (success) {
			return new ResponseEntity<Object>(userBackBeans,HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);
	}

}
