package org.teamneko.schrodinger.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.teamneko.schrodinger.pojo.User;

import com.sun.jersey.api.NotFoundException;

/**
 * Servlet implementation class UserResource
 */
@Path("/user")
public class UserResource {
	@Context
	UriInfo uriInfo;
	 
	@Context
	Request request;
	
	// Basic "is the service running" test
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String respondAsReady() {
        return "Demo service is ready!";
    }
    
    @GET
    @Path("sample")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSamplePerson() {
        return new User(1, "Tommy", "Savaria", "662248910017");
    }
    
    @GET
    @Path("notfound")
    @Produces(MediaType.APPLICATION_JSON)
    public User getNotFoundUser() {
    	throw new NotFoundException();
    }
}