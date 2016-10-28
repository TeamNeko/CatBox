package org.teamneko.meowlib;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static final String ipAddress = "192.168.0.101";
	public static final int port = 10808;
	
	public static String send(String message) {
		String etat;
		
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
		
		return etat;
	}
}
