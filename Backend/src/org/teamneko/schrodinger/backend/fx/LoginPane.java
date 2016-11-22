/*package org.teamneko.schrodinger.backend.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginPane extends CustomAnchorPane {
	@FXML protected TextField rfidText;
	 
	public LoginPane() {
		super();
	}
	
	 @FXML protected void login(ActionEvent event) {
		 if(Context.getInstance().login(rfidText.getText())){
			 
		 }
		 else{
			 
		 }
		 
	 }
}
*/

package org.teamneko.schrodinger.backend.fx;

import org.teamneko.schrodinger.backend.RC522.MFRC522;
import org.teamneko.schrodinger.backend.RC522.RFIDFactory;

import com.sun.jersey.api.client.UniformInterfaceException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginPane extends CustomAnchorPane {
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
			MFRC522 rc522 = RFIDFactory.createMFRC522();
			
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
		
		
		while(!rc522.readID()){
			try {
				
				if(rc522.currentID == nullID)
				{
					window.jLabel_Xrouge.setVisible(true);	//changer path to Xrouge
				}
			
			} catch (UniformInterfaceException e) { //Exception
				e.printStackTrace();
			} 
		}
		window.jLabel_crochetvert.setVisible(true); //changer Path to crochetvert
	return true;

	};






}

