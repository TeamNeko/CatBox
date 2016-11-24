package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.DeviceInitializationException;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.MFRC522;

public class IntegrationMFRC522Test {
	 public static void main(String args[]) throws InterruptedException, DeviceInitializationException {
		  
		MFRC522 rfid;
		  
		try {
			rfid = DeviceFactory.createMFRC522();
			rfid.InitialisationSPI();
				
		} 
		catch (Pi4JMissingException e) {
			System.out.println("Not running on a Raspberry Pi");
			return;
		}
			
		System.out.println("Objet initialisé");
		
		while (true){
			
			if (rfid.read()){
				System.out.println("Detecte card: " + rfid.currentID);
			}
			
			else{
				System.out.println("Card not detected ");
			}
			
			Thread.sleep(1000);

		}
	}
}
