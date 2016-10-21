package frontend.catbox.teamneko;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontendServer
 */
@WebServlet("/FrontendServer")
public class FrontendServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String SearchWord;
    public static boolean DefaultSearch = true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontendServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String NullChecker = request.getParameter("Recherche");
		if (NullChecker != null)
		{
			SearchWord = NullChecker;
			if(SearchWord.isEmpty())
			{
				DefaultSearch = true;
			}
			else
			{
				DefaultSearch = false;
			}
		}
		System.out.println(DefaultSearch);
		System.out.println(SearchWord);
		request.getRequestDispatcher("/ProductsList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ProductsList.jsp").forward(request, response);
	}

}
