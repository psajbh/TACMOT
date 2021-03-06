package com.jhart.web.user;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
			User user = userService.findById(id);
			
			if (null != user) {
				Iterator<Todo> todos = todoService.listAll().iterator();
				while(todos.hasNext()) {
					Todo todo = todos.next();
					
					User deleteUser = todo.getUser();
					if (null == deleteUser) {
						continue;
					}
				
					if (deleteUser.equals(user)) {
						if (todo.isComplete()) {
							//if todo is complete, and user is being deleted then delete the todo.
							todoService.delete(todo);
						}
						else {
							//if todo is not complete, save the todo with null user..
							todo.setUser(null);
							todoService.save(todo);
						}
					}
				}
				
				userService.delete(user);
				log.debug("deleteUser- successfully deleted user id: " + id);
			}
			else {
				log.error("deleteUser- failure to delete user with id: " + id);
			}
		}
		catch(Exception e) {
			log.error("deleteUser- exception: " + e.getMessage(),e);
		}
		
		return "users/index";
	}

}
