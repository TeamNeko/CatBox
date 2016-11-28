package org.teamneko.schrodinger.backend.fx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

public class MainWindow extends SplitPane {
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	
	@FXML protected SplitPane mainPane;
	
	public MainWindow() {
		CustomAnchorPane.loadFXML("MainWindow.fxml", this);
	}
	
	private void setPane(final Pane pane, final int side) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainPane.getItems().set(side, pane);
			}
		});
	}
	
	public void showLoginPane() {
		setPane(new LoginPane(), RIGHT);
	}
	
	public void showTablePane() {
		setPane(new TablePane(), RIGHT);
	}
	
	public void showDetailPane() {
		setPane(new DetailPane(), RIGHT);
	}
	
	public void showButtonPane() {
		setPane(new ButtonPane(), LEFT);
	}
}
