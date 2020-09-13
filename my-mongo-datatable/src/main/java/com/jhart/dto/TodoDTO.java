package com.jhart.dto;

import java.util.Date;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TodoDTO {
	
    private String id;
    private String name;
    private boolean completed;
    private Date createDate;


}
