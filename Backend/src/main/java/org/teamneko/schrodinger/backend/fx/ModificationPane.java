package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ModificationPane extends CustomAnchorPane {
	
	public ModificationPane() {
		super();
	}
	
	@FXML protected void plus(ActionEvent event) {
		
	}
	
	@FXML protected void minus(ActionEvent event) {
		
	}
	
	@FXML protected void up(ActionEvent event) {
		
	}
	
	@FXML protected void down(ActionEvent event) {
	
	}
	
	@FXML protected void ok(ActionEvent event) {
		Context.getInstance().getMainWindow().showButtonPane();
	}
	
	@FXML protected void cancel(ActionEvent event) {
		Context.getInstance().getMainWindow().showButtonPane();
	}
}
