package com.jhart.web.user;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhart.domain.User;
import com.jhart.orchestration.user.UserConductor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AddUserController {
	
	private UserConductor conductor;
	
	public AddUserController(UserConductor conductor) {
		this.conductor = conductor;
	}
	
	@GetMapping("user/add")
	public String addNewUser(Model model) {
		log.debug("addNewUser- start");
		model.addAttribute("users", conductor.getAllUsers());
		model.addAttribute("user", new User());
		log.debug("addNewUser- finished");
		return "users/newuser";
	}
	
	@RequestMapping(value="/user/add",params="cancel",method=RequestMethod.POST)
	public String cancelNewUser(User user) {
		log.debug("cancelNewUser -> redirect:/index");
		return "redirect:/users/index";
	}
	//TODO: look at pushing most of this coded to a single conductor call.
	@RequestMapping(value="/user/add", params="submit", method=RequestMethod.POST)
	public String saveNewUser(User user) {
		log.debug("saveNewUser - start");
		
		if (StringUtils.isEmpty(user.getName())){
			log.warn("saveNewUser - cannot persist user name");
			return "redirect:/users/index";
		}

		for (User existingUser : conductor.getAllUsers()){
			if (existingUser.getName().equals(user.getName())) {
				if (existingUser.getName().contentEquals(user.getName())) {
					log.warn("saveNewUser - attempting to add a duplicate user: " + user.getName());
					return "redirect:/users/index";
				}
			}
		}
		
		user.setDateCreated(new Date());
		User persistedUser = conductor.save(user);
		log.debug("saveNewUser - saved user: " + persistedUser.getName());
		return "redirect:/users/index";		
	}

}
