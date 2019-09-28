package view;

//import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * Help page launched by the menu bar. Reads from a reference help file and displays that text, very passive class otherwise.
 */
public class Help {

	public static final String TITLE = "Help Page";
	public static final int HELP_WIDTH = 800;
	public static final int HELP_HEIGHT = 600;
	
	public static final String HELP_URL = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
	public static final String HELP_URL2 = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands2_J2W.php";
	
	
	public Help() {
		launch(HELP_URL);
		launch(HELP_URL2);
	}
	
	/**
	 * Launches new page with help file contents displayed.
	 * @param helpUrl 
	 * @throws FileNotFoundException if help file cannot be found.
	 */
	private void launch(String helpUrl) { // throws FileNotFoundException
        
        //File hf = new File("data/help/help.html");
        //if(!hf.exists()) {
        //	throw new FileNotFoundException();
        //}
        WebView webView = new WebView();
        webView.getEngine().load(helpUrl);	//hf.toURI().toString())
        
        

		Group root = new Group();
		Stage stage = new Stage();
        stage.setTitle(TITLE);
        
        Scene myScene = new Scene(root, HELP_WIDTH, HELP_HEIGHT);
        
        myScene.widthProperty().addListener(e -> webView.setPrefWidth(myScene.getWidth()));
        myScene.heightProperty().addListener(e -> webView.setPrefHeight(myScene.getHeight()));
        
        stage.setScene(myScene);
        stage.show();
        root.getChildren().add(webView);

	}
	
}
