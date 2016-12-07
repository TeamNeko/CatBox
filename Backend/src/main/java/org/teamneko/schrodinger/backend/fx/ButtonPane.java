package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//
/**
 * The Class ButtonPane.
 */
public class ButtonPane extends CustomAnchorPane {
	
	/**
	 * Instantiates a new button pane.
	 */
	public ButtonPane() {
		super();
	}
	
	/**
	 * Modifier.
	 *
	 * @param event the event
	 */
	@FXML protected void modifier(ActionEvent event) {
		Context.getInstance().getMainWindow().showModificationPane();
	}
	
	/**
	 * Parametres.
	 *
	 * @param event the event
	 */
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().getMainWindow().showOptionsPane();
	}

}
