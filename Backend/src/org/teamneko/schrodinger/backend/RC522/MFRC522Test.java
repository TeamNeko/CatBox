package org.teamneko.schrodinger.backend.RC522;

import static org.junit.Assert.*;

import org.junit.Test;

public class MFRC522Test {

	@Test
	public void testMFRC522() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testMFRC522Int() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testMFRC522IntInt() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testMFRC522_Init() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testRequest() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testAntiColl() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testBytesToHex() {
		byte[] test = {00,00,00,00,00};
		assertEquals("0000000000",MFRC522.bytesToHex(test));
	}

	@Test
	public void testReadIDIntInt() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testReadID() {
	//	fail("Not yet implemented");
	}

}
