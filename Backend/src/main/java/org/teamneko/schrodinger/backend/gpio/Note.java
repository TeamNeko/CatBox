package org.teamneko.schrodinger.backend.gpio;


/**
 * The Class Note.
 */
public class Note {
	
	/** The tone. */
	public final Tone tone;
	
	/** The duration. */
	public final Duration duration;
	
	/**
	 * Instantiates a new note.
	 *
	 * @param tone the tone
	 * @param duration the duration
	 */
	public Note(Tone tone, Duration duration) {
		this.tone = tone;
		this.duration = duration;
	}
}
