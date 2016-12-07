package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.DeviceInitializationException;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;

import org.teamneko.schrodinger.backend.gpio.MFRC522;

public class IntegrationMFRC522Test {
	 public static void main(String args[]) throws InterruptedException, DeviceInitializationException, Pi4JMissingException {
		
		MFRC522 rfid = DeviceFactory.createMFRC522();
		System.out.println("Objet initialisé");
		
		while (true){
			
			if (rfid.read()){
				System.out.println("Detecte card: " + rfid.getID());
			}
			
			else{
				System.out.println("Missed 4 times ");
			}
			
			Thread.sleep(1000);

		}
	}
}
