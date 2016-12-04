package org.teamneko.schrodinger.backend.fx;

import org.teamneko.schrodinger.backend.gpio.RFIDReader;
import org.teamneko.schrodinger.backend.runnable.RFIDCallback;
import org.teamneko.schrodinger.backend.runnable.RFIDThread;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginPane extends CustomAnchorPane implements RFIDCallback {
	private RFIDThread thread = null;
	
	@FXML protected TextField rfidText;
	 
	public LoginPane() {
		super();
		
		Context.getInstance().getKeyboardHandler().removeKeyboardListener();
		startRFIDThread();
	}
	
	 @FXML protected void login(ActionEvent event) {
		 new Thread((Runnable) () -> attemptLogin(rfidText.getText())).start();
	 }

	@Override
	public void onRead(String id) {
		attemptLogin(id);
	}
	
	private void startRFIDThread() {
		RFIDReader rfid = Context.getInstance().getRFIDReader();
		
		if(rfid != null)
			new RFIDThread(rfid, this).start();
	}
	private void attemptLogin(String id) {
		if(!Context.getInstance().login(id))
			startRFIDThread();
	}
}
