package org.teamneko.schrodinger.backend.gpio;

import java.util.Arrays;

import com.pi4j.wiringpi.Gpio;

public class MFRC522 implements RFIDReader {

	/* 	 
	  Register overview, with their address, their name (same as those in the datasheet) and a brief description
	  For further implementation details, see page 36 of the datasheet   
	  https://www.nxp.com/documents/data_sheet/MFRC522.pdf	 
	*/  
	 
	   
	 
	   //Page 0: Command and status
	   //public static final byte Reserved     = 0x00; // reserved for future use 
	   public static final byte CommandReg     = 0x01; // starts and stops command execution
	   public static final byte ComlEnReg     = 0x02; // enable and disable interrupt request control bits	 
	   public static final byte DivlEnReg     = 0x03; // enable and disable interrupt request control bits	 
	   public static final byte ComIrqReg     = 0x04; // interrupt request bits	 
	   public static final byte DivIrqReg     = 0x05; // interrupt request bits	 
	   public static final byte ErrorReg       = 0x06; // error bits showing the error status of the last command executed	 
	   public static final byte Status1Reg     = 0x07; // communication status bits	 
	   public static final byte Status2Reg     = 0x08; // receiver and transmitter status bits	 
	   public static final byte FIFODataReg     = 0x09; // input and output of 64 byte FIFO buffer	 
	   public static final byte FIFOLevelReg     = 0x0A; // number of bytes stored in the FIFO buffer	 
	   public static final byte WaterLevelReg   = 0x0B; // level for FIFO underflow and overflow warning	 
	   public static final byte ControlReg     = 0x0C; // miscellaneous control registers	 
	   public static final byte BitFramingReg   = 0x0D; // adjustments for bit-oriented frames	 
	   public static final byte CollReg       = 0x0E; // bit position of the first bit-collision detected on the RF interface
	   //public static final byte Reserved     = 0x0F; // reserved for future use
	 
	   
	   //Page 1: Command	   
	   //public static final byte Reserved     = 0x10; // reserved for future use	 
	   public static final byte ModeReg       = 0x11; // defines general modes for transmitting and receiving	 
	   public static final byte TxModeReg     = 0x12; // defines transmission data rate and framing	 
	   public static final byte RxModeReg     = 0x13; // defines reception data rate and framing	 
	   public static final byte TxControlReg     = 0x14; // controls the logical behavior of the antenna driver pins TX1 and TX2	 
	   public static final byte TxASKReg       = 0x15; // controls the setting of the transmission modulation	 
	   public static final byte TxSelReg       = 0x16; // selects the internal sources for the antenna driver	 
	   public static final byte RxSelReg       = 0x17; // selects internal receiver settings	 
	   public static final byte RxThresholdReg  = 0x18; // selects thresholds for the bit decoder	 
	   public static final byte DemodReg       = 0x19; // defines demodulator settings	 
	   //public static final byte Reserved     = 0x1A; // reserved for future use	 
	   //public static final byte Reserved     = 0x1B; // reserved for future use	 
	   public static final byte MfTxReg       = 0x1C; // controls some MIFARE communication transmit parameters	 
	   public static final byte MfRxReg       = 0x1D; // controls some MIFARE communication receive parameters	 
	   //public static final byte Reserved     = 0x1E; // reserved for future use	 
	   public static final byte SerialSpeedReg  = 0x1F; // selects the speed of the serial UART interface
	 
	   
	   //Page 2: Configuration
	   //public static final byte Reserved     = 0x20; // reserved for future use	 
	   public static final byte CRCResultRegMSB   = 0x21; // shows the MSB and LSB values of the CRC calculation	 
	   public static final byte CRCResultRegLSB   = 0x22; // shows the MSB and LSB values of the CRC calculation	 
	   //public static final byte Reserved     = 0x23; // reserved for future use	 
	   public static final byte ModWidthReg     = 0x24; // controls the ModWidth setting	 
	   //public static final byte Reserved     = 0x25; // reserved for future use	 
	   public static final byte RFCfgReg       = 0x26; // configures the receiver gain	 
	   public static final byte GsNReg       = 0x27; // selects the conductance of the antenna driver pins TX1 and TX2 for modulation	 
	   public static final byte CWGsPReg       = 0x28; // defines the conductance of the p-driver output during periods of no modulation	 
	   public static final byte ModGsPReg     = 0x29; // defines the conductance of the p-driver output during periods of modulation	 
	   public static final byte TModeReg       = 0x2A; // defines settings for the internal timer	 
	   public static final byte TPrescalerReg   = 0x2B; // defines settings for the internal timer	 
	   public static final byte TReloadRegH     = 0x2C; // defines the 16-bit timer reload value	 
	   public static final byte TReloadRegL     = 0x2D; // defines the 16-bit timer reload value	 
	   public static final byte TCounterValReg1   = 0x2E; // shows the 16-bit timer value	 
	   public static final byte TCounterValReg2   = 0x2F; // shows the 16-bit timer value
	 
	   
	   //Page 3: Test register
	   //public static final byte Reserved     = 0x30; // reserved for future use	 
	   public static final byte TestSel1Reg     = 0x31; // general test signal configuration	 
	   public static final byte TestSel2Reg     = 0x32; // general test signal configuration and PRBS control	 
	   public static final byte TestPinEnReg     = 0x33; // enables pin output driver on pins D1 to D7	 
	   public static final byte TestPinValueReg   = 0x34; // defines the values for D1 to D7 when it is used as an I/O bus	 
	   public static final byte TestBusReg     = 0x35; // shows the status of the internal test bus	 
	   public static final byte AutoTestReg     = 0x36; // controls the digital self test	 
	   public static final byte VersionReg     = 0x37; // shows the software version	 
	   public static final byte AnalogTestReg   = 0x38; // controls the pins AUX1 and AUX2	 
	   public static final byte TestDAC1Reg     = 0x39; // defines the test value for TestDAC1	 
	   public static final byte TestDAC2Reg     = 0x3A; // defines the test value for TestDAC2	 
	   public static final byte TestADCReg     = 0x3B; // shows the value of ADC I and Q channels	 
	   //public static final byte Reserved     = 0x3C; // reserved for production tests	 
	   //public static final byte Reserved     = 0x3D; // reserved for production tests	 
	   //public static final byte Reserved     = 0x3E; // reserved for production tests	 
	   //public static final byte Reserved     = 0x3F; // reserved for production tests
	 
	   
	   //Other Useful registers
  
