package com.jhart.web.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhart.domain.User;
import com.jhart.dto.MyResponse;
import com.jhart.dto.TodoBackBean;
import com.jhart.orchestration.task.TaskConductor;
import com.jhart.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateTaskController {
	private static final String SELECTED_OPTION = "<option value=%s selected>%s</option>";
	private static final String OPTION = "<option value=%s>%s</option>";
	private static final String NO_SELECT = "<option value=''>Select a User:</option>";
	
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String EMPTY = " ";
	
	private UserService userService;
	private TaskConductor conductor;
	
	public UpdateTaskController(UserService userService, TaskConductor conductor) {
		this.userService = userService;
		this.conductor = conductor;
	}

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

	@PostMapping("todo/update")
	public ResponseEntity<Object> updateTodo(@RequestBody TodoBackBean todoBackBean) {
		log.debug("updateTodo- start");
		MyResponse<List<TodoBackBean>> response = null;
		
		try {
			response = conductor.updateTodo(todoBackBean);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("updateTodo- exception" + e.getMessage(),e);
		}
		return new ResponseEntity<Object>(response, HttpStatus.I_AM_A_TEAPOT);
	}
}
