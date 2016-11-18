package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DetailPane extends CustomAnchorPane implements EventHandler<KeyEvent>{
	private String barcode = "";
	@FXML protected ListView<?> detailList;
	
	public DetailPane(){
		super();
		Context.getInstance().getKeyboardHandler().setKeyboardListener(this);
	}
	
	@FXML protected void populer(ActionEvent event) {
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
			Context.getInstance().getRestClient().search(barcode);
		else
			barcode += event.getText();
	}

}
