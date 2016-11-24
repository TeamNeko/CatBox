package org.teamneko.schrodinger.backend.fx;

import java.util.function.Consumer;

import org.teamneko.meowlib.json.BoxSearchResult;
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
	
	public void createBox() {
		System.out.println("Create Box " + lastSearchedBarcode);
	}
	
	public void editBox() {
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
			pane.showBox(((BoxSearchResult) lastSearchResult).getBox());
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
