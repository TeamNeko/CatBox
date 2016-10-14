package soprema.intranet;

import java.util.ArrayList;
import java.util.List;

public class Products {
	private List<Product> ProductList;
	
	public Products(ArrayList ItemList) 
	{
		ProductList = ItemList;
	}
	
	public List<Product> getProductList()
	{
		return ProductList;
	}
}
