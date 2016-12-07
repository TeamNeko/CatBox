package org.teamneko.schrodinger.backend.fx;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

// 
/**
 * The Class BarcodeKeyboardHandler. Used to handle KeyEvents
 */
public class BarcodeKeyboardHandler implements EventHandler<KeyEvent> {
	
	/** The barcode. */
	private String barcode = "";
	
	
	/* 
	 * Handles KeyEvent from the GUI regardless of focus
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			Context.getInstance().getRestClient().search(barcode);
		else
			barcode += event.getText();
	}

}
