package Test.intranet;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import soprema.intranet.*;

public class TestProduit {

	@Test
	public void testSetIDProduct() {
		Product ProductTest = new Product();
		ProductTest.setID(1);
		assertEquals(ProductTest.getID(), 1);	
	}
	
	@Test 
	public void testSetNameProduct() {
		Product ProductTest = new Product();
		ProductTest.setProductName("TEST");
		assertEquals(ProductTest.getProductName(), "TEST");	
	}
	
	@Test
	public void testSetProducts() {
		ArrayList<Product> ItemList = new ArrayList<Product>();
		Product AdditionnalProduct = new Product();
		AdditionnalProduct.setID(1);
		AdditionnalProduct.setProductName("TEST");
		ItemList.add(AdditionnalProduct);
		Products ProductsTest = new Products(ItemList);
		Product Tester = ProductsTest.getProductList().get(0);
		assertEquals(Tester.getID(), 1);
		assertEquals(Tester.getProductName(), "TEST");
	}
	
	@Test
	public void testBackendInitialisation() {
		Backend ProductsTest = new Backend();
		Product Tester = ProductsTest.ProductsInventory.getProductList().get(0);
		assertEquals(Tester.getID(), 0); 
		assertEquals(Tester.getProductName(), "Product 0");
	}
} 
