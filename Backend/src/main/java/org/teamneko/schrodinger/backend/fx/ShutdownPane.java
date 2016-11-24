package org.teamneko.schrodinger.backend.fx;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class ShutdownPane extends CustomAnchorPane {
	public ShutdownPane() {
		super();
	}
	
	@FXML protected void shutdown() {
		Platform.exit();
	}
}
