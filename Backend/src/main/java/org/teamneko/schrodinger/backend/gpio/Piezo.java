package org.teamneko.schrodinger.backend.gpio;

import com.pi4j.wiringpi.SoftTone;

public class Piezo {
	private int piezoPin;
	private int speed = 250;
	
	Piezo(int piezoPin){
		this.piezoPin = piezoPin;
		SoftTone.softToneCreate(piezoPin);
	}
	
	public void playSong(Note[] song) {
		for(Note currentNote: song)
			playNote(currentNote);
	}
	
	public void playNote(Note note) {
		playNote(note.tone.frequency, note.duration.value);
	}
	
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
	
	public void rest(){
		SoftTone.softToneStop(piezoPin);
	}
	
	public void setSpeed(int speed){
		this.speed= speed;
	}
	
}
