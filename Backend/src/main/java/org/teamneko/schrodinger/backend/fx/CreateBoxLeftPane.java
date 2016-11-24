package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreateBoxLeftPane extends CustomAnchorPane {
	
	public CreateBoxLeftPane() {
		super();
	}
	
	@FXML protected void creer(ActionEvent event) {
		Context.getInstance().createBox();
	}
	
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().showOptions();
	}

}
