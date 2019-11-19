package com.jhart.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService {

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
		String result = "Restful example : " + msg;
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/query")
	public Response getUsers(@QueryParam("id") String item) {
		System.out.println("item: " + item);
		return Response.status(200).entity("id: " + item).build();

	}
}