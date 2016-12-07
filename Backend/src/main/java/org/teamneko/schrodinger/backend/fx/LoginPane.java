package org.teamneko.schrodinger.backend.fx;

import org.teamneko.schrodinger.backend.gpio.RFIDReader;
import org.teamneko.schrodinger.backend.runnable.RFIDCallback;
import org.teamneko.schrodinger.backend.runnable.RFIDThread;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * The Class LoginPane.
 */
public class LoginPane extends CustomAnchorPane implements RFIDCallback {	
	/** The rfid text. */
	@FXML protected TextField rfidText;
	 
	/**
	 * Instantiates a new login pane.
	 */
	public LoginPane() {
		super();
		
		Context.getInstance().getKeyboardHandler().removeKeyboardListener();
		startRFIDThread();
	}
	
	 /**
 	 * Login.
 	 *
 	 * @param event the event
 	 */
 	@FXML protected void login(ActionEvent event) {
		 new Thread((Runnable) () -> attemptLogin(rfidText.getText())).start();
	 }

	/* call attemptLogin() when an ID is read
	 * @see org.teamneko.schrodinger.backend.runnable.RFIDCallback#onRead(java.lang.String)
	 */
	@Override
	public void onRead(String id) {
		attemptLogin(id);
	}
	
	/**
	 * Start RFID thread.
	 */
	private void startRFIDThread() {
		RFIDReader rfid = Context.getInstance().getRFIDReader();
		
		if(rfid != null)
			new RFIDThread(rfid, this).start();
	}
	
	/**
	 * Attempt login.
	 *
	 * @param id the id
	 */ 
	private void attemptLogin(String id) {
		if(!Context.getInstance().login(id))
			startRFIDThread();
	}
}
