package com.jhart.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Column(unique=true)
	private String name;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Date dateCreated;
	private String ldapId;
	// never able to resolve the orphenRemoval issue, doesn't seem to be a problem 
	//User.java is the parent (the one side is the parent)
	//https://codippa.com/how-to-resolve-a-collection-with-cascadeall-delete-orphan-was-no-longer-referenced-by-the-owning-entity-instance
	//http://cristian.sulea.net/blog/hibernate-exception-a-collection-with-cascade-all-delete-orphan-was-no-longer-referenced-by-the-owning-entity-instance/
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval= true, mappedBy="user")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Todo> todos;
	
//	public void setTodos(Set<Todo> todos) {
//		this.todos.clear();
//		this.todos.addAll(todos);
//	}
	
//	public Set<Todo> getTodos(){
//		return todos;
//	}
	
	

	
}