	   //RC522
	   public static final byte PCD_IDLE         = 0x00;	 
	   public static final byte PCD_AUTHENT      = 0x0E;	 
	   public static final byte PCD_RECEIVE      = 0x08;	 
	   public static final byte PCD_TRANSMIT     = 0x04;	 
	   public static final byte PCD_TRANSCEIVE   = 0x0C;	 
	   public static final byte PCD_RESETPHASE   = 0x0F;	 
	   public static final byte PCD_CALCCRC      = 0x03;

	   //PICC	 
	   public  static final byte PICC_REQIDL      = 0x26;	 
	   public  static final byte PICC_REQALL      = 0x52;	 
	   public  static final byte PICC_ANTICOLL    = (byte)0x93;	 
	   public  static final byte PICC_SElECTTAG   = (byte)0x93;	 
	   public  static final byte PICC_AUTHENT1A   = 0x60;	 
	   public  static final byte PICC_AUTHENT1B   = 0x61;	 
	   public  static final byte PICC_READ        = 0x30;	 
	   public  static final byte PICC_WRITE       = (byte)0xA0;	 
	   public  static final byte PICC_DECREMENT   = (byte)0xC0;	 
	   public  static final byte PICC_INCREMENT   = (byte)0xC1;	 
	   public  static final byte PICC_RESTORE     = (byte)0xC2;	 
	   public  static final byte PICC_TRANSFER    = (byte)0xB0;	 
	   public  static final byte PICC_HALT        = 0x50;
	   
	   public  static final int MI_ERR = 2;
	   public  static final int MI_NOTAGERR = 1;
	   public  static final int MI_OK = 0;
		
	   public  static final int MAX_LEN = 16;
	
	   public  String currentID = "0000000000";
	
	   private SoftSPI spi;
	   private int resetPin = 22;

