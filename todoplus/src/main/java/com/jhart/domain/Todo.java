package com.jhart.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

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
@Entity
@Table(name="Todo")
public class Todo {
    
    public Todo(String taskName) {
        this.taskName = taskName;
    }
	
	public Todo(String taskName, User user) {
		this.taskName = taskName;
		this.user = user;
		this.complete = false;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String taskName;
	
	private User user;
    private boolean complete;
    private Date completeDate;
    private Date createDate;
    
    @PrePersist
    void createDate() {
        this.createDate = new Date();
    }
}
