package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.gpio.RFIDReader;


/**
 * The Class RFIDThread.
 */
public class RFIDThread extends Thread {
	
	/** The rfid. */
	RFIDReader rfid;
	
	/** The callback. */
	RFIDCallback callback;
	
	/** The led Thread. */
	Thread ledT;
	
	/** The sound Thread. */
	Thread soundT;
	
	/**
	 * Instantiates a new RFID thread.
	 *
	 * @param rfid the rfid
	 * @param callback the callback
	 */
	public RFIDThread(RFIDReader rfid, RFIDCallback callback) { 
		this.callback = callback;
		this.rfid = rfid;
	}

	/* Called when Thread.start. Start rfid reading thread and led waiting connection thread
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println(getClass().getSimpleName() + " start!");
		
		ledT = new Thread(new LEDFlash(1, 1, 0, 100));
		ledT.start();  
		
		while(!isInterrupted()){ 
			if(rfid.read()) {
				new Thread((Runnable)() -> callback.onRead(rfid.getID())).start();
				ledT.interrupt();
				return;
			}
		}
	}
}
 