package frontend.catbox.teamneko.org;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontendBoxDetail
 */
@WebServlet("/FrontendBoxDetail")
public class FrontendBoxDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static public String Item;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontendBoxDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String NullChecker = request.getParameter("Item");
		if (NullChecker != null)
		{
			Item = NullChecker;
		}
		request.getRequestDispatcher("/ProductDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ProductDetail.jsp").forward(request, response);
	}

}
