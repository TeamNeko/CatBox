package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;
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
import org.teamneko.schrodinger.backend.runnable.LEDFlash;
import org.teamneko.schrodinger.backend.runnable.PiezoNotification;
import org.teamneko.schrodinger.client.SchrodingerClient;

import com.sun.jersey.api.client.UniformInterfaceException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class Context.
 */
public class Context {
	
	/** The instance. */
	private static Context instance = new Context();
	
	/** The keyboard handler. */
	private KeyboardHandler keyboardHandler;
	
	/** The table pane. */
	private TablePane tablePane;
	
	/** The detail pane. */
	private DetailPane detailPane;
	
	/** The main window. */
	private MainWindow mainWindow;
	
	/** The rest client. */
	private SchrodingerClient restClient;
	
	/** The user. */
	private User user;
	
	/** The last searched barcode. */
	private String lastSearchedBarcode = "";
	
	/** The last search result. */
	private SearchResult lastSearchResult;
	
	/** The is new barcode. */
	private boolean isNewBarcode;
	
	/** The rfid. */
	private MFRC522 rfid = null;
	
	/** The led. */
	private RGBLed led = null;
	
	/** The piezo. */
	private Piezo piezo = null;
	
	/** The piezo thread. */
	private Thread piezoThread;
	
	/** The temporary modified products. */
	private ObservableList<ModifiedProduct> temporaryModifiedProducts = null;
	
	/** The populate named products. */
	private NamedProduct[] populateNamedProducts = null;
	 
	/** The product list length. */
	private int productListLength = 0;
	
	/** The modified products. */
	List<TransactionRequest.Product> modifiedProducts;
	
	/** The led Thread. */
	Thread ledT;
	
	/**
	 * Creates the box.
	 */
	public void createBox() {
		setupEmptyDetail();
		mainWindow.showModificationPane();
		mainWindow.showTablePane();
		System.out.println("Create Box " + lastSearchedBarcode);
	}
	
	/**
	 * Edits the box.
	 */
	public void editBox() {
		mainWindow.showModificationPane();
		mainWindow.showTablePane();
		System.out.println("Edit Box " + lastSearchedBarcode);
	}
	 
	/**
	 * Login.
	 *
	 * @param userCode the user code
	 * @return true, if successful
	 */
	public boolean login(String userCode) {
		try {
			if(ledT != null) {
				ledT.interrupt();
			}
	 		user = restClient.requestUser(userCode);
	 
	 		//success
	 		new Thread(new PiezoNotification(PiezoNotification.PiezoMode.LoginSuccess)).start();
	 		ledT = new Thread(new LEDFlash(0, 1, 0, 100));
			ledT.start();  
	 		mainWindow.showDetailPane();
	 		mainWindow.showDisabledBoxLeftPane();
	 		mainWindow.setLoginName(user);
	 		System.out.println("User: " + user.getNumber() + " is connected");
	 	} catch(UniformInterfaceException e) {
	 		//failure
	 		new Thread(new PiezoNotification(PiezoNotification.PiezoMode.LoginFail)).start();
	 		ledT = new Thread(new LEDFlash(1, 0, 0, 100));
			ledT.start();  
	 		System.out.println("User failed to connect");
	 		return false;
	 	}
		return true;
	}
	
	/**
	 * Logout.
	 */
	public void logout() {
		ledT.interrupt();
		user = null;
		lastSearchedBarcode = "";
		mainWindow.resetLoginName();
		mainWindow.showLoginPane();
		mainWindow.showShutdownPane();
	}

	/**
	 * Gets the keyboard handler.
	 *
	 * @return the keyboard handler
	 */
	public KeyboardHandler getKeyboardHandler() {
		return keyboardHandler;
	}
	
	/**
	 * Gets the main window.
	 *
	 * @return the main window
	 */
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	/**
	 * Gets the piezo.
	 *
	 * @return the piezo
	 */
	public Piezo getPiezo() {
		return piezo;
	}
	
	/**
	 * Gets the RFID reader.
	 *
	 * @return the RFID reader
	 */
	public RFIDReader getRFIDReader() {
		return rfid;
	}
	
	/**
	 * Gets the RGB led.
	 *
	 * @return the RGB led
	 */
	public RGBLed getRGBLed() {
		return led;
	}
	
