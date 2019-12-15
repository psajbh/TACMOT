package com.jhart.service;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
import com.jhart.transform.TodoTransformer;
import com.jhart.transform.UserTransformer;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceImplIT {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private UserTransformer userTransformer;
	
	@Autowired
	private TodoTransformer todoTransformer;
	
	@Test
	public void test() {
		int a = 1;
		Assert.assertEquals(a, 1);
	}
	
	@Test
	@Transactional  //Note: adding transactional prevents LazyInitialzation Exception
	public void testGsonConversions() {
		List<String> todosJsonAccumulator = new ArrayList<>();
		Gson gson = new Gson();
		
		Iterable<Todo> todos = todoService.listAll();
		Assert.assertNotNull(todos);
		
		Iterator<Todo> todoIterator = todos.iterator();
		int i = 0;
		while(todoIterator.hasNext()){
			i++;
			log.debug("testGsonConversions- processing todo: " + i);
			Todo todo = todoIterator.next();
			TodoBackBean todoBackBean = todoTransformer.convertTodoToTodoBackBean(todo);
			String strTodo = gson.toJson(todoBackBean);
			log.debug("testGsonConversions- strTodo("+i+"): " + strTodo);
			todosJsonAccumulator.add(strTodo);
		}
		
		Assert.assertTrue(todosJsonAccumulator.size() == i);
		
		log.debug("testGsonConversions- todosJsonAccumulator:" + todosJsonAccumulator);
		
	}
	

}
