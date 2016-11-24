package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditBoxLeftPane extends CustomAnchorPane {
	
	public EditBoxLeftPane() {
		super();
	}
	
	@FXML protected void modifier(ActionEvent event) {
		Context.getInstance().editBox();
	}
	
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().showOptions();
	}

}
