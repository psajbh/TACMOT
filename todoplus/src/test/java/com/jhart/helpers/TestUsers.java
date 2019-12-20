package com.jhart.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jhart.domain.User;

public class TestUsers implements Iterable {
	
	private List<User> users; 
	
	public TestUsers() {
		init();
	}
    
	@Override
    public Iterator<User> iterator() {
        return this.users.iterator();
    }

	private void init() {
		this.users = new ArrayList<>();
		
		User user = new User();
		user.setName("Joe");
		this.users.add(user);
		
		 user = new User();
		 user.setName("Jack");
		 this.users.add(user);
		
	}
}
