package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * The Class EditBoxLeftPane.
 */
public class EditBoxLeftPane extends CustomAnchorPane {
	
	/**
	 * Instantiates a new edits the box left pane.
	 */
	public EditBoxLeftPane() {
		super();
	}
	
	/**
	 * Show edit box options.
	 *
	 * @param event the event
	 */
	@FXML protected void modifier(ActionEvent event) {
		Context.getInstance().editBox();
	}
	
	/**
	 * Show parametres.
	 *
	 * @param event the event
	 */
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().showOptions();
	}

}
