package org.teamneko.schrodinger.backend.fx;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * The Class TablePane.
 */
public class TablePane extends CustomAnchorPane {
	
	/** The detail table. */
	@FXML TableView detailTable;
	
	/** The barcode field. */
	@FXML TextField barcodeField;
	
	/**
	 * Instantiates a new table pane.
	 */
	public TablePane() {
		super();
		this.setMouseTransparent(true);
		Context.getInstance().setTablePane(this);
		Context.getInstance().setBarcodeCallback(s -> handleBarcode(s));
		barcodeField.setText(Context.getInstance().getLastSearchedBarcode());
		setupDetailTable();
		selectRow(0);
		System.out.println(detailTable.getItems().toString());
	}

	/**
	 * Handle barcode.
	 *
	 * @param barcode the barcode
	 */
	public void handleBarcode(String barcode) {
		Context.getInstance().search(barcode, this);
	}
	 
	/**
	 * Setup detail table.
	 */
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
	
	/**
	 * Select row.
	 *
	 * @param row the row
	 */
	public void selectRow(int row) {
		detailTable.requestFocus();
		detailTable.getSelectionModel().select(row);
		detailTable.getFocusModel().focus(row);	
	}
	
	/**
	 * Sets the row item.
	 *
	 * @param row the row
	 * @param modifiedProduct the modified product
	 */
	public void setRowItem(int row, ModifiedProduct modifiedProduct) {
		detailTable.getItems().set(row, modifiedProduct);
	}
	
	/**
	 * Adds the row item.
	 *
	 * @param product the product
	 */
	public void addRowItem(ModifiedProduct product) {
		detailTable.getItems().add(product);
	}
	
	/**
	 * Gets the row item.
	 *
	 * @param row the row
	 * @return the row item
	 */
	public ModifiedProduct getRowItem(int row) {
		return (ModifiedProduct) detailTable.getItems().get(row);
	}
}
