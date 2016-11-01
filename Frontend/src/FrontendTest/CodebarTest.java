package FrontendTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import frontend.catbox.teamneko.Codebar;
import frontend.catbox.teamneko.BarCodeGenerator;

public class CodebarTest {

	@Test
	public void createCodeBar() 
	{
		try {
			Codebar.generateBarCode("TEST", "C:/Users/User/Desktop/Engineering/APP S3/Projet/Frontend/PrintCode/TEST.png");
		} catch (IOException e) { 
			e.printStackTrace();
		}
		File file = new File("C:/Users/User/Desktop/Engineering/APP S3/Projet/Frontend/PrintCode/TEST.png");
		assertTrue(file.exists());
	}
	
	@Test
	public void codeBarSetup()
	{
		new BarCodeGenerator().setupCodeBar("TEST1");
		File file = new File("C:/Users/User/Desktop/Engineering/APP S3/Projet/Frontend/PrintCode/TEST1.png");
		assertTrue(file.exists());
	}

}