	/**
	 * Gets the rest client.
	 *
	 * @return the rest client
	 */
	public SchrodingerClient getRestClient() {
		return restClient;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Gets the last searched barcode.
	 *
	 * @return the last searched barcode
	 */
	public String getLastSearchedBarcode() {
		return lastSearchedBarcode;
	}
	
	/**
	 * Gets the checks if is new barcode.
	 *
	 * @return the checks if is new barcode
	 */
	public boolean getIsNewBarcode() {
		return isNewBarcode;
	}
	
	/**
	 * Gets the temporary modified prod.
	 *
	 * @return the temporary modified prod
	 */
	public ObservableList<ModifiedProduct> getTemporaryModifiedProd() {
		return temporaryModifiedProducts;
	}
	
	/**
	 * Gets the product list length.
	 *
	 * @return the product list length
	 */
	public int getProductListLength() {
		return productListLength;
	}

	/**
	 * Gets the populate named products.
	 *
	 * @return the populate named products
	 */
	public NamedProduct[] getPopulateNamedProducts() {
		return populateNamedProducts;
	}
	
	/**
	 * Removes the barcode callback.
	 */
	public void removeBarcodeCallback() {
		keyboardHandler.removeKeyboardListener();
	}
	
	/**
	 * Sets the table pane.
	 *
	 * @param table the new table pane
	 */
	public void setTablePane(TablePane table) {
		tablePane = table;
	}
	
	/**
	 * Gets the table pane.
	 *
	 * @return the table pane
	 */
	public TablePane getTablePane() {
		return tablePane;
	}
	
	/**
	 * Sets the detail pane.
	 *
	 * @param list the new detail pane
	 */
	public void setDetailPane(DetailPane list) {
		detailPane = list;
	}
	
	/**
	 * Gets the detail pane.
	 *
	 * @return the detail pane
	 */
	public DetailPane getDetailPane() {
		return detailPane;
	}
	
	/**
	 * Select table row.
	 *
	 * @param row the row
	 */
	public void selectTableRow(int row) {
		tablePane.selectRow(row);
	}
	
	/**
	 * Shutdown.
	 */
	public void shutdown()  {
		while(true) {
			try {
				Runtime.getRuntime().exec("sudo shutdown -h now");
				Platform.exit();
				System.exit(0);
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * Modify table row.
	 *
	 * @param row the row
	 * @param add the add
	 */
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
	
	/**
	 * Gets the modified product.
	 *
	 * @param id the id
	 * @return the modified product
	 */
	public TransactionRequest.Product getModifiedProduct(int id){
		for(TransactionRequest.Product product : modifiedProducts) {
			if(product.getId() == id)
				return product;
		}
		return null;
	}
	
	/**
	 * Commit transaction.
	 */
	public void commitTransaction() {
		restClient.postTransaction(new TransactionRequest((int)user.getId(),
														  lastSearchedBarcode,
														  modifiedProducts));
	}
	
	/**
	 * Search.
	 *
	 * @param barcode the barcode
	 * @param pane the pane
	 */
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
	
	/**
	 * Search.
	 *
	 * @param barcode the barcode
	 * @param pane the pane
	 */
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
	
	/**
	 * Gets the table product.
	 *
	 * @param product the product
	 * @param modifiedList the modified list
	 * @return the table product
	 */
	public ModifiedProduct getTableProduct(Product product, List<ModifiedProduct> modifiedList) {
		for (ModifiedProduct item : modifiedList) {
			if(item.getId() == product.getId()) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Sets the up box detail.
	 *
	 * @param boxResult the new up box detail
	 */
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
	
	/**
	 * Setup empty detail.
	 */
	private void setupEmptyDetail() {
		List<ModifiedProduct> modProdList = new ArrayList();
		productListLength = 0;
		temporaryModifiedProducts = FXCollections.observableArrayList(modProdList);
	} 

	/**
	 * Sets the barcode callback.
	 *
	 * @param consumer the new barcode callback
	 */
	public void setBarcodeCallback(Consumer<String> consumer) {
		keyboardHandler.setKeyboardListener(new BarcodeScannerListener(consumer));
	}
	
	/**
	 * Sets the rest client.
	 *
	 * @param restClient the new rest client
	 */
	public void setRestClient(SchrodingerClient restClient) {
		this.restClient = restClient;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Show options.
	 */
	public void showOptions() {
		mainWindow.showOptionsPane();
	}
	
	/**
	 * Gets the single instance of Context.
	 *
	 * @return single instance of Context
	 */
	public static Context getInstance() {
		return instance;
	}
	
	/**
	 * Instantiates a new context.
	 */
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
		restClient = new SchrodingerClient(System.getProperty("catbox.rest.url"));
		modifiedProducts = new ArrayList();
	}	
}
