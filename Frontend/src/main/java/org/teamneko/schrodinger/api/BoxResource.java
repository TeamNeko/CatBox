package org.teamneko.schrodinger.api;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.Transaction;
import org.teamneko.meowlib.json.TransactionRequest;
import org.teamneko.meowlib.sql.AlertRow;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.meowlib.sql.InventoryRow;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.TransactionsDAO;
import org.teamneko.schrodinger.sql.Database;


/**
 * The Class BoxResource.
 */
@Path("/box")
public class BoxResource {
	
	/** The Constant OUT_OF_INVENTORY_LEVEL. */
	private static final int OUT_OF_INVENTORY_LEVEL = 1;
	
	/** The Constant LOW_INVENTORY_LEVEL. */
	private static final int LOW_INVENTORY_LEVEL = 2;
	
	/** The alerts. */
	private AlertsDAO alerts = Database.getDAOFactory().getAlertsDAO();
	
	/** The boxes. */
	private BoxesDAO boxes = Database.getDAOFactory().getBoxesDAO();
	
	/** The history. */
	private HistoryDAO history = Database.getDAOFactory().getHistoryDAO();
	
	/** The inventory. */
	private InventoryDAO inventory = Database.getDAOFactory().getInventoryDAO();
	
	/** The products. */
	private ProductsDAO products = Database.getDAOFactory().getProductsDAO();
	
	/** The transactions. */
	private TransactionsDAO transactions = Database.getDAOFactory().getTransactionsDAO();
	
	/** The uri info. */
	@Context
	UriInfo uriInfo;
	 
	/** The request. */
	@Context
	Request request;
	
	/**
	 * Box exists.
	 *
	 * @param barcode the barcode
	 * @return the string
	 */
	@GET
	@Path("/exists/{barcode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String boxExists(@PathParam("barcode") String barcode) {
		return Boolean.toString(boxes.exists(barcode));
	}
	
	/**
	 * Gets the box details.
	 *
	 * @param id the id
	 * @return the box details
	 */
	@GET
	@Path("/details/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NamedProduct> getBoxDetails(@PathParam("id") int id) {
		return inventory.getBoxContents(id);
	}
	
	/**
	 * Update.
	 *
	 * @param content the content
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(TransactionRequest content) {
		//Create Box if necessary
		if(!boxes.exists(content.getBox()))
			boxes.create(content.getBox());
		
		int idBox = boxes.getId(content.getBox());
		for(TransactionRequest.Product product : content.getProductsModified()) {
			if(product.getQuantity() != 0) {
				Optional<ProductRow> productResult = products.get(product.getId());
				if(productResult.isPresent())
				{
					if(updateInventory(idBox, product.getId(), product.getQuantity())) {
						addTransaction(content.getUser(), idBox, product);
						setAlerts(productResult.get());
						addToHistory(productResult.get());
					}
				}
			}
		}
	}
	
	/**
	 * Adds the transaction.
	 *
	 * @param idUser the id user
	 * @param idBox the id box
	 * @param product the product
	 */
	private void addTransaction(int idUser, int idBox, TransactionRequest.Product product) {
		//Create new transaction object
		Transaction transaction = new Transaction();
				
		//Copy User ID
		transaction.setIdUser(idUser);
		
		//Copy Box ID
		transaction.setIdBox(idBox);
		
		//Set Time
		transaction.setTime(new Date().getTime());
				
		//Set Product Id
		transaction.setIdProduct(product.getId());
		
		//Set Type and Quantity
		if(product.getQuantity() > 0) {
			transaction.setType("add");
			transaction.setQuantity(product.getQuantity());
		}
		else {
			transaction.setType("remove");
			transaction.setQuantity(0-product.getQuantity());
		}
		
		transactions.addTransaction(transaction);
	}
	
	/**
	 * Add to history.
	 *
	 * @param product the product
	 */
	private void addToHistory(ProductRow product) {
		int stock = inventory.getStock(product.getId());
		history.add(new HistoryRow(-1, product.getId(), stock, Timestamp.from(Instant.now())));
	}
	
	/**
	 * Sets the alerts.
	 *
	 * @param product the new alerts
	 */
	private void setAlerts(ProductRow product) {
		int quantity = inventory.getStock(product.getId());
		
		if(quantity != -1) {
			if(quantity == 0) {
				//No more of this product in inventory
				setAlertToLevel(product.getId(), OUT_OF_INVENTORY_LEVEL);
			} else if(quantity < product.getThreshold() && product.getThreshold() != -1) {
				//Below required inventory level
				setAlertToLevel(product.getId(), LOW_INVENTORY_LEVEL);
			} else {
				//Everything ok, remove alerts for this product
				alerts.removeAlert(product.getId());
			}
		}
		
	}

	/**
	 * Sets the alert level.
	 *
	 * @param idProduct the id product
	 * @param level the level
	 */
	private void setAlertToLevel(int idProduct, int level) {
		Optional<AlertRow> currentAlert = alerts.getAlert(idProduct);
		
		if(currentAlert.isPresent()) {
			if(currentAlert.get().getId_message() != level)
				alerts.changeLevel(idProduct, level);
		} else {
			alerts.addAlert(idProduct, level);
		}
	}
	
	/**
	 * Update inventory.
	 *
	 * @param idBox the id box
	 * @param idProduct the id product
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	private boolean updateInventory(int idBox, int idProduct, int quantity) {
		InventoryRow item = inventory.get(idBox, idProduct).orElse(new InventoryRow(-1, idBox, idProduct, 0));;
		
		int newQuantity = item.getQuantity() + quantity;
		
		if(newQuantity < 0) {
			//Impossible to have a negative count of objects in a box
			return false;
		}
		else if(newQuantity == 0 && item.getId() != -1) {
			//Delete entry, the product is no longer in the box
			inventory.delete(item);
		} else {
			//Set the new quantity in inventory
			item.setQuantity(newQuantity);
			
			if(item.getId() == -1)
				inventory.insert(item);
			else
				inventory.update(item);
		}
			
		return true;
	}
}
