package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class CustomAnchorPane extends AnchorPane {
	public CustomAnchorPane() {
		loadFXML(this.getClass().getSimpleName() + ".fxml", this);
	}
	
	public static void loadFXML(String form, Object root) {
		FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(form));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
}
