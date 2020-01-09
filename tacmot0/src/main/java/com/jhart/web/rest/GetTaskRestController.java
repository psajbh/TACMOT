package com.jhart.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jhart.dto.TodoBackBean;
import com.jhart.dto.UserBackBean;
import com.jhart.orchestration.task.TaskConductor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GetTaskRestController {
	
	private TaskConductor conductor;
	
	public GetTaskRestController(TaskConductor conductor) {
		this.conductor = conductor;
	}
	
	//called by Postman: tacmot0 GET restTaskData (My Requests folder)
	@GetMapping({"restTaskData"})
	public ResponseEntity<Object> getRestAllTasks() {
		log.debug("getAllTasks- start");
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		UserBackBean ubb = (UserBackBean) request.getAttribute("credentialKey");

		List<TodoBackBean> todoBackBeans = null;
		boolean success = false;
		
		try {
			todoBackBeans =  conductor.getAllTodoBackBeans();
			success = true;
		}
		catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		
		log.debug("getAllTasks- success: " + success);
		
		if (success) {
			return new ResponseEntity<Object>(todoBackBeans,HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>("UnAuthenticated User",HttpStatus.I_AM_A_TEAPOT);
	}


}
