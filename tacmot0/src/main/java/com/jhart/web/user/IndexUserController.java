package com.jhart.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexUserController {

	@GetMapping({"/users/index"})
	public String getUsers() {
		log.trace("getUsers - start");
		return "users/index";
	}
}
