package org.teamneko.schrodinger.backend.fx;

public class TablePane extends CustomAnchorPane {
	public TablePane() {
		super();
		Context.getInstance().setBarcodeCallback(s -> handleBarcode(s));
	}

	public void handleBarcode(String barcode) {
		System.out.println("Barcode: " + barcode);
	}

}
