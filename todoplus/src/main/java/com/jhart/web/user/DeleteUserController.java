package com.jhart.web.user;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteUserController {
	
	UserService userService;
	TodoService todoService;
	
	public DeleteUserController(UserService userService, TodoService todoService) {
		this.userService = userService;
		this.todoService = todoService;
	}
	
	@PostMapping("user/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		log.debug("deleteUser- start user id: " + id);
		
		try {
			UserBackBean userBackBean = userService.findById(id);
			
			if (null != userBackBean) {
				
				for(TodoBackBean todoBackBean : todoService.listAll()) {
					if (todoBackBean.getUser().getName().contentEquals(userBackBean.getName())){
						if (todoBackBean.getComplete().contentEquals("Yes")) {
							todoService.delete(todoBackBean);
						}
						else {
							todoBackBean.setUser(null);
							todoService.save(todoBackBean);
						}
							
					}
				}
				
				userService.delete(userBackBean);
				
			}	
		}
		catch(Exception e) {
			log.error("failed to delete user msg: " + e.getMessage(), e);
		}
		
		return "users/index";
	}

}
