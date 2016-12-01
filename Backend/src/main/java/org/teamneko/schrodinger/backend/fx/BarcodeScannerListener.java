package org.teamneko.schrodinger.backend.fx;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BarcodeScannerListener implements EventHandler<KeyEvent> {
	private String barcode = "";
	private Consumer<String> barcodeListener;
	
	public BarcodeScannerListener(Consumer<String> barcodeListener) {
		this.barcodeListener = barcodeListener;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
		{
			Context.getInstance().getRestClient().search(barcode);
			barcodeListener.accept(barcode);
			barcode = "";
		}
		else
			barcode += event.getText();
	}

}
