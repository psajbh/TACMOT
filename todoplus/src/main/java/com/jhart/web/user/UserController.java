package com.jhart.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@GetMapping({"/users/index"})
	public String getUsers() {
		log.debug("getUsers: start");
		return "users/index";
	}
}
