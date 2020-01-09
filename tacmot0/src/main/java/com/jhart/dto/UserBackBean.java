package com.jhart.dto;

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
	private String username;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Boolean hasTasks;
	private String ldapId;
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	

}
