package org.teamneko.schrodinger.backend.fx;


import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * The Class ShutdownPane.
 */
public class ShutdownPane extends CustomAnchorPane {
	
	/**
	 * Instantiates a new shutdown pane.
	 */
	public ShutdownPane() {
		super();
	}
	
	/**
	 * Shutdown.
	 */
	@FXML protected void shutdown() {
		Context.getInstance().shutdown();
	}
}
