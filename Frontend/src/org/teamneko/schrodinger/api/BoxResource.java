package org.teamneko.schrodinger.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.teamneko.meowlib.dto.Transaction;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.sql.Database;

@Path("/box")
public class BoxResource {
	BoxesDAO dao = Database.getDAOFactory().getBoxesDAO();
	
	@Context
	UriInfo uriInfo;
	 
	@Context
	Request request;
	
	@GET
	@Path("/exists/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String boxExists(@PathParam("barcode") String barcode) {
		System.out.println(barcode);
		return Boolean.toString(dao.exists(barcode));
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addItems(Transaction content) {
		if(!dao.exists(content.getBarcode()))
			dao.create(content.getBarcode());
		
		System.out.println("------------------------------------");
		System.out.println("Box barcode: " + content.getBarcode());
		System.out.println("Items added:");
		for(Transaction.Product product : content.getProductsAdded()) {
			System.out.println("\tItem " + product.getId() + " : " + product.getQuantity());
		}
		System.out.println("Items removed:");
		for(Transaction.Product product : content.getProductsRemoved()) {
			System.out.println("\tItem " + product.getId() + " : " + product.getQuantity());
		}
	}
}
