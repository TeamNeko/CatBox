package org.teamneko.schrodinger.backend.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.spi.SpiMode;

/**
 * Software Implementation of an SPI port
 * @author Tommy « LeChat » Savaria
 */
public class SoftSPI {
	public final GpioPinDigitalOutput chipSelect;
	public final GpioPinDigitalOutput clock;
	public final GpioPinDigitalOutput masterOut;
	public final GpioPinDigitalInput masterIn;
	
	public final long speed;
	
	public final boolean clockPolarity;
	public final boolean clockPhase;

	/**
	 * Build a new SoftSPI object by specifying the pins to use (wiringPi scheme)
	 * @param cs Chip Select Pin
	 * @param miso Master In Slave Out Pin
	 * @param mosi Master Out Slave In Pin
	 * @param clk Clock Pin
	 * @param speed Speed of the SPI port in bits per second (b/s)
	 */
	public SoftSPI(Pin cs, Pin miso, Pin mosi, Pin clk, long speed, SpiMode mode) {
		GpioController controller = GpioFactory.getInstance();
		
		this.chipSelect = controller.provisionDigitalOutputPin(cs);
		this.clock = controller.provisionDigitalOutputPin(clk);
		this.masterOut = controller.provisionDigitalOutputPin(mosi);
		this.masterIn = controller.provisionDigitalInputPin(miso);
		
		this.speed = speed;
		
		this.chipSelect.high();
		
		switch(mode) {
		default:
		case MODE_0:
			clockPolarity = false;
			clockPhase = false;
			break;
		case MODE_1:
			clockPolarity = false;
			clockPhase = true;
			break;
		case MODE_2:
			clockPolarity = true;
			clockPhase = false;
			break;
		case MODE_3:
			clockPolarity = true;
			clockPhase = true;
			break;
		}
		
		this.clock.setState(this.clockPolarity);
	}

    /**
     * Initiate a read/write communication on the SPI port
     * @param spi SPI Pins Definition
     * @param data Data to write on the SPI port
     * @return Data read from the SPI port
     */
	public byte[] readWrite(byte[] data){
		byte[] result = new byte[data.length];
		long nanos = 500000000 / speed, start;
		
		chipSelect.low();
		
		for(int i = 0; i < data.length; i++) {
			byte in = 0;
			byte out = data[i];
			
			for(int j = 0; j < 8; j++) {
				if(!clockPhase)
					masterOut.setState(out < 0);				
				
				//Assert clock
				clock.setState(!clockPolarity);
				
				//Wait for required duration
				start = System.nanoTime();
				while(System.nanoTime() - start < nanos);

				if(clockPhase)
					masterOut.setState(out < 0);
				else
					in = (byte) ((in << 1) | (masterIn.getState().isHigh() ? 1 : 0));
				
				//Deassert clock
				clock.setState(clockPolarity);
				
				//Wait for required duration
				start = System.nanoTime();
				while(System.nanoTime() - start < nanos);
				
				if(clockPhase)
					in = (byte) ((in << 1) | (masterIn.getState().isHigh() ? 1 : 0));
				
				out = (byte) (out << 1);
			}
			
			result[i] = in;
		}
		
		clock.setState(clockPolarity);
		chipSelect.high();
		
		return result;
	}
}
