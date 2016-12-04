package org.teamneko.schrodinger.backend.gpio;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.teamneko.softspi.SoftSPI;

import com.pi4j.util.NativeLibraryLoader;
import com.pi4j.wiringpi.Gpio;

public class DeviceFactory {
	private static boolean pi4jSetUp = false;
	private static boolean pi4jMissing = false;

	private static final int DEFAULT_CS_PIN = 27;
	private static final int DEFAULT_CLK_PIN = 29;
	private static final int DEFAULT_MOSI_PIN = 28;
	private static final int DEFAULT_MISO_PIN = 24;
	private static final int DEFAULT_RESET_PIN = 25;
	
	private static final long DEFAULT_SPI_SPEED = 250000;
	
	private static final int DEFAULT_RED_PIN = 21;
	private static final int DEFAULT_GREEN_PIN = 22;
	private static final int DEFAULT_BLUE_PIN = 23;
	
	private static final int DEFAULT_PIEZO_PIN = 26;
	
	public static MFRC522 createMFRC522() throws Pi4JMissingException, DeviceInitializationException {
		return createMFRC522(DEFAULT_RESET_PIN);
	}
	
	public static MFRC522 createMFRC522(int resetPin) throws Pi4JMissingException, DeviceInitializationException {
		setupPi4j();
		
		MFRC522 instance = new MFRC522(new SoftSPI(DEFAULT_CS_PIN, DEFAULT_MISO_PIN, DEFAULT_MOSI_PIN, DEFAULT_CLK_PIN, DEFAULT_SPI_SPEED), resetPin);
		
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
				System.out.println("WiringPiSetup");
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
