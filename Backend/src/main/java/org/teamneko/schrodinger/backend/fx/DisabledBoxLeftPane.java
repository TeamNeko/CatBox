package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DisabledBoxLeftPane extends CustomAnchorPane {
	
	public DisabledBoxLeftPane() {
		super();
	}
	
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().showOptions();
	}

}
