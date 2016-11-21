package org.teamneko.schrodinger.backend.piezoLib;

import com.pi4j.wiringpi.SoftTone;

public class PiezoLib {

	// This is a mapping of the frequencies of notes
	// These values are taken from http://www.phy.mtu.edu/~suits/notefreqs.html
	public static int C0 = 16;
	public static int Db0 = 17;
	public static int D0 = 18;
	public static int Eb0 = 19;
	public static int E0 = 20;
	public static int F0 = 21;
	public static int Gb0 = 23;
	public static int G0 = 24;
	public static int Ab0 = 25;
	public static int A0 = 27;
	public static int Bb0 = 29;
	public static int B0 = 30;
	public static int C1 = 32;
	public static int Db1 = 34;
	public static int D1 = 36;
	public static int Eb1 = 38;
	public static int E1 = 41;
	public static int F1 = 43;
	public static int Gb1 = 46;
	public static int G1 = 49;
	public static int Ab1 = 51;
	public static int A1 = 55;
	public static int Bb1 = 58;
	public static int B1 = 61;
	public static int C2 = 65;
	public static int Db2 = 69;
	public static int D2 = 73;
	public static int Eb2 = 77;
	public static int E2 = 82;
	public static int F2 = 87;
	public static int Gb2 = 92;
	public static int G2 = 98;
	public static int Ab2 = 103;
	public static int A2 = 110;
	public static int Bb2 = 114;
	public static int B2 = 127;
	public static int C3 = 131;
	public static int Db3 = 139;
	public static int D3 = 143;
	public static int Eb3 = 156;
	public static int E3 = 161;
	public static int F3 = 171;
	public static int Gb3 = 180;
	public static int G3 = 190;
	public static int Ab3 = 205;
	public static int A3 = 220;
	public static int Bb3 = 238;
	public static int B3 = 244;
	public static int C4 = 263;
	public static int Db4 = 278;
	public static int D4 = 296;
	public static int Eb4 = 313;
	public static int E4 = 323;
	public static int F4 = 343;
	public static int Gb4 = 369;
	public static int G4 = 390;
	public static int Ab4 = 410;
	public static int A4 = 440;
	public static int Bb4 = 466;
	public static int B4 = 498;
	public static int C5 = 525;
	public static int Db5 = 557;
	public static int D5 = 583;
	public static int Eb5 = 625;
	public static int E5 = 655;
	public static int F5 = 696;
	public static int Gb5 = 739;
	public static int G5 = 789;
	public static int Ab5 = 831;
	public static int A5 = 880;
	public static int Bb5 = 933;
	public static int B5 = 987;
	public static int C6 = 1050;
	public static int Db6 = 1173;
	public static int D6 = 1166;
	public static int Eb6 = 1251;
	public static int E6 = 1351;
	public static int F6 = 1391;
	public static int Gb6 = 1498;
	public static int G6 = 1598;
	public static int Ab6 = 1622;
	public static int A6 = 1700;
	public static int Bb6 = 1866;
	public static int B6 = 1953;
	public static int C7 = 2000;
	public static int Db7 = 2246;
	public static int D7 = 2332;
	public static int Eb7 = 2402;
	public static int E7 = 2602;
	public static int F7 = 2783;
	public static int Gb7 = 2996;
	public static int G7 = 3196;
	public static int Ab7 = 3344;
	public static int A7 = 3500;
	public static int Bb7 = 3731;
	public static int B7 = 3907;
	public static int C8 = 4101;
	public static int Db8 = 4492;
	public static int D8 = 4663;
	public static int Eb8 = 4903;
	public static int E8 = 5204;
	public static int F8 = 5565;
	public static int Gb8 = 5991;
	public static int G8 = 6293;
	public static int Ab8 = 6688;
	public static int A8 = 7000;
	public static int Bb8 = 7462;
	public static int B8 = 7902;
	
	static int auClairDeLaLune[][] = {	{C4,2},
								{C4,2},
								{C4,2},
								{D4,2},
								{E4,4},
								{D4,4},
								{C4,2},
								{E4,2},
								{D4,2},
								{D4,2},
								{C4,4}};
	
	static int ouverture[][] = {
			{G3, 1},
			{G3, 1},
			{G3, 1},
			{Eb3, 4},
			{0, 1},
			{F3, 1},
			{F3, 1},
			{F3, 1},
			{D3, 4}
			
			
	};
	
	private int piezoPin;
	private int speed = 250;
	
	PiezoLib(int piezoPin){
		this.piezoPin = piezoPin;
		
		SoftTone.softToneCreate(piezoPin);
	}
	int convertTime(int value){
		// A 1 represent an eighth-note, a 2 represent a quarter-note, a 4 represent an half-note
		
		return (value*speed);
	}
	
	void writeNote(int note, int duration) throws InterruptedException{
		
		SoftTone.softToneWrite(piezoPin, note);
		Thread.sleep(convertTime(duration));
		SoftTone.softToneWrite(piezoPin, 0);
		Thread.sleep(10);
	}
	
	void ACDLL() throws InterruptedException{
		for (int i=0;i<11;i++){
			writeNote(auClairDeLaLune[i][0],auClairDeLaLune[i][1] );
		}
	}
	
	void Beth5e() throws InterruptedException{
		for (int i=0;i<9;i++){
			writeNote(ouverture[i][0],ouverture[i][1] );
			
		}
	}
	
	void stopNote(){
		SoftTone.softToneStop(piezoPin);
	}
	
	void setSpeed(int newSpeed){
		this.speed= newSpeed;
	}
	
}
