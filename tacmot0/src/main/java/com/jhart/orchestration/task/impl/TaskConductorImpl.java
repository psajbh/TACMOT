package com.jhart.orchestration.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.dto.TodoBackBean;
import com.jhart.orchestration.task.TaskConductor;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.TodoTransformer;
import com.jhart.transform.UserTransformer;
import com.jhart.web.task.UpdateTaskController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Qualifier("TaskConductor")
@Component
public class TaskConductorImpl extends TaskBaseConductor implements TaskConductor {

	public TaskConductorImpl(TodoService todoService, UserService userService, 
			TodoTransformer todoTransformer,UserTransformer userTransformer) {
		super.todoService = todoService;
		super.userService = userService;
		super.todoTransformer = todoTransformer;
		super.userTransformer = userTransformer;
	}

	@Transactional
	@Override
	public Todo save(Todo todo) {
		log.debug("save- start");
		Iterator<Todo> items = todoService.listAll().iterator();

		while (items.hasNext()) {
			Todo existingTodo = items.next();
			if (existingTodo.getTaskName().equals(todo.getTaskName())) {
				if (existingTodo.getUser().getName().contentEquals(todo.getUser().getName())) {
					log.debug("save- duplicate userName record, will not save");
					return null;
				}
			}
		}

		todo.setComplete(false);
		todo.setCreateDate(new Date());
		log.debug("save- done");
		return todoService.save(todo);
	}

	@Transactional
	@Override
	public MyResponse<List<TodoBackBean>> updateTodo(TodoBackBean todoBackBean) {
		log.debug("updateTodo- start");
		MyResponse<List<TodoBackBean>> response = null;

		try {
			Todo todo = todoService.findById(todoBackBean.getId());
			todo.setTaskName(todoBackBean.getTaskName());

			for (User user : userService.listAll()) {
				log.debug("updateTodo- iterating user: " + user.getName());
				if (user.getName().equals(todoBackBean.getUser().getName())) {
					todo.setUser(user);
					log.debug("updateTodo- todo setUser to: " + user.getName() + " id: " + user.getId());
					break;
				}
			}

			// do not allow saving task as complete if there is no user.
			if (StringUtils.isEmpty(todoBackBean.getUser().getName())) {
				todo.setCompleteDate(null);
				todo.setComplete(false);
			} else {
				if (todoBackBean.getComplete().contentEquals(UpdateTaskController.YES)) {
					todo.setCompleteDate(new Date());
					todo.setComplete(true);
				} else {
					todo.setCompleteDate(null);
					todo.setComplete(false);
				}
			}

			todoService.save(todo);
			return new MyResponse<>("success", super.getTodoList());
		} catch (Exception e) {
			log.error("updateTodo- exception: " + e.getMessage(), e);

		}
		return response;
	}

	@Transactional
	@Override
	public void deleteTodo(Long id) {
		log.debug("deleteTodo- start");
		try {
			Todo todo = todoService.findById(id);
			if (null != todo) {
				todoService.delete(todo);
				log.debug("deleteTodo- executed");
			} else {
				log.error("deleteTodo- failure to delete user with id: " + id);
			}
		} catch (Exception e) {
			log.error("deleteTodo-  " + e.getMessage());
		}
	}

	@Override
	public List<TodoBackBean> getAllTodoBackBeans() throws Exception{
		log.debug("getAllTodoBackBeans- start");
		List<TodoBackBean> todoBackBeanAccumlator = new ArrayList<>();

		try {
			Iterator<Todo> todos = todoService.listAll().iterator();
			while (todos.hasNext()) {
				TodoBackBean todoBackBean = todoTransformer.convertTodoToTodoBackBean(todos.next());
				todoBackBeanAccumlator.add(todoBackBean);
			}
			
		} 
		catch (Exception e) {
			log.error("getAllTasks - " + e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
		
		log.debug("getAllTodoBackBeans- done");
		return todoBackBeanAccumlator;
	}

}
