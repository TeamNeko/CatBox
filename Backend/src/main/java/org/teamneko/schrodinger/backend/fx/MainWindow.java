package org.teamneko.schrodinger.backend.fx;

import org.teamneko.meowlib.json.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;


/**
 * The Class MainWindow.
 */
public class MainWindow extends SplitPane {
	
	/** The Constant LEFT. */
	private static final int LEFT = 0;
	
	/** The Constant RIGHT. */
	private static final int RIGHT = 1;
	
	/** The main pane. */
	@FXML protected SplitPane mainPane;
	
	/** The login name. */
	@FXML protected Label loginName;
	
	/**
	 * Instantiates a new main window.
	 */
	public MainWindow() {
		CustomAnchorPane.loadFXML("MainWindow.fxml", this);
	}
	
	/**
	 * Show login pane.
	 */
	public void showLoginPane() { 
		setPane(new LoginPane(), RIGHT);
	}
	
	/**
	 * Show table pane.
	 */
	public void showTablePane() {
		setPane(new TablePane(), RIGHT); 
	}
	
	/**
	 * Show detail pane.
	 */
	public void showDetailPane() {
		setPane(new DetailPane(), RIGHT);
	}
	
	/**
	 * Show edit box in left pane.
	 */
	public void showEditBoxLeftPane() {
		setPane(new EditBoxLeftPane(), LEFT);
	}
	
	/**
	 * Show create box in left pane.
	 */
	public void showCreateBoxLeftPane() {
		setPane(new CreateBoxLeftPane(), LEFT);
	}
	
	/**
	 * Show disabled box in left pane.
	 */
	public void showDisabledBoxLeftPane() {
		setPane(new DisabledBoxLeftPane(), LEFT);
	}
	
	/**
	 * Show options pane.
	 */
	public void showOptionsPane() {
		setPane(new OptionsPane(), LEFT);
	}
	
	/**
	 * Show modification pane.
	 */
	public void showModificationPane() {
		setPane(new ModificationPane(), LEFT);
	}
	
	/**
	 * Show shutdown pane.
	 */
	public void showShutdownPane() {
		setPane(new ShutdownPane(), LEFT);
	}
	
	/**
	 * Sets the pane.
	 *
	 * @param pane the pane
	 * @param side the side
	 */
	private void setPane(final Pane pane, final int side) {
		Platform.runLater((Runnable) () -> mainPane.getItems().set(side, pane));
	}
	
	/**
	 * Sets the login name.
	 *
	 * @param user the new login name
	 */
	public void setLoginName(User user) {
		Platform.runLater((Runnable) () -> loginName.setText(" Utilisateur: " 
															 + user.getFirstName() + " " 
															 + user.getLastName() + " (" 
															 + user.getNumber() + ")")); 
	}
	
	/**
	 * Reset login name.
	 */
	public void resetLoginName() {
		Platform.runLater((Runnable) () -> loginName.setText(" Utilisateur non-connecte"));
	}
}
