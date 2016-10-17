package meowlib.teamneko.org;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server;
	PrintStream sortie;
	
	public Server() throws IOException {
		server = new ServerSocket(Client.port);
	}
	
	public int receive() throws NumberFormatException, IOException {  
		Socket socket = server.accept();
		int number = socket.getInputStream().read();
		sortie = new PrintStream(socket.getOutputStream());
		
		if(number >= '0' && number <= '9') {
			return number - '0';
		}
		else if(number >= 'a' && number <= 'f') {
			return number - 'a' + 10;
		}
		else {
			throw new NumberFormatException("Valeur Invalide");
		}
	}
	
	public void reply(String message) {
		sortie.println(message);
	}
	
	public void close() throws IOException {
		server.close();
	}
}
