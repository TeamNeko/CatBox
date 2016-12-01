package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ButtonPane extends CustomAnchorPane {
	
	public ButtonPane() {
		super();
	}
	
	@FXML protected void modifier(ActionEvent event) {
	}
	
	@FXML protected void quitter(ActionEvent event) {
		System.exit(0);
	}
}
