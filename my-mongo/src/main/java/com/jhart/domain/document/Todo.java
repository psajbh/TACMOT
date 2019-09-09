package com.jhart.domain.document;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Document
public class Todo {
	
	public Todo(String name) {
		this.name = name;
		this.completed = false;
		this.setCreateDate(new Date());
	}
	
	@Id
    private ObjectId id;
    private String name;
    private boolean completed;
    private Date createDate;


}
