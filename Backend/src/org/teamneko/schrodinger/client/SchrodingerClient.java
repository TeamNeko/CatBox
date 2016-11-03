package org.teamneko.schrodinger.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

import org.teamneko.meowlib.dto.BoxSearchResult;
import org.teamneko.meowlib.dto.CompositeSearchResult;
import org.teamneko.meowlib.dto.ProductSearchResult;
import org.teamneko.meowlib.dto.SearchResult;
import org.teamneko.meowlib.dto.User;
import org.teamneko.meowlib.dto.UserSearchResult;


public class SchrodingerClient extends Client {
	private String url;
	
	public SchrodingerClient(String url) {
		this.url = url;
	}
	
	public User requestUser(String number) throws UniformInterfaceException {
		return resource(url).path("user").path(number).get(User.class);
	}
	
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
}
