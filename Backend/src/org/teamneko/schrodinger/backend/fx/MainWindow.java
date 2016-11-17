package org.teamneko.schrodinger.backend.fx;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

public class MainWindow extends SplitPane {
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	
	@FXML protected SplitPane mainPane;
	
	public MainWindow() {
		CustomAnchorPane.loadFXML("MainWindow.fxml", this);
		showLoginPane();
		showButtonPane();
	}
	
	public void setLeftPane(Pane pane) {
		mainPane.getItems().set(LEFT, pane);
	}
	
	public void setRightPane(Pane pane) {
		mainPane.getItems().set(RIGHT, pane);
	}
	
	public void showLoginPane() {
		setRightPane(new LoginPane());
	}
	
	public void showTablePane() {
		setRightPane(new TablePane());
	}
	
	public void showDetailPane() {
		setRightPane(new DetailPane());
	}
	
	public void showButtonPane() {
		setLeftPane(new ButtonPane());
	}
}
