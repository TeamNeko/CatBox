package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OptionsPane extends CustomAnchorPane {
	
	public OptionsPane() {
		super();
	}
	
	@FXML protected void annuler(ActionEvent event) {
		if(Context.getInstance().getLastSearchedBarcode().isEmpty())
			Context.getInstance().getMainWindow().showDisabledBoxLeftPane();
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
