package org.teamneko.schrodinger.backend.gui;

import org.teamneko.schrodinger.backend.RC522.MFRC522;

import com.sun.jersey.api.client.UniformInterfaceException;


public class UserAuthentification {
	
	MFRC522 rc522;
	main_window window;
	
	
	public static void main(String[] args) {
		main_window window = new main_window();
		MFRC522 rc522 = new MFRC522();
			/*	try {
				
				boolean doSleep = runAuthentif();
					if (doSleep){
						Thread.sleep(5000);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			*/
		};
	
public boolean runAuthentif() throws InterruptedException{ 					//type de fonction
		
		String nullID = "0000000000";
		
			while(!rc522.ReadID()){
				try {
					
					if(rc522.currentID == nullID)
					{
						window.jLabel_Xrouge.setVisible(true);	
					}
				
				} catch (UniformInterfaceException e) { //Exception
					e.printStackTrace();
				} 
			}
			window.jLabel_crochetvert.setVisible(true);
		return true;
	}

}
