package com.jhart.web.task;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhart.dto.TodoBackBean;
import com.jhart.orchestration.task.TaskConductor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
public class TaskRestController {
	
	private TaskConductor conductor;
	
	public TaskRestController(TaskConductor conductor) {
		this.conductor = conductor;
	}
	
	@GetMapping({"todoDataTable"})
	public ResponseEntity<Object> getAllTasks() {
		log.debug("getAllTasks- start");
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
		
		return new ResponseEntity<Object>(null,HttpStatus.I_AM_A_TEAPOT);
	}

}
