package org.teamneko.schrodinger.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.StockDataPoint;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.sql.Database;

import com.sun.jersey.api.NotFoundException;

@Path("/product")
public class ProductResource {
	private ProductsDAO products = Database.getDAOFactory().getProductsDAO();
	private HistoryDAO history = Database.getDAOFactory().getHistoryDAO();
	
	@Context
	UriInfo uriInfo;
	 
	@Context
	Request request;
	
	@GET
	@Path("/history/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<StockDataPoint> getBoxDetails(@PathParam("id") int id) {
		ProductRow product = products.get(id).orElseThrow(() -> new NotFoundException());
		List<HistoryRow> dataPoints = history.getFullHistory(id);
		
		//Create List with all points but one
		ArrayList<StockDataPoint> resultDataPoints = new ArrayList<StockDataPoint>(dataPoints.size() + 1);
		resultDataPoints.add(new StockDataPoint(product.getDate_added(), 0));
		
		for(HistoryRow dataPoint : dataPoints)
			resultDataPoints.add(new StockDataPoint(dataPoint.getTime(), dataPoint.getQuantity()));
		
		return resultDataPoints;
	}
}
