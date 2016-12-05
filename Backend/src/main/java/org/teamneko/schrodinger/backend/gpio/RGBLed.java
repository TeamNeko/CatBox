package org.teamneko.schrodinger.backend.gpio;

import com.pi4j.wiringpi.SoftPwm;


/**
 * The Class RGBLed.
 */
public class RGBLed {
	
	/** The red pin. */
	private int redPin;
	
	/** The green pin. */
	private int greenPin;
	
	/** The blue pin. */
	private int bluePin;

	/**
	 * Instantiates a new RGB led.
	 *
	 * @param redPin the red pin
	 * @param greenPin the green pin
	 * @param bluePin the blue pin
	 */
	RGBLed(int redPin, int greenPin, int bluePin){
		this.redPin = redPin;
		this.greenPin = greenPin;
		this.bluePin = bluePin;
		
		SoftPwm.softPwmCreate(redPin, 0, 100);
		SoftPwm.softPwmCreate(greenPin, 0, 100);
		SoftPwm.softPwmCreate(bluePin, 0, 100);

	}
	
	/**
	 * Set led color.
	 *
	 * @param redValue the red value
	 * @param greenValue the green value
	 * @param blueValue the blue value
	 */
	public void write(int redValue, int greenValue, int blueValue){
		if(redValue > 100){
			redValue = 100;
		}
		if(greenValue > 100){
			greenValue = 100;
		}
		if(blueValue > 100){
			blueValue = 100;
		}
		
		SoftPwm.softPwmWrite(redPin, redValue);
		SoftPwm.softPwmWrite(greenPin, greenValue);
		SoftPwm.softPwmWrite(bluePin, blueValue);
	}
	
	/**
	 * Set led color with hex values.
	 *
	 * @param redValue the red value
	 * @param greenValue the green value
	 * @param blueValue the blue value
	 */
	public void writeHex(int redValue, int greenValue, int blueValue){
		
		// To ensure compatibility between normal 0xFF values per RGB color
		// It rescales the 256 possibility on the 100 range of the SoftPWM
		
		if(redValue > 255){
			redValue = 255;
		}
		if(greenValue > 255){
			greenValue = 255;
		}
		if(blueValue > 255){
			blueValue = 255;
		}
		
		write((redValue  *100)/255, 
			  (greenValue*100)/255, 
			  (blueValue *100)/255);		
	}
	
	/**
	 * Close.
	 */
	public void close() {
		SoftPwm.softPwmStop(redPin);
		SoftPwm.softPwmStop(greenPin);
		SoftPwm.softPwmStop(bluePin);
	}
}
