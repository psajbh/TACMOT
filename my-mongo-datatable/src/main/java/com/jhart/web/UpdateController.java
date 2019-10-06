package com.jhart.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;
import com.jhart.dto.MyResponse;
//import com.jhart.dto.MyResponse;
import com.jhart.service.TodoService;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UpdateController {
	
	TodoService todoService;
	
	public UpdateController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping("todo/update")
	public ResponseEntity<Object> updateTodo(@RequestBody TodoBackBean todoBackBean) {
		
		try {
			ObjectId mongoId = new ObjectId(todoBackBean.getId());  
			Todo todo = todoService.findById(mongoId);
			todo.setTaskName(todoBackBean.getTaskName());
			todo.setOwner(todoBackBean.getOwner());
			String isComplete = todoBackBean.getComplete();
			if (isComplete.contentEquals("Yes")) {
				todo.setCompleteDate(new Date());
				todo.setComplete(true);
			}
			else {
				todo.setCompleteDate(null);
				todo.setComplete(false);
			}
			todoService.save(todo);
			MyResponse<List<TodoBackBean>> response = new MyResponse<>("success",getTodoList());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
			
		}
		catch(Exception e) {
			log.error("updateTodo: " + e.getMessage(),e);
		}
		MyResponse<List<TodoBackBean>> response = new MyResponse<>("failure",getTodoList());
		return new ResponseEntity<Object>(response, HttpStatus.IM_USED);
		
	}
		
	private List<TodoBackBean> getTodoList() {
		Iterable<Todo> todoItems = todoService.listAll();
		List<TodoBackBean> beans = new ArrayList<>();
		
		Iterator<Todo> items = todoItems.iterator();
		while(items.hasNext()) {
			Todo todo = items.next();
			TodoBackBean todoBackingBean = new TodoBackBean();
			todoBackingBean.setId(todo.getId().toString());
			todoBackingBean.setTaskName(todo.getTaskName());
			todoBackingBean.setOwner(todo.getOwner());
			String createDate = DateFormatter.getStandardDate(todo.getCreateDate());
			
			todoBackingBean.setCreateDate(createDate);
			if (todo.isComplete()) {
				todoBackingBean.setComplete("Yes");
				todoBackingBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
			}
			else {
				todoBackingBean.setComplete("No");
				todoBackingBean.setCompleteDate(" ");
			}
			
			beans.add(todoBackingBean);
		}
		
		return beans;

	}
}
