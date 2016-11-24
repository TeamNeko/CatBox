package org.teamneko.schrodinger.gpio.test;

import static org.junit.Assert.*;
import org.teamneko.schrodinger.backend.gpio.Note;
import org.teamneko.schrodinger.backend.gpio.Duration;
import org.teamneko.schrodinger.backend.gpio.Tone;
import org.junit.Test;

public class NoteTest {

	@Test
	public void initNoteTest() {
		Note testNote = new Note(Tone.A0, Duration.Eight);
		assertEquals(Tone.A0,testNote.tone);
		assertEquals(Duration.Eight,testNote.duration);
	}

}
