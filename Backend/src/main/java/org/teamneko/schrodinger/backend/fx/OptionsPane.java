package org.teamneko.schrodinger.backend.fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OptionsPane extends CustomAnchorPane {
	
	public OptionsPane() {
		super();
	}
	
	@FXML protected void annuler(ActionEvent event) {
		Context.getInstance().getMainWindow().showButtonPane();
	}
	
	@FXML protected void deconnection(ActionEvent event) {
		Context.getInstance().getMainWindow().showLoginPane();
		Context.getInstance().getMainWindow().showButtonPane();
	}
	
	@FXML protected void mise_hors_tension(ActionEvent event) {
		Platform.exit();
	}
}
