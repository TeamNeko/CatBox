package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.gpio.RFIDReader;

public class RFIDThread extends Thread {
	RFIDReader rfid;
	RFIDCallback callback;
	
	public RFIDThread(RFIDReader rfid, RFIDCallback callback) {
		this.callback = callback;
		this.rfid = rfid;
	}

	@Override
	public void run() {
		while(!this.isInterrupted()){
			
			new Thread(new LEDFlash(1.0, 1.0, 0.0, 0)).start();
			
			if(rfid.read()) 
				callback.onRead(rfid.getID());
		}
	}
}
