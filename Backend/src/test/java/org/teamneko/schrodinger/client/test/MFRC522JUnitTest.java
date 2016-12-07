package org.teamneko.schrodinger.client.test;

import static org.junit.Assert.*;

import org.teamneko.schrodinger.backend.gpio.MFRC522;

import org.junit.Test;

public class MFRC522JUnitTest {
	
	byte[] bytesToHexTest = {1,2,3,4,5}; // "12345"
	
	@Test
	public void bytesToHexTest(){
		
		String expectedValue = "0102030405";
		String obtainedValue = MFRC522.bytesToHex(bytesToHexTest);

		assertEquals(expectedValue,obtainedValue);	
	}
}