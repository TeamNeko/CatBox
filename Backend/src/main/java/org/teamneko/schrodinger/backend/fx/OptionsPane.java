package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * The Class OptionsPane.
 */
public class OptionsPane extends CustomAnchorPane {
	
	/** The close button. */
	@FXML Button closeButton;
	
	/**
	 * Instantiates a new options pane.
	 */
	public OptionsPane() {
		super();
		if (!Context.getInstance().getUser().getType().equals("Gestionnaire")) 
			closeButton.setDisable(true);
	}
	
	/**
	 * Return to default pane.
	 *
	 * @param event the event
	 */
	@FXML protected void annuler(ActionEvent event) {
		if(Context.getInstance().getLastSearchedBarcode().isEmpty())
			Context.getInstance().getMainWindow().showDisabledBoxLeftPane();
		else if (Context.getInstance().getIsNewBarcode())
			Context.getInstance().getMainWindow().showCreateBoxLeftPane();
		else 
			Context.getInstance().getMainWindow().showEditBoxLeftPane();
	}
	
	/**
	 * Disconnect user.
	 *
	 * @param event the event
	 */
	@FXML protected void deconnection(ActionEvent event) {
		Context.getInstance().logout();
	}
	
	/**
	 * Shutdown.
	 *
	 * @param event the event
	 */
	@FXML protected void mise_hors_tension(ActionEvent event) {
		/*
		try {
			Runtime.getRuntime().exec("shutdown -h now");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		Platform.exit();
	}
}
