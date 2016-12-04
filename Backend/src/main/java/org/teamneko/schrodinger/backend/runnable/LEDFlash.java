package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.fx.Context;
import org.teamneko.schrodinger.backend.gpio.RGBLed;

public class LEDFlash implements Runnable {
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
			while (true) {
				for (int k=0; k<3;k++){
					for(int i = 0; i<=100; i=i+5){
						rgb.write((int)red*i,(int)green*i,(int)blue*i);
						Thread.sleep(timeinMs);
					}
					for(int j = 100; j>=0; j=j-5){
						rgb.write((int)red*j,(int)green*j,(int)blue*j);
						Thread.sleep(timeinMs);
					}
				} 
			}
		}
		catch(InterruptedException e) {}
	}

}
