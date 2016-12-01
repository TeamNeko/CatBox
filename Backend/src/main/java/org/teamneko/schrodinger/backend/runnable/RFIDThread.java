package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.gpio.Pi4JMissingException;
import org.teamneko.schrodinger.backend.gpio.RFIDReader;

public class RFIDThread extends Thread {
	RFIDReader rfid;
	RFIDCallback callback;
	Thread ledT;
	Thread soundT;
	
	public RFIDThread(RFIDReader rfid, RFIDCallback callback) {
		this.callback = callback;
		this.rfid = rfid;
	}

	@Override
	public void run() {
		while(!this.isInterrupted()){
			ledT = new Thread(new LEDFlash(1.0, 1.0, 0.0, 0));
			ledT.start();
			if(rfid.read()) {
				ledT.interrupt();
				callback.onRead(rfid.getID());
				ledT = new Thread(new LEDFlash(0.0, 1.0, 0.0, 0));
				soundT = new Thread(new PiezoNotification(PiezoNotification.PiezoMode.LoginSuccess));
				soundT.start();
			}
			else {
				ledT = new Thread(new LEDFlash(1.0, 0.0, 0.0, 0));
				soundT = new Thread(new PiezoNotification(PiezoNotification.PiezoMode.LoginFail));
				soundT.start();
			}
		}
	}
}
