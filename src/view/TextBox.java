package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CodeInterpreter;

/**
 * TextBox component of the UI; allows the user to enter and run code; imported code
 * file will also be displayed here
 *
 */
public class TextBox extends Element implements SizingElement {
	
	public static final String TBPROMPT = "TextBoxPrompt";
	public static final String RUN = "Run";
	public static final String CLEAR = "Clear";
	public static final String SAVE = "Save";
	public static final String UNDO = "Undo";
	public static final String ERROR = "Error: ";
	public static final String CODEALERT = "Code error found!";
	public static final String FILEALERT = "File can't be saved!";
	public static final String TXTTEXT = "TXT files (*.txt)";
	public static final String LOGOTEXT = "Logo files (*.logo)";
	public static final String TXTEXT = "*.txt";
	public static final String LOGOEXT = "*.logo";
	
	public static final double TARESIZE = 9.0 / 10.0;
	public static final double BUTTONRESIZE = 6;
	public static final int BUFFER = 2;
	public static final double BUTTONSCALING = 2;
	
	private final TextArea ta = new TextArea();
	private final Button run = new Button();
	private final Button clear = new Button();
	private final Button save = new Button();
	private final Button undo = new Button();
	private int gridSpan;
	private String last;
	
	/**
	 * Constructor for the TextBox
	 * @param ci
	 * @param console
	 */
	public TextBox(Stage stage, CodeInterpreter ci, Console console) {
		super(new GridPane());
		gridSpan = 0;
		last = "";
		setUpTA(ci, console);
		setUpRun(ci, console);
		setUpClear();
		setUpSave(stage);
		setUpUndo();
	}

	private void setUpTA(CodeInterpreter ci, Console console) {
		ta.setLayoutX(BUFFER);
		ta.setLayoutY(BUFFER);
		ta.setOnKeyPressed(action -> handleKeyInput(action, ci, console));
		
		((GridPane) getPane()).add(ta, gridSpan, 0, 1, (int) BUTTONSCALING);
		gridSpan++;
	}

	private void setUpRun(CodeInterpreter ci, Console console) {
		run.setOnAction(action -> run(ci, console));
		
		((GridPane) getPane()).add(run, gridSpan, 0);
	}
	
	private void setUpClear() {
		clear.setOnAction(action -> ta.clear());
		
		((GridPane) getPane()).add(clear, gridSpan, 1);
		gridSpan++;
	}
	
	
	private void setUpSave(Stage stage) {
		save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(TXTTEXT, TXTEXT);
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter(LOGOTEXT, LOGOEXT);
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.getExtensionFilters().add(extFilter2);
            File file = fileChooser.showSaveDialog(stage);
            if(file != null){
                saveFile(ta.getText(), file);
            }
        });
		
		((GridPane) getPane()).add(save, gridSpan, 0);
	}

	private void setUpUndo() {
		undo.setOnAction(action -> {
			undo();
		});
		
		((GridPane) getPane()).add(undo, gridSpan, 1);
		gridSpan++;
	}
	
	private void run(CodeInterpreter ci, Console console) {
		String text = ta.getText();
		last = text;
		if(text.length() > 0) {
			try {
				ci.execute(text.split(Console.NEWLINE));
				console.displayText(text);
				ta.clear();
			}
			catch(Exception e) {
				new MakeAlert(CODEALERT, e.getMessage());
			}
		}
	}
	
	private void undo() {
		ta.setText(last);
	}
	
	private void saveFile(String content, File file){
		try {
        	FileWriter fileWriter;
        	fileWriter = new FileWriter(file);
        	fileWriter.write(content);
        	fileWriter.close();
        } catch (IOException ex) {
        	new MakeAlert(FILEALERT, ex.getMessage());
        }
	}
	
	private void handleKeyInput(KeyEvent event, CodeInterpreter ci, Console console) {
		KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
		KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
		KeyCombination keyComb3 = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
		
		if(keyComb1.match(event)) {
    		run(ci, console);
    	} else if(keyComb2.match(event)) {
			ta.clear();
		} else if(keyComb3.match(event)) {
			console.clear();
		}
	}
	
	/**
	 * imports text into the text box; called in MenuBar class
	 * @param s
	 */
	protected void displayText(String s) {
		ta.appendText(s);
	}
	
	/**
	 * sets the text of the buttons and prompt text; called in MenuBar class
	 * @param myR
	 */
	protected void setLang(ResourceBundle myR) {
		ta.setPromptText(myR.getString(TBPROMPT));
		run.setText(myR.getString(RUN));
		clear.setText(myR.getString(CLEAR));;
		save.setText(myR.getString(SAVE));
		undo.setText(myR.getString(UNDO));
	}
	
	/**
	 * sets the width of the TextBox
	 * @param w
	 */
	public void setWidth(double w) {
		setPaneWidth(w);
		ta.setPrefWidth(w * TARESIZE - BUFFER * 2);
		run.setPrefWidth(w / BUTTONRESIZE - BUFFER);
		clear.setPrefWidth(w / BUTTONRESIZE - BUFFER);
		save.setPrefWidth(w / BUTTONRESIZE - BUFFER);
		undo.setPrefWidth(w / BUTTONRESIZE - BUFFER);
	}

	/**
	 * sets the length of the TextBox
	 * @param h
	 */
	public void setHeight(double h) {
		setPaneHeight(h);
		ta.setPrefHeight(h);
		run.setPrefHeight(h / BUTTONSCALING);
		clear.setPrefHeight(h / BUTTONSCALING);
		save.setPrefHeight(h / BUTTONSCALING);
		undo.setPrefHeight(h / BUTTONSCALING);
	}

	/**
	 * sets the x position of the TextBox
	 */
	@Override
	public void setElementX(double x) {
		this.setX(x);
	}

	/**
	 * sets the y position of the TextBox
	 */
	@Override
	public void setElementY(double y) {
		this.setY(y);
	}
	
}
