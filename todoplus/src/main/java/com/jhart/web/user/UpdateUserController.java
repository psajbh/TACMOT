package com.jhart.web.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateUserController {
	
	private UserService userService;
	
	public UpdateUserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("user/update")
	public ResponseEntity<Object> updateUser(@RequestBody UserBackBean latestUserBackBean){
		log.debug("updateUser - start");
		UserBackBean updatedUserBackBean = userService.save(latestUserBackBean);
		// compare the updated with the lates.
		
		MyResponse<List<UserBackBean>> response = new MyResponse<>("success", getUserList());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	private List<UserBackBean> getUserList(){
		userService.listAll();
		return userService.listAll();
		
	}

}
