package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.RGBLed;

public class IntegrationRGBLEDTest {

	public static void main(String[] args) throws InterruptedException {
		
		RGBLed led;
		
		try {
			led = DeviceFactory.createRGBLed();
		} catch (Pi4JMissingException e) {
			System.out.println("Not running on a Raspberry Pi");
			return;
		}
		
		System.out.println("Objet créé");
		
		int j = 0;
		
		while (j<= 50){
			System.out.println("valeur"+j);
		
			led.flashRed();
			led.flashGreen();
			led.flashBlue();
			
			j=j+5;
		}
		
		led.flashWhite();
		
		led.write(100, 100, 100);
		
		led.close();
		
		System.out.println("Terminé");
	}
}
