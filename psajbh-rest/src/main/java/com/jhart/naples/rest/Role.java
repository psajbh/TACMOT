package com.jhart.naples.rest;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Role {
	
	private Integer id;
	private String name;
	private List<User> users;

}
