package com.jhart.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	private Set<Todo> todos;

}
