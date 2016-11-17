package org.teamneko.schrodinger.backend.fx;

import org.teamneko.meowlib.json.User;
import org.teamneko.schrodinger.client.SchrodingerClient;

import com.sun.jersey.api.client.UniformInterfaceException;

public class Context {
	private static Context instance = new Context();
	private KeyboardHandler keyboardHandler;
	private ApplicationState state;
	private SchrodingerClient restClient;
	private User user;
	private MainWindow mainWindow;
	
	public KeyboardHandler getKeyboardHandler() {
		return keyboardHandler;
	}

	public ApplicationState getState() {
		return state;
	}

	public SchrodingerClient getRestClient() {
		return restClient;
	}

	public User getUser() {
		return user;
	}

	public static void setInstance(Context instance) {
		Context.instance = instance;
	}

	public void setKeyboardHandler(KeyboardHandler keyboardHandler) {
		this.keyboardHandler = keyboardHandler;
	}

	public void setState(ApplicationState state) {
		this.state = state;
	}

	public void setRestClient(SchrodingerClient restClient) {
		this.restClient = restClient;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static Context getInstance() {
		return instance;
	}
	
	private Context() {
		mainWindow = new MainWindow();
		restClient = new SchrodingerClient("http://localhost:8080/Frontend/rest");
	}	
	
	public boolean login(String userCode) {
		try {
	 		user = restClient.requestUser(userCode);
	 		mainWindow.showTablePane();
	 	} catch(UniformInterfaceException e) {
	 		return false;
	 	}
		
		return true;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
}
