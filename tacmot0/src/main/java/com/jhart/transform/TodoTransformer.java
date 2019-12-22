package com.jhart.transform;

import com.jhart.command.TodoBackBean;
import com.jhart.domain.Todo;

public interface TodoTransformer {
	TodoBackBean convertTodoToTodoBackBean(Todo todo);
	Todo convertTodoBackBeanToTodo(TodoBackBean todoBackBean);

}
