package org.teamneko.schrodinger.api;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.teamneko.meowlib.adapters.ProductAdapter;
import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.BoxSearchResult;
import org.teamneko.meowlib.json.ProductSearchResult;
import org.teamneko.meowlib.json.SearchResult;
import org.teamneko.meowlib.json.User;
import org.teamneko.meowlib.json.UserSearchResult;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.UsersDAO;
import org.teamneko.schrodinger.sql.Database;

@Path("/search/{barcode}")
public class SearchResource {
	private BoxesDAO boxes = Database.getDAOFactory().getBoxesDAO();
	private ProductsDAO products = Database.getDAOFactory().getProductsDAO();
	private UsersDAO users = Database.getDAOFactory().getUsersDAO();
	
	@Context
	UriInfo uriInfo;
	 
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResult search(@PathParam("barcode") String barcode) {
		Optional<Box> boxResult = boxes.search(barcode);
		if(boxResult.isPresent())
			return new BoxSearchResult(boxResult.get());
		
		Optional<ProductRow> productResult = products.search(barcode);
		if(productResult.isPresent())
			return new ProductSearchResult(ProductAdapter.toJSONProduct(productResult.get()));
		
		Optional<User> userResult = users.search(barcode);
		if(userResult.isPresent())
			return new UserSearchResult(userResult.get());
		
		return new SearchResult("not found");
	}
}
