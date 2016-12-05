package org.teamneko.schrodinger.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.teamneko.meowlib.json.User;
import org.teamneko.schrodinger.dao.UsersDAO;
import org.teamneko.schrodinger.sql.Database;

import com.sun.jersey.api.NotFoundException;

/**
 * Servlet implementation class UserResource.
 */
@Path("/user/{code}")
public class UserResource {
	
	/** The dao. */
	private static UsersDAO dao = Database.getDAOFactory().getUsersDAO();
	
	/** The uri info. */
	@Context
	UriInfo uriInfo;
	 
	/** The request. */
	@Context
	Request request;
    
    /**
     * Gets the user.
     *
     * @param code the code
     * @return the user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("code") String code) {
    	return dao.search(code).orElseThrow(() -> new NotFoundException());
    }
}