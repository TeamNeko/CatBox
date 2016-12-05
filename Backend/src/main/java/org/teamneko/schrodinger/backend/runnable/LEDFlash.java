package org.teamneko.schrodinger.backend.runnable;

import org.teamneko.schrodinger.backend.fx.Context;
import org.teamneko.schrodinger.backend.gpio.RGBLed;


/**
 * The Class LEDFlash.
 */
public class LEDFlash implements Runnable {
    
    /** The red value. */
    public final double red;
    
    /** The green value. */
    public final double green;
    
    /** The blue value. */
    public final double blue;
    
    /** The time in ms. */
    public final int timeInMs;
    
	/**
	 * Instantiates a new LED flash.
	 *
	 * @param red the red
	 * @param green the green
	 * @param blue the blue
	 * @param timeinMs the timein ms
	 */
	public LEDFlash(double red, double green, double blue, int timeinMs) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.timeInMs = timeinMs;
	}

	/* Execute when Thread.start. Make LED flash
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		RGBLed rgb = Context.getInstance().getRGBLed();
		try {
			while (true) {
				for (int k=0; k<3;k++){
					for(int i = 0; i<=100; i=i+5){
						rgb.write((int)red*i,(int)green*i,(int)blue*i);
						Thread.sleep(timeInMs);
					}
					for(int j = 100; j>=0; j=j-5){
						rgb.write((int)red*j,(int)green*j,(int)blue*j);
						Thread.sleep(timeInMs);
					}
				} 
			}
		}
		catch(InterruptedException e) {}
	}

}
