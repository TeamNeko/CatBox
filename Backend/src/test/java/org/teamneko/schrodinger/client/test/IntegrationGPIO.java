package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.Duration;
import org.teamneko.schrodinger.backend.gpio.MFRC522;
import org.teamneko.schrodinger.backend.gpio.Note;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.RGBLed;
import org.teamneko.schrodinger.backend.gpio.Tone;

@SuppressWarnings("unused")
public class IntegrationGPIO {

	private final static Note auClairDeLaLune[] = {
			new Note(Tone.C4, Duration.Quarter),
			new Note(Tone.C4, Duration.Quarter),
			new Note(Tone.C4, Duration.Quarter),
			new Note(Tone.D4, Duration.Quarter),
			new Note(Tone.E4, Duration.Half),
			new Note(Tone.D4, Duration.Half),
			new Note(Tone.C4, Duration.Quarter),
			new Note(Tone.E4, Duration.Quarter),
			new Note(Tone.D4, Duration.Quarter),
			new Note(Tone.D4, Duration.Quarter),
			new Note(Tone.C4, Duration.Half)
		};
	
	private final static Note ouverture5eBeethoven[] = {
			new Note(Tone.G3, Duration.Eight),
			new Note(Tone.G3, Duration.Eight),
			new Note(Tone.G3, Duration.Eight),		
			new Note(Tone.Eb3, Duration.Half),
			new Note(Tone.Rest, Duration.Eight),
			new Note(Tone.F3, Duration.Eight),
			new Note(Tone.F3, Duration.Eight),
			new Note(Tone.F3, Duration.Eight),
			new Note(Tone.D3, Duration.Half)
		};
	
	public static void main(String[] args) throws InterruptedException {
		
		Piezo piezo;
		try {
			piezo = DeviceFactory.createPiezo();
		} 
		catch (Pi4JMissingException e) {
			System.out.println("Piezo Error : Not running on a Raspberry Pi");
			return;
		}
		
		RGBLed led;
		try {
			led = DeviceFactory.createRGBLed();
		} 
		catch (Pi4JMissingException e) {
			System.out.println("RGB Error : Not running on a Raspberry Pi");
			return;
		}
		
		/*
		MFRC522 rfid;
		try {
			rfid = DeviceFactory.DeviceFactory.createMFRC522();
		} 
		catch (Pi4JMissingException e) {
			System.out.println("RFID Error : Not running on a Raspberry Pi");
			return;
		}
		 */
		int i = 0;
		while (true){
			
			if (i > 2){ //use rfid.read() here
				System.out.println("Detecte card: "   /* + rfid.getID()*/ );
				led.flashGreen();
				piezo.playSong(auClairDeLaLune);
				Thread.sleep(1000);
				i=0;
			}
			
			else{
				System.out.println("Missed 4 times ");
				led.flashRed();
				piezo.playSong(ouverture5eBeethoven);
				i++;
			}
			
			Thread.sleep(1000);
			
			
			
			
		}
		
		
		
		
		
	}

}