	/**
	 * Enables the parameters of the MFRC522 object.
	 * In order to use the object you must initialise the SPI 
	 * communication on the RPi.
	 * 
	 * The SPI ports are predefined on the RPi, 
	 * please refer to https://www.raspberrypi.org/ to find the 
	 * proper port for your RPi version and needs
	 * 
	 * @param spiPort
	 * @param resetPin
	 * @param clockSpeed
	 */
	
	
	public MFRC522(SoftSPI spi, int resetPin) {
		this.spi = spi;
		this.resetPin = resetPin;
	}

	
	/**
	 * Simple conversion of a table of bytes into their 
	 * correspondant array of hexadecimal values
	 * 
	 * Since in a byte you can have valeus from 0 to F (15)
	 * It writes the value of a byte on two character 
	 * ex: byte 6 => "06"
	 * 
	 * @param bytes
	 * @return
	 */
	
	public static String bytesToHex(byte[] bytes) {
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	/**
	 * This provides a protection against collision
	 * In his parameter he passes the ID of the card 
	 * to read to the precedent and subsequent function
	 * 
	 * If the function was able to correctly read an ID 
	 * without a collision, it returns true and the 
	 * ID is passed by back_data
	 * 
	 * Otherwise, if it reads the same card for a second 
	 * time or sensor wasn't able to read the card, 
	 * it returns a back_data full of '0' 
	 * 
	 * @param back_data
	 * @return
	 */
	
	
	public int collisionDetection(byte[] back_data) {
		int status;
		byte[] serial_number = new byte[2];
		int serial_number_check = 0;
		int backLen[] = new int[1];
		int back_bits[] = new int[1];
		int i;

		writeRegister(BitFramingReg, (byte) 0x00);
		serial_number[0] = PICC_ANTICOLL;
		serial_number[1] = 0x20;
		status = writeCard(PCD_TRANSCEIVE, serial_number, 2, back_data, back_bits, backLen);
		if (status == MI_OK) {
			if (backLen[0] == 5) {
				for (i = 0; i < 4; i++)
					serial_number_check ^= back_data[i];
				if (serial_number_check != back_data[4]) {
					status = MI_ERR;
					//System.out.println("check error");
				}
			} 
			else {
				status = MI_OK;
				//System.out.println("backLen[0]=" + backLen[0]);
			}
		}
		return status;
	}
	
	
	/**
	 * Initialise the MFRC522 by setting his:
	 * TMode Register
	 * 	Bit 7 	(1) Timers starts at the end of the transmission in all communication modes at all speeds
	 * 	Bit 6-5 (00) Non-gated mode
	 * 	Bit 4 	(0) To avoid the use of IRQ
	 * 	Bit 3-0 (1101) Upper 4 bits of the Prescaler Register
	 * 	
	 * Prescaler
	 * 	ftimer = 13.56MHz / (2*TPreScaler+1)
	 * 	The TPreScaler is on 12 bytes, the lower ones are 0x3E
	 * 	To get a value of 0xD3E (3390)
	 * 	Which means ftimer equals ~2
	 * 
	 * Load Registers (Low and High, because it doesn't fit on one)
	 * 16 bits Defines the 16-bits timer reload value on a start event
	 * 0x1E00 (7680)
	 * 
	 * ASK Register
	 * Bit 7 reserved
	 * Bit 6 (1) Forces a 100% ASK modulation independent of other register setting
	 * Bit 5-0 reserved
	 * 
	 * Mode Register
	 * Bit 7 (0) MSBFirst CRC is not used
	 * Bit 6 reserved
	 * Bit 5 (1) TxWaitRF Transmitter can only be started if an RF field is generated 
	 * Bit 4 reserved
	 * Bit 3 (1) PolMFin MFIN is active HIGH
	 * Bit 2 reserved
	 * Bit 1-0 (01) CRCPreset Preset value for the CRC 6363h 
	 * 
	 * RFCfg Register
	 * 	To enable more power to the sensor 
	 * 	to expand his reading range
	 * 
	 * Bit 7 reserved
	 * Bit 6-4 (111) receiver's signal voltage gain factor of 48db
	 * Bit 3-0 reserved
	 * 
	 */

	public void init() {
		
		Gpio.pinMode(resetPin, Gpio.OUTPUT);
		Gpio.digitalWrite(resetPin, Gpio.HIGH);
		
		reset();
		writeRegister(TModeReg, (byte) 0x8D);
		writeRegister(TPrescalerReg, (byte) 0x3E);
		writeRegister(TReloadRegL, (byte) 30 );
		writeRegister(TReloadRegH, (byte) 0);
		writeRegister(TxASKReg, (byte) 0x40);
		writeRegister(ModeReg, (byte) 0x3D);
		writeRegister(RFCfgReg, (byte) 0x7F);
		turnAntennaOn();
	}

	
	
	/**
	 * Enables reading with fonctional parameters 
	 * 
	 * 500ms between readings
	 * 3 successful hits needed to write the card ID 
	 * 	in the currentID member parameter of the function
	 * 
	 */
	public boolean read() {
		try {
			return readID(500, 3);
		} 
		catch(InterruptedException e) {
			return false;
		}
	}

	
	/**
	 * Getter of the currentID read by the sensor
	 */
	
	public String getID() {
		return currentID;
	}
	
	
	
	/**
	 * Returns a boolean to indicate if the reading 
	 * was successful or not
	 * 
	 * 
	 * @param sleepTime
	 * 	Used to make a pause between the reads
	 * 
	 * @param nbHit
	 * 	Number of hits needed to write the ID read in 
	 * 	the member variable currentID to ensure that 
	 * 	there is no misread, you can increase this number, 
	 * 	but it diminish performance
	 * 
	 * @return 
	 * 
	 * 
	 * @throws InterruptedException
	 */
	public boolean readID(int sleepTime, int nbHit) throws InterruptedException {

		// Security if user enters an unallowed number of Hit
		if (nbHit < 1) {
			nbHit = 3;
		}

		// Security if user enters an unallowed sleep time
		if (sleepTime < 100) {
			sleepTime = 500;
		}

		int back_len[] = new int[1];
		byte tagid[] = new byte[5];
		byte tagidVerif[] = new byte[5];
		byte tagidNull[] = { 0, 0, 0, 0, 0 };
		int hitCount = 0;
		int notHitCount = 0;

		// Read till you read "nbHit" times the same UID, then return his value
		while (true) {

			// Verify if there's a card to read
			if (this.request(PICC_REQIDL, back_len) == MI_OK) {
				//System.out.println("Detected card " + back_len[0]);
			}
	        
	        
			
			// Verify if there's a collision with the signal to read
			if (this.collisionDetection(tagid) != MI_OK) {
				notHitCount++;
				if (notHitCount > nbHit) {
					// If it's been a bad read more than nbHit times in a row,
					// reset the count and the UID stored
					//System.out.println("Error, card is not detected ");
					currentID = bytesToHex(tagidNull);
					return false;
				}
				continue;
			} 
	
			 
				// Initialization of the verification ID tag (tagidVerif)
				if (Arrays.equals(tagidVerif, tagidNull)){
					tagidVerif = tagid;
					hitCount = 0;
					notHitCount = 0;
				}
	
				// The right id is read
				if (Arrays.equals(tagidVerif, tagid)) {
					hitCount++;
					notHitCount = 0;
				}
				else{
					hitCount = 0;
				}
	
				if (hitCount == nbHit) {
					currentID = bytesToHex(tagid);
					return true;
				}
	
				// Insert a timer to read the RFID card at a reasonable rate
				Thread.sleep(sleepTime);
			
		}
	}
	
	/**
	 * Passes the card type as the req_mode, 
	 * Retrieves the number of valid bytes in the back_bits
	 * 
	 * @param req_mode
	 * @param back_bits
	 * @return
	 */

	public int request(byte req_mode, int[] back_bits) {
		int status;
		byte tagType[] = new byte[1];
		byte data_back[] = new byte[16];
		int backLen[] = new int[1];

		writeRegister(BitFramingReg, (byte) 0x07);

		tagType[0] = req_mode;
		back_bits[0] = 0;
		status = writeCard(PCD_TRANSCEIVE, tagType, 1, data_back, back_bits, backLen);
		if (status != MI_OK || back_bits[0] != 0x10) {
			//System.out.println("status=" + status + ",back_bits[0]=" + back_bits[0]);
			status = MI_ERR;
		}

		return status;
	}

	
	/**
	 * Simple byte masking used for masking addresses
	 * 
	 * @param address
	 * @param mask
	 */
	
	
	private void clearBitMask(byte address, byte mask) {
		byte value = readRegister(address);
		writeRegister(address, (byte) (value & (~mask)));
	}
	
	/**
	 * Reset the sensor before intialising everything needed
	 * Bit 4 (0) Wakes up the sensor
	 * Bit 3-0 (1111) SoftReset, it resets the MFRC522
	 */
	
	private void reset() {
		writeRegister(CommandReg, PCD_RESETPHASE);
	}

	/**
	 * Mask the address passed in parameter
	 * 
	 * @param address
	 * @param mask
	 */
	
	private void setBitMask(byte address, byte mask) {
		byte value = readRegister(address);
		writeRegister(address, (byte) (value | mask));
	}

	/**
	 * Bit 1-0 (11) Enables the energy for the antenna
	 * 
	 */
	private void turnAntennaOn() {
		setBitMask(TxControlReg, (byte) 0x03);
	}

	/**
	 * It writes a command to the sensor by passing the command, the type of the card as data and it's lenght
	 * Retrieve the back_data, with his lenght (backLen).
	 * The back_bits reads the ControlReg to indicate the number of valid byte 
	 * if bit 2-0 equals 000b, the whole back_data is valid  
	 * 
	 * 
	 * @param command
	 * @param data
	 * @param dataLen
	 * @param back_data
	 * @param back_bits
	 * @param backLen
	 * @return
	 */
	
	private int writeCard(byte command, byte[] data, int dataLen, byte[] back_data, int[] back_bits, int[] backLen) {
		int status = MI_ERR;
		byte irq = 0, irq_wait = 0, lastBits = 0;
		int n = 0, i = 0;

		backLen[0] = 0;
		if (command == PCD_AUTHENT) {
			irq = 0x12;
			irq_wait = 0x10;
		} else if (command == PCD_TRANSCEIVE) {
			irq = 0x77;
			irq_wait = 0x30;
		}

		writeRegister(ComlEnReg, (byte) (irq | 0x80));
		clearBitMask(ComIrqReg, (byte) 0x80);
		setBitMask(FIFOLevelReg, (byte) 0x80);

		writeRegister(CommandReg, PCD_IDLE);

		for (i = 0; i < dataLen; i++)
			writeRegister(FIFODataReg, data[i]);

		writeRegister(CommandReg, command);
		if (command == PCD_TRANSCEIVE)
			setBitMask(BitFramingReg, (byte) 0x80);

		i = 2000;
		while (true) {
			n = readRegister(ComIrqReg);
			i--;
			if ((i == 0) || (n & 0x01) > 0 || (n & irq_wait) > 0) {
				//System.out.println("Write_Card i="+i+",n="+n);
				break;
			}
		}
		clearBitMask(BitFramingReg, (byte) 0x80);

		if (i != 0) {
			if ((readRegister(ErrorReg) & 0x1B) == 0x00) {
				status = MI_OK;
				if ((n & irq & 0x01) > 0)
					status = MI_NOTAGERR;
				if (command == PCD_TRANSCEIVE) {
					n = readRegister(FIFOLevelReg);
					lastBits = (byte) (readRegister(ControlReg) & 0x07);
					if (lastBits != 0)
						back_bits[0] = (n - 1) * 8 + lastBits;
					
					else{
						back_bits[0] = n * 8;
						
					}

					if (n == 0)
						n = 1;
					if (n > MAX_LEN)
						n = MAX_LEN;
					backLen[0] = n;
					for (i = 0; i < n; i++)
						back_data[i] = readRegister(FIFODataReg);
				}
			} else
				status = MI_ERR;
		}
		
		return status;
	}
	
	/**
	 * Read a register from the MFRC522
	 * @param address Register address to read
	 * @return Value in the register
	 */
	public byte readRegister(byte address) {
		byte[] data = new byte[2];
		data[0] = (byte)((address << 1) | 0x80);
		
		data = spi.readWrite(data);
		return data[1];
	}
	
	/**
	 * Write to a register on the SPI device
	 * @param address Register address to write
	 * @param value Value to write to the register
	 */
	public void writeRegister(byte address, byte value) {
		byte[] data = new byte[2];
		data[0] = (byte)((address << 1) & 0x7F);
		data[1] = value;
		spi.readWrite(data);
	}
	
}
