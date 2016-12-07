package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * The Class DisabledBoxLeftPane.
 */
public class DisabledBoxLeftPane extends CustomAnchorPane {
	
	/**
	 * Instantiates a new disabled box left pane.
	 */
	public DisabledBoxLeftPane() {
		super();
	}
	
	/**
	 * Show parameters
	 *
	 * @param event the event
	 */
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().showOptions();
	}

}
