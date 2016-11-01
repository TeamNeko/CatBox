package frontend.catbox.teamneko;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BarCodeGenerator
 */
@WebServlet("/BarCodeGenerator")
public class BarCodeGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarCodeGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void saveBarCode(String barCode, String url) throws IOException
    {
		Codebar.generateBarCode(barCode, url);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String barCode = request.getParameter("Code"); 
		setupCodeBar(barCode);
		request.getRequestDispatcher("/GenerateSuccess.jsp").forward(request, response);
	}

	public void setupCodeBar(String barCode) {
		if(barCode == null) 
		{
			barCode = "123456789";
		}
		String url = "C:/Users/User/Desktop/Engineering/APP S3/Projet/Frontend/PrintCode/"+barCode+".png";
		try {
			saveBarCode(barCode, url);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Barcode.jsp").forward(request, response);
	}

}
