package com.jhart.command;

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
	
	private String id;
	private String taskName;
	private String owner;
	private String createDate;
	private String complete;
	private String completeDate;

}
