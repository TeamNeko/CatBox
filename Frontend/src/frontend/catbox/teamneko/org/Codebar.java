package frontend.catbox.teamneko;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Codebar {

	 public static void generateBarCode(String barCode, String url) throws IOException 
		{
			String barcodeString = barCode;
			Code128Bean barcode128Bean = new Code128Bean();
			final int dpi = 100;
			
			barcode128Bean.setCodeset(Code128Constants.CODESET_B);
			barcode128Bean.setModuleWidth(UnitConv.in2mm(5.0f/dpi)); 
			barcode128Bean.doQuietZone(false);
		
			File outputFile = new File(url);
			OutputStream out = new FileOutputStream(outputFile);
			try 
			{
				BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(
		    		  out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
				barcode128Bean.generateBarcode(canvasProvider,barcodeString);
				canvasProvider.finish();
			} 
			finally 
			{
				out.close();
			}
	  	}
}
