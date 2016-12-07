package org.teamneko.schrodinger.client;

import javax.ws.rs.core.MediaType;

import org.teamneko.meowlib.json.BoxSearchResult;
import org.teamneko.meowlib.json.CompositeSearchResult;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.ProductSearchResult;
import org.teamneko.meowlib.json.SearchResult;
import org.teamneko.meowlib.json.TransactionRequest;
import org.teamneko.meowlib.json.User;
import org.teamneko.meowlib.json.UserSearchResult;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * The Class SchrodingerClient.
 */
public class SchrodingerClient extends Client {
	
	/** The url. */
	private String url;
	
	/**
	 * Instantiates a new schrodinger client.
	 *
	 * @param url the url
	 */
	public SchrodingerClient(String url) {
		this.url = url;
		this.addFilter(new LoggingFilter(System.out));
	}
	
	/**
	 * Request user.
	 *
	 * @param number the number
	 * @return the user
	 * @throws UniformInterfaceException the uniform interface exception
	 */
	public User requestUser(String number) throws UniformInterfaceException {
		return resource(url).path("user").path(number).get(User.class);
	}
	
	/**
	 * Search.
	 *
	 * @param barcode the barcode
	 * @return the search result
	 */
	public SearchResult search(String barcode) { 
		ClientResponse cr = resource(url).path("search").path(barcode).get(ClientResponse.class);
		CompositeSearchResult s = cr.getEntity(CompositeSearchResult.class);
	
		if(s.getType().equals("box")) {
			return new BoxSearchResult(s.getBox());
		} else if(s.getType().equals("product")) {
			return new ProductSearchResult(s.getProduct());
		} else if(s.getType().equals("user")) {
			return new UserSearchResult(s.getUser());
		}
		
		return new SearchResult(s.getType());
	}
	 
	/**
	 * Post transaction.
	 *
	 * @param transaction the transaction
	 */
	public void postTransaction(TransactionRequest transaction) {
		resource(url).path("box").path("update").type(MediaType.APPLICATION_JSON).post(transaction);
	}
	
	/**
	 * Gets the box details.
	 *
	 * @param idBox the id box
	 * @return the box details
	 */
	public NamedProduct[] getBoxDetails(int idBox) {
		return resource(url).path("box").path("details").path(Integer.toString(idBox)).get(NamedProduct[].class);
	}
}
