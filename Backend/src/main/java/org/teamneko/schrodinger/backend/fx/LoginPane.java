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
	/*
	@FXML protected TextField rfidText;
	 
	public LoginPane() {
		super();
	}
	
	 @FXML protected void login(ActionEvent event) {
		// Context.getInstance().login(rfidText.getText());
		 if(Context.getInstance().login(rfidText.getText())){
			 
		 }
		 else{
			 
		 }
		 
	 }

	//main du Swing	
	MFRC522 rc522;
	//private main_window window;
	public static void main(String[] args) {
	//	main_window window = new main_window(); À FAIRE POUR FX (Alex l'a fait?)
	//	UserAuthentification userAuth = new UserAuthentification(window);
		MFRC522 rc522 = DeviceFactory.createMFRC522();
		
		try {
		
		boolean doSleep = userAuth.runAuthentification();  // À FAIRE POUR FX
			if (doSleep){
				Thread.sleep(5000);
			}
		}
		catch (Exception e) { 
			e.printStackTrace();
			}
	}
	
//Authentification (Login RFID)

	public void authentification(){
	  initialize();
  
	}

	private void initialize() {
		// TODO Auto-generated method stub
		//Mettre Labels, buttons, etc ici!!! Ou call interface quelque part d'autre?
	};
	
	public boolean runAuthentification() throws InterruptedException{
		String nullID = "0000000000";
		while(!rc522.read()){
			try {
				
				if(rc522.currentID == nullID)
				{
					//window.jLabel_Xrouge.setVisible(true);	//changer path to Xrouge
				}
			
			} catch (UniformInterfaceException e) { //Exception
				e.printStackTrace();
			} 
		}
		//window.jLabel_crochetvert.setVisible(true); //changer Path to crochetvert
	return true;
	};
	*/
}
