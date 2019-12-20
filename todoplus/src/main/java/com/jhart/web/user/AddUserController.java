package com.jhart.web.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.service.user.UserService;
import com.jhart.util.DateFormatter;

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
	
	private boolean exists(UserBackBean userBackBean) {
		if (StringUtils.isEmpty(userBackBean) || StringUtils.isEmpty(userBackBean.getName())){
			log.warn("saveNewTodo- cannot persist task without a task name or a task owner (user)");
			return false;
		}
		return true;
	}
	
	private boolean isNotDuplicate(UserBackBean userBackBean) {
		//List<UserBackBean> userBackBeans = userService.listAll();
		for(UserBackBean existingtUserBackBean : userService.listAll()) {
			if (existingtUserBackBean.getName().equals(userBackBean.getName())) {
				return false;
			}
		}
		return true;
	}
	

	
	@RequestMapping(value="/user/add", params="submit", method=RequestMethod.POST)
	public String saveNewUser(UserBackBean userBackBean) {
		log.debug("saveNewUser - start");
		
		if (!exists(userBackBean)) {
			return "redirect:/users/index";
		}
		
		if (isNotDuplicate(userBackBean)) {
			return "redirect:/users/index";
		}
		
		userService.save(userBackBean);
		log.debug("saveNewUser - saved user: " + userBackBean.getName());
		return "redirect:/users/index";		
	}

}
