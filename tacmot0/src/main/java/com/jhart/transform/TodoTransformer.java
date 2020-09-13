package com.jhart.transform;

import com.jhart.domain.Todo;
import com.jhart.dto.TodoBackBean;

public interface TodoTransformer {
	TodoBackBean convertTodoToTodoBackBean(Todo todo);
	Todo convertTodoBackBeanToTodo(TodoBackBean todoBackBean);

}
