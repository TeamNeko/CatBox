package org.teamneko.schrodinger.backend.gpio;

public class Note {
	public final Tone tone;
	public final Duration duration;
	
	public Note(Tone tone, Duration duration) {
		this.tone = tone;
		this.duration = duration;
	}
}
