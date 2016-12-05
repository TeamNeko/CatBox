package org.teamneko.schrodinger.backend.fx;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


// 
/**
 * The Class ApplicationLauncher. Used for initiating the GUI
 */
public class ApplicationLauncher extends Application {
	
	/* Initiate the graphic interface of the raspberry pi
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		Context context = Context.getInstance();
		
		Scene scene = new Scene(context.getMainWindow(),480,320);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, context.getKeyboardHandler());
		
		context.getMainWindow().showLoginPane();
		context.getMainWindow().showShutdownPane();
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		
		primaryStage.show();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
