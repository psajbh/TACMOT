package com.jhart.orchestration.user.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.orchestration.user.UserConductor;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Qualifier("UserConductor")
@Component
public class UserConductorImpl extends UserBaseConductor implements UserConductor {

	public UserConductorImpl(UserService userService, TodoService todoService, 
			UserTransformer userTransformer) {
		super.userService = userService;
		super.todoService = todoService;
		super.userTransformer = userTransformer;
	}
	
	@Transactional
	@Override
	public User save(User user) {
		return userService.save(user);
	}
	
	@Transactional
	@Override
	public void deleteUser(Long id) {
		try {
			User user = userService.findById(id);

			if (null != user) {
				Iterator<Todo> todos = todoService.listAll().iterator();
				while (todos.hasNext()) {
					Todo todo = todos.next();
					User deleteUser = todo.getUser();
					
					if (null == deleteUser) {
						continue;
					}

					if (deleteUser.equals(user)) {
						if (todo.isComplete()) {
							// if todo is complete, and user is being deleted then delete the todo.
							todoService.delete(todo);
						} 
						else {
							// if todo is not complete, save the todo with null user..
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
		catch (Exception e) {
			log.error("deleteUser- exception: " + e.getMessage(), e);
		}
	}	
	
	@Override
	public List<User> getAllUsers(){
		List<User> userAccumulator = new ArrayList<>();
		Iterator<User> users = userService.listAll().iterator();
		while(users.hasNext()) {
			User user = users.next();
			userAccumulator.add(user);
		}
		
		return userAccumulator;
	}
	
	@Override
	public  List<UserBackBean> getAllUserBackBeans(){
		List<UserBackBean> userBackBeanAccumulator = new ArrayList<>();
		Iterator<User> users = userService.listAll().iterator();
		while(users.hasNext()) {
			UserBackBean userBackBean = userTransformer.convertUserToUserBackBean(users.next());
			userBackBeanAccumulator.add(userBackBean);
		}
		
		return userBackBeanAccumulator;
	}
	
	@Transactional
	@Override
	public MyResponse<List<UserBackBean>> updateUser(UserBackBean userBackBean) {
		log.debug("updateUser- start");
		User user = userService.findById(userBackBean.getId());

		if (null != user) {
			log.debug("updateUser- user found in database");
			User transformedUser = userTransformer.convertUserBackBeanToUser(userBackBean);
			try {
				userService.save(transformedUser);
				log.debug("updateUser - user updated and persisted");
				return new MyResponse<>("success", getUserList());
			}
			catch(Exception e) {
				if (e.getCause().getMessage().contains("Unique index or primary key violation")) {
					log.error("constraint violation exception", e);
				} 
				else {
					log.error(e.getMessage(), e);
				}
			}
			
			return new MyResponse<>("failure", getUserList());
		}
		else {
			log.debug("updateUser - user not found in database");
			return new MyResponse<>("failure", getUserList());
		}
	}
	
	

}
