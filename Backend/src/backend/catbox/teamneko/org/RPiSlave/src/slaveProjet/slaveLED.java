package backend.catbox.teamneko.org.RPiSlave.src.slaveProjet;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class slaveLED {
	private boolean awaitingAcknowledge;
	
	private final GpioController gpio;

    // provision gpio pin #01 & #03 & #05 & #07 as an output pins and blink
    GpioPinDigitalOutput leds[] = new GpioPinDigitalOutput[4];
    GpioPinDigitalOutput ledAck;

    final GpioPinDigitalInput myButton;
	
	public slaveLED(){
		gpio = GpioFactory.getInstance();
		
		leds[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		leds[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);
		leds[2] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
		leds[3] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
		ledAck = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
		myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
		
		myButton.addListener(new GpioPinListenerDigital() {
			@Override 
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event){
				if(myButton.isLow())
					clearAcknowledge();
			}
		});
		
		awaitingAcknowledge = false;
	}
	
	public void AffichageLED(int nb)
	{
		boolean on;
		
		if(!awaitingAcknowledge)
		{
			for(int i = 0; i < 4; i++) {
				on = getBit(i,nb);
				System.out.print(on ? "1" : "0");
				leds[i].setState(on);
			}
			
			System.out.println();
			setAcknowledge();
		}
	}
	
	public void setAcknowledge() {
		setWaitingAcknowledge(true);
		ledAck.high();
	}
	
	public void clearAcknowledge() {
		setWaitingAcknowledge(false);
		ledAck.low();
	}
	
	public boolean getBit(int position, int entree)
	{
	   return ((entree >> position) & 1) != 0 ;
	}
	
	public synchronized boolean getAwaitingAcknowledge() {
		return awaitingAcknowledge;
	}
	
	public synchronized void setWaitingAcknowledge(boolean awaitingAcknowledge) {
		this.awaitingAcknowledge = awaitingAcknowledge;
	}
	
	public static void main(String[] args) throws IOException {
		
		slaveLED yellowSlave = new slaveLED();
	    ServerSocket server;
	    
	    try {
			server = new ServerSocket(10808);
		} catch (IOException e) {
			System.out.print("Could not start Socket Server: " + e.getMessage());
			return;
		}
	    
	    try
	    {
		    while (true){
				Socket socket = server.accept();
				int number = socket.getInputStream().read();
				PrintStream sortie = new PrintStream(socket.getOutputStream());
				if(number >= '0' && number <= '9') {
					yellowSlave.AffichageLED(number - '0');
					while(yellowSlave.getAwaitingAcknowledge());
					sortie.println("OK");
				}
				else if(number >= 'a' && number <= 'f') {
					yellowSlave.AffichageLED(number - 'a' + 10);
					while(yellowSlave.getAwaitingAcknowledge());
					sortie.println("OK");
				}
				else {
					sortie.println("Valeur Invalide");
				}
				
		    }
	    } catch (Throwable t) {
	    	System.out.println("Une erreur est survenue: " + t.getMessage());
	    }
	    
		server.close();
	}


}
