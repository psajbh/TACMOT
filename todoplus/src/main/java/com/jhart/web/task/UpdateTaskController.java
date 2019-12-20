package com.jhart.web.task;

import java.util.Date;
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
	
	private TodoService todoService;
	private UserService userService;
	
	public UpdateTaskController(TodoService todoService, UserService userService, 
			TodoTransformer todoTransformer, UserTransformer userTransformer) {
		this.todoService = todoService;
		this.userService = userService;
	}

	//builds a set of select options and marks the selected option from selectedName.
	@PostMapping("todo/users")
	public @ResponseBody String getUsers(@RequestBody(required=false) String selectName , HttpServletRequest request ) {
		log.debug("getUsers- selected name: " + selectName);
		StringBuilder sb = new StringBuilder();
		
		if (null == selectName) {
			sb.append(UpdateTaskController.NO_SELECT);
		}
		
		for (UserBackBean userBackBean : userService.listAll()) {
			if (null != selectName && userBackBean.getName().equals(selectName)) {
				sb.append(String.format(UpdateTaskController.SELECTED_OPTION, 
						userBackBean.getName(),userBackBean.getName()));
			}
			else {
				sb.append(String.format(UpdateTaskController.OPTION, 
						userBackBean.getName(),userBackBean.getName()));
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
			TodoBackBean currentTodoBackBean = todoService.findById(todoBackBean.getId());
			
			currentTodoBackBean.setTaskName(todoBackBean.getTaskName());
			
			for (UserBackBean userBackBean : userService.listAll()) {
				log.debug("updateTodo- iterating user: " + userBackBean.getName());
				if(userBackBean.getName().equals(todoBackBean.getUser().getName())) {
					currentTodoBackBean.setUser(userBackBean);
					log.debug("updateTodo- todo setUser to: " + currentTodoBackBean.getUser().getName() + " id: "
					+ currentTodoBackBean.getId());
					break;
				}
			}
			//do not allow saving task as complete if there is no user.
			if (StringUtils.isEmpty(currentTodoBackBean.getUser().getName())) {
				currentTodoBackBean.setCompleteDate(null);
				currentTodoBackBean.setComplete(UpdateTaskController.NO);
			}
			else {
				if (currentTodoBackBean.getComplete().contentEquals(UpdateTaskController.YES)) {
					currentTodoBackBean.setCompleteDate(DateFormatter.getStandardDate(new Date()));
					currentTodoBackBean.setComplete(UpdateTaskController.YES);
				}
				else {
					currentTodoBackBean.setCompleteDate(null);
					currentTodoBackBean.setComplete(UpdateTaskController.NO);
				}
			}
			
			todoService.save(currentTodoBackBean);
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
		return todoService.listAll();
	}
}
