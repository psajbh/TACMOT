package com.jhart.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.service.task.TodoService;
import com.jhart.service.user.UserService;
import com.jhart.transform.TodoTransformer;
import com.jhart.transform.UserTransformer;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateTaskController {
	
	private static final String SELECTED_OPTION = "<option value=%s selected>%s</option>";
	private static final String OPTION = "<option value=%s>%s</option>";
	private static final String NO_SELECT = "<option value=''>Select a User:</option>"; 
	private static final String YES = "Yes";
	private static final String NO = "No";
	private static final String EMPTY = " ";
	
	private TodoService todoService;
	private UserService userService;
	private UserTransformer userTransformer;
	private TodoTransformer todoTransformer; 
	
	public UpdateTaskController(TodoService todoService, UserService userService, 
			TodoTransformer todoTransformer, UserTransformer userTransformer) {
		this.todoService = todoService;
		this.userService = userService;
		this.todoTransformer = todoTransformer;
		this.userTransformer = userTransformer;
	}

	//builds a set of select options and marks the selected option from selectedName.
	@PostMapping("todo/users")
	public @ResponseBody String getUsers(@RequestBody(required=false) String selectName , HttpServletRequest request ) {
		log.debug("getUsers- selected name: " + selectName);
		
		StringBuilder sb = new StringBuilder();
		
		if (null == selectName) {
			sb.append(UpdateTaskController.NO_SELECT);
		}
		
		for (User user : userService.listAll()) {
			if (null != selectName && user.getName().equals(selectName)) {
				sb.append(String.format(UpdateTaskController.SELECTED_OPTION, user.getName(),user.getName()));
			}
			else {
				sb.append(String.format(UpdateTaskController.OPTION, user.getName(),user.getName()));
			}
		}
		
		log.debug("getUsers- completed with userList: " + sb.toString());
		return sb.toString();
	}

	//updates data supporting an existing todo and return updated list
	@PostMapping("todo/update")
	public ResponseEntity<Object> updateTodo(@RequestBody TodoBackBean todoBackBean) {
		log.debug("updateTodo- start");
		try {
			//processing translation here vice in translator do to special circumstances wrt null users.
			Todo todo = todoService.findById(todoBackBean.getId());
			todo.setTaskName(todoBackBean.getTaskName());
			
			for (User user : userService.listAll()) {
				log.debug("updateTodo- iterating user: " + user.getName());
				if(user.getName().equals(todoBackBean.getUser().getName())) {
					todo.setUser(user);
					log.debug("updateTodo- todo setUser to: " + user.getName() + " id: " + user.getId());
					break;
				}
			}
			//do not allow saving task as complete if there is no user.
			if (StringUtils.isEmpty(todoBackBean.getUser().getName())) {
				todo.setCompleteDate(null);
				todo.setComplete(false);
			}
			else {
				if (todoBackBean.getComplete().contentEquals(UpdateTaskController.YES)) {
					todo.setCompleteDate(new Date());
					todo.setComplete(true);
				}
				else {
					todo.setCompleteDate(null);
					todo.setComplete(false);
				}
			}
			
			todoService.save(todo);
			MyResponse<List<TodoBackBean>> response = new MyResponse<>("success", getTodoList());
			log.debug("updateTodo- success");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
			
		}
		catch(Exception e) {
			log.error("updateTodo- exception" + e.getMessage(),e);
		}
		log.debug("updateTodo- failure");
		MyResponse<List<TodoBackBean>> response = new MyResponse<>("failure", getTodoList());
		return new ResponseEntity<Object>(response, HttpStatus.I_AM_A_TEAPOT);
	}
	
	// returns a list of TodoBackBean based on 
	private List<TodoBackBean> getTodoList() {
		log.debug("getTodoList- start");
		
		Iterable<Todo> todoItems = todoService.listAll();
		List<TodoBackBean> todoBackBeanAccumulator = new ArrayList<>();
		
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
				todoBackingBean.setComplete(UpdateTaskController.YES);
				todoBackingBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
			}
			else {
				todoBackingBean.setComplete(UpdateTaskController.NO);
				todoBackingBean.setCompleteDate(UpdateTaskController.EMPTY);
			}
			
			todoBackBeanAccumulator.add(todoBackingBean);
		}
		
		return todoBackBeanAccumulator;

	}
}
