package com.jhart.web.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jhart.command.TodoBackBean;
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
	private UserTransformer userTransformer;
	
	public UpdateUserController(UserService userService, UserTransformer userTransformer) {
		this.userService = userService;
		this.userTransformer = userTransformer;
	}
	
	@PostMapping("user/update")
	public ResponseEntity<Object> updateUser(@RequestBody UserBackBean userBackBean){
		log.debug("updateUser - start");
		User user = userService.findById(userBackBean.getId());
		
		if (null != user) {
			log.debug("updateUser - user found in database" );
			User transformedUser = userTransformer.convertUserBackBeanToUser(userBackBean);
			try {
				userService.save(transformedUser);
			}
			catch(Exception e) {
				if (e.getCause().getMessage().contains("Unique index or primary key violation")) {
					log.error("constraint violation exception", e);
				}
				else {
					log.error(e.getMessage(),e);
				}
				// TODO: do something.... 
			}
			log.debug("updateUser - user updated and persisted");
		}
		
		MyResponse<List<UserBackBean>> response = new MyResponse<>("success", getUserList());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	
	private List<UserBackBean> getUserList(){
		List<UserBackBean> userBeanAccumulator = new ArrayList<>();
		Iterable<User> users = userService.listAll();
		
		Iterator<User> userItems = users.iterator();
		while(userItems.hasNext()) {
			userBeanAccumulator.add(userTransformer.convertUserToUserBackBean(userItems.next()));
		}
		
		return userBeanAccumulator;
		
	}
	


}
