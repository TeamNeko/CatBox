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
		
		RFIDReader rfid = Context.getInstance().getRFIDReader();
		
		if(rfid != null) {
			thread = new RFIDThread(rfid, this);
			thread.start();
		}
	}
	
	 @FXML protected void login(ActionEvent event) {
		 new Thread(new Runnable() {
			@Override
			public void run() {
				attemptLogin(rfidText.getText());
			}
		 }).start();
	 }

	@Override
	public void onRead(String id) {
		attemptLogin(id);
	}
	
	private void attemptLogin(String id) {
		if(Context.getInstance().login(id) && thread != null)
			thread.interrupt();
	}
}
