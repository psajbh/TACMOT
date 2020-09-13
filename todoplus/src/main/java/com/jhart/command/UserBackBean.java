package com.jhart.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserBackBean {

	private Long id;
	private String name;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Boolean hasTasks;
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	

}
