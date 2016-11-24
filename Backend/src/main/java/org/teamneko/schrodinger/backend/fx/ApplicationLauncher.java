package org.teamneko.schrodinger.backend.fx;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ApplicationLauncher extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Context context = Context.getInstance();
		
		Scene scene = new Scene(context.getMainWindow(),480,320);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, context.getKeyboardHandler());
		
		context.getMainWindow().showLoginPane();
		context.getMainWindow().showShutdownPane();
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
