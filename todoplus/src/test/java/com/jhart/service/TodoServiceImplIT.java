
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
import com.jhart.service.task.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceImplIT {

	@Autowired
	private TodoService todoService;

	@Test
	public void test() {
		int a = 1;
		Assert.assertEquals(a, 1);
	}

	@Test
	@Transactional // Note: adding transactional prevents LazyInitialzation Exception
	public void testGsonConversions() {
		List<String> todosJsonAccumulator = new ArrayList<>();
		Gson gson = new Gson();

		List<TodoBackBean> todos = todoService.listAll();
		int numberOfTodos = todos.size();
		Assert.assertNotNull(todos);

		int i = 0;
		for (TodoBackBean todoBackBean : todos) {
			String strTodo = gson.toJson(todoBackBean);
			todosJsonAccumulator.add(strTodo);
			i++;
		}
		Assert.assertEquals(i, numberOfTodos);

		log.debug("testGsonConversions- todosJsonAccumulator:" + todosJsonAccumulator);

	}

}
