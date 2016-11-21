package piezoLib;

import com.pi4j.wiringpi.Gpio;


public class PiezoTest {


	public static void main(String[] args) throws InterruptedException {
		
		Gpio.wiringPiSetup();
		System.out.println("GPIO initialisť");
		
		piezoLib piezo = new piezoLib(23);
		
		System.out.println("Objet initialisť");
		while (true){
			System.out.println("In the while");
			piezo.Beth5e();
			
			Thread.sleep(1000);
		}
		


	}

}
