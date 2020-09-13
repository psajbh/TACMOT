package com.jhart.web.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jhart.dto.UserBackBean;
import com.jhart.orchestration.user.UserConductor;

@Controller
public class UpdateUserController {
	
	private UserConductor conductor;
	
	public UpdateUserController(UserConductor conductor) {
		this.conductor = conductor;
	}
	
	@PostMapping("user/update")
	public ResponseEntity<Object> updateUser(@RequestBody UserBackBean userBackBean){
		return new ResponseEntity<Object>(conductor.updateUser(userBackBean), HttpStatus.OK);
	}
}
