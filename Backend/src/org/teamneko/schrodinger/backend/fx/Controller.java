package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

public class Controller {
	private static ApplicationState state = ApplicationState.ReadingRFID;
	
	@FXML protected SplitPane mainPane;
	
	@FXML protected void modifier(ActionEvent event) throws IOException {
		if(state == ApplicationState.ReadingRFID) {
			state = ApplicationState.EditingBox;
			mainPane.getItems().set(1, FXMLLoader.load(getClass().getResource("TablePane.fxml")));
		} else if(state == ApplicationState.EditingBox) {
			state = ApplicationState.ReadingRFID;
			mainPane.getItems().set(1, FXMLLoader.load(getClass().getResource("AnimationPane.fxml")));
		}
	}
	
	@FXML protected void quitter(ActionEvent event) {
		System.exit(0);
	}
}
