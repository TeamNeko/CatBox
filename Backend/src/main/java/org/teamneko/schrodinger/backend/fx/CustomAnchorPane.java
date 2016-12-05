package org.teamneko.schrodinger.backend.fx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


// TODO: Auto-generated Javadoc
/**
 * The Class CustomAnchorPane.
 */
public class CustomAnchorPane extends AnchorPane {
	
	/**
	 * Instantiates a new custom anchor pane.
	 */
	public CustomAnchorPane() {
		loadFXML(this.getClass().getSimpleName() + ".fxml", this);
	}
	
	/**
	 * Load FXML.
	 *
	 * @param form the form
	 * @param root the root
	 */
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
