package org.teamneko.schrodinger.backend.gpio;

public interface RFIDReader {
	public boolean read();
	public String getID();
}
