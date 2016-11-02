package org.teamneko.schrodinger.api;

import java.util.Date;

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

import org.teamneko.meowlib.dto.Product;
import org.teamneko.meowlib.dto.TransactionRequest;
import org.teamneko.meowlib.pojo.InventoryItem;
import org.teamneko.meowlib.pojo.Transaction;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.dao.TransactionsDAO;
import org.teamneko.schrodinger.sql.Database;

@Path("/box")
public class BoxResource {
	private BoxesDAO boxes = Database.getDAOFactory().getBoxesDAO();
	private TransactionsDAO transactions = Database.getDAOFactory().getTransactionsDAO();
	private InventoryDAO inventory = Database.getDAOFactory().getInventoryDAO();
	
	@Context
	UriInfo uriInfo;
	 
	@Context
	Request request;
	
	@GET
	@Path("/exists/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String boxExists(@PathParam("barcode") String barcode) {
		System.out.println(barcode);
		return Boolean.toString(boxes.exists(barcode));
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addItems(TransactionRequest content) {
		//Create new transaction object
		Transaction t = new Transaction();
		
		//Create Box if necessary
		if(!boxes.exists(content.getBox()))
			boxes.create(content.getBox());
		
		//Copy User ID
		t.setIdUser(content.getUser());
		
		//Copy Box ID
		t.setIdBox(boxes.getId(content.getBox()));
		
		//Set Time
		t.setTime(new Date().getTime());
		
		//Set type to add
		t.setType("add");
		for(TransactionRequest.Product product : content.getProductsAdded()) {
			//Add transaction
			t.setIdProduct(product.getId());
			t.setQuantity(product.getQuantity());
			transactions.addTransaction(t);
			
			//Update Inventory
			inventory.addToInventory(new InventoryItem(t.getIdBox(), product.getId(), product.getQuantity()));
		}
		
		//Set type to remove
		t.setType("remove");
		for(TransactionRequest.Product product : content.getProductsRemoved()) {
			//Add transaction
			t.setIdProduct(product.getId());
			t.setQuantity(product.getQuantity());
			transactions.addTransaction(t);
			
			//Update Inventory
			inventory.removeFromInventory(new InventoryItem(t.getIdBox(), product.getId(), product.getQuantity()));
		}
	}
}
