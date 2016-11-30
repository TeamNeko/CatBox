package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.RGBLed;

public class IntegrationRGBLEDTest {

	public static void main(String[] args) throws InterruptedException {
		
		RGBLed led;
		
		try {
			led = DeviceFactory.createRGBLed();
		} 
		catch (Pi4JMissingException e) {
			System.out.println("Not running on a Raspberry Pi");
			return;
		}
		
		System.out.println("Objet créé");
		
		int j;
		while (true){
			
			j = 1;	
			while (j <= 5){
				
				System.out.println("Iteration " + j + " de 5");
				led.flashRed();
				led.flashGreen();
				led.flashBlue();
				
				j++;
			}
			
			led.flashWhite();
		}
	}
}
