package org.teamneko.schrodinger.backend.runnable;

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
		boolean stop = false;
		System.out.println(getClass().getSimpleName() + " start!");
		
		ledT = new Thread(new LEDFlash(1, 1, 0, 100));
		ledT.start();
		
		while(!stop && !isInterrupted()){ 
			if(rfid.read()) {
				stop = true;
				new Thread((Runnable)() -> callback.onRead(rfid.getID()));
			}
		}
		
		ledT.interrupt();
		System.out.println(getClass().getSimpleName() + " done!");
	}
}
 