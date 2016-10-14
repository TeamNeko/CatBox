package soprema.intranet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Backend
 */
@WebServlet("/Backend")
public class Backend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Products ProductsInventory;
    /**
     * Default constructor. 
     */
    public Backend() {
        // TODO Remove when SQL List implemented
    	initializePlaceHolderProductList();
    }

	private void initializePlaceHolderProductList() {
		ArrayList<Product> ItemList = new ArrayList<Product>();
    	for (int i = 0; i < 5; i++)
    	{
    		Product AdditionnalProduct = new Product();
    		AdditionnalProduct.setID(i);
    		AdditionnalProduct.setProductName("Product " + i);
    		ItemList.add(AdditionnalProduct);
    	}
    	ProductsInventory = new Products(ItemList);
	} 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ProductsList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ItemRecherche = new String();
		request.setAttribute("Recherche", ItemRecherche);
		request.getRequestDispatcher("/ProductsList.jsp").forward(request, response);
	}
}
