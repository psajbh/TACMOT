package com.jhart.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.UserTransformer;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateController {
	
	private static final String SELECTED_OPTION = "<option value=%s selected>%s</option>";
	private static final String OPTION = "<option value=%s>%s</option>";
	private static final String YES = "Yes";
	private static final String NO = "No";
	private static final String EMPTY = " ";
	
	private TodoService todoService;
	private UserService userService;
	private UserTransformer userTransformer;
	
	public UpdateController(TodoService todoService, UserService userService, UserTransformer userTransformer) {
		this.todoService = todoService;
		this.userService = userService;
		this.userTransformer = userTransformer;
	}

	//builds a set of select options and marks the selected option from selectedName.
	@PostMapping("todo/users")
	public @ResponseBody String getUsers(@RequestBody(required=false) String selectName , HttpServletRequest request ) {
		log.debug("getUsers (todo/users): - selected name: " + selectName);
		if (null == selectName) {
			return "redirect/task/index";
		}
		StringBuilder sb = new StringBuilder();
		
		for (User user : userService.listAll()) {
			if (user.getName().equals(selectName)) {
				sb.append(String.format(UpdateController.SELECTED_OPTION, user.getName(),user.getName()));
			}
			else {
				sb.append(String.format(UpdateController.OPTION, user.getName(),user.getName()));
			}
		}
		
		return sb.toString();
	}

	//updates data supporting an existing todo and return updated list
	@PostMapping("todo/update")
	public ResponseEntity<Object> updateTodo(@RequestBody TodoBackBean todoBackBean) {
		log.debug("updateTodo: - start");
		try {
			Todo todo = todoService.findById(todoBackBean.getId());
			todo.setTaskName(todoBackBean.getTaskName());
			
			for (User user : userService.listAll()) {
				log.debug("updateTodo: iterating user: " + user.getName());
				if(user.getName().equals(todoBackBean.getUser().getName())) {
					todo.setUser(user);
					log.debug("updateTodo: - todo setUser to: " + user.getName() + " id: " + user.getId());
					break;
				}
			}
			
			String isComplete = todoBackBean.getComplete();
			if (isComplete.contentEquals(UpdateController.YES)) {
				todo.setCompleteDate(new Date());
				todo.setComplete(true);
			}
			else {
				todo.setCompleteDate(null);
				todo.setComplete(false);
			}
			todoService.save(todo);
			MyResponse<List<TodoBackBean>> response = new MyResponse<>("success", getTodoList());
			log.debug("updateTodo: - success");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
			
		}
		catch(Exception e) {
			log.error("updateTodo: " + e.getMessage(),e);
		}
		log.debug("updateTodo: - failure");
		MyResponse<List<TodoBackBean>> response = new MyResponse<>("failure", getTodoList());
		return new ResponseEntity<Object>(response, HttpStatus.IM_USED);
		
	}
	
	// returns a list of TodoBackBean based on 
	private List<TodoBackBean> getTodoList() {
		Iterable<Todo> todoItems = todoService.listAll();
		List<TodoBackBean> beans = new ArrayList<>();
		
		Iterator<Todo> items = todoItems.iterator();
		while(items.hasNext()) {
			Todo todo = items.next();
			TodoBackBean todoBackingBean = new TodoBackBean();
			todoBackingBean.setId(todo.getId());
			todoBackingBean.setTaskName(todo.getTaskName());
			
			if (null != todo.getUser()) {
				UserBackBean userBackBean = userTransformer.convertUserToUserBackBean(todo.getUser());
				todoBackingBean.setUser(userBackBean);
			}
			
			String createDate = DateFormatter.getStandardDate(todo.getCreateDate());
			todoBackingBean.setCreateDate(createDate);
			
			if (todo.isComplete()) {
				todoBackingBean.setComplete(UpdateController.YES);
				todoBackingBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
			}
			else {
				todoBackingBean.setComplete(UpdateController.NO);
				todoBackingBean.setCompleteDate(UpdateController.EMPTY);
			}
			
			beans.add(todoBackingBean);
		}
		
		return beans;

	}
}
