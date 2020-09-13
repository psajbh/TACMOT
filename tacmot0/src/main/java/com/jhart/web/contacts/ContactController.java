package com.jhart.web.contacts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
	
	@GetMapping({"/contacts/contacts"})
	public String contacts() {
		return "contacts/contacts";
	}


}
