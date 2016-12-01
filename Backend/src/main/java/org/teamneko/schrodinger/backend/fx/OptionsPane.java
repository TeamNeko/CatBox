package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OptionsPane extends CustomAnchorPane {
	
	@FXML Button closeButton;
	
	public OptionsPane() {
		super();
		if (!Context.getInstance().getUser().getType().equals("Gestionnaire")) 
			closeButton.setDisable(true);
	}
	
	@FXML protected void annuler(ActionEvent event) {
		if(Context.getInstance().getLastSearchedBarcode().isEmpty())
			Context.getInstance().getMainWindow().showDisabledBoxLeftPane();
		else if (Context.getInstance().getIsNewBarcode())
			Context.getInstance().getMainWindow().showCreateBoxLeftPane();
		else 
			Context.getInstance().getMainWindow().showEditBoxLeftPane();
	}
	
	@FXML protected void deconnection(ActionEvent event) {
		Context.getInstance().logout();
	}
	
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
