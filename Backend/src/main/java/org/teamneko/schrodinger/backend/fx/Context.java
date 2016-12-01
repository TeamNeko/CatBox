package org.teamneko.schrodinger.backend.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.BoxSearchResult;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.ProductSearchResult;
import org.teamneko.meowlib.json.SearchResult;
import org.teamneko.meowlib.json.TransactionRequest;
import org.teamneko.meowlib.json.User;
import org.teamneko.meowlib.json.UserSearchResult;
import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.DeviceInitializationException;
import org.teamneko.schrodinger.backend.gpio.MFRC522;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.RFIDReader;
import org.teamneko.schrodinger.backend.gpio.RGBLed;
import org.teamneko.schrodinger.backend.runnable.PiezoNotification;
import org.teamneko.schrodinger.client.SchrodingerClient;

import com.sun.jersey.api.client.UniformInterfaceException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Context {
	private static Context instance = new Context();
	
	private KeyboardHandler keyboardHandler;
	private TablePane tablePane;
	
	private MainWindow mainWindow;
	private SchrodingerClient restClient;
	private User user;
	
	private String lastSearchedBarcode = "";
	private SearchResult lastSearchResult;
	private boolean isNewBarcode;
	
	private MFRC522 rfid = null;
	private RGBLed led = null;
	private Piezo piezo = null;
	private Thread piezoThread;
	
	private ObservableList<ModifiedProduct> temporaryModifiedProducts = null;
	private NamedProduct[] populateNamedProducts = null;
	private int productListLength = 0;
	List<TransactionRequest.Product> modifiedProducts;
	
	public void createBox() {
		setupEmptyDetail();
		mainWindow.showModificationPane();
		mainWindow.showTablePane();
		System.out.println("Create Box " + lastSearchedBarcode);
	}
	
	public void editBox() {
		mainWindow.showModificationPane();
		mainWindow.showTablePane();
		System.out.println("Edit Box " + lastSearchedBarcode);
	}
	
	public boolean login(String userCode) {
		try {
	 		user = restClient.requestUser(userCode);
	 		
	 		//success
	 		mainWindow.showDetailPane();
	 		mainWindow.showDisabledBoxLeftPane();
	 		mainWindow.setLoginName(user);
	 	} catch(UniformInterfaceException e) {
	 		//failure
	 		return false;
	 	}
		return true;
	}
	
	public void logout() {
		user = null;
		mainWindow.resetLoginName();
		mainWindow.showLoginPane();
		mainWindow.showShutdownPane();
	}

	public KeyboardHandler getKeyboardHandler() {
		return keyboardHandler;
	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public Piezo getPiezo() {
		return piezo;
	}
	
	public RFIDReader getRFIDReader() {
		return rfid;
	}
	
	public RGBLed getRGBLed() {
		return led;
	}
	
	public SchrodingerClient getRestClient() {
		return restClient;
	}

	public User getUser() {
		return user;
	}
	
	public String getLastSearchedBarcode() {
		return lastSearchedBarcode;
	}
	
	public boolean getIsNewBarcode() {
		return isNewBarcode;
	}
	
	public ObservableList<ModifiedProduct> getTemporaryModifiedProd() {
		return temporaryModifiedProducts;
	}
	
	public int getProductListLength() {
		return productListLength;
	}

	public NamedProduct[] getPopulateNamedProducts() {
		return populateNamedProducts;
	}
	
	public void removeBarcodeCallback() {
		keyboardHandler.removeKeyboardListener();
	}
	
	public void setTablePane(TablePane table) {
		tablePane = table;
	}
	
	public void selectTableRow(int row) {
		tablePane.selectRow(row);
	}
	
	public void modifyTableRow(int row, boolean add) {
		ModifiedProduct modifiedProduct = tablePane.getRowItem(row);
		if (add) 
			modifiedProduct.setModifiedqty(modifiedProduct.getModifiedqty()+1);
		else if (!add && (modifiedProduct.getModifiedqty()+modifiedProduct.getQuantity())!=0)
			modifiedProduct.setModifiedqty(modifiedProduct.getModifiedqty()-1);
		int quantity = modifiedProduct.getModifiedqty();
		TransactionRequest.Product product = getModifiedProduct(modifiedProduct.getId());
		if (product!=null) 
			product.setQuantity(quantity);
		else
			modifiedProducts.add(new TransactionRequest.Product(modifiedProduct.getId(),quantity));
		tablePane.setRowItem(row, modifiedProduct);
	}
	
	public TransactionRequest.Product getModifiedProduct(int id){
		for(TransactionRequest.Product product : modifiedProducts) {
			if(product.getId() == id)
				return product;
		}
		return null;
	}
	
	public void commitTransaction() {
		restClient.postTransaction(new TransactionRequest((int)user.getId(),
														  lastSearchedBarcode,
														  modifiedProducts));
	}
	
	public void search(String barcode, DetailPane pane) {
		lastSearchResult = restClient.search(barcode);
		lastSearchedBarcode = barcode;
		isNewBarcode = false;
		piezoThread = new Thread((new PiezoNotification(PiezoNotification.PiezoMode.ItemFound)));
		if(lastSearchResult.getClass() == ProductSearchResult.class)
		{
			mainWindow.showDisabledBoxLeftPane();
			pane.showProduct(((ProductSearchResult) lastSearchResult).getProduct());
		}	
		else if(lastSearchResult.getClass() == BoxSearchResult.class)
		{
			mainWindow.showEditBoxLeftPane();
			Box boxResult = ((BoxSearchResult) lastSearchResult).getBox();
		    setupBoxDetail(boxResult);
			pane.showBox(boxResult);
		}
		else if(lastSearchResult.getClass() == UserSearchResult.class)
		{
			mainWindow.showDisabledBoxLeftPane();
			pane.showUser(((UserSearchResult) lastSearchResult).getUser());
		}
		else
		{
			mainWindow.showCreateBoxLeftPane();
			pane.showNotFound();
			isNewBarcode = true;
			piezoThread = new Thread((new PiezoNotification(PiezoNotification.PiezoMode.NewItem)));
		}
		piezoThread.start();
	}
	
	public void search(String barcode, TablePane pane) {
		lastSearchResult = restClient.search(barcode);
		
		if(lastSearchResult.getClass() == ProductSearchResult.class)
		{
			Product product = ((ProductSearchResult) lastSearchResult).getProduct();
			ModifiedProduct addProduct = getTableProduct(product, (List<ModifiedProduct>)pane.detailTable.getItems());
			if(addProduct == null) {
				addProduct = new ModifiedProduct(product.getId(), 0, product.getName(), 1);
				pane.addRowItem(addProduct);
				modifiedProducts.add(new TransactionRequest.Product(addProduct.getId(),1));
				productListLength++;
			}
		}
	}
	
	public ModifiedProduct getTableProduct(Product product, List<ModifiedProduct> modifiedList) {
		for (ModifiedProduct item : modifiedList) {
			if(item.getId() == product.getId()) {
				return item;
			}
		}
		return null;
	}
	
	private void setupBoxDetail(Box boxResult) {
		populateNamedProducts = Context.getInstance().getRestClient().getBoxDetails(boxResult.getId());
		List<ModifiedProduct> modProdList = new ArrayList();
		productListLength = populateNamedProducts.length;
		for(int i=0; i < productListLength; i++)
		{
			modProdList.add(new ModifiedProduct(populateNamedProducts[i].getId(), 
								    			populateNamedProducts[i].getQuantity(), 
								    			populateNamedProducts[i].getName()));
		}
		temporaryModifiedProducts = FXCollections.observableArrayList(modProdList);
	}
	
	private void setupEmptyDetail() {
		List<ModifiedProduct> modProdList = new ArrayList();
		productListLength = 0;
		temporaryModifiedProducts = FXCollections.observableArrayList(modProdList);
	} 

	public void setBarcodeCallback(Consumer<String> consumer) {
		keyboardHandler.setKeyboardListener(new BarcodeScannerListener(consumer));
	}
	
	public void setRestClient(SchrodingerClient restClient) {
		this.restClient = restClient;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void showOptions() {
		mainWindow.showOptionsPane();
	}
	public static Context getInstance() {
		return instance;
	}
	
	private Context() {
		try {
			DeviceFactory.setupPi4j();
			led = DeviceFactory.createRGBLed();
			piezo = DeviceFactory.createPiezo();
			
			try {
				rfid = DeviceFactory.createMFRC522();
			} catch (DeviceInitializationException e) {
				e.printStackTrace();
			}
			
		} catch (Pi4JMissingException e) {
			System.err.println("Could not initialize Pi4j, probably not running on a Pi");
		}
		
		keyboardHandler = new KeyboardHandler();
		mainWindow = new MainWindow();
		restClient = new SchrodingerClient("http://localhost:8080/Frontend/rest");
		modifiedProducts = new ArrayList();
	}	
}
