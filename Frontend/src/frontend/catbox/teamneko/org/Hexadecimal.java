package frontend.catbox.teamneko.org;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hexadecimal
 */
@WebServlet("/Hexadecimal")
public class Hexadecimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ipAddress = "192.168.0.101";
	private static final int port = 10808;
	
	private static String etat = "Prêt";
	
    /**
     * Default constructor. 
     */
    public Hexadecimal() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        envoyerMessage(request.getParameter("valeur"));
        response.getWriter().write(etat);
	}
	
	private void envoyerMessage(String message) {
		try {
			Socket socket = new Socket(ipAddress, port);
			socket.getOutputStream().write(message.getBytes());
			etat = (new BufferedReader(new InputStreamReader(socket.getInputStream()))).readLine();
			socket.close();
		} catch (UnknownHostException e) {
			etat = "Erreur: Hôte non connu";
		} catch (IOException e) {
			etat = "Erreur de communication";
		}
	}
	
	public static String getEtat() {
		return etat;
	}
}
