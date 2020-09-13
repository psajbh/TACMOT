package com.jhart.domain;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class User {
	
	public User() {
	}
	
	@Id
	private ObjectId id;
	private String name;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	@DBRef
	private Set<Todo> todos;

}
