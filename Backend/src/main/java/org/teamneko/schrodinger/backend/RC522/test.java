package org.teamneko.schrodinger.backend.RC522;

public class test {

	public static void main(String args[]) throws InterruptedException, DeviceInitializationException
    {
	    MFRC522 rfid;
		try {
			rfid = RFIDFactory.createMFRC522();
		} catch (Pi4JMissingException e) {
			System.out.println("Error 314: Could not open Pi4j");
			System.out.println("This probably means you are not running on a Pi");
			System.out.println("If you are running on a Pi, this probably means Pi4J is improperly configured");
			System.exit(1);
			return;
		}
	        
	    while (true){
	    	if (rfid.readID());
	    	System.out.println("Detecte card: "+ rfid.currentID);
	    }
	
    };

}
