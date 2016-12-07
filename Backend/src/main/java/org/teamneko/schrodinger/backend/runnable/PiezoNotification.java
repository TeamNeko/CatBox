package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.gpio.DeviceFactory;
import org.teamneko.schrodinger.backend.gpio.Duration;
import org.teamneko.schrodinger.backend.gpio.Note;
import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.Piezo;
import org.teamneko.schrodinger.backend.gpio.Tone;


/**
 * The Class PiezoNotification.
 */
public class PiezoNotification implements Runnable {

	/**
	 * Instantiates a new piezo notification.
	 *
	 * @param loginsuccess the loginsuccess
	 */
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

	/** The sound notification. */
	Piezo soundNotification;
	
	/** The mode. */
	private int mode;
	
	/* Called when Thread.start. Play song in the piezo
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			switch(mode) {
				case 1:
					soundNotification.playSong(getLoginSuccessSong());
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

	
	/**
	 * Gets the login success song.
	 *
	 * @return the login success song
	 * @throws Pi4JMissingException the pi4J missing exception
	 */
	private Note[] getLoginSuccessSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.C6,Duration.Eight),
					   new Note(Tone.G5,Duration.Eight),
					   new Note(Tone.A5,Duration.Eight), 
					   new Note(Tone.E5,Duration.Half)};
		return song;
	}
	
	/**
	 * Gets the login failure song.
	 *
	 * @return the login failure song
	 * @throws Pi4JMissingException the pi4J missing exception
	 */
	private Note[] getLoginFailureSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.Eb6,Duration.Eight),
					   new Note(Tone.D6,Duration.Eight),
					   new Note(Tone.Db6,Duration.Eight), 
					   new Note(Tone.C6,Duration.Half)};
		return song;
	}
	
	/**
	 * Gets the product found song.
	 *
	 * @return the product found song
	 * @throws Pi4JMissingException the pi4J missing exception
	 */
	private Note[] getProductFoundSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.C5,Duration.Eight),
					   new Note(Tone.G6,Duration.Eight),
					   new Note(Tone.E6,Duration.Eight), 
					   new Note(Tone.C6,Duration.Half)};
		return song;
	}
	
	/**
	 * Gets the new product song.
	 *
	 * @return the new product song
	 * @throws Pi4JMissingException the pi4J missing exception
	 */
	private Note[] getNewProductSong() throws Pi4JMissingException {
		Note[] song = {new Note(Tone.C5,Duration.Eight),
					   new Note(Tone.F6,Duration.Eight),
					   new Note(Tone.G6,Duration.Eight), 
					   new Note(Tone.E6,Duration.Half)};
		return song;
	}
	
	/**
	 * The Enum PiezoMode.
	 */
	public enum PiezoMode {
		
		/** The Login success. */
		LoginSuccess(1),
		
		/** The Login fail. */
		LoginFail(2),
		
		/** The Item found. */
		ItemFound(3),
		
		/** The New item. */
		NewItem(4);
		
		/** The mode. */
		private int mode;
		
		/**
		 * Instantiates a new piezo mode.
		 *
		 * @param selectedMode the selected mode
		 */
		private PiezoMode(int selectedMode) {
			this.mode = selectedMode;
		}
		
		/**
		 * Gets the mode.
		 *
		 * @return the mode
		 */
		public int getMode() {
			return mode;
		}
	}

}
