package com.jhart.web.task;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.jhart.command.UserBackBean;
import com.jhart.domain.User;
import com.jhart.helpers.TestUsers;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;

public class AddTaskControllerTest {

	@Mock
	Model model;

	@Mock
	TodoService todoService;

	@Mock
	UserService userService;

	AddTaskController controller;
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new AddTaskController(todoService, userService);
	}

	@Test
	public void testMockMvc() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/todo/add")).andExpect(status().isOk()).andExpect(view().name("task/newtodo"));
	}

	@Test
	public void testAddNewTodo() throws Exception {
		// given
		List<UserBackBean> userBackBeanAccumulator = new ArrayList<>();
		UserBackBean userBackBean = new UserBackBean();
		userBackBean.setName("Mick");
		userBackBeanAccumulator.add(userBackBean);
		userBackBean = new UserBackBean();
		userBackBean.setName("Jagger");
		userBackBeanAccumulator.add(userBackBean);
		
		// when
		when(userService.listAll()).thenReturn(userBackBeanAccumulator);
		String viewName = controller.addNewTodo(model);
		
		// then
		assertEquals(viewName, "task/newtodo");
		verify(userService, times(1)).listAll();
	}

}
