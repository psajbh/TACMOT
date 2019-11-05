package com.jhart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
	
	@GetMapping({"/contacts"})
	public String contacts() {
		return "contacts";
	}


}
