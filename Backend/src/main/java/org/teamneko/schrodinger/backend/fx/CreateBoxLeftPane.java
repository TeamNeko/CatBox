package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateBoxLeftPane.
 */
public class CreateBoxLeftPane extends CustomAnchorPane {
	
	/**
	 * Instantiates a new creates the box left pane.
	 */
	public CreateBoxLeftPane() {
		super();
	}
	
	/**
	 * Creer.
	 *
	 * @param event the event
	 */
	@FXML protected void creer(ActionEvent event) {
		Context.getInstance().createBox();
	}
	
	/**
	 * Parametres.
	 *
	 * @param event the event
	 */
	@FXML protected void parametres(ActionEvent event) {
		Context.getInstance().showOptions();
	}

}
