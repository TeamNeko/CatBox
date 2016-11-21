package org.teamneko.schrodinger.client.test;

import com.pi4j.wiringpi.Gpio;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		Gpio.wiringPiSetup();
		
		RGBLib led = new RGBLib(21,22,25);
		
		System.out.println("Objet cr��");
		
		int j = 0;
		
		while (j<= 50){
			System.out.println("valeur"+j);
		
			led.redFlash();
			led.greenFlash();
			led.blueFlash();
			
			j=j+5;
		}
		led.RGBWrite(100, 100, 100);
		led.RGBClose();
		System.out.println("Termin�");
	}
}
