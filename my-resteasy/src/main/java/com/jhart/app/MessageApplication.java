package com.jhart.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.jhart.rest.MessageRestService;

public class MessageApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public MessageApplication() {
		singletons.add(new com.jhart.rest.MessageRestService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
