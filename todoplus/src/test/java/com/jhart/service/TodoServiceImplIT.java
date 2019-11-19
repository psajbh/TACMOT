package com.jhart.service;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.service.task.TodoService;
import com.jhart.transform.UserTransformer;
import com.jhart.util.DateFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceImplIT {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private UserTransformer userTransformer;
	
	@Test
	public void testGsonConversions() {
		System.out.println("Hello");
		Iterable<Todo> todos = todoService.listAll();
		System.out.println("todos: " + todos);
		Assert.assertNotNull(todos);
		
		String strTodos = null;
		Gson gson = new Gson();
		try {
			strTodos = gson.toJson(todos);
		}
		catch(Exception e) {
			System.out.println("e:" + e);
		}
		
		List<TodoBackBean> beans = new ArrayList<>();
		
		Iterator<Todo> items = todos.iterator();
		while(items.hasNext()) {
			Todo todo = items.next();
			User user = todo.getUser();
			TodoBackBean todoBackingBean = new TodoBackBean();
			todoBackingBean.setId(todo.getId());
			todoBackingBean.setTaskName(todo.getTaskName());
			String createDate = DateFormatter.getStandardDate(todo.getCreateDate());
			todoBackingBean.setCreateDate(createDate);
			
			if (todo.isComplete()) {
				todoBackingBean.setComplete("Yes");
				todoBackingBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
			}
			else {
				todoBackingBean.setComplete("No");
				todoBackingBean.setCompleteDate(" ");
			}
			
			UserBackBean userBackBean;
			
			if (null != user) {
				userBackBean = userTransformer.convertUserToUserBackBean(user);
			}
			else {
				userBackBean = new UserBackBean();
			}
					
			todoBackingBean.setUser(userBackBean);		
			
			beans.add(todoBackingBean);
		}
		
		System.out.println("getAllTasks: returning beans: " + beans);
		String beanStr = null;
		gson = new Gson();
		try {
			beanStr = gson.toJson(beans);
			System.out.println("beanStr:" + beanStr);
		}
		catch(Exception e) {
			System.out.println("e:" + e);
		}

				
		
		
		
		
		
	}
	

}
