package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.Duration;
import org.teamneko.schrodinger.backend.gpio.Note;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.Tone;

public class PiezoNotification implements Runnable {

	public PiezoNotification(PiezoMode loginsuccess){
		super();
		try {
		this.mode = loginsuccess.getMode();
		this.soundNotification = DeviceFactory.createPiezo();
		}
		catch(Pi4JMissingException e) {
			System.err.println("Could not initialize Pi4j, probably not running on a Pi");
		}
	}

	Piezo soundNotification;
	private int mode;
	
	@Override
	public void run() {
		try {
			switch(mode) {
				case 1:
					soundNotification.playSong(getLoginSucessSong());
					break;
				case 2:
					soundNotification.playSong(getLoginFailureSong());
					break;
				case 3:
					soundNotification.playSong(getProductFoundSong());
					break;
				case 4:
					soundNotification.playSong(getNewProductSong());
					break;
				default:
					break;
			}
		} catch (Pi4JMissingException e) {
			System.err.println("Could not initialize Pi4j, probably not running on a Pi");
		} catch (NullPointerException e) {
			System.err.println("No piezo initialized");
		}
	}

	
	private Note[] getLoginSucessSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.C6,Duration.Eight),
					   new Note(Tone.G5,Duration.Eight),
					   new Note(Tone.A5,Duration.Eight), 
					   new Note(Tone.E5,Duration.Half)};
		return song;
	}
	
	private Note[] getLoginFailureSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.Eb6,Duration.Eight),
					   new Note(Tone.D6,Duration.Eight),
					   new Note(Tone.Db6,Duration.Eight), 
					   new Note(Tone.C6,Duration.Half)};
		return song;
	}
	
	private Note[] getProductFoundSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.C5,Duration.Eight),
					   new Note(Tone.G6,Duration.Eight),
					   new Note(Tone.E6,Duration.Eight), 
					   new Note(Tone.C6,Duration.Half)};
		return song;
	}
	
	private Note[] getNewProductSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.C5,Duration.Eight),
					   new Note(Tone.F6,Duration.Eight),
					   new Note(Tone.G6,Duration.Eight), 
					   new Note(Tone.E6,Duration.Half)};
		return song;
	}
	
	public enum PiezoMode {
		
		LoginSuccess(1),
		LoginFail(2),
		ItemFound(3),
		NewItem(4);
		
		private int mode;
		
		private PiezoMode(int selectedMode) {
			this.mode = selectedMode;
		}
		
		public int getMode() {
			return mode;
		}
	}

}
