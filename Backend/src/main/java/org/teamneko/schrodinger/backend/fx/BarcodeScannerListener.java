package org.teamneko.schrodinger.backend.fx;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


// 
/**
 * The listener interface for receiving barcodeScanner events.
 * The class that is interested in processing a barcodeScanner
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addBarcodeScannerListener<code> method. When
 * the barcodeScanner event occurs, that object's appropriate
 * method is invoked.
 *
 * @see BarcodeScannerEvent
 */
public class BarcodeScannerListener implements EventHandler<KeyEvent> {
	
	/** The barcode. */
	private String barcode = "";
	
	/** The barcode listener. */
	private Consumer<String> barcodeListener;
	
	/**
	 * Instantiates a new barcode scanner listener.
	 *
	 * @param barcodeListener the barcode listener
	 */
	public BarcodeScannerListener(Consumer<String> barcodeListener) {
		this.barcodeListener = barcodeListener;
	}
	
	/* 
	 * Handles KeyEvents
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
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
