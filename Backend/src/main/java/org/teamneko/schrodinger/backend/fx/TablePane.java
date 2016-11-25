package org.teamneko.schrodinger.backend.fx;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePane extends CustomAnchorPane {
	@FXML TableView detailTable;
	@FXML TextField barcodeField;
	
	public TablePane() {
		super();
		Context.getInstance().setTablePane(this);
		Context.getInstance().setBarcodeCallback(s -> handleBarcode(s));
		barcodeField.setText(Context.getInstance().getLastSearchedBarcode());
		setupDetailTable();
		selectRow(0);
	}

	private void setupDetailTable() {
		detailTable.setItems(Context.getInstance().getTemporaryModifiedProd());
		TableColumn<ModifiedProduct,String> nameCol = new TableColumn<ModifiedProduct,String>("Nom");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		nameCol.setPrefWidth(220);
		
		TableColumn<ModifiedProduct,Integer> qteCol = new TableColumn<ModifiedProduct,Integer>("Qte");
		qteCol.setCellValueFactory(new PropertyValueFactory("quantity"));
		qteCol.setPrefWidth(50);
		
		TableColumn<ModifiedProduct,Integer> modqteCol = new TableColumn<ModifiedProduct,Integer>("+/-");
		modqteCol.setCellValueFactory(new PropertyValueFactory("modifiedqty"));
		modqteCol.setPrefWidth(50);
		
		detailTable.getColumns().setAll(nameCol,qteCol,modqteCol);
	}

	public void handleBarcode(String barcode) {
		System.out.println("Barcode: " + barcode);
	}
	
	public void selectRow(int row) {
		detailTable.requestFocus();
		detailTable.getSelectionModel().select(row);
		detailTable.getFocusModel().focus(row);	
	}
}
