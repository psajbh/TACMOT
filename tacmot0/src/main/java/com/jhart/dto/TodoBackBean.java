package com.jhart.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TodoBackBean {
	
	private Long id;
	private String taskName;
	private UserBackBean user;
	private String createDate;
	private String complete;
	private String completeDate;

}
