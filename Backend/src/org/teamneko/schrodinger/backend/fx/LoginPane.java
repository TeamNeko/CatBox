package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginPane extends CustomAnchorPane {
	@FXML protected TextField rfidText;
	 
	public LoginPane() {
		super();
	}
	
	 @FXML protected void login(ActionEvent event) {
		 Context.getInstance().login(rfidText.getText());
	 }
}
