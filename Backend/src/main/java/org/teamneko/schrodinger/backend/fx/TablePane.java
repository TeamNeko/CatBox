package org.teamneko.schrodinger.backend.fx;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePane extends CustomAnchorPane {
	@FXML TableView detailTable;
	public TablePane() {
		super();
		Context.getInstance().setBarcodeCallback(s -> handleBarcode(s));
		System.out.println("Test:" + Context.getInstance().getPopulateNamedProducts()[0].getName());
		detailTable.setItems(Context.getInstance().getTemporaryModifiedProd());
		TableColumn<ModifiedProduct,String> nameCol = new TableColumn<ModifiedProduct,String>("Nom");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<ModifiedProduct,Integer> qteCol = new TableColumn<ModifiedProduct,Integer>("Qty");
		qteCol.setCellValueFactory(new PropertyValueFactory("qty"));
		detailTable.getColumns().setAll(nameCol,qteCol);
	}

	public void handleBarcode(String barcode) {
		System.out.println("Barcode: " + barcode);
	}

}
