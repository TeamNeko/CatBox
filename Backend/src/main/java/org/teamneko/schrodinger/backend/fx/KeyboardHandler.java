package org.teamneko.schrodinger.backend.fx;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


/**
 * The Class KeyboardHandler.
 */
public class KeyboardHandler implements EventHandler<KeyEvent> {
	
	/** The listener. */
	private EventHandler<KeyEvent> listener = null;
	
	/**
	 * Removes the keyboard listener.
	 */
	public void removeKeyboardListener() {
		this.listener = null;
	}
	
	/**
	 * Sets the keyboard listener.
	 *
	 * @param listener the new keyboard listener
	 */
	public void setKeyboardListener(EventHandler<KeyEvent> listener) {
		this.listener = listener;
	}

	/* Handles key events
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		if(listener != null) {
			listener.handle(event);
			event.consume();
		}
	}
}
