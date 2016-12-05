package org.teamneko.schrodinger.backend.gpio;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.teamneko.softspi.SoftSPI;

import com.pi4j.util.NativeLibraryLoader;
import com.pi4j.wiringpi.Gpio;


/**
 * A factory for creating Device objects.
 */
public class DeviceFactory {
	
	/** The pi 4 j set up. */
	private static boolean pi4jSetUp = false;
	
	/** The pi 4 j missing. */
	private static boolean pi4jMissing = false;

	/** The Constant DEFAULT_CS_PIN. */
	private static final int DEFAULT_CS_PIN = 27;
	
	/** The Constant DEFAULT_CLK_PIN. */
	private static final int DEFAULT_CLK_PIN = 29;
	
	/** The Constant DEFAULT_MOSI_PIN. */
	private static final int DEFAULT_MOSI_PIN = 28;
	
	/** The Constant DEFAULT_MISO_PIN. */
	private static final int DEFAULT_MISO_PIN = 24;
	
	/** The Constant DEFAULT_RESET_PIN. */
	private static final int DEFAULT_RESET_PIN = 25;
	
	/** The Constant DEFAULT_SPI_SPEED. */
	private static final long DEFAULT_SPI_SPEED = 250000;
	
	/** The Constant DEFAULT_RED_PIN. */
	private static final int DEFAULT_RED_PIN = 21;
	
	/** The Constant DEFAULT_GREEN_PIN. */
	private static final int DEFAULT_GREEN_PIN = 22;
	
	/** The Constant DEFAULT_BLUE_PIN. */
	private static final int DEFAULT_BLUE_PIN = 23;
	
	/** The Constant DEFAULT_PIEZO_PIN. */
	private static final int DEFAULT_PIEZO_PIN = 26;
	
	/**
	 * Creates a new Device object.
	 *
	 * @return the mfrc522
	 * @throws Pi4JMissingException the pi 4 J missing exception
	 * @throws DeviceInitializationException the device initialization exception
	 */
	public static MFRC522 createMFRC522() throws Pi4JMissingException, DeviceInitializationException {
		return createMFRC522(DEFAULT_RESET_PIN);
	}
	
	/**
	 * Creates a new Device object.
	 *
	 * @param resetPin the reset pin
	 * @return the mfrc522
	 * @throws Pi4JMissingException the pi 4 J missing exception
	 * @throws DeviceInitializationException the device initialization exception
	 */
	public static MFRC522 createMFRC522(int resetPin) throws Pi4JMissingException, DeviceInitializationException {
		setupPi4j();
		
		MFRC522 instance = new MFRC522(new SoftSPI(DEFAULT_CS_PIN, DEFAULT_MISO_PIN, DEFAULT_MOSI_PIN, DEFAULT_CLK_PIN, DEFAULT_SPI_SPEED), resetPin);
		
		instance.init();
		return instance;
	}
	
	/**
	 * Creates a new Device object.
	 *
	 * @return the RGB led
	 * @throws Pi4JMissingException the pi 4 J missing exception
	 */
	public static RGBLed createRGBLed() throws Pi4JMissingException {
		return createRGBLed(DEFAULT_RED_PIN, DEFAULT_GREEN_PIN, DEFAULT_BLUE_PIN);
	}
	
	/**
	 * Creates a new Device object.
	 *
	 * @param redPin the red pin
	 * @param greenPin the green pin
	 * @param bluePin the blue pin
	 * @return the RGB led
	 * @throws Pi4JMissingException the pi 4 J missing exception
	 */
	public static RGBLed createRGBLed(int redPin, int greenPin, int bluePin) throws Pi4JMissingException {
		setupPi4j();
		return new RGBLed(redPin, greenPin, bluePin);
	}
	
	/**
	 * Creates a new Device object.
	 *
	 * @return the piezo
	 * @throws Pi4JMissingException the pi 4 J missing exception
	 */
	public static Piezo createPiezo() throws Pi4JMissingException {
		return createPiezo(DEFAULT_PIEZO_PIN);
	}
	
	/**
	 * Creates a new Device object.
	 *
	 * @param pin the pin
	 * @return the piezo
	 * @throws Pi4JMissingException the pi 4 J missing exception
	 */
	public static Piezo createPiezo(int pin) throws Pi4JMissingException {
		setupPi4j();
		return new Piezo(pin);
	}
	
	/**
	 * Setup pi4j.
	 *
	 * @throws Pi4JMissingException the pi4J missing exception
	 */
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
