package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class for SLogo. Starts application.
 */
public class Main extends Application {

	public static final String TITLE = "SLogo";
	
	public static final int INIT_X_SIZE = 900;
	public static final int INIT_Y_SIZE = 600;
	public static final String IMAGE = "turtle.png";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	

		primaryStage.setTitle(TITLE);
		
		primaryStage.getIcons().add(new Image(IMAGE));
		
		Gui gui = new Gui();
		
        Scene scene = gui.setScene(primaryStage, INIT_X_SIZE, INIT_Y_SIZE);
        
        primaryStage.setScene(scene);

        primaryStage.show();
	
	}

}
