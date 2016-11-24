package org.teamneko.schrodinger.backend.fx;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler implements EventHandler<KeyEvent> {
	private EventHandler<KeyEvent> listener = null;
	
	public void removeKeyboardListener() {
		this.listener = null;
	}
	
	public void setKeyboardListener(EventHandler<KeyEvent> listener) {
		this.listener = listener;
	}

	@Override
	public void handle(KeyEvent event) {
		if(listener != null) {
			listener.handle(event);
			event.consume();
		}
	}
}
