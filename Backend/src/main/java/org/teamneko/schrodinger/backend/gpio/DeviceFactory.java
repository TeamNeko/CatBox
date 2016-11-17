package org.teamneko.schrodinger.backend.gpio;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.pi4j.util.NativeLibraryLoader;
import com.pi4j.wiringpi.Gpio;

public class DeviceFactory {
	private static boolean pi4jSetUp = false;
	private static boolean pi4jMissing = false;

	private static final int DEFAULT_SPI_PORT = 500000;
	private static final int DEFAULT_CLOCK_SPEED = 500000;
	private static final int DEFAULT_RESET_PIN = 22;
	
	private static final int DEFAULT_RED_PIN = 21;
	private static final int DEFAULT_GREEN_PIN = 22;
	private static final int DEFAULT_BLUE_PIN = 25;
	
	private static final int DEFAULT_PIEZO_PIN = 23;
	
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
		setupPi4j();
		
		MFRC522 instance = new MFRC522(spiPort, resetPin, clockSpeed);
		instance.init();
		return instance;
	}
	
	public static RGBLed createRGBLed() throws Pi4JMissingException {
		return createRGBLed(DEFAULT_RED_PIN, DEFAULT_GREEN_PIN, DEFAULT_BLUE_PIN);
	}
	
	public static RGBLed createRGBLed(int redPin, int greenPin, int bluePin) throws Pi4JMissingException {
		setupPi4j();
		return new RGBLed(redPin, greenPin, bluePin);
	}
	
	public static Piezo createPiezo() throws Pi4JMissingException {
		return createPiezo(DEFAULT_PIEZO_PIN);
	}
	
	public static Piezo createPiezo(int pin) throws Pi4JMissingException {
		setupPi4j();
		return new Piezo(pin);
	}
	
	public static void setupPi4j() throws Pi4JMissingException {
		Logger.getLogger(NativeLibraryLoader.class.getName()).setFilter(new Filter() {
			@Override
			public boolean isLoggable(LogRecord record) {
				return false;
			}
		});
		
		if(!pi4jSetUp && !pi4jMissing) {
			try {
				Gpio.wiringPiSetup();           //Enabling wiringPi pin schema
			} catch(UnsatisfiedLinkError e) {
				pi4jMissing = true;
			}
		}
		
		//Call to wiringPiSetup has been done once
		pi4jSetUp = true;
		
		//If Pi4J is not available, throw exception
		if(pi4jMissing)
			throw new Pi4JMissingException();
	}
}