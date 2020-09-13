package com.jhart.web.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jhart.orchestration.task.TaskConductor;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;

public class AddTaskControllerTest {

	@Mock
	TodoService todoService;
	
	@Mock
	UserService userService;
	
	@Mock 
	TaskConductor taskConductor;
	
	AddTaskController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new AddTaskController(userService, taskConductor);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();		
	}
	
	@Test
	public void testMockMvc()throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/todo/add")).andExpect(status().isOk()).andExpect(view().name("task/newtodo"));
	}
	
	@Test
	public void testAddNewTodo() throws Exception{
		System.out.println("Hello");
	}
	
	
	

}
