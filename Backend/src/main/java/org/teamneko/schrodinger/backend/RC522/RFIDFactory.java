package org.teamneko.schrodinger.backend.RC522;

import com.pi4j.wiringpi.Gpio;

public class RFIDFactory {
	private static boolean pi4jSetUp = false;
	private static boolean pi4jMissing = false;

	private static final int DEFAULT_SPI_PORT = 500000;
	private static final int DEFAULT_CLOCK_SPEED = 500000;
	private static final int DEFAULT_RESET_PIN = 22;
	
	public static MFRC522 createMFRC522() throws Pi4JMissingException, DeviceInitializationException {
		return createMFRC522(DEFAULT_SPI_PORT, DEFAULT_RESET_PIN, DEFAULT_CLOCK_SPEED);
	}
	
	public static MFRC522 createMFRC522(int spiPort) throws Pi4JMissingException, DeviceInitializationException {
		return createMFRC522(spiPort, DEFAULT_RESET_PIN, DEFAULT_CLOCK_SPEED);
	}
	
	public static MFRC522 createMFRC522(int spiPort, int resetPin) throws Pi4JMissingException, DeviceInitializationException {
		return createMFRC522(spiPort, resetPin, DEFAULT_CLOCK_SPEED);
	}
	
	public static MFRC522 createMFRC522(int spiPort, int resetPin, int clockSpeed) throws Pi4JMissingException, DeviceInitializationException {
		MFRC522 instance;
		
		//Setup Pi4J if neccessary
		if (!pi4jSetUp)
			setupPi4j();
		
		//If Pi4J is not available, throw exception
		if(pi4jMissing)
			throw new Pi4JMissingException();
		
		instance = new MFRC522(spiPort, resetPin, clockSpeed);
		instance.init();
		return instance;
	}
	
	private static void setupPi4j() {
		pi4jSetUp = true;
		
		try {
			Gpio.wiringPiSetup();           //Enabling wiringPi pin schema
		} catch(UnsatisfiedLinkError e) {
			pi4jMissing = true;
		}
		
	}
}
