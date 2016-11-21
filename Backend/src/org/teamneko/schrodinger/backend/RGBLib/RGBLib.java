package RGBLib;

import com.pi4j.wiringpi.SoftPwm;

public class RGBLib {
	
	// You need to initialize the wiringPi library in the code before constructing the object
    // com.pi4j.wiringpi.Gpio.wiringPiSetup();
	
	int R = 21;
	int G = 22;
	int B = 25;
	int flashSleepTime = 7;
	

	RGBLib(int R, int G, int B){
		
		// With wiringPi, we don't use the real pin number, 
		// but the GPIO number to allow portability of the code 
		// to different raspberry pi model (A,B,2,3,Zero)
		// SoftPwm.softPwmCreate(PIN, RANGEMIN, RANGEMAX(100));
		SoftPwm.softPwmCreate(R, 0, 100); // Pin 29 GPIO 21
		SoftPwm.softPwmCreate(G, 0, 100); // Pin 31 GPIO 22
		SoftPwm.softPwmCreate(B, 0, 100); // Pin 37 GPIO 25

		}
	
	void RGBWrite(int valueR, int valueG, int valueB){
		if(valueR > 100){
			valueR = 100;
		}
		if(valueG > 100){
			valueG = 100;
		}
		if(valueB > 100){
			valueB = 100;
		}
			
		
		SoftPwm.softPwmWrite(R, valueR);
		SoftPwm.softPwmWrite(G, valueG);
		SoftPwm.softPwmWrite(B, valueB);
		
	}
	
	void RGBWriteHex(int valueR, int valueG, int valueB){
		
		// To ensure compatibility between normal 0xFF values per RGB color
		// It rescales the 256 possibility on the 100 range of the SoftPWM
		
		if(valueR > 255){
			valueR = 255;
		}
		if(valueG > 255){
			valueG = 255;
		}
		if(valueB > 255){
			valueB = 255;
		}
		
		valueR = (int) (valueR/2.55);
		valueG = (int) (valueG/2.55);
		valueB = (int) (valueB/2.55);
		
		SoftPwm.softPwmWrite(R, valueR);
		SoftPwm.softPwmWrite(G, valueG);
		SoftPwm.softPwmWrite(B, valueB);
		
	}
	
	void redFlash() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				RGBWrite(i,0,0);
				Thread.sleep(flashSleepTime);
			}
			for(int j = 100; j>=0; j=j-5){
				RGBWrite(j,0,0);
				Thread.sleep(flashSleepTime);
			}
		}	
	}
	
	void greenFlash() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				RGBWrite(0,i,0);
				Thread.sleep(flashSleepTime);
			}
			for(int j = 100; j>=0; j=j-5){
				RGBWrite(0,j,0);
				Thread.sleep(flashSleepTime);
			}
		}	
	}
	
	void blueFlash() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				RGBWrite(0,0,i);
				Thread.sleep(flashSleepTime);
			}
			for(int j = 100; j>=0; j=j-5){
				RGBWrite(0,0,j);
				Thread.sleep(flashSleepTime);
			}
		}	
	}
	
	void RGBFlash() throws InterruptedException{	
		for (int k=0; k<3;k++){
			for(int i = 0; i<=100; i=i+5){
				RGBWrite(i,i,i);
				Thread.sleep(flashSleepTime);
			}
			for(int j = 100; j>=0; j=j-5){
				RGBWrite(j,j,j);
				Thread.sleep(flashSleepTime);
			}
		}	
	}
	
	
	
	
	
	void RGBClose(){
		// By closing the PWM, it uses less resources than just 
		// setting all the values to 0, in which case, every 
		// process continues, but nothing outputs
		SoftPwm.softPwmStop(R);
		SoftPwm.softPwmStop(G);
		SoftPwm.softPwmStop(B);
	}
}








