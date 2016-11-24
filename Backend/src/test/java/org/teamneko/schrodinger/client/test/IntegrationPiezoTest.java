package org.teamneko.schrodinger.client.test;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.Duration;
import org.teamneko.schrodinger.backend.gpio.Note;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.Tone;

public class IntegrationPiezoTest {
	
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
			System.out.println("Not running on a Raspberry Pi");
			return;
		}
		
		System.out.println("Objet initialisé");
		
		while (true){
			
			piezo.playSong(auClairDeLaLune);
			piezo.playSong(ouverture5eBeethoven);
			
			Thread.sleep(1000);
		}
	}
}
