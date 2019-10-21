package com.jhart.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Document
public class Todo {
	
	public Todo(String taskName) {
		this.taskName = taskName;
		this.complete = false;
		this.setCreateDate(new Date());
	}
	
	public Todo(String taskName, User user) {
		this.taskName = taskName;
		this.user = user;
		this.complete = false;
		this.setCreateDate(new Date());
	}
	
	@Id
    private ObjectId id;
	private String taskName;
	
	@DBRef
	private User user;
    private boolean complete;
    private Date completeDate;
    private Date createDate;
}
