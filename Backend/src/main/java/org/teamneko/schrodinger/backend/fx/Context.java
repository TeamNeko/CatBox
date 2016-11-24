package org.teamneko.schrodinger.backend.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.BoxSearchResult;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.ProductSearchResult;
import org.teamneko.meowlib.json.SearchResult;
import org.teamneko.meowlib.json.User;
import org.teamneko.meowlib.json.UserSearchResult;
import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.DeviceInitializationException;
import org.teamneko.schrodinger.backend.gpio.MFRC522;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.RFIDReader;
import org.teamneko.schrodinger.backend.gpio.RGBLed;
import org.teamneko.schrodinger.client.SchrodingerClient;

import com.sun.jersey.api.client.UniformInterfaceException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Context {
	private static Context instance = new Context();
	
	private KeyboardHandler keyboardHandler;
	
	private MainWindow mainWindow;
	private SchrodingerClient restClient;
	private User user;
	
	private String lastSearchedBarcode;
	private SearchResult lastSearchResult;
	
	private MFRC522 rfid = null;
	private RGBLed led = null;
	private Piezo piezo = null;
	
	private ObservableList<ModifiedProduct> temporaryModifiedProducts = null;
	private NamedProduct[] populateNamedProducts = null;
	
	public void createBox() {
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
	 		mainWindow.showDetailPane();
	 		mainWindow.showDisabledBoxLeftPane();
	 	} catch(UniformInterfaceException e) {
	 		return false;
	 	}
		return true;
	}
	
	public void logout() {
		user = null;
		
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
	
	public ObservableList<ModifiedProduct> getTemporaryModifiedProd() {
		return temporaryModifiedProducts;
	}

	public NamedProduct[] getPopulateNamedProducts() {
		return populateNamedProducts;
	}
	public void removeBarcodeCallback() {
		keyboardHandler.removeKeyboardListener();
	}
	
	public void search(String barcode, DetailPane pane) {
		lastSearchResult = restClient.search(barcode);
		lastSearchedBarcode = barcode;
		
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
		}
	}

	private void setupBoxDetail(Box boxResult) {
		populateNamedProducts = Context.getInstance().getRestClient().getBoxDetails(boxResult.getId());
		List<ModifiedProduct> modProdList = new ArrayList();
		for(int i=0; i<populateNamedProducts.length; i++)
		{
			modProdList.add(new ModifiedProduct(populateNamedProducts[i].getId(), 
								    			populateNamedProducts[i].getQuantity(), 
								    			populateNamedProducts[i].getName()));
		}
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
	}	
}
