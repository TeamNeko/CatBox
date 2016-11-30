package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.DeviceInitializationException;
import org.teamneko.schrodinger.backend.gpio.Duration;
import org.teamneko.schrodinger.backend.gpio.MFRC522;
import org.teamneko.schrodinger.backend.gpio.Note;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.RGBLed;
import org.teamneko.schrodinger.backend.gpio.Tone;

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
		
		MFRC522 rfid;
		try {
			rfid = DeviceFactory.createMFRC522();
		} 
		catch (Pi4JMissingException e) {
			System.out.println("RFID Error : Not running on a Raspberry Pi");
			return;
		} catch (DeviceInitializationException e) {
			System.out.println("RFID Error : Initialization Error");
			return;
		}
		
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
		
		while (true){
			
			if (rfid.read()){ // If the sensor has read a correct UID, GreenFlash + auClairDeLaLune
				System.out.println("Detecte card: " + rfid.getID() + " Led flash : Vert, Au Clair de la lune" );
				led.flashGreen();
				piezo.playSong(auClairDeLaLune);
				Thread.sleep(1000);
			}
			
			else{ // If the sensor has not read a correct UID, RedFlash + ouverture5eBeethoven
				System.out.println("Missed 4 times " + "Led flash : Rouge, Ouverture 5e Beethoven");
				led.flashRed();
				piezo.playSong(ouverture5eBeethoven);
			}
			
			Thread.sleep(1000);	
		}
	}
}
