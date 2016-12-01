package org.teamneko.schrodinger.backend.fx;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BarcodeKeyboardHandler implements EventHandler<KeyEvent> {
	private String barcode = "";
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			Context.getInstance().getRestClient().search(barcode);
		else
			barcode += event.getText();
	}

}
