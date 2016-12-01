package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.fx.Context;
import org.teamneko.schrodinger.backend.gpio.RGBLed;

public class LEDFlash implements Runnable {
	private final static int FLASH_SLEEP_TIME = 7;
	
    public final double red;
    public final double green;
    public final double blue;
    public final int timeinMs;
    
	public LEDFlash(double red, double green, double blue, int timeinMs) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.timeinMs = timeinMs;
	}

	@Override
	public void run() {
		RGBLed rgb = Context.getInstance().getRGBLed();
		try {
			for (int k=0; k<3;k++){
				for(int i = 0; i<=100; i=i+5){
					rgb.write((int)red*i,(int)green*i,(int)blue*i);
					Thread.sleep(FLASH_SLEEP_TIME);
				}
				for(int j = 100; j>=0; j=j-5){
					rgb.write((int)red*j,(int)green*j,(int)blue*j);
					Thread.sleep(FLASH_SLEEP_TIME);
				}
			}
		}
		catch(InterruptedException e) {}
	}

}
