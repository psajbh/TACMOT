package com.jhart.transform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.jhart.command.TodoBackBean;
import com.jhart.command.UserBackBean;
import com.jhart.domain.Todo;
import com.jhart.domain.User;
import com.jhart.util.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TodoTransformerImpl implements TodoTransformer {
	private static final String YES = "Yes";
	private static final String NO = "No";
	
	private UserTransformer userTransformer;
	
	public TodoTransformerImpl(UserTransformer userTransformer) {
		this.userTransformer = userTransformer;
	}

	@Override
	public TodoBackBean convertTodoToTodoBackBean(Todo todo) {
		TodoBackBean todoBackBean = new TodoBackBean();
		todoBackBean.setId(todo.getId());
		todoBackBean.setTaskName(todo.getTaskName());
		String createDate = DateFormatter.getStandardDate(todo.getCreateDate());
		todoBackBean.setCreateDate(createDate);
		
		if (todo.getCompleteDate() == null) {
			todoBackBean.setComplete(TodoTransformerImpl.NO);	
			todoBackBean.setCompleteDate(null);
		}
		else {
			todoBackBean.setComplete(TodoTransformerImpl.YES);
			todoBackBean.setCompleteDate(DateFormatter.getStandardDate(todo.getCompleteDate()));
		}
		
		User user = todo.getUser();
		if (null != user) {
			UserBackBean userBackBean = userTransformer.convertUserToUserBackBean(user);
			todoBackBean.setUser(userBackBean);
		}
		else {
			todoBackBean.setUser(new UserBackBean());
		}
		
		return todoBackBean;
	}

	@Override
	public Todo convertTodoBackBeanToTodo(TodoBackBean todoBackBean) {
		Todo todo = new Todo();
		todo.setId(todoBackBean.getId());
		todo.setTaskName(todoBackBean.getTaskName());
		String completeDate = todoBackBean.getCompleteDate();
		if (null != completeDate) {
			todo.setComplete(true);
			Date date = null;
			try {
			    date = new SimpleDateFormat(DateFormatter.DMY_PATTERN).parse(completeDate);
			}
			catch(ParseException parseException) {
				log.error("convertTodoBackBeanToTodo - failed to parse Date: " + completeDate);
				date = new Date();
			}
			todo.setCompleteDate(date);
		}
		
		UserBackBean userBackBean = todoBackBean.getUser();
		User user = userTransformer.convertUserBackBeanToUser(userBackBean);
		todo.setUser(user);
		return todo;
	}

}
