package org.teamneko.schrodinger.backend.gui;

import backend.catbox.teamneko.RC522.MFRC522;

import com.sun.jersey.api.client.UniformInterfaceException;






public class UserAuthentification {
	
	MFRC522 rc522;
	private String crochet = "" ;
	main_window window;
	
	
	public void main(String[] args) {
		window = new main_window();
		rc522 = new MFRC522();
				try {
					
				boolean doSleep = runAuthentif();
					if (doSleep){
						Thread.sleep(5000);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			
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
