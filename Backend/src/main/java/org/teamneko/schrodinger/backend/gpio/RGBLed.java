package org.teamneko.schrodinger.backend.gpio;

import com.pi4j.wiringpi.SoftPwm;

public class RGBLed {
	private final static int FLASH_SLEEP_TIME = 7;
	
	private int redPin;
	private int greenPin;
	private int bluePin;

	RGBLed(int redPin, int greenPin, int bluePin){
		this.redPin = redPin;
		this.greenPin = greenPin;
		this.bluePin = bluePin;
		
		SoftPwm.softPwmCreate(redPin, 0, 100);
		SoftPwm.softPwmCreate(greenPin, 0, 100);
		SoftPwm.softPwmCreate(bluePin, 0, 100);

	}
	
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
		
		write((redValue*100)/255, (greenValue*100)/255, (blueValue*100)/255);		
	}
	
	public void flashRed() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				write(i,0,0);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
			for(int j = 100; j>=0; j=j-5){
				write(j,0,0);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
		}	
	}
	
	public void flashGreen() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				write(0,i,0);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
			for(int j = 100; j>=0; j=j-5){
				write(0,j,0);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
		}	
	}
	
	public void flashBlue() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				write(0,0,i);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
			for(int j = 100; j>=0; j=j-5){
				write(0,0,j);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
		}	
	}
	
	public void flashWhite() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				write(i,i,i);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
			for(int j = 100; j>=0; j=j-5){
				write(j,j,j);
				Thread.sleep(FLASH_SLEEP_TIME);
			}
		}	
	}
	
	public void close() {
		SoftPwm.softPwmStop(redPin);
		SoftPwm.softPwmStop(greenPin);
		SoftPwm.softPwmStop(bluePin);
	}
}
