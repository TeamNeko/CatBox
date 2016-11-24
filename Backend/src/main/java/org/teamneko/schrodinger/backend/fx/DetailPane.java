package org.teamneko.schrodinger.backend.fx;

import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class DetailPane extends CustomAnchorPane {
	@FXML protected ListView<String> detailList;
	@FXML protected TextField barcodeField;

	public DetailPane(){
		super();
		Context.getInstance().setBarcodeCallback(s -> handleBarcode(s));
		HBox.setHgrow(barcodeField, Priority.ALWAYS);
	}
	
	@FXML protected void populer(ActionEvent event) {
	}
	
	public void handleBarcode(String barcode) {
		barcodeField.setText(barcode);
		Context.getInstance().search(barcode, this);
	}
	
	public void showBox(Box box) {
		printInListView("Box: " + box.getBarcode());
	}
	
	public void showNotFound() {
		printInListView("Not found");
	}
	
	public void showProduct(Product product) {
		printInListView("Product: " + product.getName());
	}
	
	public void showUser(User user) {
		printInListView(user.getType() + ": " + user.getFirstName() + " " + user.getLastName());
	}
	
	private void printInListView(String... elements) {
		detailList.getItems().clear();
		detailList.getItems().addAll(elements);
	}
}
