package org.teamneko.schrodinger.backend.gpio;

import com.pi4j.wiringpi.SoftTone;

// TODO: Auto-generated Javadoc
/**
 * The Class Piezo.
 */
public class Piezo {
	
	/** The piezo pin. */
	private int piezoPin;
	
	/** The speed. */
	private int speed = 250;
	
	/**
	 * Instantiates a new piezo.
	 *
	 * @param piezoPin the piezo pin
	 */
	Piezo(int piezoPin){
		this.piezoPin = piezoPin;
		SoftTone.softToneCreate(piezoPin);
	}
	
	/**
	 * Play song.
	 *
	 * @param song the song
	 */
	public void playSong(Note[] song) {
		for(Note currentNote: song)
			playNote(currentNote);
	}
	
	/**
	 * Play note.
	 *
	 * @param note the note
	 */
	public void playNote(Note note) {
		playNote(note.tone.frequency, note.duration.value);
	}
	
	/**
	 * Play note.
	 *
	 * @param note the note
	 * @param duration the duration
	 */
	public void playNote(int note, int duration) {
		//Sustain
		try {
			SoftTone.softToneWrite(piezoPin, note);
			Thread.sleep(duration*speed);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//Release
		try {
			SoftTone.softToneWrite(piezoPin, 0);
			Thread.sleep(10);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Rest.
	 */
	public void rest(){
		SoftTone.softToneStop(piezoPin);
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed){
		this.speed= speed;
	}
	
}
