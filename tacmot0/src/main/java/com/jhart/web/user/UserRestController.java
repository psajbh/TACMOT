package com.jhart.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.dto.UserBackBean;
import com.jhart.orchestration.user.UserConductor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
public class UserRestController {
	
	private UserConductor conductor;
	
	public UserRestController(UserConductor conductor) {
		this.conductor = conductor;
	}
	
	@GetMapping({"userDataTable"})
	public ResponseEntity<Object> getAllUsers(HttpServletRequest request){
		log.debug("getAllUsers- start");
		boolean success = false;
		List<UserBackBean> userBackBeans = conductor.getAllUserBackBeans();
		if (null != userBackBeans) {
			success = true;
		}
		
		log.debug("getAllUsers- success: " + success);
		
		if (success) {
			return new ResponseEntity<Object>(userBackBeans,HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);
	}

}
