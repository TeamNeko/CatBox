package org.teamneko.schrodinger.backend.fx;

import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


// TODO: Auto-generated Javadoc
/**
 * The Class DetailPane.
 */
public class DetailPane extends CustomAnchorPane {
	
	/** The detail list. */
	@FXML protected ListView<String> detailList;
	
	/** The barcode field. */
	@FXML protected TextField barcodeField;
 
	/**
	 * Instantiates a new detail pane.
	 */
	public DetailPane(){
		super();
		Context.getInstance().setDetailPane(this);
		Context.getInstance().setBarcodeCallback(s -> handleBarcode(s));
		String lastCodeBar = Context.getInstance().getLastSearchedBarcode();
		if(!lastCodeBar.isEmpty())
			handleBarcode(lastCodeBar);
		HBox.setHgrow(barcodeField, Priority.ALWAYS);
	}
	
	/**
	 * Populer.
	 *
	 * @param event the event
	 */
	@FXML protected void populer(ActionEvent event) {
	}
	
	/**
	 * Handle barcode.
	 *
	 * @param barcode the barcode
	 */
	public void handleBarcode(String barcode) {
		barcodeField.setText(barcode);
		Context.getInstance().search(barcode, this);
	}
	
	/**
	 * Show box.
	 *
	 * @param box the box
	 */
	public void showBox(Box box) {
		printInListView("Boite: ");
		NamedProduct[] content = Context.getInstance().getPopulateNamedProducts();
		for(int i=0; i<content.length; i++)
		{
			detailList.getItems().add(content[i].getName() + " x" + content[i].getQuantity());
		}
	}
	
	/**
	 * Show not found.
	 */
	public void showNotFound() {
		printInListView("Non trouvé");
	}
	
	/**
	 * Show product.
	 *
	 * @param product the product
	 */
	public void showProduct(Product product) {
		printInListView("Product: " + product.getName());
	}
	
	/**
	 * Show user.
	 *
	 * @param user the user
	 */
	public void showUser(User user) {
		printInListView(user.getType() + ": " + user.getFirstName() + " " + user.getLastName());
	}
	
	/**
	 * Gets the detail list.
	 *
	 * @return the detail list
	 */
	public ListView<String> getDetailList() {
		return detailList;
	}
	
	/**
	 * Prints the in list view.
	 *
	 * @param elements the elements
	 */
	private void printInListView(String... elements) {
		detailList.getItems().clear();
		detailList.getItems().addAll(elements);
	}
}
