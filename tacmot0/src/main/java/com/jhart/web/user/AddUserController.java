package com.jhart.web.user;

import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhart.domain.User;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AddUserController {
	
	private UserService userService;
	
	public AddUserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("user/add")
	public String addNewUser(Model model) {
		log.debug("addNewUser - start");
		model.addAttribute("users", userService.listAll());
		model.addAttribute("user", new User());
		return "users/newuser";
	}
	
	@RequestMapping(value="/user/add",params="cancel",method=RequestMethod.POST)
	public String cancelNewUser(User user) {
		log.debug("cancelNewUser -> redirect:/index");
		return "redirect:/users/index";
	}
	
	@RequestMapping(value="/user/add", params="submit", method=RequestMethod.POST)
	public String saveNewUser(User user) {
		log.debug("saveNewUser - start");
		
		if (StringUtils.isEmpty(user.getName())){
			log.warn("saveNewUser - cannot persist user name");
			return "redirect:/users/index";
		}

		Iterator<User> items = userService.listAll().iterator();
		while(items.hasNext()) {
			User existingUser = items.next();
			if (existingUser.getName().equals(user.getName())) {
				if (existingUser.getName().contentEquals(user.getName())) {
					log.warn("saveNewUser - attempting to add a duplicate user: " + user.getName());
					return "redirect:/users/index";
				}
			}
		}
		
		user.setDateCreated(new Date());
		userService.save(user);
		log.debug("saveNewUser - saved user: " + user.getName());
		return "redirect:/users/index";		
	}

}
