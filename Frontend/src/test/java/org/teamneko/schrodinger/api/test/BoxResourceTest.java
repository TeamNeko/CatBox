package org.teamneko.schrodinger.api.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.Transaction;
import org.teamneko.meowlib.json.TransactionRequest;
import org.teamneko.meowlib.json.TransactionRequest.Product;
import org.teamneko.meowlib.sql.AlertRow;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.meowlib.sql.InventoryRow;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.api.BoxResource;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.TransactionsDAO;

public class BoxResourceTest {
	private final static String EXISTING_BOX_BARCODE = "1234567890";
	private final static String NONEXISTING_BOX_BARCODE = "0987654321";
			
	private BoxResource resourceUnderTest = new BoxResource();
	
	private AlertsDAO alertsMock;
	private BoxesDAO boxesMock;
	private HistoryDAO historyMock;
	private InventoryDAO inventoryMock;
	private ProductsDAO productsMock;
	private TransactionsDAO transactionsMock;
	
	private ArrayList<NamedProduct> products;
	
	public BoxResourceTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		alertsMock = mock(AlertsDAO.class);
		boxesMock = mock(BoxesDAO.class);
		historyMock = mock(HistoryDAO.class);
		inventoryMock = mock(InventoryDAO.class);
		productsMock = mock(ProductsDAO.class);
		transactionsMock = mock(TransactionsDAO.class);
		
		products = new ArrayList<NamedProduct>();
		products.add(new NamedProduct(1, 5, "FFX"));
		products.add(new NamedProduct(2, 4, "FFIX"));
		products.add(new NamedProduct(3, 3, "FFVII"));
		products.add(new NamedProduct(4, 2, "FFVI"));
		products.add(new NamedProduct(5, 1, "FFIV"));
		
		when(alertsMock.getAlert(1)).thenReturn(Optional.empty());
		when(alertsMock.getAlert(2)).thenReturn(Optional.of(new AlertRow()));
		
		when(boxesMock.exists(EXISTING_BOX_BARCODE)).thenReturn(true);
		when(boxesMock.exists(NONEXISTING_BOX_BARCODE)).thenReturn(false);
		when(boxesMock.search(EXISTING_BOX_BARCODE)).thenReturn(Optional.of(new Box(EXISTING_BOX_BARCODE, 1, (float) 1.0, new Timestamp(0), new Timestamp(-1), "")));
		when(boxesMock.search(NONEXISTING_BOX_BARCODE)).thenReturn(Optional.empty());
		when(boxesMock.getId(EXISTING_BOX_BARCODE)).thenReturn(1);
		when(boxesMock.getId(NONEXISTING_BOX_BARCODE)).thenReturn(2);
		
		when(inventoryMock.getBoxContents(1)).thenReturn(products);
		when(inventoryMock.get(1, 1)).thenReturn(Optional.of(new InventoryRow(1, 1, 1, 1)));
		when(inventoryMock.getStock(3)).thenReturn(2);
		
		when(productsMock.get(1)).thenReturn(Optional.of(new ProductRow("", new Timestamp(0), new Timestamp(-1), "", 1, "", 5, 1.0)));
		when(productsMock.get(2)).thenReturn(Optional.of(new ProductRow("", new Timestamp(0), new Timestamp(-1), "", 2, "", 5, 1.0)));
		when(productsMock.get(3)).thenReturn(Optional.of(new ProductRow("", new Timestamp(0), new Timestamp(-1), "", 3, "", 5, 1.0)));
		when(productsMock.get(4)).thenReturn(Optional.empty());
		
		injectMock("alerts", alertsMock);
		injectMock("boxes", boxesMock);
		injectMock("history", historyMock);
		injectMock("inventory", inventoryMock);
		injectMock("products", productsMock);
		injectMock("transactions", transactionsMock);
	}
	
	private void injectMock(String fieldName, Object mockedObject) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field boxDaoField = BoxResource.class.getDeclaredField(fieldName);
		boxDaoField.setAccessible(true);
		boxDaoField.set(resourceUnderTest, mockedObject);
	}
	
	@Test
	public void testExistsValid() {
		assertEquals("true", resourceUnderTest.boxExists(EXISTING_BOX_BARCODE));
	}
	
	@Test
	public void testExistsInvalid() {
		assertEquals("false", resourceUnderTest.boxExists(NONEXISTING_BOX_BARCODE));
	}
	
	@Test
	public void testGetBoxDetails() {
		assertSame(products, resourceUnderTest.getBoxDetails(1));
	}
	
	@Test
	public void testUpdateExistingBox() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, new ArrayList<Product>(0)));	
		verify(boxesMock, never()).create(EXISTING_BOX_BARCODE);
	}
	
	@Test
	public void testUpdateNonexistingBox() {
		resourceUnderTest.update(new TransactionRequest(1, NONEXISTING_BOX_BARCODE, new ArrayList<Product>(0)));
		verify(boxesMock).create(NONEXISTING_BOX_BARCODE);
	}
	
	@Test
	public void testUpdateWithZeroQuantity() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(1, 0)
		})));
		
		verify(productsMock, never()).get(1);
	}
	
	@Test
	public void testWithInexistingProduct() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(4, 5)
		})));
		
		verify(productsMock).get(4);
		verify(inventoryMock, never()).get(1, 4);
		
	}
	
	@Test
	public void testWithNegativeInventory() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(1, -5)
		})));
		
		verify(inventoryMock, never()).delete(any(InventoryRow.class));
		verify(inventoryMock, never()).insert(any(InventoryRow.class));
		verify(inventoryMock, never()).update(any(InventoryRow.class));
	}
	
	@Test
	public void testUpdateInventory() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(1, 5)
		})));
		
		when(inventoryMock.getStock(1)).thenReturn(6);
				
		verify(inventoryMock, never()).delete(any(InventoryRow.class));
		verify(inventoryMock, never()).insert(any(InventoryRow.class));
		verify(inventoryMock).update(any(InventoryRow.class));
		
		verify(historyMock).add(any(HistoryRow.class));
		verify(transactionsMock).addTransaction(any(Transaction.class));
	}
	
	@Test
	public void testDeleteFromInventory() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(1, -1)
		})));

		when(inventoryMock.getStock(1)).thenReturn(0);
		
		verify(inventoryMock).delete(any(InventoryRow.class));
		verify(inventoryMock, never()).insert(any(InventoryRow.class));
		verify(inventoryMock, never()).update(any(InventoryRow.class));
		
		verify(alertsMock).addAlert(1, 1);
		verify(historyMock).add(any(HistoryRow.class));
		verify(transactionsMock).addTransaction(any(Transaction.class));
	}
	
	@Test
	public void testInsertIntoInventory() {
		resourceUnderTest.update(new TransactionRequest(1, NONEXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(3, 1)
		})));
		
		verify(inventoryMock, never()).delete(any(InventoryRow.class));
		verify(inventoryMock).insert(any(InventoryRow.class));
		verify(inventoryMock, never()).update(any(InventoryRow.class));
		
		verify(alertsMock).addAlert(3, 2);
		verify(historyMock).add(any(HistoryRow.class));
		verify(transactionsMock).addTransaction(any(Transaction.class));
	}
	
	/*
	@Test
	public void testWithValidProducts() {
		resourceUnderTest.update(new TransactionRequest(1, EXISTING_BOX_BARCODE, Arrays.asList(new Product[]{
				new Product(2, 5),
				new Product(3, -5)
		})));
		
		verify(productsMock, never()).get(1);
	}
	*/
}

/*
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
*/